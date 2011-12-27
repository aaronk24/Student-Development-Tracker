package org.tg8.sdt.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class SDTFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7623997642385080034L;

	private EditStudentPanel editStudent;
	private ServiceAttendancePanel serviceAttendance;
	
	ServiceAttendancePanel getServiceAttendance() {
		return serviceAttendance;
	}
	
	EditStudentPanel getEditStudent() {
		return editStudent;
	}

	public SDTFrame (String title){
		super(title);
		
		this.serviceAttendance= new ServiceAttendancePanel(); 
		this.editStudent = new EditStudentPanel();
		
		JTabbedPane tabs = new JTabbedPane();
		tabs.addTab("Attendance", this.serviceAttendance);
		tabs.add("Student Profiles", this.editStudent);
		tabs.add("New Student", new NewStudentPanel());
		tabs.add("VIP", new VIPPanel());
		tabs.add("Past Attendance", new PastAttendancePanel());
		
		this.serviceAttendance.setEditStudentListener(new EditStudentListener(editStudent, tabs));
		
		this.getContentPane().add(tabs);
		this.setSize(new Dimension(1152, 720));
	}
	
	
	

}
