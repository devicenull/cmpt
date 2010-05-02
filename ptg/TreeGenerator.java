package ptg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Date;

import pal.alignment.Alignment;


/**
 * Handles the actual act of generating a tree for a specific algorithm and data.  This class
 * takes care of writing the status information to disk.  It also provides features for 
 * saving the generated data to disk.
 * 
 * A new instance of this class should be created for each separate Algorithm being used.  Behavior
 * is undefined if one instance is reused for all of them.
 * @see Algorithm
 * @see Alignment
 */
public class TreeGenerator {
	private Algorithm algo;
	private Alignment alignData;
	private PrintWriter statusLog;
	
	/**
	 * Set the Algorithm to be used for this tree generation
	 * @param a Instance of algorithm to use
	 */
	public void setAlgo(Algorithm a)
	{
		Log.info("Selected algorithm "+a.getName());
		algo = a;
	}
	/**
	 * Set the Alignment data to be used for this tree generation
	 * @param f Alignment instance to generate a tree from
	 */
	public void setAlignment(Alignment f)
	{
		alignData = f;
	}
	/**
	 * This will set the file to record status information.  There are three types of status lines:
	 * timestamp in all of these is the number of seconds since the unix epoc
	 * 		"timestamp starting" - Tree generation has started
	 * 		"timestamp iteration n" - The nth iteration of tree generation has completed
	 * 		"timestamp done" - Tree generation has ended
	 * A warning will be written to the log file if this file cannot be opened, but execution will continue.
	 * @param s Full path to the file to record status information in.
	 */
	public void setStatus(String s)
	{
		try {
			statusLog = new PrintWriter(new FileOutputStream(s),true);
		} catch (FileNotFoundException e) {
			Log.warn("FileNotFound error when attempting to open status log "+s);
		}
	}
	/**
	 * Run the algorithm on the specified data.  The status log will be updated as the algorithm runs.
	 * This function will not return until tree generation has completed.
	 */
	public void generateTree()
	{
		Log.info("Tree generation begins");
		algo.setAlignment(alignData);
		if (statusLog != null)
		{
			statusLog.printf("%d starting\n", (new Date()).getTime());
		}
		long iterations = 0;
		while (algo.DoFrame())
		{
			Log.info("Tree iteration done!");
			iterations++;
			if (statusLog != null)
			{
				statusLog.printf("%d iteration %d\n", (new Date()).getTime(), iterations);
			}
		}
		if (statusLog != null)
		{
			statusLog.printf("%d done\n", (new Date()).getTime());
		}
		Log.info("Tree generation ends");
	}
	
	/**
	 * Save the results of this run to disk.  Directory should already exist, and algorithm should have been run
	 * Files will be overwritten if they already exist in the specified directory
	 * @param saveDirectory Full path of directory where files should be saved.  File will be: "name of the algorithm.nwk"
	 */
	public void saveToDisk(String saveDirectory)
	{
		if (algo.getResult() == null) return;
		
		String outFile = saveDirectory + File.separator + algo.getName() + ".nwk";
		Log.info("Writing output of " + algo.getName() + " to " + outFile);
		PrintWriter out;
		try {
			out = new PrintWriter(new FileOutputStream(outFile), true);
			out.print(algo.getResult());
			out.close();
		} catch (FileNotFoundException e) {
			Log.error("FileNotFoundException thrown when trying to write results of "+algo.getName());
		}
	}
}
