package org.tg8.sdt.data.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

class BaseFileIOCoordinator<E> {

	protected File myFile;
	protected SDTTranslator<E> translator;
	
	protected File getFile() {
		return myFile;
	}
	protected void setFile(File file) {
		this.myFile = file;
	}
	protected SDTTranslator<E> getTranslator() {
		return translator;
	}
	protected void setTranslator(SDTTranslator<E> translator) {
		this.translator = translator;
	}
	protected List<E> loadFile () throws IOException {
		List<E> l = new ArrayList<E>();
		if (myFile.exists()) {
			CSVReader in = new CSVReader(new BufferedReader(new FileReader(myFile)));
			List<String> record = in.readRecord();
			while(record != null) {
				l.add(translator.listToObject(record));
				record = in.readRecord();
			} 
			in.close();
		}
		return l;
	}
	protected void saveList(List<E> l) throws IOException {
		GenerationFileBackup.copyFileGenerations(myFile.getName(), SDTDAOImpl.TEMP_FILE_SUFFIX, 
				GenerationFileBackup.NBR_GENERATIONS);
		CSVLineWriter out = new CSVLineWriter(new PrintWriter(new BufferedWriter(new FileWriter((myFile)))));
		for(E obj: l) {
			prepareObjectForSave(obj);
			out.write(translator.objectToList(obj));
		}
		out.close();
	}
	protected E prepareObjectForSave (E obj) {
		return obj;
	}
	
}
