import java.util.ArrayList;

public class AlgoMgr {
	ArrayList<Algorithm> loaded = new ArrayList<Algorithm>();
	
	public void Load(String s)
	{
		Log.info("Loading algorithm "+s);
		try{
			Class<?> cAlgo = Class.forName(s);
			Algorithm algo = (Algorithm)cAlgo.newInstance();
			loaded.add(algo);
		}
		catch (ClassNotFoundException e)
		{
			Log.error("Couldn't find algorith class "+s);
		}
		catch (IllegalAccessException e)
		{
			Log.error("IllegalAccessException while trying to create "+s);
		}
		catch (InstantiationException e)
		{
			Log.error("InstantionException while trying to create "+s);
		}
		
		
	}
	
	public Algorithm[] getAlgorithms()
	{
		Algorithm ret[] = new Algorithm[loaded.size()];
		loaded.toArray(ret);
		return ret;
	}

}
