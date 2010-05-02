package tests;

import java.io.File;

import ptg.CLAlgorithm;
import ptg.TreeGenerator;

/**
 *Unit Testing TreeGenerator Class
 *This test will ensure that an algorithm can be set and that a the 
 *sequences are aligned. It will test that the status is set with 
 *proper information including the starting time the iteration and 
 *the finish time. It will test that a status file can be created 
 *and will throw a file not found exception if the status file is 
 *not located. This test will ensure that this class can generate a 
 *tree when it has an algorithm supplied and save that information 
 *to a file. algo, alignData and StatusLog must be initialized for 
 *generateTree to be executed.
 *
 *These test cases will assume that a stub has been written for the 
 *algorithm class.
 *the name of the stub will be assumed to be Algostub. It will be 
 *assumed that the next line of code is declared in Algostub
 *Alignment alignment = new Alignment();
 *
 *it will be assumed that the following line will be declared in 
 *TestTreeGenerator to create new instances of Algostub to be used 
 *with this class
 *
 *Algostub CLAlgorithm = new Algostub(); 
 *Algostub SLAlgorithm = new Algostub();
 *Algostub UPGMAAlgorithm = new Algostub();
 *Algostub WPGMAAlgorithm = new Algostub();
 *the above statements are no longer applicable since the algorithm 
 *class was coded and implemented.
 *
 */
public class TreeGeneratorTest extends junit.framework.TestCase
{
    public void testSet1Algorithm()
    {
        CLAlgorithm cLAlgori1 = new CLAlgorithm();
        TreeGenerator treeGene1 = new TreeGenerator();
        treeGene1.setAlgo(cLAlgori1);
        assertEquals(cLAlgori1,treeGene1.getAlgo());
    }
    
    public void testSetNulAlgorithm()
    {
        TreeGenerator treeGene1 = new TreeGenerator();
        assertNull(treeGene1.getAlgo()); 	
    }

    public void testNullAlignment()
    {
        TreeGenerator treeGene1 = new TreeGenerator();
        assertNull(treeGene1.getAlignment());
        treeGene1.setAlignment(null);
        assertNull(treeGene1.getAlignment());
    }
       
    // Attempt to create a file at the path set by testPath.
    // This must be updated if tests are run on a non-windows OS
    public void testCreateStatusFile()
    {
    	String testPath = "C:\\status.txt";
    	File statusFile = new File(testPath);
    	statusFile.delete();
    	assertFalse(statusFile.exists());
        TreeGenerator treeGene1 = new TreeGenerator();
        treeGene1.setStatus(testPath);
        assertTrue(statusFile.exists());
    }
}
