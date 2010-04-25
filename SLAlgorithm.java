import pal.alignment.SitePattern;
import pal.distance.AlignmentDistanceMatrix;
import pal.tree.ClusterTree;

/**
 * Implementation of the Single Linkage algorithm.
 * @see Algorithm
 */
public class SLAlgorithm extends Algorithm {
	public SLAlgorithm()
	{
		myName = "SingleLinkage";
	}

	public boolean DoFrame()
	{
		SitePattern sp = new SitePattern(myAlign);
		AlignmentDistanceMatrix adm = new AlignmentDistanceMatrix(sp);
		result = new ClusterTree(adm,ClusterTree.SINGLE_LINKAGE);
		return false;
	}
}
