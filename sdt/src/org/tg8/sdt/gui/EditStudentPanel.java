package org.tg8.sdt.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

class EditStudentPanel extends JPanel implements ActionListener {
	
	static final String CANCEL_STUDENT_INFO = "cancelStudentInfo";
	static final String CANCEL_STUDENT_LIST = "cancelStudentList";
	static final String CHOOSE_STUDENT_FOR_EDIT = "chooseStudentForEdit";
	static final String FIND_STUDENT = "find";
	static final String SAVE_STUDENT_INFO = "saveStudentInfo";
	static final String STUDENT_INFO = "edit";
	static final String STUDENT_LIST = "list";
	static final String FIND_STUDENTS_BY_NAME_FOR_EDIT = "findStudentsByNameForEdit";
	
	private JList studentList;
	
	private JPanel studentInfoPanel;
	private StudentInfoFieldsPanel studentInfoFields;
	private JButton findStudentButton;
	private JPanel findStudentPanel;
	private JPanel findStudentInputs;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JButton saveStudentInfo;
	private JPanel studentListPanel;
	private TG8ListModel<Student> studentListModel;
	
	Student selectedStudent;
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -96596309191239159L;

	EditStudentPanel () {
		super();
		CardLayout layout = new CardLayout();
		this.setLayout(layout);
		this.add(this.getFindStudentPanel(), 
				EditStudentPanel.FIND_STUDENT);
		this.add(this.getStudentListPanel(), EditStudentPanel.STUDENT_LIST);
		this.add(this.getStudentInfoPanel(), STUDENT_INFO);
		
	}
	
	private JPanel getFindStudentPanel () {
		if (this.findStudentPanel == null) {
			JPanel fsp = new JPanel();
			fsp.add(this.getFindStudentInputs());
			
			JPanel findStudentButtons = new JPanel();
			findStudentButtons.add(this.getFindStudentButton());
			fsp.add(findStudentButtons);
			
			this.findStudentPanel = fsp;						
		}
		return this.findStudentPanel;
	}
	
	private JPanel getFindStudentInputs () {
		if (this.findStudentInputs == null) {
			JPanel fsi = new JPanel(new GridLayout( 2, 2));
			fsi.add(new JLabel("First Name "));
			fsi.add(this.getFirstNameTextField());
			fsi.add(new JLabel("Last Name "));
			fsi.add(this.getLastNameTextField());
			
			this.findStudentInputs = fsi;
		}
		return this.findStudentInputs;
	}
	JTextField getFirstNameTextField () {
		if(this.firstNameTextField == null) {
			JTextField fn = new JTextField();
			fn.setActionCommand(EditStudentPanel.FIND_STUDENTS_BY_NAME_FOR_EDIT);
			fn.addActionListener(this);
			this.firstNameTextField = fn;
		}
		return this.firstNameTextField;
	}
	JTextField getLastNameTextField () {
		if(this.lastNameTextField == null) {
			JTextField ln = new JTextField();
			ln.setActionCommand(EditStudentPanel.FIND_STUDENTS_BY_NAME_FOR_EDIT);
			ln.addActionListener(this);
			this.lastNameTextField = ln;
		}
		return this.lastNameTextField;
	}
	private JButton getFindStudentButton () {
		if (this.findStudentButton == null) {
			JButton fs = new JButton("Find Student");
			fs.setActionCommand(FIND_STUDENTS_BY_NAME_FOR_EDIT);
			fs.addActionListener(this);
			this.findStudentButton = fs;
		}
		return this.findStudentButton;
	}
	
	private JPanel getStudentListPanel () {
		if (this.studentListPanel == null) {
			JPanel sl = new JPanel(new BorderLayout());
			JScrollPane studentListPane = new JScrollPane();
			studentListPane.setViewportView(this.getStudentList());
			sl.add(studentListPane, BorderLayout.CENTER);
			
			JPanel slb = new JPanel(); // student list buttons
			JButton chooseStudentButton = new JButton("Choose Student");
			chooseStudentButton.addActionListener(this);
			chooseStudentButton.setActionCommand(EditStudentPanel.CHOOSE_STUDENT_FOR_EDIT);
			slb.add(chooseStudentButton);
			JButton cancelStudentListButton = new JButton("Search again");
			cancelStudentListButton.addActionListener(this);
			cancelStudentListButton.setActionCommand(CANCEL_STUDENT_LIST);
			slb.add(cancelStudentListButton);
			sl.add(slb, BorderLayout.PAGE_END);
			
			this.studentListPanel = sl;
		}
		return this.studentListPanel;
	}
	private JList getStudentList () {
		if (this.studentList == null) {
			JList sl = new JList(this.getStudentListModel());
			sl.setCellRenderer(new StudentListCellRenderer());
			this.studentList = sl;
		}
		return this.studentList;
	}
	TG8ListModel<Student> getStudentListModel () {
		if (this.studentListModel == null) {
			TG8ListModel<Student> lm = new TG8ListModel<Student>();
			this.studentListModel = lm;
		}
		return this.studentListModel;
	}

	
	private JPanel getStudentInfoPanel () {
		if (this.studentInfoPanel == null) {
			JPanel si = new JPanel();
			
			si.add(this.getStudentInfoFields());
			
			JPanel buttons = new JPanel();
			buttons.add(this.getSaveStudentInfo());
			JButton cancel = new JButton("Go back");
			cancel.addActionListener(this);
			cancel.setActionCommand(EditStudentPanel.CANCEL_STUDENT_INFO);
			buttons.add(cancel);
			si.add(buttons);
			
			this.studentInfoPanel = si;
		}
		return this.studentInfoPanel;
	}
	
	StudentInfoFieldsPanel getStudentInfoFields () {
		if (this.studentInfoFields == null) {
			this.studentInfoFields = new StudentInfoFieldsPanel();
		}
		
		return this.studentInfoFields;
	}
	
	JButton getSaveStudentInfo () {
		if (this.saveStudentInfo == null) {
			JButton saveSI = new JButton("Save");
			saveSI.setActionCommand(EditStudentPanel.SAVE_STUDENT_INFO);
			saveSI.addActionListener(this);
			this.saveStudentInfo = saveSI;
		}
		return this.saveStudentInfo;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(EditStudentPanel.CHOOSE_STUDENT_FOR_EDIT)) {
			this.editStudent(((Student)this.getStudentListModel
					().getElementAt(this.getStudentList().getSelectedIndex())));
		}
		else if (e.getActionCommand().equals(EditStudentPanel.SAVE_STUDENT_INFO)) {
			this.getStudentInfoFields().extractStudentInfo(this.selectedStudent);
		}
		else if (e.getActionCommand().equals(EditStudentPanel.CANCEL_STUDENT_INFO)) {
			((CardLayout)this.getLayout()).show(this, EditStudentPanel.STUDENT_LIST);
		}
		else if (e.getActionCommand().equals(CANCEL_STUDENT_LIST)) {
			((CardLayout)this.getLayout()).show(this, EditStudentPanel.FIND_STUDENT);
		}
		else if (e.getActionCommand().equals(FIND_STUDENTS_BY_NAME_FOR_EDIT)) {
			String firstName = this.getFirstNameTextField().getText().trim();
			String lastName = this.getLastNameTextField().getText().trim();
			List<Student> foundStudents =
				SDTDomainLogic.getSDTDomainLogic().findStudentsByNameForEdit(firstName, lastName);
			// logic to check whether there are any results
			if (foundStudents.size() == 0) {
				JOptionPane.showMessageDialog(this, "No students found");
			}
			TG8ListModel<Student> lm = this.getStudentListModel();
			lm.clear();
			for (Student s : foundStudents ) {
				lm.add(s);
			}
			((CardLayout)this.getLayout()).show(this, STUDENT_LIST);
		}
	}
	
	public void editStudent(Student s) {
		this.selectedStudent = s;
		studentInfoFields.populateStudentInfo(s);
		((CardLayout)this.getLayout()).show(this, EditStudentPanel.STUDENT_INFO);
	}
}
