
public class TestAlgorithm extends Algorithm {
	private int iterations = 10;
	public TestAlgorithm()
	{
		myName = "dynamic algo!";
	}

	public boolean DoFrame()
	{
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		
		iterations--;
		if (iterations > 0)
		{
			return true;
		}
		return false;
	}
}
