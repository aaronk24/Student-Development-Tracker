package org.tg8.sdt.data.file;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.tg8.sdt.data.Gender;
import org.tg8.sdt.data.SDTDate;
import org.tg8.sdt.domain.Student;

class StudentTranslator extends SDTTranslator<Student> {

	// How to deal with embedded line breaks?
	// Escape character?
	
	
	@Override
	Student listToObject(List<String> elements) {
		Student s = new Student();
		
		s.setInternalID(Integer.parseInt(elements.get(0)));
		try {
			s.setBirthday(new SDTDate(elements.get(1)));
		}
		catch (ParseException e) {
			throw new RuntimeException(e);
		}
		s.setFirstName(elements.get(2));
		try {
			s.setGateBirthday(new SDTDate((elements.get(3))));
		}
		catch (ParseException e) {
			throw new RuntimeException(e);
		}
		s.setGateId(elements.get(4));
		s.setGender(Gender.getGender(elements.get(5)));
		s.setLastName(elements.get(6));
		s.setMiddleName(elements.get(7));
		s.setSchool(elements.get(8));
		s.setAddress(elements.get(9));
		s.setEmail(elements.get(10));
		s.setFacebookEmail(elements.get(11));
		s.setFacebookProfile(elements.get(12));
		s.setOtherContactInformation(elements.get(13));
		s.setNote(elements.get(14));
		
				
		return s;
	}

	@Override
	List<String> objectToList(Student s) {
		List<String> elementList = new ArrayList<String>();
		
		elementList.add(s.getInternalID().toString());
		elementList.add(s.getBirthday() == null? "" : s.getBirthday().toString());
		elementList.add(s.getFirstName());
		elementList.add(s.getGateBirthday() == null ? "" : s.getGateBirthday().toString());
		elementList.add(s.getGateId());
		elementList.add(s.getGender().toString());
		elementList.add(s.getLastName());
		elementList.add(s.getMiddleName());
		elementList.add(s.getSchool());
		elementList.add(s.getAddress());
		elementList.add(s.getEmail());
		elementList.add(s.getFacebookEmail());
		elementList.add(s.getFacebookProfile());
		elementList.add(s.getOtherContactInformation());
		elementList.add(s.getNote());
		
		
		return elementList;
	}

	
	
}
