package org.tg8.sdt.domain;

import java.util.ArrayList;
import java.util.List;

import org.tg8.sdt.data.SDTDAO;
import org.tg8.sdt.data.SDTDate;


// This class was moved from the gui package.
// I noticed that many methods in SDTFacade merely delegate to SDTDAO.
// I considered moving all methods to SDTDAO.
// For now, I decided not to move methods to SDTDAO.
// One reason is that I feel like the DAO is complicated enough
// as it is. Another reason is that I feel like the DAO should be "only" 
// data storage, not domain logic. 

public class SDTDomainLogic {
	private static SDTDomainLogic domainLogic; //singleton reference
	
	private SDTDAO dao;
	
	public static void createSDTDomainLogic (SDTDAO dao){
		SDTDomainLogic.domainLogic = new SDTDomainLogic();
		SDTDomainLogic.domainLogic.dao = dao;
	}
	
	public static SDTDomainLogic getSDTDomainLogic() {
		return SDTDomainLogic.domainLogic;
	}
	
	public List<Student> initializeStudentAttendanceList() {
		if (dao.getGateService(new SDTDate()) == null) {
			return new ArrayList<Student>();
		}
		return dao.getStudentsAttendingService(this.getGateService());
	}
	
	// Add's student to today's (system date) attendance list.
	public boolean addStudentToAttendanceList (Student s) {
		GateService gs = this.getGateService();
		
		if (isStudentNotAttendingService(s, gs)) {
			StudentAttendance sa = dao.getNewStudentAttendance();
			sa.setService(gs);
			sa.setStndt(s);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean isStudentNotAttendingService(Student s, GateService gs) {
		List<Student> students = dao.getStudentsAttendingService(gs);
		return !(students.contains(s));
	}
	
	// remove student from today's attendance list
	public void deleteStudentAttendance (Student s) {
		GateService gs = this.getGateService();
		dao.deleteStudentAttendance(s, gs);
	}
	
	private synchronized GateService getGateService () {
		GateService gs;
		SDTDate d = new SDTDate();
		gs = dao.getGateService(d);
		if (gs == null) {
			gs = dao.getNewGateService();
		}
		gs.setServiceDate(d);
		
		return gs;
	}
	
	
	
	public Student getStudent(String gateId) {
		return dao.getStudent(gateId);
	}

	public List<Student> findStudentsByNameForEdit (String firstName, String lastName) {
		return dao.getStudentsByName(firstName, lastName);
	}
	
	public StudentAttendance getMostRecentStudentAttendance (Student s) {
		return dao.getMostRecentStudentAttendance(s);
	}
	
	public StudentAttendance getPreviousMostRecentStudentAttendance (Student s) {
		return dao.getPreviousMostRecentStudentAttendance(s);
	}

	public List<GateService> getAllGateServices () {
		return dao.getAllGateServices();
	}
	public List<Student> getStudentsAttendingService (GateService gs) {
		return dao.getStudentsAttendingService(gs);
	}
	public List<Student> getStudentsNotAttendingService (GateService gs) {
		return dao.getStudentsNotAttendingService(gs);
	}
	public List<StudentContact> getStudentContactsByStudent (Student s) {
		return dao.getStudentContactsByStudent(s);
	}
	public Student getNewStudent () {
		return dao.getNewStudent();
	}
	public StudentContact getNewStudentContact() {
		return dao.getNewStudentContact();
	}
}
