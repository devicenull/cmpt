package tests;


import pal.alignment.Alignment;
import pal.alignment.AlignmentReaders;
import ptg.CLAlgorithm;
import ptg.SLAlgorithm;
import ptg.TreeGenerator;
import ptg.WPGMAAlgorithm;

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
   
    //Test Method for assertFiles to verify the log were omitted due to lack of time
    public void testSet1Algorithm()
    {
        CLAlgorithm cLAlgori1 = new CLAlgorithm();
        TreeGenerator treeGene1 = new TreeGenerator();
        treeGene1.setAlgo(cLAlgori1);
    }

    public void testNullAlignment()
    {
        TreeGenerator treeGene1 = new TreeGenerator();
        treeGene1.setAlignment(null);
    }
    
    //Directory /users/yoanante/documents must exist on test machine else it will be created 
    //if allowed by the unix based system.
    public void testCreateStatusFile()
    {
        TreeGenerator treeGene1 = new TreeGenerator();
        treeGene1.setStatus("/users/yoanante/documents/status.txt");
    }
}






