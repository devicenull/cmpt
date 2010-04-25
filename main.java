import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import pal.alignment.Alignment;
import pal.alignment.AlignmentReaders;
import pal.datatype.AminoAcids;

public class main {

	public static void main(String[] args) {
		ConfigMgr cfg = new ConfigMgr(args);
		
		if (cfg.getConfig("loadFile") == null || cfg.getConfig("saveDirectory") == null
			|| cfg.getConfig("algorithms") == null || cfg.getConfig("statusFile") == null
			|| cfg.getConfig("logFile") == null)
			{
				System.out.println("Expected loadFile,saveDirectory,algorithms,statusFile,logFile to be set on command line!");
				System.exit(-1);
			}
		Log.Init(cfg.getConfig("logFile"));
		Log.info("Logger loaded");
			
		Log.info("CONFIG: loading file "+cfg.getConfig("loadFile"));
		Log.info("CONFIG: saving results to "+cfg.getConfig("saveDirectory"));
		Log.info("CONFIG: using algorithms "+cfg.getConfig("algorithms"));
		Log.info("CONFIG: status file "+cfg.getConfig("statusFile"));
		
		AlgoMgr am = new AlgoMgr();
		for (String s: cfg.getConfig("algorithms").split(","))
		{
			am.Load(s);
		}
		Alignment curData = null;
		try {
			curData = AlignmentReaders.readFastaSequences(new FileReader(cfg.getConfig("loadFile"))
				, new AminoAcids());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(curData);
		
		for (Algorithm a: am)
		{
			TreeGenerator tg = new TreeGenerator();
			tg.setAlgo(a);
			tg.setAlignment(curData);
			tg.setStatus(cfg.getConfig("statusFile"));
			tg.generateTree();
		}
		Log.info("Execution has finished");
	}

}
