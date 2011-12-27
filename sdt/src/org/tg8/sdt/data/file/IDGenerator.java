package org.tg8.sdt.data.file;

import java.util.Map;

class IDGenerator {
	
	private Map<String, IDRecord> idMap;

	IDGenerator(Map<String, IDRecord> idMap) {
		this.idMap = idMap;
	}
	
	synchronized int getId(String key) {
		
		IDRecord keyRecord = this.idMap.get(key);
		
		int oldId = keyRecord.getId();
		keyRecord.setId(oldId + 1);
		
		return oldId;
	}
}
