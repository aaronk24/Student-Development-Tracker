package org.tg8.sdt.gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import org.tg8.sdt.domain.Student;

public class StudentListCellRenderer extends DefaultListCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2775704243243121398L;

	//Allow students to be displayed by a JList
	@Override
	public Component getListCellRendererComponent(JList arg0, Object student,
			int arg2, boolean arg3, boolean arg4) {
		Student s = (Student) student;
		String studentDisplay = s.getFirstName() + " " + s.getLastName();
		return super.getListCellRendererComponent(arg0, studentDisplay, arg2, arg3, arg4);
	}

	
	
}
