import java.io.IOException;
import java.util.logging.*;

public class Log {
	static private Logger logger = Logger.getLogger("ptglog");
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
	
	static public void warn(String logline)
	{
		logger.warning(logline);
	}
	
	static public void error(String logline)
	{
		logger.log(Level.SEVERE,logline);
	}
	
	static public void info(String logline)
	{
		logger.log(Level.FINE,logline);
	}

}
