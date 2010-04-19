
public class PTreeNode extends TreeNode<String> {
	public PTreeNode(String s)
	{
		setObject(s);
	}
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
