import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;


public class Fasta {
	private enum ParseState
	{
		BEGIN
		,DESCR
		,DATA
		,DONE
	}

	public Fasta(String srcfile) {
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
	
}
