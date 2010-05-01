import java.io.IOException;
import java.util.logging.*;

/**
 * The Log class provides a simple way to record log messages or other debugging information.
 * Currently, three logging levels are provided:
 * 		info - These messages allow tracing of what action is being performed
 * 		warn - These messages indicate a problem that needs to be fixed, but execution can continue anyway
 * 		error - These messages indicate a fatal error that has caused execution to stop
 * 
 * The log file used will have any new messages appended to it.  No effort is made to truncate the log 
 * if it gets to long.  This should be handled externally, perhaps using other software such as logrotate(8)
 * 
 * This class is meant to be a Singleton.  You should not try to have multiple active Log classes, behavior
 * is undefined if that is done.
 */
public class Log {
	static private Logger logger = Logger.getLogger("ptglog");
	/**
	 * Initializes the class, opens the log file and prepares to write new items to it.  If an error occurs
	 * during this, a stack trace will be printed to the console.  There is no other way to record failure information
	 * before the logging subsystem has been initialized.
	 * @param logfile Full path to the log file to record messages to.  File will be created if it does not exist
	 * @see java.util.logging.Logger
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
