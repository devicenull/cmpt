import pal.alignment.Alignment;
import pal.tree.SimpleTree;


public abstract class Algorithm {
	protected String myName;
	protected Alignment myAlign;
	protected SimpleTree result;
	
	public String getName()
	{
		return myName;
	}
	/**
	 * @param a Alignment data to use when doing processing for this algorithm
	 */
	public void setAlignment(Alignment a)
	{
		myAlign = a;
	}
	public String getResult()
	{
		return result.toString();
	}
	
	/**
	 * Run one "frame" of the processing algorithm.  This is done so that progress can
	 * be kept track of elsewhere.
	 * @return true if more processing is needed, false otherwise
	 */
	public abstract boolean DoFrame();
}
