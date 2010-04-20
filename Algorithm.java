
public abstract class Algorithm {
	protected String myName;
	public String getName()
	{
		return myName;
	}
	
	// Returns true if more processing is needed, false if we are done
	public abstract boolean DoFrame();
}
