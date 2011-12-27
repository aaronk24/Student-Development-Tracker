package org.tg8.sdt.domain;

import org.tg8.sdt.data.InternalID;
import org.tg8.sdt.data.SDTDate;

public class GateService implements Comparable<GateService>, InternalID {

	private Integer internalID;
	private SDTDate serviceDate;
	
	public Integer getInternalID() {
		return internalID;
	}
	public void setInternalID(Integer internalID) {
		this.internalID = internalID;
	}
	public SDTDate getServiceDate() {
		return serviceDate;
	}
	public void setServiceDate(SDTDate serviceDate) {
		this.serviceDate = serviceDate;
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
		GateService other = (GateService) obj;
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
	public int compareTo(GateService o) {
		return this.getServiceDate().compareTo(o.getServiceDate());
		/*
		 * 2011-04-26 AAK I implemented a comparison a while ago.
		 * I felt clever until I realized that I could re-use the 
		 * Date compareTo method :) 
		long difference = this.getServiceDate().getTime() - o.getServiceDate().getTime();
		int returnVal;
		// The return type for compareTo is int, but dates store their time offset
		// value as a long.
		// Must check whether the difference is a valid int value.
		// If difference is either too high or too low,
		// must set to a valid int value.
		if (difference > (long) Integer.MAX_VALUE) {
			returnVal = Integer.MAX_VALUE;
		}
		else if (difference < (long) Integer.MIN_VALUE) {
			returnVal = Integer.MIN_VALUE;
		}
		else {
			returnVal = (int) difference;
		}
		return returnVal;
		*/
	}
	
	
}
