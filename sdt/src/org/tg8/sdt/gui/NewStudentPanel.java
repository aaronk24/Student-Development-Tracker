package org.tg8.sdt.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.tg8.sdt.domain.SDTDomainLogic;
import org.tg8.sdt.domain.Student;

class NewStudentPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 5431538184641514978L;
	private static final String SAVE_STUDENT = "saveStudent";

	private StudentInfoFieldsPanel newStudentFields;
	
	NewStudentPanel () {
		super();
		
		this.add(this.getNewStudentFields());
		
		JPanel buttons = new JPanel();
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(this);
		saveButton.setActionCommand(SAVE_STUDENT);
		buttons.add(saveButton);
		this.add(buttons);
	}

	public StudentInfoFieldsPanel getNewStudentFields() {
		if (this.newStudentFields == null) {
			this.newStudentFields = new StudentInfoFieldsPanel();
		}
		return this.newStudentFields;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals(SAVE_STUDENT)) {
			Student s = SDTDomainLogic.getSDTDomainLogic().getNewStudent();
			this.getNewStudentFields().extractStudentInfo(s);
			this.getNewStudentFields().clearStudentInfo();
		}
		
	}
	
	
}
