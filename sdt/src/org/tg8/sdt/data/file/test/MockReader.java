package org.tg8.sdt.data.file.test;

import java.io.IOException;
import java.io.Reader;

public class MockReader extends Reader {

	private StringBuilder myString = new StringBuilder();
	
	@Override
	public void close() throws IOException {}

	@Override
	public int read(char[] buffer, int startIndex, int length) throws IOException {
		int charsRead = 0;

		if (myString.length() > 0) { 
			for (int i = startIndex; i < (startIndex + length); i += 1) {
				if (myString.length() > 0) {
					buffer[i] = myString.charAt(0);
					myString.deleteCharAt(0);
					charsRead += 1;
				}
			}
		} else {
			charsRead = -1;
		}
		return charsRead;
	}
	
	public void append (String appendedString) {
		myString.append(appendedString);
	}

}
