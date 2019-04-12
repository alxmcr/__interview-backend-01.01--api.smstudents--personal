package com.alejandro.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.alejandro.classes.Student;
import com.alejandro.classes.StudentsList;

public class Util {
	public static String getNameFromConsole(Scanner sc) {

		String name = "";
		// name
		do {
			try {
				System.out.println("Enter student's name: ");
				name = sc.nextLine();

				if (name.isEmpty()) {
					System.out.println("Please, you should enter a valid name (not a empty string).");
				}

			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} while (name.isEmpty());

		return name;
	}

	public static String getTypeFromConsole(Scanner sc) {
		String type = "";
		// type
		do {
			try {
				System.out.println("Enter student's type (Kinder, Elementary, High, University): ");
				type = sc.nextLine();

				if (type.isEmpty()) {
					System.out.println("Please, you should enter a valid type (not a empty string).");
				}

			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} while (type.isEmpty());

		return type;
	}

	public static String getGenderFromConsole(Scanner sc) {
		String gender = "";
		// gender
		do {
			try {
				System.out.println("Enter student's gender (female, male): ");
				gender = sc.nextLine();

				if (gender.isEmpty()) {
					System.out.println("Please, you should enter a valid gender (not a empty string).");
				}

			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} while (gender.isEmpty());

		return gender;
	}

	public static String getFilenameFromConsole(Scanner sc) {
		String filename = "";
		// filename
		do {
			try {
				System.out.println("Enter student's filename (input.csv): ");
				filename = sc.nextLine();

				if (filename.isEmpty()) {
					System.out.println("Please, you should enter a valid filename (not a empty string).");
				}

			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} while (filename.isEmpty());

		return filename;
	}

	public static int getOptionMenuFromConsole(Scanner sc) {

		int option = -1;

		String optionStr = "";
		// option
		do {
			try {
				System.out.println("Enter menu's option (0): ");
				optionStr = sc.nextLine();

				option = Integer.parseInt(optionStr);

				if (optionStr.isEmpty()) {
					System.out.println("Please, you should enter a valid number (not a empty string).");
				}

			} catch (Exception e) {
				System.out.println("Please, you should enter a valid number (not a empty string).");
			}
		} while (optionStr.isEmpty());

		return option;
	}

	public static ArrayList<String> readFileCSV(String filename, String separator) {

		String workingDirectory = System.getProperty("user.dir");

		if (filename == null) {
			System.out.println("Please, you should pass a valid file's name");
			return null;
		}

		String fullPathFile = workingDirectory + "\\" + filename;

		ArrayList<String> lines = null;

		try {
			BufferedReader s = new BufferedReader(new FileReader(fullPathFile));

			lines = new ArrayList<String>();

			String line;
			while ((line = s.readLine()) != null) {
				lines.add(line);
			}

		} catch (FileNotFoundException e) {
			System.err.println("Error. File not found");
		} catch (IOException e) {
			System.err.println("Error. File can not open");
		}

		System.out.println("Finished.");

		return lines;
	}

	public static StudentsList combineTwoLists(ArrayList<Student> studentsList,
			ArrayList<Student> studentsListFromFile) {

		if (studentsList == null)
			return null;

		ArrayList<Student> combinedList = new ArrayList<Student>(studentsList);
		combinedList.addAll(studentsListFromFile);

		return new StudentsList(combinedList);
	}

	public static Student buildStudentFromArray(String[] valuesStudent) {

		if (valuesStudent == null || valuesStudent.length == 0)
			return null;

		// Student
		String name = null;
		String gender = null;
		String type = null;
		String createdAtStr = null;

		for (int i = 0; i < valuesStudent.length; i++) {
			String valueStudenti = valuesStudent[i];

			switch (i) {
			case 0:
				type = valueStudenti;
				break;
			case 1:
				name = valueStudenti;
				break;
			case 2:
				gender = valueStudenti;
				break;
			case 3:
				createdAtStr = valueStudenti;
				break;
			}
		}

		if (name != null) {
			if (gender != null) {
				if (type != null) {
					if (createdAtStr != null) {
						Calendar createdAt = convertStringToCalendar(createdAtStr);

						if (createdAt != null) {
							return new Student(name, gender, type, createdAt);
						} else {
							System.err.println("Please, we can't build a new student.");
						}
					} else {
						System.err.println("Please, you should pass valid timestamp.");
					}
				} else {
					System.err.println("Please, you should pass valid type.");
				}
			} else {
				System.err.println("Please, you should pass valid gender.");
			}
		} else {
			System.err.println("Please, you should pass valid name.");
		}

		return null;
	}

	private static Calendar convertStringToCalendar(String calendarStr) {

		if (calendarStr == null || calendarStr.isEmpty())
			return null;

		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(df.parse(calendarStr));
		} catch (ParseException e) {
			System.err.println("Please, you should use a valid timestamp format (yyyyMMddHHmmss).");
		}

		return cal;
	}

}
