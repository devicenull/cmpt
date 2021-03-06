// ClockTree.java
//
// (c) 1999-2001 PAL Development Core Team
//
// This package may be distributed under the
// terms of the Lesser GNU General Public License (LGPL)


package pal.tree;

import pal.misc.*;


/**
 * provides parameter interface to a clock tree
 * (parameters are the minimal node height differences
 * at each internal node)
 *
 * @version $Id: ClockTree.java,v 1.16 2003/06/04 03:17:52 matt Exp $
 *
 * @author Korbinian Strimmer
 * @author Alexei Drummond
 */
public class ClockTree extends ParameterizedTree.ParameterizedTreeBase  implements ParameterizedTree
{
	//
	// Public stuff
	//

	public ClockTree() {}


	/**
	 * take any tree and afford it with an interface
	 * suitable for a clock-like tree (parameters
	 * are the minimal node height differences at each internal node).
	 * <p>
	 * <em>This parameterisation of a clock-tree, ensuring that
	 * all parameters are independent of each other is due to
				 * Andrew Rambaut (personal communication).</em>
	 */
	public ClockTree(Tree t)
	{
		setBaseTree(t);

		if (getRoot().getChildCount() < 2)
		{
			throw new IllegalArgumentException(
			"The root node must have at least two childs!");
		}

		parameter = new double[getNumParameters()];

		for (int i = 0; i < parameter.length; i++)
		{
			parameter[i] = getDefaultValue(i);
		}


		// init
		parameters2Heights();
		NodeUtils.heights2Lengths(getRoot());
	}

	/** make clocktree consistent with branch lengths */
	public void update()
	{
		createNodeList();
		NodeUtils.lengths2Heights(getRoot());
		heights2parameters();
	}

	// interface Parameterized

	public int getNumParameters()
	{
		return getInternalNodeCount();
	}

	public void setParameter(double param, int n)
	{
		parameter[n] = param;

		parameters2Heights();
		NodeUtils.heights2Lengths(getRoot());
	}

	public double getParameter(int n)
	{
		return parameter[n];
	}

	public void setParameterSE(double paramSE, int n)
	{
		return; // we are not interested in these SEs
	}

	public double getLowerLimit(int n)
	{
		return BranchLimits.MINARC;
	}

	public double getUpperLimit(int n)
	{
		return BranchLimits.MAXARC;
	}

	public double getDefaultValue(int n)
	{
		return BranchLimits.DEFAULT_LENGTH;
	}

	//
	// Private stuff
	//

	protected void parameters2Heights()
	{
		// nodes have been stored by a post-order traversal

		for (int i = 0; i < getExternalNodeCount(); i++)
		{
			getExternalNode(i).setNodeHeight(0.0);
		}

		for (int i = 0; i < getInternalNodeCount(); i++)
		{
			Node node = getInternalNode(i);
			node.setNodeHeight(parameter[i] + NodeUtils.findLargestChild(node));
		}
	}

	protected void heights2parameters()
	{
		for (int i = 0; i < getInternalNodeCount(); i++)
		{
			Node node = getInternalNode(i);
			parameter[i] = node.getNodeHeight()-NodeUtils.findLargestChild(node);
		}
	}

	protected double[] parameter;

	public Tree getCopy() {
		return new ClockTree(this);
	}

	public String getParameterizationInfo() {
		return "Molecular clock tree";
	}

// ===========================================================================
// ===== Static stuff =======

	/**
	 * Obtain a ParameterizedTree.Factory for generating Clock trees
	 */
	public static final ParameterizedTree.Factory getParameterizedTreeFactory() {
		return TreeFactory.DEFAULT_INSTANCE;
	}

	private static class TreeFactory implements ParameterizedTree.Factory {
		public static final ParameterizedTree.Factory DEFAULT_INSTANCE = new TreeFactory();
		public ParameterizedTree generateNewTree(Tree base) {
			return new ClockTree(base);
		}
	}
}
