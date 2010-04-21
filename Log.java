import java.io.IOException;
import java.util.logging.*;

public class Log {
	static private Logger logger = Logger.getLogger("ptglog");
	/**
	 * @param logfile Full path to the log file to record messages to.  Log file will not be cleared
	 */
	static public void Init(String logfile)
	{
		try {
			Formatter format = new PTGFormatter();
			
			FileHandler fh = new FileHandler(logfile,true);
			fh.setFormatter(format);
			
			StreamHandler ch = new StreamHandler(System.out,format);
			
			logger.addHandler(fh);
			logger.addHandler(ch);
			logger.setLevel(Level.ALL);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Print a warning message to the log file.  This is a non-fatal error, but is something
	 * that needs to be looked into
	 * @param logline Log line to be written
	 */
	static public void warn(String logline)
	{
		logger.warning(logline);
	}
	
	/**
	 * Print an error message to the log file.  This is a fatal error, and execution would
	 * typically stop because of it
	 * @param logline Log line to be written.
	 */
	static public void error(String logline)
	{
		logger.log(Level.SEVERE,logline);
	}
	
	/**
	 * Print an informational message to the log file.  This is typically debugging information,
	 * or very detailed information that may not always be useful in normal circumstances
	 * @param logline Log line to be written
	 */
	static public void info(String logline)
	{
		logger.log(Level.FINE,logline);
	}

}
