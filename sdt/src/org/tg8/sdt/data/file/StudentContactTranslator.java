package org.tg8.sdt.data.file;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.tg8.sdt.data.SDTDate;
import org.tg8.sdt.domain.StudentContact;

public class StudentContactTranslator extends SDTTranslator<StudentContact> {

	private SDTDAOImpl dao;
	
	StudentContactTranslator (SDTDAOImpl dao) {
		this.dao = dao;
	}
	
	@Override
	StudentContact listToObject(List<String> elements) {
		StudentContact sc = new StudentContact();
		
		try {
			sc.setInternalID(Integer.parseInt(elements.get(0)));
			sc.setContactDate(new SDTDate(elements.get(1)) );
			sc.setStudent(dao.getStudent(Integer.parseInt(elements.get(3))));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		sc.setNote(elements.get(2));
		
		return sc;
	}

	@Override
	List<String> objectToList(StudentContact sc) {
		List<String> elementList = new ArrayList<String>();
		
		elementList.add(sc.getInternalID().toString());
		elementList.add(sc.getContactDate().toString());
		elementList.add(sc.getNote());
		elementList.add(sc.getStudent().getInternalID().toString());
		
		
		return elementList;
	}

}
