/**
 * 
 */
package com.alejandro.classes.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.alejandro.classes.Student;
import com.alejandro.classes.StudentsList;
import com.alejandro.util.Util;

class StudentsListTest {

	private StudentsList studentList;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		String filenameStr = "inputTest.csv";
		String separatorCSV = ",";
		studentList = storeStudentsFromCSV(filenameStr, separatorCSV);
	}

	/*
	 * Case 001: Store students from file
	 */

	@Test
	void test001() {
		ArrayList<Student> students = studentList.getStudents();

		assertNotNull(studentList);
		assertNotNull(students);
		assertEquals(7, students.size());
	}

	/*
	 * Case 002: Create a new student
	 */

	@Test
	void test002() {
		ArrayList<Student> students = studentList.getStudents();

		assertNotNull(studentList);
		assertEquals(7, students.size());

		// Create a student
		Student s = new Student("Alejandro", "M", "High");
		students.add(s);

		assertEquals(8, students.size());
	}

	/*
	 * Case 003: Delete an existent student
	 */
	@Test
	void test003() {
		ArrayList<Student> students = studentList.getStudents();

		assertNotNull(studentList);
		assertEquals(7, students.size());

		boolean isDeleted = studentList.deleteStudentByName("Mark");
		assertTrue(isDeleted);
	}

	/*
	 * Case 004: Delete an not existent student
	 */
	@Test
	void test004() {
		ArrayList<Student> students = studentList.getStudents();

		assertNotNull(studentList);
		assertEquals(7, students.size());

		boolean isDeleted = studentList.deleteStudentByName("Not Exist");
		assertFalse(isDeleted);
	}

	/*
	 * Case 005: Search students by name
	 */
	@Test
	void test005() {
		ArrayList<Student> students = studentList.getStudents();

		assertNotNull(studentList);
		assertEquals(7, students.size());

		ArrayList<Student> studentsByName = studentList.findByName("leia");
		assertEquals(2, studentsByName.size());
	}

	/*
	 * Case 006: Search students by type
	 */
	@Test
	void test006() {
		ArrayList<Student> students = studentList.getStudents();

		assertNotNull(studentList);
		assertEquals(7, students.size());

		ArrayList<Student> studentsByType = studentList.findByType("kinder");
		assertEquals(3, studentsByType.size());
	}

	/*
	 * Case 007: Search students by gender and type
	 */
	@Test
	void test007() {
		ArrayList<Student> students = studentList.getStudents();

		assertNotNull(studentList);
		assertEquals(7, students.size());

		ArrayList<Student> studentsByType = studentList.findByGenderShortAndType("F", "kinder");
		assertEquals(2, studentsByType.size());
	}

	private static StudentsList storeStudentsFromCSV(String filenameStr, String separatorCSV) {
		// Store the students in the system
		ArrayList<String> linesCSV = Util.readFileCSV(filenameStr, separatorCSV);

		if (linesCSV == null || linesCSV.isEmpty()) {
			System.out.println("Sorry, file is empty");
			return null;
		}

		StudentsList studentsCSV = new StudentsList();

		for (String line : linesCSV) {
			String[] valuesStudent = line.split(separatorCSV);
			Student student = Util.buildStudentFromArray(valuesStudent);

			if (student != null) {
				studentsCSV.storeStudent(student);
			} else {
				System.err.println("Error. We couldn't build a student.");
			}
		}

		return studentsCSV;
	}

}
