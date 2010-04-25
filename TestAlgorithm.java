import pal.alignment.Alignment;
import pal.alignment.SitePattern;
import pal.distance.AlignmentDistanceMatrix;
import pal.tree.ClusterTree;


/**
 * Bare minimum needed to test all the algorithm code.  Will run for 10 iterations
 * each iteration will sleep for 1 second before returning.
 * @see Algorithm
 */
public class TestAlgorithm extends Algorithm {
	private Alignment align;
	public TestAlgorithm()
	{
		myName = "dynamic algo!";
	}
	
	public void setAlignment(Alignment a)
	{
		align = a;
	}

	public boolean DoFrame()
	{
		SitePattern sp = new SitePattern(align);
		AlignmentDistanceMatrix adm = new AlignmentDistanceMatrix(sp);
		ClusterTree result = new ClusterTree(adm,ClusterTree.WPGMA);
		System.out.println(result);
		return false;
	}
}
