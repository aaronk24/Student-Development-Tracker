package org.tg8.sdt.domain;

import java.util.Comparator;

public class StudentNameComparator implements Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) {
		if (lastNamesAreIdentical(s1, s2)) {
			return compareFirstNames(s1, s2);
		}
		else {
			return compareLastNames(s1, s2);
		}
	}
	
	private boolean lastNamesAreIdentical (Student s1, Student s2) {
		return (compareLastNames(s1, s2) == 0);
	}
	
	private int compareLastNames(Student s1, Student s2) {
		return s1.getLastName().compareTo(s2.getLastName());
	}
	
	private int compareFirstNames(Student s1, Student s2) {
		return s1.getFirstName().compareTo(s2.getFirstName());
	}
}
