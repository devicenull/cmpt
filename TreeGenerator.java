import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Date;


public class TreeGenerator {
	private Algorithm algo;
	private String statusName;
	private Fasta fastaData;
	private PrintWriter statusLog;
	
	public void setAlgo(Algorithm a)
	{
		Log.info("Selected algorithm "+a.getName());
		algo = a;
	}
	public void setFasta(Fasta f)
	{
		fastaData = f;
	}
	public void setStatus(String s)
	{
		statusName = s;
		try {
			statusLog = new PrintWriter(new FileOutputStream(s),true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void generateTree()
	{
		Log.info("Tree generation begins");
		statusLog.printf("%d starting", (new Date()).getTime());
		long iterations = 0;
		while (algo.DoFrame())
		{
			Log.info("Tree iteration done!");
			iterations++;
			statusLog.printf("%d iteration %d\n", (new Date()).getTime(), iterations);
		}
		statusLog.printf("%d done", (new Date()).getTime());
		Log.info("Tree generation ends");
	}
}
