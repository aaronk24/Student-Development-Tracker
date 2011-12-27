package org.tg8.sdt.data.file;

import java.util.ArrayList;
import java.util.List;

class CSVRecordReader {
	
	private boolean endOfRecord;
	private boolean quoteEscape;
	private StringBuilder record;
	private boolean unbalancedQuotes;

	
	
	CSVRecordReader(StringBuilder newRecord) {
		record = new StringBuilder(newRecord);
		endOfRecord = false;
		quoteEscape = false;
		unbalancedQuotes = false;
	}
	
	List<String> getList() {
		List<String> valueList = new ArrayList<String>();
		while (!endOfRecord) {
			valueList.add(nextValue());
		}
		return valueList;
	}
	
	private String nextValue() {
		StringBuilder value = new StringBuilder();
		boolean done = false;
		String nextChar;
		while (!done) {
			nextChar = consumeCharFromRecord();
			if (
					unbalancedQuotes
					|| !nextChar.equals(CSVLineWriter.SEPARATOR) 
			) {
				value.append(nextChar);
			}
			done = (
				(
					nextChar.equals(CSVLineWriter.SEPARATOR) 
					&& !unbalancedQuotes
				)
				|| nextChar.length() == 0
			);
		}
		return value.toString();
	}
	
	private String consumeCharFromRecord() {
		String nextChar;
		if (record.length() > 0) {
			nextChar = Character.toString(record.charAt(0));
			record.deleteCharAt(0);
			if (nextChar.equals("\"")) {
				toggleQuoteEscape();
				toggleUnbalancedQuotes();
				if (quoteEscape) {
					nextChar = consumeCharFromRecord();
				}
			} 
			else {
				quoteEscape = false;
			}
		} 
		else {
			nextChar = "";
			endOfRecord = true;
			quoteEscape = false;
		}
		return nextChar;
	}
	
	private void toggleQuoteEscape() {
		quoteEscape = !quoteEscape;
	}
	
	private void toggleUnbalancedQuotes() {
		unbalancedQuotes = !unbalancedQuotes;
	}
	
}
