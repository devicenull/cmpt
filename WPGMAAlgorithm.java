import pal.alignment.SitePattern;
import pal.distance.AlignmentDistanceMatrix;
import pal.tree.ClusterTree;


/**
 * Implementation of the WPGMA algorithm.
 * @see Algorithm
 */
public class WPGMAAlgorithm extends Algorithm {
	public WPGMAAlgorithm()
	{
		myName = "WPGMA";
	}

	public boolean DoFrame()
	{
		SitePattern sp = new SitePattern(myAlign);
		AlignmentDistanceMatrix adm = new AlignmentDistanceMatrix(sp);
		result = new ClusterTree(adm,ClusterTree.WPGMA);
		return false;
	}
}
