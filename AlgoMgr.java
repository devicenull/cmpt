import java.util.ArrayList;

public class AlgoMgr {
	ArrayList<Algorithm> loaded = new ArrayList<Algorithm>();
	
	public void Load(String s)
	{
		Log.info("Loading algorithm "+s);
		loaded.add(new TestAlgorithm(s));
	}
	
	public Algorithm[] getAlgorithms()
	{
		Algorithm ret[] = new Algorithm[loaded.size()];
		loaded.toArray(ret);
		return ret;
	}

}
