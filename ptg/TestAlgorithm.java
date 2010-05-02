package ptg;

import pal.tree.SimpleTree;

public class TestAlgorithm extends Algorithm {
	public int iterations = 0;
	
	public TestAlgorithm() {
		myName = "TestAlgorithm";
	}

	public boolean DoFrame() {
		if (iterations < 5)
		{
			iterations++;
			return true;
		}
		result = new SimpleTree();
		return false;
	}
}
