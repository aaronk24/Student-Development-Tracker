package org.tg8.sdt.gui;

import java.awt.GridLayout;
import java.text.ParseException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.tg8.sdt.data.Gender;
import org.tg8.sdt.data.SDTDate;
import org.tg8.sdt.domain.Student;

// Reusuable component that contains the editable information
// for a student.
class StudentInfoFieldsPanel extends JPanel {
	private static final long serialVersionUID = -7004035541078481486L;
	
	private JTextField editBirthdayTextField;
	private JTextField editFirstNameTextField;
	private JTextField editGateBirthdayTextField;
	private JComboBox editGenderComboBox;
	private JTextField editLastNameTextField;
	private JTextField editMiddleNameTextField;
	private JComboBox editSchoolComboBox;
	private JTextField barcodeNumberTextField;
	private JTextArea addressTextArea;
	private JTextField emailTextField;
	private JTextField facebookEmailTextField;
	private JTextField facebookProfileTextField;
	private JTextField otherContactInformationTextField;
	private JTextArea noteTextField;

	StudentInfoFieldsPanel () {
		this.setLayout(new GridLayout(14,2));
		this.add(new JLabel("First Name"));
		this.add(this.getEditFirstNameTextField());
		this.add(new JLabel("Middle Name"));
		this.add(this.getEditMiddleNameTextField());
		this.add(new JLabel("Last Name"));
		this.add(this.getEditLastNameTextField());
		this.add(new JLabel("Barcode Number"));
		this.add(this.getBarcodeNumberTextField());
		this.add(new JLabel("Birthday"));
		this.add(this.getEditBirthdayTextField());
		this.add(new JLabel("Gate Birthday"));
		this.add(this.getEditGateBirthdayTextField());
		this.add(new JLabel("Gender"));
		this.add(this.getEditGenderComboBox());
		this.add(new JLabel("School"));
		this.add(this.getEditSchoolComboBox());
		this.add(new JLabel("Address"));
		this.add(this.getAddressTextArea());
		this.add(new JLabel("Email"));
		this.add(this.getEmailTextField());
		this.add(new JLabel("Facebook email"));
		this.add(this.getFacebookEmailTextField());
		this.add(new JLabel("Facebook profile"));
		this.add(this.getFacebookProfileTextField());
		this.add(new JLabel("Other contact info"));
		this.add(this.getOtherContactInformationTextField());
		this.add(new JLabel("Note"));
		this.add(this.getNoteTextField());
	}
	
	JTextField getEditBirthdayTextField () {
		if (this.editBirthdayTextField == null) {
			JTextField b = new JTextField(10);
			this.editBirthdayTextField = b;
		}
		return this.editBirthdayTextField;
	}
	
	JTextField getEditFirstNameTextField () {
		if (this.editFirstNameTextField == null) {
			JTextField fn = new JTextField(15);
			this.editFirstNameTextField = fn;
		}
		
		return this.editFirstNameTextField;
	}
	
	JTextField getEditGateBirthdayTextField () {
		if (this.editGateBirthdayTextField == null) {
			JTextField gb = new JTextField(10);
			this.editGateBirthdayTextField = gb;
		}
		return this.editGateBirthdayTextField;
	}
	
	JComboBox getEditGenderComboBox () {
		if (this.editGenderComboBox == null) {
			JComboBox g = new JComboBox(Gender.GENDER_OPTIONS);
			this.editGenderComboBox = g;
		}
		return this.editGenderComboBox;
	}
	
	JTextField getEditLastNameTextField () {
		if (this.editLastNameTextField == null) {
			JTextField ln = new JTextField(20);
			this.editLastNameTextField = ln; 
		}
		return this.editLastNameTextField;
	}
	
	JTextField getEditMiddleNameTextField () {
		if (this.editMiddleNameTextField == null) {
			JTextField mn = new JTextField(15);
			this.editMiddleNameTextField = mn;
		}
		
		return this.editMiddleNameTextField;
	}
	
	JComboBox getEditSchoolComboBox () {
		if (this.editSchoolComboBox == null) {
			JComboBox s = new JComboBox();
			this.editSchoolComboBox = s;
		}
		return this.editSchoolComboBox;
	}
	
	JTextField getBarcodeNumberTextField () {
		if (this.barcodeNumberTextField == null) {
			JTextField b = new JTextField();
			this.barcodeNumberTextField = b;
		}
		return this.barcodeNumberTextField;
	}
	
	JTextArea getAddressTextArea () {
		if (this.addressTextArea == null) {
			this.addressTextArea = new JTextArea();
		}
		return this.addressTextArea;
	}

	JTextField getEmailTextField () {
		if (this.emailTextField == null) {
			this.emailTextField = new JTextField();
		}
		return this.emailTextField;
	}
	
	JTextField getFacebookEmailTextField () {
		if (this.facebookEmailTextField == null) {
			this.facebookEmailTextField = new JTextField();
		}
		return this.facebookEmailTextField;
	}
	
	JTextField getFacebookProfileTextField () {
		if (this.facebookProfileTextField == null) {
			this.facebookProfileTextField = new JTextField();
		}
		return this.facebookProfileTextField;
	}
	
	JTextField getOtherContactInformationTextField () {
		if (this.otherContactInformationTextField == null) {
			this.otherContactInformationTextField = new JTextField();
		}
		return this.otherContactInformationTextField;
	}
	
	JTextArea getNoteTextField () {
		if (this.noteTextField == null) {
			this.noteTextField = new JTextArea();
		}
		return this.noteTextField;
	}
	
	Student extractStudentInfo(Student stu) {
		// extract student info from gui
		stu.setFirstName(getEditFirstNameTextField().getText());
		stu.setLastName(getEditLastNameTextField().getText());
		try {
			stu.setBirthday(new SDTDate((getEditBirthdayTextField().getText())));
			stu.setGateBirthday(new SDTDate(getEditGateBirthdayTextField().getText()));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		stu.setMiddleName(getEditMiddleNameTextField().getText());
		stu.setGender((Gender)getEditGenderComboBox().getSelectedItem());
		stu.setGateId(getBarcodeNumberTextField().getText().trim());
		stu.setAddress(getAddressTextArea().getText().trim());
		stu.setEmail(getEmailTextField().getText().trim());
		stu.setFacebookEmail(getFacebookEmailTextField().getText().trim());
		stu.setFacebookProfile(getFacebookProfileTextField().getText().trim());
		stu.setOtherContactInformation(getOtherContactInformationTextField().getText().trim());
		stu.setNote(getNoteTextField().getText().trim());
		return stu;
	}

	void populateStudentInfo(Student s) {
		this.getEditFirstNameTextField().setText(s.getFirstName());
		this.getEditMiddleNameTextField().setText(s.getMiddleName());
		this.getEditLastNameTextField().setText(s.getLastName());
		this.getEditBirthdayTextField().setText(s.getBirthday().toString() );
		this.getEditGateBirthdayTextField().setText((s.getGateBirthday().toString() ) );
		this.getEditGenderComboBox().setSelectedItem(s.getGender());
		this.getBarcodeNumberTextField().setText(s.getGateId());
		this.getAddressTextArea().setText(s.getAddress());
		this.getEmailTextField().setText(s.getEmail());
		this.getFacebookEmailTextField().setText(s.getFacebookEmail());
		this.getFacebookProfileTextField().setText(s.getFacebookProfile());
		this.getOtherContactInformationTextField().setText(s.getOtherContactInformation());
		this.getNoteTextField().setText(s.getNote());
	}
	
	void clearStudentInfo() {
		this.getEditFirstNameTextField().setText("");
		this.getEditMiddleNameTextField().setText("");
		this.getEditLastNameTextField().setText("");
		this.getEditBirthdayTextField().setText("");
		this.getEditGateBirthdayTextField().setText("");
		this.getEditGenderComboBox().setSelectedItem(Gender.UNKNOWN);
		this.getBarcodeNumberTextField().setText("");
		this.getAddressTextArea().setText("");
		this.getEmailTextField().setText("");
		this.getFacebookEmailTextField().setText("");
		this.getFacebookProfileTextField().setText("");
		this.getOtherContactInformationTextField().setText("");
		this.getNoteTextField().setText("");
	}
}
