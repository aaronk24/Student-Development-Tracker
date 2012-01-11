package org.tg8.sdt.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.tg8.sdt.domain.SDTDomainLogic;
import org.tg8.sdt.domain.Student;
import org.tg8.sdt.domain.StudentAttendance;

public class DisplayStudentPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7483614025317242204L;

	private JLabel age;
	private JLabel barcodeNumber;
	private JLabel birthday;
	private JLabel firstName;
	private JLabel gateBirthday;
	private JLabel gender;
	private JLabel lastName;
	private JLabel middleName;
	private JLabel mostRecentAttendance;
	private JLabel school;
	private JLabel address;
	private JLabel email;
	private JLabel facebookEmail;
	private JLabel facebookProfile;
	private JLabel otherContactInfo;
	private JLabel note;
	
	private PicturePanel picturePanel;
	
	DisplayStudentPanel () {
		this.setLayout(new BorderLayout());
		
		// innerPanel exists because without innerPanel
		// the labels expand to fill the entire available area
		JPanel innerPanel = new JPanel();
		innerPanel.setLayout(new GridLayout(14,1));
		
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		namePanel.add(this.getFirstName());
		namePanel.add(this.getMiddleName());
		namePanel.add(this.getLastName());
		innerPanel.add(namePanel);

		innerPanel.add(this.getGender());
		innerPanel.add(this.getMostRecentAttendance());
		innerPanel.add(this.getGateBirthday());
		
		innerPanel.add(this.getBirthday());
		innerPanel.add(this.getAge());
		innerPanel.add(this.getSchool());
		
		innerPanel.add(this.getAddress());
		innerPanel.add(this.getEmail());
		innerPanel.add(this.getFacebookEmail());
		innerPanel.add(this.getFacebookProfile());
		innerPanel.add(this.getOtherContactInfo());
		innerPanel.add(this.getNote());
		
		this.add(innerPanel, BorderLayout.CENTER);
		// this.add(this.getPicturePanel(), BorderLayout.LINE_START);
	}

	

	JLabel getAge() {
		if (age == null) {
			age = new JLabel();
		}
		return age;
	}
	JLabel getBarcode() {
		if (barcodeNumber == null) {
			barcodeNumber = new JLabel();
		}
		return barcodeNumber;
	}
	
	JLabel getBirthday() {
		if (birthday == null) {
			birthday = new JLabel();
		}
		return birthday;
	}
	JLabel getFirstName() {
		if (firstName == null) {
			firstName = new JLabel();
		}
		return firstName;
	}
	JLabel getGateBirthday() {
		if (gateBirthday == null) {
			gateBirthday = new JLabel();
		}
		return gateBirthday;
	}
	JLabel getGender() {
		if (gender == null) {
			gender = new JLabel();
		}
		return gender;
	}
	JLabel getLastName() {
		if (lastName == null) {
			lastName = new JLabel();
		}
		return lastName;
	}
	JLabel getMiddleName() {
		if (middleName == null) {
			middleName = new JLabel();
		}
		return middleName;
	}
	JLabel getMostRecentAttendance() {
		if (mostRecentAttendance == null) {
			mostRecentAttendance = new JLabel();
		}
		return mostRecentAttendance;
	}
	private PicturePanel getPicturePanel() {
		if (picturePanel == null) {
			this.picturePanel = new PicturePanel();
		}
		return picturePanel;
	}
	JLabel getSchool() {
		if (school == null) {
			school = new JLabel();
		}
		return school;
	}
	JLabel getAddress() {
		if (address == null) {
			address = new JLabel();
		}
		return address;
	}
	JLabel getEmail() {
		if (email == null) {
			email = new JLabel();
		}
		return email;
	}
	JLabel getFacebookEmail() {
		if (facebookEmail == null) {
			facebookEmail = new JLabel();
		}
		return facebookEmail;
	}
	JLabel getFacebookProfile() {
		if (facebookProfile == null) {
			facebookProfile = new JLabel();
		}
		return facebookProfile;
	}
	JLabel getOtherContactInfo() {
		if (otherContactInfo == null) {
			otherContactInfo = new JLabel();
		}
		return otherContactInfo;
	}
	JLabel getNote() {
		if (note == null) {
			note = new JLabel();
		}
		return note;
	}
	
	void displayStudent (Student s) {
		
		displayStudentObject(s);
		
		displayStudentAttendance(s);
	}

	private void displayStudentAttendance(Student s) {
		StudentAttendance sa = SDTDomainLogic.getSDTDomainLogic().getPreviousMostRecentStudentAttendance(s);
		this.getMostRecentAttendance().setText(sa == null ?
				"First time attender"
				: "Most recent attendance before today " + sa.getService().getServiceDate() );
	}

	private void displayStudentObject(Student s) {
		// eventually, need to format all data elements including school
		this.getBarcode().setText(s.getGateId());
		this.getFirstName().setText(s.getFirstName());
		this.getLastName().setText(s.getLastName());
		this.getMiddleName().setText(s.getMiddleName());
		
		//TODO experiment of drawing an image
		if (s.getGateId().equals("1")) {
			BufferedImage picture;
			try {
				picture = ImageIO.read(new File("pictures/josh.jpg"));
				this.getPicturePanel().setPicture(picture);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			
		}
		else {
			this.getPicturePanel().setPicture(null);
		}
		
		this.getGender().setText(s.getGender().toString());
		this.getBirthday().setText("Birthday " + s.getBirthday() );
		this.getAge().setText("Age " + s.getAge());
		this.getGateBirthday().setText("Gate Birthday " + s.getGateBirthday());
		
		this.getAddress().setText("Address " + s.getAddress());
		this.getEmail().setText("Email " + s.getEmail());
		this.getFacebookEmail().setText("Facebook email" + s.getFacebookEmail());
		this.getFacebookProfile().setText("Facebook Profile " + s.getFacebookProfile());
		this.getOtherContactInfo().setText("Other contact info " + s.getOtherContactInformation());
		this.getNote().setText("Note " + s.getNote());
	}
	
	
}
