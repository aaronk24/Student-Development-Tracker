package org.tg8.sdt.data;

import java.io.IOException;
import java.util.List;

import org.tg8.sdt.domain.GateService;
import org.tg8.sdt.domain.Student;
import org.tg8.sdt.domain.StudentAttendance;
import org.tg8.sdt.domain.StudentContact;

public interface SDTDAO {
	
	public void deleteStudentAttendance(Student s, GateService gs);
	
	public void destroy();
	
	public List<GateService> getAllGateServices();
	
	public GateService getGateService(SDTDate d);
	
	public StudentAttendance getMostRecentStudentAttendance(Student s);
	
	public GateService getNewGateService ();
	
	public Student getNewStudent();
	
	public StudentAttendance getNewStudentAttendance();
	
	public StudentContact getNewStudentContact();
	
	public StudentAttendance getPreviousMostRecentStudentAttendance(Student s);
	
	public Student getStudent(String gateId);
	
	public List<Student> getStudents();
	
	public List<Student> getStudentsAttendingService(GateService gs);
	
	public List<Student> getStudentsByName(String firstName, String lastName);
	
	public List<StudentContact> getStudentContactsByStudent(Student s);
	
	public List<Student> getStudentsNotAttendingService(GateService gs);
	
	public void save() throws IOException;
}
