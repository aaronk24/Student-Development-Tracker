package org.tg8.sdt.data.file.test;

import java.io.IOException;
import java.io.Writer;

public class MockWriter extends Writer {
	
	private StringBuilder myString = new StringBuilder();
	
	@Override
	public void close() throws IOException {
	}

	@Override
	public void flush() throws IOException {
	}

	@Override
	public void write(char[] chars, int start, int length) throws IOException {
		for (int i = start; i < (start + length); i += 1) {
			myString.append(chars[i]);
		}
	}
	
	public String getOutput () {
		return myString.toString();
	}
	
}
