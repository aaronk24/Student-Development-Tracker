package org.tg8.sdt.data.file;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class CSVLineWriter {

	public static final String SEPARATOR = ",";
	private PrintWriter writer;
	
	public CSVLineWriter(PrintWriter newWriter) {
		writer = newWriter;
	}

	public void write(List<String> myList) {
		Iterator<String> i = myList.iterator();
		while (i.hasNext()) {
			writeString(i.next());
			if (i.hasNext()) {
				writer.print(SEPARATOR);
			}
		}
		writer.println();
	}

	private void writeString(String dataElement) {
		String nullCheckedString = dataElement == null ?
				"" : dataElement;
		String s = this.escapeSpecialCharacters(nullCheckedString);
		writer.print(s);
	}
	
	private String escapeSpecialCharacters (String input) {
		String quoteEscape = this.escapeQuotes(input);
		return this.escapeDelimiter(quoteEscape);
	}

	private String escapeQuotes(String input) {
		return input.replaceAll("\\\"", "\"\"");
	}
	
	private String escapeDelimiter(String input) {
		String returnVal;
		if (input.contains(CSVLineWriter.SEPARATOR) ||
				input.contains("\n")) {
			returnVal = "\"" + input + "\"";
		} else {
			returnVal = input;
		}
		return returnVal;
	}

	public void close() {
		writer.close();
	}
}
