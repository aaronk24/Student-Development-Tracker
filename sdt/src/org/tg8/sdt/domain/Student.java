package org.tg8.sdt.domain;

import org.tg8.sdt.data.Gender;
import org.tg8.sdt.data.InternalID;
import org.tg8.sdt.data.SDTDate;

public class Student implements InternalID {

	private SDTDate birthday;
	private String firstName;
	private SDTDate gateBirthday; // Note to self: Perhaps gateBirthday should be derived as first attendance...?
	private String gateId;
	private Gender gender;
	private Integer internalID;
	private String lastName;
	private String middleName;
	private String school;
	
	//TODO new attributes
	private String address;
	private String email;
	private String facebookEmail;
	private String facebookProfile;
	private String otherContactInformation;
	private String note;
	
	// Other desired items:
	// Parental info
	// Picture
	
	public SDTDate getBirthday() {
		return birthday;
	}
	public void setBirthday(SDTDate birthday) {
		this.birthday = birthday;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public SDTDate getGateBirthday() {
		return gateBirthday;
	}
	public void setGateBirthday(SDTDate gateBirthday) {
		this.gateBirthday = gateBirthday;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getGateId() {
		return gateId;
	}
	public void setGateId(String gateId) {
		this.gateId = gateId;
	}
	public Integer getInternalID() {
		return internalID;
	}
	public void setInternalID(Integer internalId) {
		this.internalID = internalId;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFacebookEmail() {
		return facebookEmail;
	}
	public void setFacebookEmail(String facebookEmail) {
		this.facebookEmail = facebookEmail;
	}
	public String getFacebookProfile() {
		return facebookProfile;
	}
	public void setFacebookProfile(String facebookProfile) {
		this.facebookProfile = facebookProfile;
	}
	public String getOtherContactInformation() {
		return otherContactInformation;
	}
	public void setOtherContactInformation(String otherContactInformation) {
		this.otherContactInformation = otherContactInformation;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getAge() {
		int returnValue = this.getBirthday() == null ?
				0 :
				this.getBirthday().getAgeInYears();
		return returnValue;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((internalID == null) ? 0 : internalID.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (internalID == null) {
			if (other.internalID != null)
				return false;
		} else if (!internalID.equals(other.internalID))
			return false;
		return true;
	}
}
