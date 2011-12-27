package org.tg8.sdt.data.file.test;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tg8.sdt.data.file.CSVReader;

public class CSVReaderTest {
	private CSVReader reader;
	private MockReader myMockReader;
	
	@Before
	public void setUp () {
		myMockReader = new MockReader();
		reader = new CSVReader(new BufferedReader(myMockReader));
	}
	
	@Test
	public void testRead () {
		myMockReader.append("Hi,there\n");
		List<String> myList = new ArrayList<String>();
		myList.add("Hi");
		myList.add("there");
		List<String> readList = reader.readRecord();
		Assert.assertEquals(myList, readList);
	}
	
	@Test
	public void testReadSpecialCharacters() {
		myMockReader.append("\"Yo,\nyo\",\"\"s\"\"up\"\"");
		List<String> myList = new ArrayList<String>();
		myList.add("Yo,\nyo");
		myList.add("\"s\"up\"");
		List<String> readList = reader.readRecord();
		Assert.assertEquals(myList, readList);
	}
	
	@Test
	public void testNull() {
		List<String> readList = reader.readRecord();
		Assert.assertNull(readList);
	}
	
	@Test
	public void testEmptyString() {
		myMockReader.append("\n");
		List<String> myList = new ArrayList<String>();
		myList.add("");
		List<String> readList = reader.readRecord();
		Assert.assertEquals(myList, readList);
	}
	
	@Test
	public void testMultipleRecords() {
		myMockReader.append("Hi\nthere");
		List<String> myList = new ArrayList<String>();
		myList.add("there");
		reader.readRecord();
		List<String> readList = reader.readRecord();
		Assert.assertEquals(myList, readList);
	}
}
