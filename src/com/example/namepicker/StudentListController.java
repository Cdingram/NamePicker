package com.example.namepicker;

import java.io.IOException;

public class StudentListController {
	
	// Lazy singleton
	private static StudentList studentList = null;
	
	// Warning: throws a runtime error
	static public StudentList getStudentList() {
		if (studentList == null) {
			try {
				studentList = StudentListManager.getManager().loadStudentList();
				studentList.addListener(new Listener() {

					@Override
					public void update() {
						// TODO Auto-generated method stub
						saveStudentList();
					}
					
				});
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize studentList from studentListManager");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize studentList from studentListManager");
			}
		}
		return studentList;
	}
	
	static public void saveStudentList() {
		try {
			StudentListManager.getManager().saveStudentList(getStudentList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Could not deserialize studentList from studentListManager");
		}
	}
	
	public Student chooseStudent() throws EmptyStudentListException {
		return getStudentList().chooseStudent();
	}
	
	public void addStudent(Student student) {
		getStudentList().addStudent(student);
	}

	public void bulkImport(String text) {
		// TODO Auto-generated method stub
		String [] lines = text.split("\n");
		StudentList sl = getStudentList();
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i].trim();
			if (!lines.equals("")) {
				Student s = new Student(line);
				sl.addStudent(s);
			}
		}
	}
}
