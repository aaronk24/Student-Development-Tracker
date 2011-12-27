package org.tg8.sdt.data.file;

import java.util.ArrayList;
import java.util.List;

public class IDTranslator extends SDTTranslator<IDRecord> {

	@Override
	List<String> objectToList(IDRecord id) {
		List<String> elementList = new ArrayList<String>();
		
		elementList.add(id.getKey());
		elementList.add(id.getId().toString());
		
		return elementList;
	}

	@Override
	IDRecord listToObject(List<String> elements) {
		IDRecord id = new IDRecord();
		
		id.setKey(elements.get(0));
		id.setId(Integer.parseInt(elements.get(1)));
		
		return id;
	}

}