package org.tg8.sdt.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.tg8.sdt.domain.SDTDomainLogic;
import org.tg8.sdt.domain.Student;

class ServiceAttendancePanel extends JPanel implements ActionListener, FocusListener {

	
	private static final String ADD_STUDENT_SERVICE = "addStudentService";
	private static final String DELETE_STUDENT_SERVICE = "deleteStudentService";
	/**
	 * 
	 */
	private static final long serialVersionUID = -5732423015507020315L;
	private JPanel studentInfo;
	private JLabel barcodeLabel;
	private JTextField barcodeText;
	private JPanel currentStudentContainerPanel;
	private JPanel attendingStudentsPanel;
	private TG8ListModel<Student> attendingStudentsListModel;
	private JList attendingStudents;
	private JScrollPane attendingStudentsPane;
	private DisplayStudentPanel currentStudentPanel;
	private JButton deleteStudentAttendanceButton;
	
	private EditStudentListener editStudentListener;
	private JButton editStudentButton;

	/*
	 * Base panel
	 */
	ServiceAttendancePanel () {
		
		super();
		this.setLayout(new BorderLayout());

		this.add(this.getStudentInfoPanel(), BorderLayout.LINE_START);
		this.add(this.getAttendingStudentsPanel(), BorderLayout.LINE_END);
		
		this.add(this.getCurrentStudentContainerPanel(), BorderLayout.CENTER);
		
		List<Student> students = SDTDomainLogic.getSDTDomainLogic().initializeStudentAttendanceList();
		for (Student s : students) {
			this.getAttendingStudentsListModel().add(s);
		}

	}
	
	private JPanel getStudentInfoPanel () {
		if (studentInfo == null){
			studentInfo = new JPanel();
			studentInfo.add(this.getBarcodeLabel());
			studentInfo.add(this.getBarcodeText());
			JButton barcodeButton = new JButton("Add");
			barcodeButton.setActionCommand(ADD_STUDENT_SERVICE);
			barcodeButton.addActionListener(this);
			studentInfo.add(barcodeButton);
		}
		return studentInfo;
	}
	
	private JLabel getBarcodeLabel () {
		if (barcodeLabel == null) {
			barcodeLabel = new JLabel("barcode");
		}
		
		return barcodeLabel;
	}
	
	JTextField getBarcodeText () {
		if (barcodeText == null) { 		
			barcodeText = new JTextField(5);
			//Not sure if this is the best place to add
			//the action listener
			barcodeText.addActionListener(this);
			barcodeText.setActionCommand(ADD_STUDENT_SERVICE);
		}
		return this.barcodeText;
	}
	
	private JPanel getCurrentStudentContainerPanel () {
		if (currentStudentContainerPanel == null) {
			currentStudentContainerPanel = new JPanel();
			currentStudentContainerPanel.add(this.getCurrentStudentPanel());
			editStudentButton = new JButton("Edit Profile");
			editStudentButton.addActionListener(editStudentListener);
			editStudentButton.setEnabled(false);
			currentStudentContainerPanel.add(editStudentButton);
		}
		return currentStudentContainerPanel;
	}
	
	private DisplayStudentPanel getCurrentStudentPanel () {
		if (this.currentStudentPanel == null) {
			this.currentStudentPanel = new DisplayStudentPanel();
		}
		return this.currentStudentPanel;
	}
	
	private JPanel getAttendingStudentsPanel () {
		if (this.attendingStudentsPanel == null) {
			this.attendingStudentsPanel = new JPanel();
			this.attendingStudentsPanel.add(this.getAttendingStudentsPane());
			JPanel deletePanel = new JPanel();
			deletePanel.add(this.getDeleteStudentAttendanceButton());
			this.attendingStudentsPanel.add(deletePanel);
		}
		return this.attendingStudentsPanel;
	}
	TG8ListModel<Student> getAttendingStudentsListModel () {
		if (this.attendingStudentsListModel == null) {
			this.attendingStudentsListModel = new TG8ListModel<Student>();
		}
		return this.attendingStudentsListModel;
	}
	
	private JList getAttendingStudents () {
		if (this.attendingStudents == null) {
			this.attendingStudents = new JList (this.getAttendingStudentsListModel());
			this.attendingStudents.setCellRenderer(new StudentListCellRenderer());
			this.attendingStudents.addFocusListener(this);
			this.attendingStudents.setVisibleRowCount(25);
		}
		return this.attendingStudents;
	}
	
	private JScrollPane getAttendingStudentsPane () {
		if (this.attendingStudentsPane == null) {
			this.attendingStudentsPane = new JScrollPane(this.getAttendingStudents());
			//this.attendingStudentsPane.setPreferredSize(new Dimension(200,500));
		}
		return this.attendingStudentsPane;
	}
	
	private JButton getDeleteStudentAttendanceButton () {
		if (this.deleteStudentAttendanceButton == null) {
			this.deleteStudentAttendanceButton = new JButton("Remove");
			this.deleteStudentAttendanceButton.addActionListener(this);
			this.deleteStudentAttendanceButton.setActionCommand(DELETE_STUDENT_SERVICE);
			this.deleteStudentAttendanceButton.setEnabled(false);
		}
		return this.deleteStudentAttendanceButton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(ADD_STUDENT_SERVICE)) {
			// Check for empty barcode
			// Get student from barcode
			// Check for null student
			// Add student to attendance list
			// Add student to list model
			// display the student
			// clear barcode text
			if (this.getBarcodeText().getText().equals("")) {
				return;
			}
			Student s = SDTDomainLogic.getSDTDomainLogic()
					.getStudent(this.getBarcodeText().getText().trim());
			if (s == null) {
				JOptionPane.showMessageDialog(this, 
						"There is not student with that barcode number.", 
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.getCurrentStudentPanel().displayStudent(s);
			if (SDTDomainLogic.getSDTDomainLogic().addStudentToAttendanceList(s)) {
				this.getAttendingStudentsListModel().add(s);
			}
			this.getBarcodeText().setText("");
			this.editStudentListener.setStudent(s);
			editStudentButton.setEnabled(true);
		}
		else if (e.getActionCommand().equals(DELETE_STUDENT_SERVICE)) {
			this.getDeleteStudentAttendanceButton().setEnabled(false);
			Student selectedStudent = (Student)this.getAttendingStudentsListModel()
					.getElementAt(this.getAttendingStudents().getSelectedIndex()); 
			
			SDTDomainLogic.getSDTDomainLogic().deleteStudentAttendance(selectedStudent);
			this.getAttendingStudentsListModel().remove(selectedStudent);
		}
	}

	// For now, the only component that focus is listened to is
	// the student attendance list
	@Override
	public void focusGained(FocusEvent e) {
		this.getDeleteStudentAttendanceButton().setEnabled(true);
	}

	@Override
	public void focusLost(FocusEvent arg0) {}
	
	void setEditStudentListener (EditStudentListener newEditStudentListener) {
		this.editStudentListener = newEditStudentListener;
		this.editStudentButton.addActionListener(editStudentListener);
	}
	
}
