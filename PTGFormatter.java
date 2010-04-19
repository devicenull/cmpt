import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

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
