import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import pal.alignment.Alignment;
import pal.alignment.AlignmentReaders;
import pal.datatype.AminoAcids;

public class main {
	public static final int exitOk = 0; // Execution has completed successfully
	public static final int exitError = -1; // Execution has failed, review the log for more details
	
	/**
	 * Main wrapper for all functionality related to tree generator
	 * The following configuration options need to be passed in:<br>
	 * 		loadFile - Full path to FASTA file to load<br>
	 * 		algorithms - Comma separated list of algorithms to use.<br>
	 * 			Currently available algorithms:<br>
	 * 				CLAlgorithm - Complete Linkage<br>
	 * 				SLAlgorithm - Single Linkage<br>
	 * 				UPGMAAlgorithm - UPGMA<br>
	 * 				WPGMAAlgorithm - WPGMA<br>
	 * 		logFile - Full path of the file to print all log messages to<br>
	 * 		saveDirectory - Full path of directory to save output to.  Directory does not need to exist<br>
	 * 		statusFile - Full path of the file to write all status information to<br>
	 * They should be passed with one key/value pair per array item, in the format "key=value".
	 * For example: "loadFile=/tmp/test.fasta"
	 * @param args Configuration options to use
	 */
	public static void main(String[] args) {
		// Load the configuration from the passed in array.  Verify that all elements we need are present
		ConfigMgr cfg = new ConfigMgr(args);
		if (cfg.getConfig("loadFile") == null || cfg.getConfig("saveDirectory") == null
			|| cfg.getConfig("algorithms") == null || cfg.getConfig("statusFile") == null
			|| cfg.getConfig("logFile") == null)
			{
				System.out.println("Expected loadFile,saveDirectory,algorithms,statusFile,logFile to be set on command line!");
				System.exit(exitError);
			}
		
		// The logging subsystem needs to be initialized first, so any other classes can record any errors that might happen
		Log.Init(cfg.getConfig("logFile"));
		Log.info("Logger loaded");
			
		Log.info("CONFIG: loading file "+cfg.getConfig("loadFile"));
		Log.info("CONFIG: saving results to "+cfg.getConfig("saveDirectory"));
		Log.info("CONFIG: using algorithms "+cfg.getConfig("algorithms"));
		Log.info("CONFIG: status file "+cfg.getConfig("statusFile"));
		
		// Sanity checking: The saveDirectory should not be a file, and needs to be created if it doesn't exist
		File saveDir = new File(cfg.getConfig("saveDirectory"));
		if (saveDir.exists() && saveDir.isFile())
		{
			Log.error("Save directory exists, but is not directory.  aborting");
			System.exit(exitError);
		}
		else if (!saveDir.exists())
		{
			Log.info("Save directory didn't exist, creating");
			saveDir.mkdir();
		}
		
		// Next step is to load all the specified algorithms.  Execution will stop if any are unavailable
		Log.info("Loading algorithms...");
		AlgoMgr am = new AlgoMgr();
		for (String s: cfg.getConfig("algorithms").split(","))
		{
			if (!am.Load(s))
			{
				Log.error("Error loading algorithm "+s+". aborting");
				System.exit(exitError);
			}
		}
		
		// Now load the data from the FASTA file.  We rely on the PAL library to do this.
		Log.info("Loading FASTA file");
		Alignment curData = null;
		try {
			curData = AlignmentReaders.readFastaSequences(new FileReader(cfg.getConfig("loadFile"))
				, new AminoAcids());
		} catch (FileNotFoundException e) {
			Log.error("FASTA file not found!");
			System.exit(exitError);
		} catch (IOException e) {
			Log.error("IO error while attempting to read FASTA file");
			System.exit(exitError);
		}

		// By this point, everything we need has been loaded.  Only thing left to do is attempt
		// tree generation for every loaded algorithm
		Log.info("Beginning tree generation");
		for (Algorithm a: am)
		{
			TreeGenerator tg = new TreeGenerator();
			tg.setAlgo(a);
			tg.setAlignment(curData);
			tg.setStatus(cfg.getConfig("statusFile"));
			tg.generateTree();
			tg.saveToDisk(cfg.getConfig("saveDirectory"));
		}
		Log.info("Execution has finished sucessfully");
		System.exit(exitOk);
	}

}
