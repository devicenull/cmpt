
public class PTreeNode extends TreeNode<String> {
	/**
	 * @param s Name of this node.  Most likely would be an organism name
	 */
	public PTreeNode(String s)
	{
		setObject(s);
	}
	/**
	 * Return Newick notation for this tree node and all sub nodes.
	 */
	public String toString()
	{
		if (getSize() == 0) 
		{
			return (String)getObject();
		}
		StringBuilder sb = new StringBuilder("(");
		for (TreeNode<String> n: this)
		{
			sb.append(n);
			sb.append(",");
		}
		sb.append(")");
		
		return sb.toString();
	}
}
