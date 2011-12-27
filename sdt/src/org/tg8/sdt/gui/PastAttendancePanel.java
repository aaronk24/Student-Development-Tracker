package org.tg8.sdt.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.tg8.sdt.domain.GateService;
import org.tg8.sdt.domain.SDTDomainLogic;
import org.tg8.sdt.domain.Student;

public class PastAttendancePanel extends JPanel implements ActionListener, ListSelectionListener {

	private static final String BACK_TO_SERVICE_LIST = "backToServiceList";
	private static final String CHOOSE_SERVICE = "chooseService";
	private static final String SERVICE_LIST = "serviceList";
	private static final String ATTENDANCE_FOR_SERVICE = "attendanceForService";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5624575270590658316L;

	private JList serviceList;
	private TG8ListModel<GateService> serviceListModel;
	private JLabel numberStudentsAttendingLabel;
	private JLabel numberStudentsNotAttendingLabel;
	private JList studentsAttendingList;
	private TG8ListModel<Student> studentsAttendingListModel;
	private JList studentsNotAttendingList;
	private TG8ListModel<Student> studentsNotAttendingListModel;
	private DisplayStudentWithContactPanel selectedStudentPanel;
	
	// 2011-06-17 AAK I considered breaking up some methods into 
	// smaller methods. Particularly, the constructor, actionPerformed, 
	// and valueChanged. 
	// For now, I decided not to break up the methods. Neither option
	// (refactor or keep as is) seems clearly superior. 
	
	PastAttendancePanel () {
		super();
		
		CardLayout layout = new CardLayout();
		this.setLayout(layout);
		
		JPanel serviceListContainerPanel = new JPanel(new BorderLayout());
		
		JPanel serviceListPanel = new JPanel();
		JScrollPane serviceListPane = new JScrollPane();
		serviceListPane.setViewportView(this.getServiceList());
		serviceListPanel.add(serviceListPane);
		serviceListContainerPanel.add(serviceListPanel, BorderLayout.PAGE_START);
		
		JPanel serviceListButtons = new JPanel();
		JButton chooseServiceButton = new JButton("Choose Service");
		chooseServiceButton.addActionListener(this);
		chooseServiceButton.setActionCommand(CHOOSE_SERVICE);
		serviceListButtons.add(chooseServiceButton);
		serviceListContainerPanel.add(serviceListButtons, BorderLayout.CENTER);
		
		JPanel attendanceForServiceContainerPanel = new JPanel(new BorderLayout());
		JPanel studentListContainerPanel = new JPanel(new BorderLayout());
		
		// for now, I am adding the studentsAttendingList
		// and the StudentsNotAttendingList to the 
		// studentListPanel.
		// I do not know what should be the final layout
		// 2010-12-18
		JPanel studentListPanel = new JPanel();
		studentListPanel.setLayout(new GridLayout(1, 2));
		 
		JPanel studentsAttendingPanel = new JPanel(new BorderLayout());
		
		JPanel studentsAttendingLabelPanel = new JPanel();
		studentsAttendingLabelPanel.add(new JLabel("Students who attended"));
		studentsAttendingLabelPanel.add(this.getNumberStudentsAttendingLabel());
		
		studentsAttendingPanel.add(studentsAttendingLabelPanel, BorderLayout.PAGE_START);
		studentsAttendingPanel.add(new JScrollPane(this.getStudentsAttendingList()), BorderLayout.CENTER);
		
		JPanel studentsNotAttendingPanel = new JPanel(new BorderLayout());
		
		JPanel studentsNotAttendingLabelPanel = new JPanel();
		studentsNotAttendingLabelPanel.add(new JLabel("Students who missed"));
		studentsNotAttendingLabelPanel.add(this.getNumberStudentsNotAttendingLabel());
		
		studentsNotAttendingPanel.add(studentsNotAttendingLabelPanel, BorderLayout.PAGE_START);
		studentsNotAttendingPanel.add(new JScrollPane(this.getStudentsNotAttendingList()), BorderLayout.CENTER);
		
		studentListPanel.add(studentsAttendingPanel);
		studentListPanel.add(studentsNotAttendingPanel);
		
		studentListContainerPanel.add(studentListPanel, BorderLayout.CENTER);
		studentListContainerPanel.add(this.getSelectedStudentPanel(), BorderLayout.PAGE_END);
		
		attendanceForServiceContainerPanel.add(studentListContainerPanel, BorderLayout.CENTER);
		
		JPanel attendanceForServiceButtons = new JPanel();
		JButton backToServiceList = new JButton("Back to service list");
		backToServiceList.addActionListener(this);
		backToServiceList.setActionCommand(BACK_TO_SERVICE_LIST);
		attendanceForServiceButtons.add(backToServiceList);
		attendanceForServiceContainerPanel.add(attendanceForServiceButtons, BorderLayout.PAGE_END);
		
		
		this.add(serviceListContainerPanel, SERVICE_LIST);
		this.add(attendanceForServiceContainerPanel, ATTENDANCE_FOR_SERVICE);
		
		this.populateServiceList();
	}
	
	private JList getServiceList () {
		if (this.serviceList == null) {
			JList sl = new JList(this.getServiceListModel());
			sl.setCellRenderer(new ServiceListCellRenderer());
			this.serviceList = sl;
		}
		return this.serviceList;
	}
	private TG8ListModel<GateService> getServiceListModel () {
		if (this.serviceListModel == null) {
			this.serviceListModel = new TG8ListModel<GateService>();
		}
		return this.serviceListModel;
	}
	
	private void populateServiceList () {
		this.getServiceListModel().clear();
		for (GateService gs : SDTDomainLogic.getSDTDomainLogic().getAllGateServices()) {
			this.getServiceListModel().add(gs);
		}
	}
	private JLabel getNumberStudentsAttendingLabel() {
		if (this.numberStudentsAttendingLabel == null) {
			this.numberStudentsAttendingLabel = new JLabel("()");
		}
		return this.numberStudentsAttendingLabel;
	}
	private JLabel getNumberStudentsNotAttendingLabel() {
		if (this.numberStudentsNotAttendingLabel == null) {
			this.numberStudentsNotAttendingLabel = new JLabel("()");
		}
		return this.numberStudentsNotAttendingLabel;
	}
	private JList getStudentsAttendingList () {
		if (this.studentsAttendingList == null) {
			JList sal = new JList(this.getStudentsAttendingListModel());
			sal.setCellRenderer(new StudentListCellRenderer());
			sal.addListSelectionListener(this);
			this.studentsAttendingList = sal;
		}
		return this.studentsAttendingList;
		
	}
	private TG8ListModel<Student> getStudentsAttendingListModel () {
		if (this.studentsAttendingListModel == null) {
			this.studentsAttendingListModel = new TG8ListModel<Student>();
		}
		return this.studentsAttendingListModel;
	}
	private JList getStudentsNotAttendingList () {
		if (this.studentsNotAttendingList == null) {
			JList snal = new JList(this.getStudentsNotAttendingListModel());
			snal.setCellRenderer(new StudentListCellRenderer());
			snal.addListSelectionListener(this);
			this.studentsNotAttendingList = snal;
		}
		return this.studentsNotAttendingList;
		
	}
	private TG8ListModel<Student> getStudentsNotAttendingListModel () {
		if (this.studentsNotAttendingListModel == null) {
			this.studentsNotAttendingListModel = new TG8ListModel<Student>();
		}
		return this.studentsNotAttendingListModel;
	}
	private DisplayStudentWithContactPanel getSelectedStudentPanel() {
		if (this.selectedStudentPanel == null) {
			this.selectedStudentPanel = new DisplayStudentWithContactPanel();
		}
		return this.selectedStudentPanel;
	}
	
	private void setNumberStudentsAttending(int nbr) {
		this.getNumberStudentsAttendingLabel().setText("(" + nbr + ")");
	}
	private void setNumberStudentsNotAttending(int nbr) {
		this.getNumberStudentsNotAttendingLabel().setText("(" + nbr + ")");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(CHOOSE_SERVICE)) {
			
			// clear out any students from previously viewed
			// services
			this.getStudentsAttendingListModel().clear();
			this.getStudentsNotAttendingListModel().clear();
			
			GateService selectedService = (GateService) this.getServiceList().getSelectedValue();
			
			this.getStudentsAttendingListModel().addAll(
					SDTDomainLogic.getSDTDomainLogic().getStudentsAttendingService(selectedService));
			this.setNumberStudentsAttending(
					SDTDomainLogic.getSDTDomainLogic().getStudentsAttendingService(selectedService).size());
			
			this.getStudentsNotAttendingListModel().addAll(
					SDTDomainLogic.getSDTDomainLogic().getStudentsNotAttendingService(selectedService));
			this.setNumberStudentsNotAttending(
					SDTDomainLogic.getSDTDomainLogic().getStudentsNotAttendingService(selectedService).size());
			
			((CardLayout)this.getLayout()).show(this, ATTENDANCE_FOR_SERVICE);
		}
		else if(e.getActionCommand().equals(BACK_TO_SERVICE_LIST)) {
			((CardLayout)this.getLayout()).show(this, SERVICE_LIST);
		}
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		// if nothing selected, return.
		// most likely, nothing would be selected because
		// the list is cleared. This check was added to prevent
		// null pointer exceptions.
		if (((JList)(e.getSource())).getSelectedValue() == null) {
			return;
		}
		
		// Listens to Student Lists
		this.getSelectedStudentPanel().displayStudent(
				(Student)((JList)(e.getSource())).getSelectedValue());
		
		// clears selection from the list that wasn't selected
		if (e.getSource().equals(this.getStudentsAttendingList())) {
			this.getStudentsNotAttendingList().clearSelection();
		}
		else if(e.getSource().equals(this.getStudentsNotAttendingList())) {
			this.getStudentsAttendingList().clearSelection();
		}
	}
	
	
	
	
}
