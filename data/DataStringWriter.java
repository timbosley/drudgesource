package drudge.data;

import java.io.*;
import java.util.*;
import java.net.*;
import drudge.global.FileNames;

/*This class is for the data storage of the links it finds using streams
 *I thought it was clever to write up
 */

public class DataStringWriter extends DataWriter {
final private StringWriter buffer = new StringWriter();

	public DataStringWriter() {

	}

	protected LineNumberReader createReader() {
	LineNumberReader reader = new LineNumberReader(new BufferedReader(new StringReader(buffer.toString())));
		try {
		reader.setLineNumber(0);
		reader.mark(0);
		}
		catch (IOException I) {
		D.error(I);
		}
	return reader;
	}

	protected PrintWriter createWriter() {
	return new PrintWriter(buffer);
	}

}
