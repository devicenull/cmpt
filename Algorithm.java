
public abstract class Algorithm {
	protected String myName;
	public String getName()
	{
		return myName;
	}
	
	// Returns true if more processing is needed, false if we are done
	/**
	 * Run one "frame" of the processing algorithm.  This is done so that progress can
	 * be kept track of elsewhere.
	 * @return true if more processing is needed, false otherwise
	 */
	public abstract boolean DoFrame();
}
