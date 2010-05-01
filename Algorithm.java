import pal.alignment.Alignment;
import pal.tree.SimpleTree;


/**
 * Interface that any tree generation algorithm needs to implement.
 * Each algorithm should set myName in its constructor.  All code here supports spaces in Algorithm names,
 * however they are generally not used to prevent compatibility issues in the future.
 * The result of the algorithm run should be stored in the result class variable by default.
 */
public abstract class Algorithm {
	protected String myName = "unnamed";
	protected Alignment myAlign;
	protected SimpleTree result = null;
	
	/**
	 * Retrieve the name of this algorithm.
	 * @return Name of this algorithm.  Default is "unnamed"
	 */
	public String getName()
	{
		return myName;
	}
	/**
	 * Set the Alignment data (typically loaded from a FASTA file) to use with this algorithm
	 * @param a Alignment data to use when doing processing for this algorithm
	 * @see Alignment
	 */
	public void setAlignment(Alignment a)
	{
		myAlign = a;
	}
	
	/**
	 * Retrieve result of this algorithm run.  Note that processing must be done first, or this will crash.
	 * @return Result in Newick Notation format
	 */
	public String getResult()
	{
		return result.toString();
	}
	
	/**
	 * Run one "frame" of the processing algorithm. There is no clear point where each frame is complete.
	 * That would depend on the exact algorithm being implemented.
	 * @return true if more processing is needed, false if processing is complete
	 */
	public abstract boolean DoFrame();
}
