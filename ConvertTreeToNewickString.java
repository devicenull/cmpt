String ConvertTreeIntoNewickNotation(PhylogeneticTreeNode Node)
{

	String ReturnString = "";

	if(RootNode.hasLeftChild() && RootNode.hasRightChild())
	{
		ReturnString = "(" + 
		ConvertTreeIntoNewickNotation(Node.getLeftChild()) +
		"," +
		ConvertTreeIntoNewickNotation(Node.getRightChild()) +
		")"
	} elseif(Node.hasLeftChild() && !Node.hasRightChild()) {
		ReturnString = "(" +
		ConvertTreeIntoNewickNotation(Node.getLeftChild()) +
		",)"
	} elseif(!Node.hasLeftChild() && Node.hasRightChild()) {
		ReturnString = 
		"(," + ConvertTreeIntoNewickNotation(Node.getRightChild()) +
		")"
	}

	ReturnString = ReturnString & Node.Sequence.toString();

	return ReturnString;

}

