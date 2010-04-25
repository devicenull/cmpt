import pal.alignment.SitePattern;
import pal.distance.AlignmentDistanceMatrix;
import pal.tree.ClusterTree;


/**
 * Implementation of the Complete Linkage algorithm.
 * @see Algorithm
 */
public class CLAlgorithm extends Algorithm {
	public CLAlgorithm()
	{
		myName = "CompleteLinkage";
	}

	public boolean DoFrame()
	{
		SitePattern sp = new SitePattern(myAlign);
		AlignmentDistanceMatrix adm = new AlignmentDistanceMatrix(sp);
		result = new ClusterTree(adm,ClusterTree.COMPLETE_LINKAGE);
		return false;
	}
}
