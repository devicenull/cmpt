package ptg;

import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

/**
 * This provides formatting functions for the Log class.  Log messages are printed in the following format:
 * 	level - message \n
 * @see java.util.logging.SimpleFormatter
 * @see Log
 */
public class PTGFormatter extends SimpleFormatter {
	public String format(LogRecord record) {
		StringBuilder sb = new StringBuilder();
		sb.append(record.getLevel());
		sb.append(" - ");
		sb.append(record.getMessage());
		sb.append("\n");
		return sb.toString();
	}
}
