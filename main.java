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
		
		Fasta curFasta = new Fasta(cfg.getConfig("loadFile"));
		
		for (Algorithm a:am.getAlgorithms())
		{
			TreeGenerator tg = new TreeGenerator();
			tg.setAlgo(a);
			tg.setFasta(curFasta);
			tg.setStatus(cfg.getConfig("statusFile"));
			tg.generateTree();
		}
		
		System.out.println("execution done");
	}

}
