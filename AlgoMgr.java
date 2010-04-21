import java.util.ArrayList;
import java.util.Iterator;

public class AlgoMgr implements Iterable<Algorithm> {
	ArrayList<Algorithm> loaded = new ArrayList<Algorithm>();

	/**
	 * Load an argument from it's name.  Name should be the full classname of the algorithm
	 * @param s Class name of the algorithm to load
	 */
	public void Load(String s) {
		Log.info("Loading algorithm " + s);
		try {
			Class<?> cAlgo = Class.forName(s);
			Algorithm algo = (Algorithm) cAlgo.newInstance();
			loaded.add(algo);
			Log.info("Algorithm "+s+" has been loaded");
		} catch (ClassNotFoundException e) {
			Log.error("Couldn't find algorith class " + s);
		} catch (IllegalAccessException e) {
			Log.error("IllegalAccessException while trying to create " + s);
		} catch (InstantiationException e) {
			Log.error("InstantionException while trying to create " + s);
		}
	}

	public Iterator<Algorithm> iterator() {
		return loaded.iterator();
	}

}
