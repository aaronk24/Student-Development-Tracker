package org.tg8.sdt.domain;

import org.tg8.sdt.data.InternalID;
import org.tg8.sdt.data.SDTDate;

public class StudentContact implements Comparable<StudentContact>, InternalID {

	private SDTDate contactDate;
	private Integer internalID;
	private String note;
	private Student st;
	
	@Override
	public Integer getInternalID() {
		return internalID;
	}
	@Override
	public void setInternalID(Integer internalID) {
		this.internalID = internalID;		
	}
	public SDTDate getContactDate() {
		return contactDate;
	}
	public void setContactDate(SDTDate contactDate) {
		this.contactDate = contactDate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Student getStudent() {
		return st;
	}
	public void setStudent(Student st) {
		this.st = st;
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
		StudentContact other = (StudentContact) obj;
		if (internalID == null) {
			if (other.internalID != null)
				return false;
		} else if (!internalID.equals(other.internalID))
			return false;
		return true;
	}
	@Override
	/**
	* Note: this class has a natural ordering that is inconsistent with equals.
	*/
	public int compareTo(StudentContact o) {
		return this.getContactDate().compareTo(o.getContactDate());
	}
	
}
