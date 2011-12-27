package org.tg8.sdt.data.file;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.tg8.sdt.data.SDTDate;
import org.tg8.sdt.domain.GateService;

class GateServiceTranslator extends SDTTranslator<GateService> {

	@Override
	List<String> objectToList(GateService g) {
		List <String>elementList = new ArrayList<String>();
		
		elementList.add(g.getInternalID().toString());
		elementList.add(g.getServiceDate().toString());
		
		return elementList;
	}

	@Override
	GateService listToObject(List<String> elements) {
		GateService g = new GateService();
		
		g.setInternalID(Integer.parseInt(elements.get(0)));
		try {
			g.setServiceDate(new SDTDate(elements.get(1)) );
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
				
		return g;
	}

}
