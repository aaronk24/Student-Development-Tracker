package org.tg8.sdt.data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SDTDate implements Comparable<SDTDate> {
	

	private static final DateFormat dateFormatObj = new SimpleDateFormat ("MM/dd/yyyy");
	
	private Date dateValue;
	
	// creates a date set to today
	public SDTDate () {
		this.dateValue = new Date();
		this.dateValue = this.setTimeToMidnight(this.dateValue);
	}
	public SDTDate (String str) throws ParseException {
		if (str != null && ! (str.trim().equals("") ) ) {
			try {
				this.dateValue = SDTDate.dateFormatObj.parse(str);
			}
			catch (ParseException e) {
				this.dateValue = new Date(Long.parseLong(str));
			}
			this.dateValue = this.setTimeToMidnight(this.dateValue);
		}
	}
	
	public int compareTo (SDTDate otherDate) {
		return this.getDateValue().compareTo(otherDate.getDateValue());
	}
	public boolean equals (Object anotherObj) {
		return this.getDateValue().equals( ( (SDTDate)(anotherObj) ).getDateValue() );
	}
	protected Date getDateValue () {
		return this.dateValue;
	}
	public int getAgeInYears () {
		if (this.getDateValue() == null) {
			return 0;
		}
		Calendar thisCal = Calendar.getInstance();
		thisCal.setTime(this.getDateValue());
		Calendar todayCal = Calendar.getInstance();
		todayCal.setTime(new SDTDate().getDateValue() );
		
		int rawDiffInYears = todayCal.get(Calendar.YEAR) - thisCal.get(Calendar.YEAR);
		int diffInYears;
		if (todayCal.get(Calendar.DAY_OF_YEAR) >= thisCal.get(Calendar.DAY_OF_YEAR)) {
			diffInYears = rawDiffInYears;
		}
		else {
			diffInYears = rawDiffInYears - 1;
		}
		return diffInYears;
	}
	
	public int hashCode () {
		return this.getDateValue().hashCode();
	}
	
	protected Date setTimeToMidnight (Date d) {
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(d);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		d.setTime(cal.getTimeInMillis());
		return cal.getTime();
	}
	
	public String toString () {
		String returnVal = 
			this.getDateValue() == null ? "" :
			SDTDate.dateFormatObj.format(this.getDateValue() );
		return returnVal;
	}
}
