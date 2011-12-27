package org.tg8.sdt.gui;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.tg8.sdt.domain.SDTDomainLogic;
import org.tg8.sdt.domain.Student;
import org.tg8.sdt.domain.StudentContact;

class StudentContactListPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7140527752847520650L;
	
	public StudentContactListPanel() {
		super();
	}
	
	public void loadStudentData(Student s) {
		List<StudentContact> scl = SDTDomainLogic.getSDTDomainLogic().getStudentContactsByStudent(s);
		this.removeAll();
		
		JPanel gridPanel = new JPanel(new GridLayout(scl.size(), 1));
		for (StudentContact sc: scl) {
			gridPanel.add(this.createStudentContactPanel(sc) );
		}
		this.add(gridPanel);
		this.invalidate();
	}
	
	private JPanel createStudentContactPanel (StudentContact sc) {
		JPanel scPanel = new JPanel();
		JLabel dateLabel = new JLabel(sc.getContactDate().toString());
		JLabel noteLabel = new JLabel(sc.getNote());
		scPanel.add(dateLabel);
		scPanel.add(noteLabel);
		return scPanel;
	}
}
