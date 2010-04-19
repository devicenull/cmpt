
public class PTreeNode extends TreeNode {
	private String myDesc = "";
	
	public PTreeNode(String description)
	{
		myDesc = description;
	}
	
	public String toString()
	{
		if (getSize() == 0) 
		{
			return myDesc;
		}
		StringBuilder sb = new StringBuilder("(");
		for (TreeNode n: this)
		{
			sb.append(n);
			sb.append(",");
		}
		sb.append(")");
		
		return sb.toString();
	}
	/*public String getNewickNotation()
	{

		String ReturnString = "";

		if(this.hasLeftChild() && this.hasRightChild())
		{
			ReturnString = "(" + 
			ConvertTreeIntoNewickNotation(this.getLeftChild()) +
			"," +
			ConvertTreeIntoNewickNotation(this.getRightChild()) +
			")"
		} elseif(this.hasLeftChild() && !this.hasRightChild()) {
			ReturnString = "(" +
			ConvertTreeIntoNewickNotation(this.getLeftChild()) +
			",)"
		} elseif(!this.hasLeftChild() && this.hasRightChild()) {
			ReturnString = 
			"(," + ConvertTreeIntoNewickNotation(this.getRightChild()) +
			")"
		}

		ReturnString = ReturnString & this.Sequence.toString();

		return ReturnString;

	}*/
}
