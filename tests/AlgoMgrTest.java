package tests;

import ptg.AlgoMgr;
import ptg.Algorithm;

/**
 *Unit Testing AlgoMgr
 *This test is performed to ensure that the data passed to this class 
 *is can be loaded and an instance of the algorithm is created.
 *
 *Test Data
 *The expected data is a string in the form of the algorithm's class's name. 
 *This class will throw exceptions for class not found when it tries to
 *reflectively create an instance, set or get a field that it does not have
 *access to. It will also throw an instantiation exception when if it tries 
 *to load the algorithm abstract class. The data that will used be used to 
 *verify this class is the current algorithm class names as strings when
 *AlgoMgr is created.
 */
public class AlgoMgrTest extends junit.framework.TestCase
{
    public void testLoadCLAlgorithm()
    {
        AlgoMgr algoMgr1 = new AlgoMgr();
        assertTrue(algoMgr1.Load("ptg.CLAlgorithm"));
    }

    public void testLoadSLAlgorithm()
    {
        AlgoMgr algoMgr1 = new AlgoMgr();
        assertTrue(algoMgr1.Load("ptg.SLAlgorithm"));
    }

    public void testLoadUPGMAALgorithm()
    {
        AlgoMgr algoMgr1 = new AlgoMgr();
        assertTrue(algoMgr1.Load("ptg.UPGMAAlgorithm"));
    }

    public void testLoadWPGMAAlgorithm()
    {
        AlgoMgr algoMgr1 = new AlgoMgr();
        assertTrue(algoMgr1.Load("ptg.WPGMAAlgorithm"));
    }
    //Negative Test
	public void testInstantiationExceptionThrow()
	{
		AlgoMgr algoMgr1 = new AlgoMgr();
		assertFalse(algoMgr1.Load("Algorithm"));  
	}

	public void testClassNotFoundException()
	{
		AlgoMgr algoMgr1 = new AlgoMgr();
		assertFalse(algoMgr1.Load("NoneNamedAlgorithm"));
	}

	public void testIteratorCountValid()
	{
	    AlgoMgr algoMgr1 = new AlgoMgr();
	    algoMgr1.Load("ptg.CLAlgorithm");
	    algoMgr1.Load("ptg.SLAlgorithm");
	    algoMgr1.Load("ptg.UPGMAAlgorithm");
	    algoMgr1.Load("ptg.WPGMAAlgorithm");
	    int i = 0;
	    for (@SuppressWarnings("unused") Algorithm a:algoMgr1)
	    {
	    	i++;
	    }
	    assertEquals(4, i);
	}
	public void testIteratorCountInvalid()
	{
	    AlgoMgr algoMgr1 = new AlgoMgr();
	    algoMgr1.Load("ptg.Algorithm");
	    algoMgr1.Load("DoesNotExist");
	    int i = 0;
	    for (@SuppressWarnings("unused") Algorithm a:algoMgr1)
	    {
	    	i++;
	    }
	    assertEquals(0, i);
	}
	public void testIteratorCountDefault()
	{
		AlgoMgr algoMgr1 = new AlgoMgr();
	    int i = 0;
	    for (@SuppressWarnings("unused") Algorithm a:algoMgr1)
	    {
	    	i++;
	    }
	    assertEquals(0, i);
	}
}







