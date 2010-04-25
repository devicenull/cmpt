import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Date;

import pal.alignment.Alignment;


public class TreeGenerator {
	private Algorithm algo;
	private Alignment alignData;
	private PrintWriter statusLog;
	
	/**
	 * @param a Algorithm to use for this generation
	 */
	public void setAlgo(Algorithm a)
	{
		Log.info("Selected algorithm "+a.getName());
		algo = a;
	}
	/**
	 * @param f Alignment instance containing Sequences to generate a tree from
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
	 * @param s Full path to the file to record status information in.
	 */
	public void setStatus(String s)
	{
		try {
			statusLog = new PrintWriter(new FileOutputStream(s),true);
		} catch (FileNotFoundException e) {
			Log.error("FileNotFound error when attempting to open status log "+s);
		}
	}
	/**
	 * Run the algorithm on the specified data.  Will not return until generation is complete.
	 */
	public void generateTree()
	{
		algo.setAlignment(alignData);
		Log.info("Tree generation begins");
		statusLog.printf("%d starting\n", (new Date()).getTime());
		long iterations = 0;
		while (algo.DoFrame())
		{
			Log.info("Tree iteration done!");
			iterations++;
			statusLog.printf("%d iteration %d\n", (new Date()).getTime(), iterations);
		}
		statusLog.printf("%d done\n", (new Date()).getTime());
		Log.info("Tree generation ends");
	}
	
	/**
	 * Save the results of this run to disk.  Directory should already exist, and algorithm should have been run
	 * @param saveDirectory Directory to write the files to.  File will be created with "name of the algorithm.nwk"
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
