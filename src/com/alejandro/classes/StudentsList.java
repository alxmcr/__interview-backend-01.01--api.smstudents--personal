package com.alejandro.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import com.alejandro.sorters.CreatedAtSorter;
import com.alejandro.sorters.NameSorter;

public class StudentsList {
	private ArrayList<Student> students;

	public StudentsList() {
		this.students = new ArrayList<Student>();
	}

	public StudentsList(ArrayList<Student> students) {
		this.students = students;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (students != null) {
			for (Student student : students) {
				builder.append(student + "\n");
			}
		}
		return builder.toString();
	}

	public void storeStudent(Student student) {
		this.students.add(student);
	}

	public ArrayList<Student> findByName(String studentName) {

		if (studentName == null || studentName.isEmpty())
			return null;

		System.out.println("\n ** Finding students with name '" + studentName + "' **");
		System.out.println("\n");
		ArrayList<Student> studentsByName = new ArrayList<Student>();

		for (Student student : this.students) {
			String name = student.getName();

			if (name != null) {
				// Lower case.
				name = name.toLowerCase();
				studentName = studentName.toLowerCase();

				if (name.compareTo(studentName) == 0) {
					studentsByName.add(student);
				}
			}
		}

		// Sort
		Collections.sort(studentsByName, new NameSorter());

		return studentsByName;
	}

	public ArrayList<Student> findByType(String studentType) {
		if (studentType == null || studentType.isEmpty())
			return null;

		System.out.println("\n ** Finding students with type '" + studentType + "' **");
		System.out.println("\n");
		ArrayList<Student> studentsByType = new ArrayList<Student>();

		for (Student student : this.students) {
			String type = student.getType();

			if (type != null) {
				// Lower case.
				type = type.toLowerCase();
				studentType = studentType.toLowerCase();

				if (type.compareTo(studentType) == 0) {
					studentsByType.add(student);
				}
			}
		}

		// Sort
		Collections.sort(studentsByType, new CreatedAtSorter());

		return studentsByType;
	}

	public ArrayList<Student> findByGender(String studentGender) {
		if (studentGender == null || studentGender.isEmpty())
			return null;

		System.out.println("\n ** Finding students with gender '" + studentGender + "' **");
		System.out.println("\n");
		ArrayList<Student> studentsByGender = new ArrayList<Student>();

		for (Student student : this.students) {
			String gender = student.getGender();

			if (gender != null) {
				// Lower case.
				gender = gender.toLowerCase();
				studentGender = studentGender.toLowerCase();

				if (gender.compareTo(studentGender) == 0) {
					studentsByGender.add(student);
				}
			}
		}

		// Sort
		Collections.sort(studentsByGender, new CreatedAtSorter());

		return studentsByGender;
	}

	public ArrayList<Student> findByGenderShort(String studentGenderShort) {
		if (studentGenderShort == null || studentGenderShort.isEmpty())
			return null;

		System.out.println("\n ** Finding students with gender '" + studentGenderShort + "' **");
		System.out.println("\n");
		ArrayList<Student> studentsByGender = new ArrayList<Student>();

		for (Student student : this.students) {
			String gender = student.getGender();

			if (gender != null) {
				// Lower case.
				gender = gender.toLowerCase();
				studentGenderShort = studentGenderShort.toLowerCase();

				if (gender.compareTo(studentGenderShort) == 0) {
					studentsByGender.add(student);
				}
			}
		}

		// Sort
		Collections.sort(studentsByGender, new CreatedAtSorter());

		return studentsByGender;
	}

	public ArrayList<Student> findByGenderShortAndType(String studentGenderShort, String studentType) {
		if (studentType == null || studentGenderShort == null)
			return null;

		System.out.println("\n ** Finding students with type '" + studentType + "' and short gender '"
				+ studentGenderShort + "' **");
		System.out.println("\n");
		ArrayList<Student> studentsByTypeAndGender = new ArrayList<Student>();

		for (Student student : this.students) {
			String type = student.getType();
			String gender = student.getGender();

			if (gender != null) {
				// Lower case.
				gender = gender.toLowerCase();
				studentGenderShort = studentGenderShort.toLowerCase();

				type = type.toLowerCase();
				studentType = studentType.toLowerCase();

				if (type.compareTo(studentType) == 0 && gender.compareTo(studentGenderShort) == 0) {
					studentsByTypeAndGender.add(student);
				}
			}
		}

		// Sort
		Collections.sort(studentsByTypeAndGender, new CreatedAtSorter());

		return studentsByTypeAndGender;
	}

	public boolean registerNewStudent(Student student) {

		if (student != null) {
			this.students.add(student);
			return true;
		}
		return false;
	}

	public boolean deleteStudentByName(String nameToDelete) {
		if (nameToDelete == null || nameToDelete.isEmpty())
			return false;

		boolean result = false;

		Iterator<Student> i = this.students.iterator();

		while (i.hasNext()) {
			Student student = i.next();
			String name = student.getName();

			if (name != null && !name.isEmpty()) {
				name = name.toLowerCase();
				nameToDelete = nameToDelete.toLowerCase();

				if (name.compareTo(nameToDelete) == 0) {
					i.remove();
					result = true;
				}
			}
		}
		return result;
	}


}
