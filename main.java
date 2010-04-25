import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import pal.alignment.Alignment;
import pal.alignment.AlignmentReaders;
import pal.datatype.AminoAcids;

public class main {
	public static final int exitOk = 0;
	public static final int exitError = -1;
	
	public static void main(String[] args) {
		ConfigMgr cfg = new ConfigMgr(args);
		
		if (cfg.getConfig("loadFile") == null || cfg.getConfig("saveDirectory") == null
			|| cfg.getConfig("algorithms") == null || cfg.getConfig("statusFile") == null
			|| cfg.getConfig("logFile") == null)
			{
				System.out.println("Expected loadFile,saveDirectory,algorithms,statusFile,logFile to be set on command line!");
				System.exit(exitError);
			}
		Log.Init(cfg.getConfig("logFile"));
		Log.info("Logger loaded");
			
		Log.info("CONFIG: loading file "+cfg.getConfig("loadFile"));
		Log.info("CONFIG: saving results to "+cfg.getConfig("saveDirectory"));
		Log.info("CONFIG: using algorithms "+cfg.getConfig("algorithms"));
		Log.info("CONFIG: status file "+cfg.getConfig("statusFile"));
		
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
		
		AlgoMgr am = new AlgoMgr();
		for (String s: cfg.getConfig("algorithms").split(","))
		{
			am.Load(s);
		}
		
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
		Log.info("FASTA file loaded");
		
		for (Algorithm a: am)
		{
			TreeGenerator tg = new TreeGenerator();
			tg.setAlgo(a);
			tg.setAlignment(curData);
			tg.setStatus(cfg.getConfig("statusFile"));
			tg.generateTree();
			tg.saveToDisk(cfg.getConfig("saveDirectory"));
		}
		Log.info("Execution has finished");
		System.exit(exitOk);
	}

}
