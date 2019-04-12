package com.alejandro.management;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import com.alejandro.classes.Student;
import com.alejandro.classes.StudentsList;
import com.alejandro.sorters.CreatedAtSorter;
import com.alejandro.sorters.NameSorter;
import com.alejandro.util.Util;

public class StudentSolution {

	public static Scanner scanner = new Scanner(System.in);

	public static String filename = null;
	public static String studentName = null;
	public static String studentGender = null;
	public static String studentType = null;

	public static StudentsList studentsCSV = null;

	public static void main(String[] args) {
		System.out.println("----- Welcome to SMStudents: an System to Manage Students -----");
		System.out.println("\n");

		if (args.length > 0) {
			String symbolToSplit = "=";
			String separatorCSV = ",";
			// Descomposition
			descompositionArguments(args, symbolToSplit);

			// Store the students in the system
			if (filename != null) {
				System.out.println("** Reading CSV file '" + filename + "' **");

				studentsCSV = storeStudentsFromCSV(filename, separatorCSV);

				operationsWithArguments(studentsCSV);
			} else {
				System.out.println("Error. Filename does not valid!");
			}

		} else {
			initOperationsMenu(scanner);
		}

	}

	private static void operationsWithArguments(StudentsList students) {
		if (students != null) {

			if (studentName != null) {
				showStudentsByName(students, studentName);
			} else {
				if (studentType != null) {
					if (studentGender != null) {
						// showStudentsByTypeAndGender(students, studentType, studentGender);
					} else {
						showStudentsByType(students, studentType);
					}
				} else {
					System.out.println("Please, you should pass a valid student's type");
				}
			}
		} else {
			System.out.println("Error. There aren't any students registered.");
		}
	}

	private static void showMenu() {

		System.out.println("---------------------");
		System.out.println("\n ** MENU **");
		System.out.println("\n");
		System.out.println("1. Load students from a CSV file");
		System.out.println("2. List all students registered alphabetically");
		System.out.println("3. Create a new student");
		System.out.println("4. Delete a student by name");
		System.out.println("5. Search students by name");
		System.out.println("6. Search students by type");
		System.out.println("7. Search students by gender and type");
		System.out.println("8. Exit");
		System.out.println("---------------------");

	}

	private static void initOperationsMenu(Scanner scannerOperations) {
		int optionMenu = -1;

		do {
			// Menu
			showMenu();

			// Choose an option
			optionMenu = Util.getOptionMenuFromConsole(scannerOperations);

			switch (optionMenu) {
			case 1:
				// 1. Load students from a CSV file
				System.out.println("** Load students from a CSV file ***");

				filename = Util.getFilenameFromConsole(scannerOperations);

				// Store the students in the system
				if (filename != null) {

					System.out.println("** Reading CSV file '" + filename + "' **");

					String separatorCSV = ",";

					StudentsList studentsFromCSV = storeStudentsFromCSV(filename, separatorCSV);

					ArrayList<Student> studentsListFromFile = null;

					if (studentsFromCSV != null) {
						studentsListFromFile = studentsFromCSV.getStudents();
					}

					if (studentsCSV != null) {
						ArrayList<Student> studentsList = studentsCSV.getStudents();
						studentsCSV = Util.combineTwoLists(studentsList, studentsListFromFile);

					} else {
						studentsCSV = new StudentsList(studentsListFromFile);
					}
				} else {
					System.out.println("Error. Filename " + filename + " does not valid!");
				}

				break;

			case 2:
				// 2. List all students
				System.out.println("** STUDENTS **");

				if (studentsCSV != null) {
					showAllStudentsRegistered(studentsCSV);
				} else {
					System.out.println("Please, you could load some students to system. See option 0");
				}

				break;
			case 3:
				// 3. Create a new student
				System.out.println("** Create a new student **");

				if (studentsCSV == null) {
					studentsCSV = new StudentsList();
				}

				// Enter from keyboard
				Student student = createNewStudent(scannerOperations);

				boolean isRegistered = studentsCSV.registerNewStudent(student);

				if (isRegistered) {
					System.out.println("\nStudent registered!");
				} else {
					System.out.println("Error, We couldn't register any student");
				}
				break;
			case 4:
				// 4. Delete
				System.out.println("** Delete a student by your name **");

				if (studentsCSV != null) {
					studentName = Util.getNameFromConsole(scannerOperations);

					boolean isDeleted = studentsCSV.deleteStudentByName(studentName);

					if (isDeleted) {
						System.out.println("\nStudent deleted!");
					} else {
						System.out.println("Error, We couldn't delete any student");
					}
				} else {
					System.out.println("Please, you could load some students to system. See option 0");
				}

				break;
			case 5:
				// 5. Search students by name
				System.out.println("** Search students by name **");

				if (studentsCSV != null) {

					studentName = Util.getNameFromConsole(scannerOperations);
					ArrayList<Student> studentsByName = studentsCSV.findByName(studentName);

					if (studentsByName != null && !studentsByName.isEmpty()) {
						showStudents(studentsByName);
					} else {
						System.out.println("Sorry, but there isn't any student with this name '" + studentName + "'");
					}
				} else {
					System.out.println("Please, you could load some students to system. See option 0");
				}

				break;
			case 6:
				// 6. Search students by type
				System.out.println("** Search students by type **");

				if (studentsCSV != null) {
					studentType = Util.getTypeFromConsole(scannerOperations);
					ArrayList<Student> studentsByType = studentsCSV.findByType(studentType);

					if (studentsByType != null && !studentsByType.isEmpty()) {
						showStudents(studentsByType);
					} else {
						System.out.println("Sorry, but there isn't any student with this type '" + studentType + "'");
					}
				} else {
					System.out.println("Please, you could load some students to system. See option 0");
				}

				break;
			case 7:
				// 7. Search students by gender and type
				System.out.println("** Search students by gender and type **");

				if (studentsCSV != null) {

					studentGender = Util.getGenderFromConsole(scannerOperations);
					studentType = Util.getTypeFromConsole(scannerOperations);

					// Short Gender
					String studentGenderShort = null;
					if (studentGender.compareTo("female") == 0) {
						studentGenderShort = "F";
					} else if (studentGender.compareTo("male") == 0) {
						studentGenderShort = "M";
					}

					ArrayList<Student> studentsByGenderAndType = studentsCSV
							.findByGenderShortAndType(studentGenderShort, studentType);

					if (studentsByGenderAndType != null && !studentsByGenderAndType.isEmpty()) {
						showStudents(studentsByGenderAndType);
					} else {
						System.out.println("Sorry, but there isn't any student with this gender '" + studentGender
								+ "' and  type '" + studentType + "'");
					}
				} else {
					System.out.println("Please, you could load some students to system. See option 0");
				}

				break;
			case 8:
				System.out.println("Good bye! Have a nice day!");
				break;

			default:
				System.out.println("Error. Option is not valid");
				break;
			}

			System.out.println("---------------------");
		} while (optionMenu != 8);

	}

	private static Student createNewStudent(Scanner sc) {

		// Student
		Student student = null;

		// Student's properties
		String name = null;
		String gender = null;
		String type = null;

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

		// gender
		do {
			try {
				System.out.println("Enter student's gender: ");
				gender = sc.nextLine();

				if (gender.isEmpty()) {
					System.out.println("Please, you should enter a valid gender (not a empty string).");
				}

			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} while (gender.isEmpty());

		// type
		do {
			try {
				System.out.println("Enter student's type: ");
				type = sc.nextLine();

				if (type.isEmpty()) {
					System.out.println("Please, you should enter a valid type (not a empty string).");
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} while (type.isEmpty());

		student = new Student(name, gender, type);

		return student;

	}

	private static void descompositionArguments(String[] args, String symbolToSplit) {
		System.out.println("---------------------------");
		System.out.println("** Details: arguments **");

		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				String argi = args[i];

				if (i == 0) {
					filename = argi;
				} else {
					if (argi.contains("name=")) {
						studentName = getValueFromArgument(argi, symbolToSplit);
					}

					if (argi.contains("type=")) {
						studentType = getValueFromArgument(argi, symbolToSplit);
					}

					if (argi.contains("gender=")) {
						studentGender = getValueFromArgument(argi, symbolToSplit);
					}
				}

			}

			if (filename != null) {
				System.out.println("filename: " + filename);
			}

			if (studentName != null) {
				System.out.println("name: " + studentName);
			}

			if (studentType != null) {
				System.out.println("type: " + studentType);
			}

			if (studentGender != null) {
				System.out.println("genre: " + studentGender);
			}

		} else {
			System.out.println("Please, you need pass any valid values.");
		}

		System.out.println("---------------------------");
		System.out.println("\n");

	}

	private static String getValueFromArgument(String argi, String symbol) {

		if (argi == null)
			return null;

		String value = null;

		String[] subarguments = argi.split(symbol);

		if (subarguments.length > 1) {
			value = subarguments[1];
		}

		return value;
	}

	private static void showAllStudentsRegistered(StudentsList studentsList) {

		if (studentsList != null) {
			ArrayList<Student> studentsRegistered = studentsList.getStudents();

			if (studentsRegistered != null) {

				// Sort
				Collections.sort(studentsRegistered, new NameSorter());

				for (Student student : studentsRegistered) {
					System.out.println(student);
				}
			} else {
				System.out.println("Sorry, there isn't any student registered");
			}

		} else {
			System.out.println("Sorry, there isn't any student to show");
		}
	}

	private static void showStudents(ArrayList<Student> students) {
		if (students != null) {
			for (Student student : students) {
				System.out.println(student);
			}
		} else {
			System.out.println("Sorry, there isn't any student to show");
		}
	}

	private static void showStudentsByName(StudentsList students, String name) {
		System.out.println("--------------------");
		ArrayList<Student> studentsFiltered = students.findByName(name);

		if (!studentsFiltered.isEmpty()) {
			// Sort alphabetically
			Collections.sort(studentsFiltered, new NameSorter());

			showStudents(studentsFiltered);
		} else {
			System.out.println("Sorry, but there isn't any student with this gender '" + name + "'");
		}
		System.out.println("--------------------");
	}

	private static void showStudentsByType(StudentsList students, String type) {
		System.out.println("--------------------");
		ArrayList<Student> studentsFiltered = students.findByType(type);

		if (!studentsFiltered.isEmpty()) {
			// Sort alphabetically
			Collections.sort(studentsFiltered, new CreatedAtSorter());

			showStudents(studentsFiltered);
		} else {
			System.out.println("Sorry, but there isn't any student with this type '" + type + "'");
		}
		System.out.println("--------------------");

	}

	private static StudentsList storeStudentsFromCSV(String filenameStr, String separatorCSV) {

		if (filenameStr == null)
			return null;

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
