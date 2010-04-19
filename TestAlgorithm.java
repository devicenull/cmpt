
public class TestAlgorithm implements Algorithm {

	private String myName;
	
	public TestAlgorithm()
	{
		myName = "dynamic algo!";
	}
	
	public TestAlgorithm(String newname) {
		myName = newname;
	}

	@Override
	public String getName() {
		return myName;
	}

}
