package org.tg8.sdt.data.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.tg8.sdt.data.SDTDAO;
import org.tg8.sdt.data.SDTDate;
import org.tg8.sdt.domain.GateService;
import org.tg8.sdt.domain.Student;
import org.tg8.sdt.domain.StudentAttendance;
import org.tg8.sdt.domain.StudentContact;
import org.tg8.sdt.domain.StudentNameComparator;

public class SDTDAOImpl implements SDTDAO{
	
	//file path is where to store the files.
	//In a future version this may be configurable
	static final String FILE_PATH = "data";	
	static final String STUDENT_FILE = "Student";
	static final String GATE_SERVICE_FILE = "GateService";
	static final String STUDENT_ATTENDANCE_FILE = "StudentAttendance";
	static final String STUDENT_CONTACT_FILE = "StudentContact";
	static final String ID_FILE = "ID";
	//Suffix to distinguish temporary files 
	//Temporary file generations are updated every time
	//the application saves. The intention is to recover
	//from an application crash. 
	//Backup file generations are updated when the application 
	//starts up. The intention is to recover from bad data 
	//being written to the files.
	static final String BACKUP_FILE_SUFFIX = "_backup";
	static final String TEMP_FILE_SUFFIX = "_temp";

	private SDTAutoSave autoSave;
	private List<Student> studentList;
	private FileIOCoordinatorID<StudentAttendance> studentAttendanceFileCoordinator;
	private List<StudentAttendance> studentAttendanceList;
	private List<StudentContact> studentContactList;
	private FileIOCoordinatorID<Student> studentFileCoordinator;
	private FileIOCoordinatorID<StudentContact> studentContactFileCoordinator;
	private FileIOCoordinatorID<GateService> gateServiceFileCoordinator;
	private List<GateService> gateServiceList;
	private BaseFileIOCoordinator<IDRecord> idFileCoordinator;
	private IDGenerator idGenerator;
	private List<IDRecord> idList;

	// If the app becomes more complex, it may 
	// be necessary to make the constructor package
	// access and use a Factory class.
	public SDTDAOImpl () throws IOException {
		// load data
		this.loadAllFiles();
		
		// make backup files
		GenerationFileBackup.backupFiles(SDTDAOImpl.BACKUP_FILE_SUFFIX);		
		
		// set up auto save thread
		this.autoSave = new SDTAutoSave(this);
		new Thread(this.autoSave).start();
	}
	
	@Override
	public GateService getGateService(SDTDate d) {
		GateService current;
		boolean foundService = false;
		Iterator<GateService> i = this.gateServiceList.iterator();
		GateService returnVal = null;
		while (i.hasNext() && !foundService){
			current = i.next();
			if (current.getServiceDate().equals(d)) {
				returnVal = current;
				foundService = true;
			}
		}
		
		return returnVal;
	}
	
	public GateService getNewGateService () {
		GateService gs = new GateService();
		gs.setInternalID(this.idGenerator.getId(GateService.class.getName()));
		this.gateServiceList.add(gs);
		return gs;
	}

	@Override
	public List<Student> getStudents() {
		return this.studentList;
	}	
	
	@Override
	public List<Student> getStudentsByName(String firstName, String lastName) {
		Student currentStudent;
		Iterator<Student> i = this.studentList.iterator();
		List<Student> returnList = new ArrayList<Student>();
		
		while (i.hasNext()) {
			currentStudent = i.next();
			if(currentStudent.getFirstName().length() >= firstName.length() && 
					currentStudent.getFirstName().substring(0, firstName.length()).compareToIgnoreCase(firstName) == 0 &&
					currentStudent.getLastName().length() >= lastName.length() &&
					currentStudent.getLastName().substring(0, lastName.length()).compareToIgnoreCase(lastName) == 0) {
				
				returnList.add(currentStudent);
			}
		}
		Collections.sort(returnList, new StudentNameComparator());
		
		return returnList;
 	}

	@Override
	public Student getStudent(String gateId) {
		Student current;
		boolean foundStudent = false;
		Iterator<Student> i = this.studentList.iterator();
		Student returnVal = null;
		
		while (i.hasNext() && !foundStudent) {
			current = i.next();
			if (current.getGateId().equals(gateId)) {
				returnVal = current;
				foundStudent = true;
			}
		}
		return returnVal;
	}
	
	@Override
	public Student getNewStudent() {
		Student s = new Student();
		s.setInternalID(this.idGenerator.getId(Student.class.getName()));
		this.studentList.add(s);
		return s;
	}
	
	public StudentAttendance getNewStudentAttendance () {
		StudentAttendance sa = new StudentAttendance();
		sa.setInternalID(this.idGenerator.getId(StudentAttendance.class.getName()));
		this.studentAttendanceList.add(sa);		
		return sa;
	}
	
	@Override
	public List<Student> getStudentsAttendingService(GateService gs) {
		List<Student> students = new ArrayList<Student>();
		
		for (StudentAttendance sa : this.studentAttendanceList) {
			if (sa.getService().equals(gs)) {
				for (Student s : this.studentList) {
					if (sa.getStndt().equals(s)) {
						students.add(s);
					}
				}
			}
		}
		Collections.sort(students, new StudentNameComparator());
		
		return students;
	}
	
	@Override
	public List<Student> getStudentsNotAttendingService(GateService gs) {
		List<Student> attendingStudents = this.getStudentsAttendingService(gs);
		List<Student> notAttendingStudents = new ArrayList<Student>();
		for (Student s: this.studentList) {
			if (!attendingStudents.contains(s)) {
				notAttendingStudents.add(s);
			}
		}
		Collections.sort(notAttendingStudents, new StudentNameComparator());
		return notAttendingStudents;
	}

	@Override
	public StudentAttendance getMostRecentStudentAttendance(Student s) {
		
		StudentAttendance mostRecentAttendance = null;
		
		for (StudentAttendance sa : this.studentAttendanceList) {
			if (sa.getStndt().equals(s)) {
				if (mostRecentAttendance == null || 
						mostRecentAttendance.getService().getServiceDate().
						compareTo(sa.getService().getServiceDate()) < 0 ) {
					mostRecentAttendance = sa;
				}
			}
		}
		return mostRecentAttendance;
	}
	
	
	@Override
	public StudentAttendance getPreviousMostRecentStudentAttendance(Student s) {
		
		StudentAttendance mostRecentPreviousAttendance = null;
		SDTDate todayDate = new SDTDate();		
		
		for (StudentAttendance sa : this.studentAttendanceList) {
			if (sa.getStndt().equals(s)) {
				if (mostRecentPreviousAttendance == null || 
						( mostRecentPreviousAttendance.getService().getServiceDate().
						compareTo(sa.getService().getServiceDate()) < 0 
						&& !sa.getService().getServiceDate().equals(todayDate))) {
					mostRecentPreviousAttendance = sa;
				}
			}
		}
		return mostRecentPreviousAttendance;
	}
	
	@Override
	public List<GateService> getAllGateServices() {
		Collections.sort(this.gateServiceList, Collections.reverseOrder());
		return this.gateServiceList;
	}

	@Override
	public void destroy() {
		//safely stop autosave thread
		this.autoSave.setKeepRunning(false);
		
		try {
			this.save();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	Student getStudent(Integer internalId) {
		Student current;
		boolean foundStudent = false;
		Iterator<Student> i = this.studentList.iterator();
		Student returnVal = null;
		
		while (i.hasNext() && !foundStudent) {
			current = i.next();
			if (current.getInternalID().equals(internalId)) {
				returnVal = current;
				foundStudent = true;
			}
		}
		return returnVal;
	}
	
	GateService getGateService(Integer internalId) {
		GateService current;
		boolean foundService = false;
		Iterator<GateService> i = this.gateServiceList.iterator();
		GateService returnVal = null;
		while (i.hasNext() && !foundService){
			current = i.next();
			if (current.getInternalID().equals(internalId)) {
				returnVal = current;
				foundService = true;
			}
		}
		
		return returnVal;
	}
	@Override
	public List<StudentContact> getStudentContactsByStudent(Student s) {
		List<StudentContact> scl = new ArrayList<StudentContact>();
		for (StudentContact sc: this.studentContactList) {
			if (s.equals(sc.getStudent())) {
				scl.add(sc);
			}
		}
		Collections.sort(scl, Collections.reverseOrder());
		return scl;
	}
	@Override
	public StudentContact getNewStudentContact() {
		StudentContact sc = new StudentContact();
		sc.setInternalID(this.idGenerator.getId(StudentContact.class.getName()));
		this.studentContactList.add(sc);
		return sc;
	}
	@Override
	public void deleteStudentAttendance(Student s, GateService gs) {

		for (StudentAttendance sa : this.studentAttendanceList) {
			if (sa.getStndt().equals(s) && sa.getService().equals(gs)) {
				this.studentAttendanceList.remove(sa);
				break;
			}
		}
	}
	private void loadAllFiles () throws IOException {
		this.idFileCoordinator = new BaseFileIOCoordinator<IDRecord>();
		this.idFileCoordinator.setFile(new File(SDTDAOImpl.FILE_PATH + File.separator + 
				SDTDAOImpl.ID_FILE));
		this.idFileCoordinator.setTranslator(new IDTranslator());
		this.idList = this.idFileCoordinator.loadFile();
		Map<String, IDRecord> idMap = new HashMap<String, IDRecord>();
		for(IDRecord rec: this.idList) {
			idMap.put(rec.getKey(), rec);
		}
		this.idGenerator = new IDGenerator(idMap);
		
		this.studentFileCoordinator = new FileIOCoordinatorID<Student>();
		this.studentFileCoordinator.setFile(new File(SDTDAOImpl.FILE_PATH + File.separator + SDTDAOImpl.STUDENT_FILE));
		this.studentFileCoordinator.setTranslator(new StudentTranslator());
		this.studentFileCoordinator.setIdGen(this.idGenerator);
		this.studentList = this.studentFileCoordinator.loadFile();
		
		this.gateServiceFileCoordinator = new FileIOCoordinatorID<GateService>();
		this.gateServiceFileCoordinator.setFile(new File(SDTDAOImpl.FILE_PATH + File.separator 
				+ SDTDAOImpl.GATE_SERVICE_FILE));
		this.gateServiceFileCoordinator.setTranslator(new GateServiceTranslator());
		this.gateServiceFileCoordinator.setIdGen(this.idGenerator);
		this.gateServiceList = this.gateServiceFileCoordinator.loadFile();
		
		this.studentAttendanceFileCoordinator = new FileIOCoordinatorID<StudentAttendance>();
		this.studentAttendanceFileCoordinator.setFile(new File(SDTDAOImpl.FILE_PATH + File.separator + 
				SDTDAOImpl.STUDENT_ATTENDANCE_FILE));
		this.studentAttendanceFileCoordinator.setTranslator(new StudentAttendanceTranslator(this));
		this.studentAttendanceFileCoordinator.setIdGen(this.idGenerator);
		this.studentAttendanceList = this.studentAttendanceFileCoordinator.loadFile();
		
		this.studentContactFileCoordinator = new FileIOCoordinatorID<StudentContact>();
		this.studentContactFileCoordinator.setFile(new File(SDTDAOImpl.FILE_PATH + File.separator + 
				SDTDAOImpl.STUDENT_CONTACT_FILE));
		this.studentContactFileCoordinator.setTranslator(new StudentContactTranslator(this));
		this.studentContactFileCoordinator.setIdGen(this.idGenerator);
		this.studentContactList = this.studentContactFileCoordinator.loadFile();
	}

	@Override
	public void save() throws IOException {
		this.studentFileCoordinator.saveList(this.studentList);
		this.gateServiceFileCoordinator.saveList(this.gateServiceList);
		this.studentAttendanceFileCoordinator.saveList(this.studentAttendanceList);
		this.studentContactFileCoordinator.saveList(this.studentContactList);
		this.idFileCoordinator.saveList(this.idList);
	}

}
