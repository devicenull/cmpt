import java.util.ArrayList;
import java.util.Iterator;

/**
 * AlgoMgr is responsible for loading any new algorithms from disk.  It does not require a hardcoded
 * list of algorithms to work, so adding new algorithms requires no code changes to existing code.
 * 
 * AlgoMgr instances are Iterable, so the list of currently loaded Algorithms can be easily used.
 * 
 * Any Algorithm that is implemented in the future should implement the Algorithm interface.
 * 
 * @see Algorithm
 */
public class AlgoMgr implements Iterable<Algorithm> {
	ArrayList<Algorithm> loaded = new ArrayList<Algorithm>();

	/**
	 * Load an Algorithm based on its name.  Name should be the full classname of the algorithm
	 * Any new algorithms just need to have their compiled code placed in the same directory
	 * as the rest of the code.  No changes to any other code would be necessary.
	 * @param s Class name of the algorithm to load
	 */
	public boolean Load(String s) {
		Log.info("Loading algorithm " + s);
		try {
			Class<?> cAlgo = Class.forName(s);
			Algorithm algo = (Algorithm) cAlgo.newInstance();
			loaded.add(algo);
			Log.info("Algorithm "+s+" has been loaded");
			return true;
		} catch (ClassNotFoundException e) {
			Log.error("Couldn't find algorith class " + s);
		} catch (IllegalAccessException e) {
			Log.error("IllegalAccessException while trying to create " + s);
		} catch (InstantiationException e) {
			Log.error("InstantionException while trying to create " + s);
		}
		return false;
	}

	public Iterator<Algorithm> iterator() {
		return loaded.iterator();
	}

}
