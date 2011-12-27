package org.tg8.sdt.data.file.test;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tg8.sdt.data.file.CSVLineWriter;

public class CSVLineWriterTest {
	
	private CSVLineWriter writer;
	private MockWriter mockWriter;
	
	@Before
	public void setUp () {
		mockWriter = new MockWriter();
		writer = new CSVLineWriter(new PrintWriter(mockWriter));
	}
	
	@Test
	public void testWrite () {
		List<String> myList = new ArrayList<String>();
		myList.add("a");
		myList.add("b");
		writer.write(myList);
		Assert.assertEquals("a" + CSVLineWriter.SEPARATOR+ "b\n", mockWriter.getOutput());
	}
	
	@Test
	public void testWriteSpecialCharacters () {
		List<String> myList = new ArrayList<String>();
		myList.add("yo,yo");
		myList.add("\"hi\"");
		myList.add("sup\nd\"og");
		writer.write(myList);
		Assert.assertEquals(
				"\"yo,yo\"" + CSVLineWriter.SEPARATOR+ "\"\"hi\"\"" + CSVLineWriter.SEPARATOR + "\"sup\nd\"\"og\"\n", 
				mockWriter.getOutput());
	}
	
	@Test
	public void testNull () {
		List<String> myList = new ArrayList<String>();
		myList.add(null);
		writer.write(myList);
		Assert.assertEquals("\n", mockWriter.getOutput());
	}
}
