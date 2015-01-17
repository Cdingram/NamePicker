package com.example.namepicker;

import java.io.Serializable;

public class Student implements Serializable {
	/**
	 * Student serializable ID
	 */
	private static final long serialVersionUID = 1172802606604716254L;
	protected String studentName;
	
	public Student(String studentName) {
		this.studentName = studentName;
	}

	public String getName() {
		return this.studentName;
	}
	
	public String toString() {
		return getName();
	}
	
	public boolean equals(Object compareStudent) {
		if(compareStudent != null && compareStudent.getClass() == this.getClass()) {
			return this.equals((Student)compareStudent);
		} else {
			return false;
		}
	}
	
	public boolean equals(Student compareStudent) {
		if(compareStudent == null) {
			return false;
		}
		return getName().equals(compareStudent.getName());
	}
	
	public int hashCode() {
		return ("Student" + getName()).hashCode();
	}

}
