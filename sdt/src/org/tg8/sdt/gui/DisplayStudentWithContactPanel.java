package org.tg8.sdt.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.tg8.sdt.domain.Student;

public class DisplayStudentWithContactPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1690998051528815649L;
	private DisplayStudentPanel displayStudent;
	private NewStudentContactPanel newStudentContact;
	private StudentContactListPanel studentContactList;

	public DisplayStudentWithContactPanel() {
		displayStudent = new DisplayStudentPanel();
		this.add(displayStudent, BorderLayout.LINE_START);
		
		this.studentContactList = new StudentContactListPanel();
		this.add(studentContactList, BorderLayout.CENTER);
		
		this.newStudentContact = new NewStudentContactPanel();
		this.add(newStudentContact, BorderLayout.LINE_END);
		this.newStudentContact.setStudentContactListPanel(this.studentContactList);	
	}
	
	void displayStudent(Student s) {
		displayStudent.displayStudent(s);
		this.displayStudentContacts(s);
	}

	private void displayStudentContacts(Student s) {
		this.studentContactList.loadStudentData(s);
		this.newStudentContact.setStudent(s);
		
	}

}
