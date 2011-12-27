package org.tg8.sdt.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;

import org.tg8.sdt.domain.Student;

class EditStudentListener implements ActionListener {

	
	EditStudentPanel editStudentPanel;
	Student student;
	JTabbedPane tabs;
	
	EditStudentListener (EditStudentPanel newEditStudentPanel, JTabbedPane newTabs) {
		this.editStudentPanel = newEditStudentPanel;
		this.tabs = newTabs;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (student != null) {
			tabs.setSelectedComponent(editStudentPanel);
			editStudentPanel.editStudent(student);
		}
	}
	
	void setStudent (Student newStudent) {
		this.student = newStudent;
	}

}
