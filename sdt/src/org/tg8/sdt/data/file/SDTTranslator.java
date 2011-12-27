package org.tg8.sdt.data.file;

import java.util.List;

abstract class SDTTranslator<E> {
	
	static final String SEPARATOR = "|";
	
	abstract E listToObject(List<String> elements);
	
	abstract List<String> objectToList(E obj);
	
	//TODO Update the design of SDTTranslator and child classes,
	// in light of CSVReader and CSVLineWriter
}
