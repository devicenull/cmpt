import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Date;

import pal.alignment.Alignment;


public class TreeGenerator {
	private Algorithm algo;
	private String statusName;
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
		statusName = s;
		try {
			statusLog = new PrintWriter(new FileOutputStream(s),true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
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
}
