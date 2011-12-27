package org.tg8.sdt.domain;

import org.tg8.sdt.data.InternalID;

public class StudentAttendance implements InternalID {

	private Integer internalID;
	private Student stndt;
	private GateService service;
	
	public Integer getInternalID() {
		return internalID;
	}
	public void setInternalID(Integer internalId) {
		this.internalID = internalId;
	}
	public Student getStndt() {
		return stndt;
	}
	public void setStndt(Student stndt) {
		this.stndt = stndt;
	}
	public GateService getService() {
		return service;
	}
	public void setService(GateService service) {
		this.service = service;
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
		StudentAttendance other = (StudentAttendance) obj;
		if (internalID == null) {
			if (other.internalID != null)
				return false;
		} else if (!internalID.equals(other.internalID))
			return false;
		return true;
	}
		
}
