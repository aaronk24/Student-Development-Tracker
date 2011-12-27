package org.tg8.sdt.data.file;

import java.util.ArrayList;
import java.util.List;

import org.tg8.sdt.domain.StudentAttendance;

class StudentAttendanceTranslator extends SDTTranslator<StudentAttendance> {

	private SDTDAOImpl dao;
	
	StudentAttendanceTranslator (SDTDAOImpl dao) {
		this.dao = dao;
	}
	
	@Override
	StudentAttendance listToObject(List<String> elements) {
		StudentAttendance sa = new StudentAttendance();
		
		sa.setInternalID(Integer.parseInt(elements.get(0)));
		sa.setStndt(dao.getStudent(Integer.parseInt(elements.get(1))));
		sa.setService(dao.getGateService(Integer.parseInt(elements.get(2))));
		
		return sa;
	}

	@Override
	List<String> objectToList(StudentAttendance sa) {
		List<String> elementList = new ArrayList<String>();
		
		elementList.add(sa.getInternalID().toString());
		elementList.add(sa.getStndt().getInternalID().toString());
		elementList.add(sa.getService().getInternalID().toString());
		
		return elementList;
	}

}
