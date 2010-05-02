package ptg;

import pal.alignment.SitePattern;
import pal.distance.AlignmentDistanceMatrix;
import pal.tree.ClusterTree;


/**
 * Implementation of the UPGMA algorithm.
 * @see Algorithm
 */
public class UPGMAAlgorithm extends Algorithm {
	public UPGMAAlgorithm()
	{
		myName = "UPGMA";
	}

	public boolean DoFrame()
	{
		SitePattern sp = new SitePattern(myAlign);
		AlignmentDistanceMatrix adm = new AlignmentDistanceMatrix(sp);
		result = new ClusterTree(adm,ClusterTree.UPGMA);
		return false;
	}
}
