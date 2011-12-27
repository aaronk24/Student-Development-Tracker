package org.tg8.sdt.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.tg8.sdt.data.SDTDate;
import org.tg8.sdt.domain.SDTDomainLogic;
import org.tg8.sdt.domain.Student;
import org.tg8.sdt.domain.StudentContact;

class NewStudentContactPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1386324225881141987L;
	private static final String NEW_CONTACT = "newContact";
	
	private JButton addButton;
	private JTextField contactDateField;
	private JTextArea contactNoteField;
	private Student stu;
	private StudentContactListPanel lp;
	
	NewStudentContactPanel () {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		JLabel dateLabel = new JLabel("Date");
		this.contactDateField = new JTextField(10);
		JLabel contactNoteLabel = new JLabel("Note");
		this.contactNoteField = new JTextArea(2, 30);
		
		addButton = new JButton("Save contact");
		addButton.addActionListener(this);
		addButton.setActionCommand(NEW_CONTACT);
		
		this.add(dateLabel);
		this.add(contactDateField);
		this.add(contactNoteLabel);
		this.add(contactNoteField);
		this.add(addButton);
	}
	void setStudent(Student s) {
		this.stu = s;
	}
	void setStudentContactListPanel (StudentContactListPanel sclp) {
		this.lp = sclp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(NEW_CONTACT)) {
			StudentContact sc = SDTDomainLogic.getSDTDomainLogic().getNewStudentContact();
			try {
				sc.setContactDate(new SDTDate(this.contactDateField.getText()));
			} catch (ParseException e1) {
				throw new RuntimeException(e1);
			}
			sc.setNote(this.contactNoteField.getText());
			sc.setStudent(this.stu);
			
			this.lp.loadStudentData(stu);
		}
	}

	
}
