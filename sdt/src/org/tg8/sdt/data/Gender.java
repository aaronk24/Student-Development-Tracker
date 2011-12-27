package org.tg8.sdt.data;

public class Gender {
	private String genderVal;
	
	public static final Gender FEMALE;
	public static final Gender MALE;
	public static final Gender UNKNOWN;
	public static final String FEMALE_STRING = "Female";
	public static final String MALE_STRING = "Male";
	public static final String UNKNOWN_STRING = "Unknown";
	public static final Gender[] GENDER_OPTIONS;
	
	static {
		FEMALE = new Gender(FEMALE_STRING);
		MALE = new Gender(MALE_STRING);
		UNKNOWN = new Gender(UNKNOWN_STRING);
		GENDER_OPTIONS = new Gender[3];
		GENDER_OPTIONS[0] = Gender.UNKNOWN;
		GENDER_OPTIONS[1] = Gender.FEMALE;
		GENDER_OPTIONS[2] = Gender.MALE;
	}
	
	public static Gender getGender (String val) {
		if (val.equals(Gender.FEMALE_STRING)) {
			return Gender.FEMALE;
		}
		if (val.equals(Gender.MALE_STRING)) {
			return Gender.MALE;
		}
		if (val.equals(Gender.UNKNOWN_STRING)) {
			return Gender.UNKNOWN;
		}
		else {
			return Gender.UNKNOWN;
		}
	}
	
	private Gender (String gVal) {
		genderVal = gVal;
	}
	
	public boolean equals (Object anotherGender) {
		return this.genderVal.equals(((Gender)anotherGender).genderVal);
	}
	public int hashCode () {
		return this.genderVal.hashCode();
	}
	public String toString () {
		return genderVal;
	}
	
	
}
