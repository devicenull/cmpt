import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Fasta implements Iterable<Sequence> {
	private enum ParseState
	{
		BEGIN
		,DESCR
		,DATA
		,DONE
	}
	
	private List<Sequence> sequences;

	/**
	 * Load a FASTA file from disk.  Will save all loaded sequences to this instance of the class
	 * They can later be retrieved by iterating over this instance
	 * @param srcfile Full path to the FASTA file to load.  File extension doesn't matter
	 */
	public Fasta(String srcfile) {
		sequences = new ArrayList<Sequence>();
		Log.info("Fasta trying to load "+srcfile);
		try {
			FileReader f = new FileReader(srcfile);
			LineNumberReader lr = new LineNumberReader(f);
			
			String curline;
			ParseState readState = ParseState.BEGIN;
			Sequence newSeq = new Sequence();
			do
			{
				switch (readState)
				{
				case BEGIN:
				{
					int curchar = lr.read();
					if (curchar == '>')
					{
						readState = ParseState.DESCR;
					}
					else if (curchar == ';')
					{
						lr.readLine();
					}
					else if (curchar == -1)
					{
						readState = ParseState.DONE;
					}
					else {
						Log.error("Malformed Fasta file, unexpected character line "+lr.getLineNumber());
					}
					break;
				}
				case DESCR:
				{
					curline = lr.readLine();
					newSeq.description = curline;
					
					readState = ParseState.DATA;
					
					break;
				}
				case DATA:
				{
					int curchar = lr.read();
					if (curchar == '>' || curchar == ';')
					{
						Log.info("Finished reading sequence "+newSeq);
						sequences.add(newSeq);
						newSeq = new Sequence();
						if (curchar == ';')
						{
							readState = ParseState.BEGIN;
							lr.readLine();
						}
						else 
						{
							readState = ParseState.DESCR;
						}
						break;
					}
					else if (curchar == -1)
					{
						Log.info("Finished reading sequence "+newSeq);
						sequences.add(newSeq);
						newSeq = new Sequence();
						readState = ParseState.DONE;
						break;
					}
					newSeq.data.append((char)curchar);
					newSeq.data.append(lr.readLine());
					break;
				}
				}	
			} while (readState != ParseState.DONE);
			
			
		}
		catch (IOException e)
		{
			Log.error("Fasta file not found!");
		}
	}
	
	public Iterator<Sequence> iterator() {
		return sequences.iterator();
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (Sequence s:sequences)
		{
			sb.append(s.toString());
			sb.append(",");
		}
		return sb.toString();
	}
	
}
