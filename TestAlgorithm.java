
/**
 * Bare minimum needed to test all the algorithm code.  Will run for 10 iterations
 * each iteration will sleep for 1 second before returning.
 * @see Algorithm
 */
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
