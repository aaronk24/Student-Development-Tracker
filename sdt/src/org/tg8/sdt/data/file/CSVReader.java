package org.tg8.sdt.data.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class CSVReader {

	static boolean stringHasUnbalancedQuotes(StringBuilder sb) {
		return (getNumberQuotes(sb) % 2 == 1);
	}
	
	static int getNumberQuotes(StringBuilder sb) {
		int nbrQuotes = 0;
		int quoteIndex = sb.indexOf("\"");
		while (quoteIndex != -1){
			quoteIndex = sb.indexOf("\"", quoteIndex + 1);
			nbrQuotes += 1;
		}
		return nbrQuotes;
	}

	static boolean stringHasNoUnbalancedQuotes(StringBuilder sb) {
		return (getNumberQuotes(sb) % 2 == 0);
	}
	
	private BufferedReader myBufferedReader;
	private StringBuilder currentRecord;
	
	
	
	public CSVReader(BufferedReader newReader) {
		myBufferedReader = newReader;
	}

	public List<String> readRecord() {
		readNextRecord();
		if (currentRecord != null) {
			CSVRecordReader recordReader = new CSVRecordReader(currentRecord);
			return recordReader.getList();
		} 
		else {
			return null;
		}
	}
	
	public void readNextRecord() {
		 try {
			 
			 String line = myBufferedReader.readLine();
			 if (line == null) {
				 currentRecord = null;
			 }
			 else {
				 currentRecord = new StringBuilder();
				 currentRecord.append(line);
				 while (stringHasUnbalancedQuotes(currentRecord)) {
					 currentRecord.append("\n");
					 // restore end of line character "lost" by readline
					 currentRecord.append(myBufferedReader.readLine());
				 } 	
			 }
		 } catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void close() throws IOException {
		myBufferedReader.close();
	}
}
