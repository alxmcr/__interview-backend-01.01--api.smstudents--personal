# SMStudents: System to Manage Students

- [SMStudents: System to Manage Students](#smstudents-system-to-manage-students)
  - [A. Project Overview & Requirements](#a-project-overview--requirements)
  - [B. Tools](#b-tools)
  - [C. Ways to run this code](#c-ways-to-run-this-code)
    - [1. Eclipse: Create different run configurations](#1-eclipse-create-different-run-configurations)
    - [2. Manual: Menu's options](#2-manual-menus-options)
  - [D. Testing: JUnit Test Cases](#d-testing-junit-test-cases)

## A. Project Overview & Requirements

You are modeling a system to manage students that will be used by high schools, elementary schools, kindergardens, etc.

Your task is to create the business objects to manage the students in the system:

- Store the students in the system
- Create new students
- Delete a specific student
- Search for students in ways that make sense for the clients
  * By name, sorted alphabetically
  * By student type (kinder, elementary, high, university) sorting by date, most recent to least recent.
  * By gender and type (female elementary) sorting by date, most recent to least recent.


## B. Tools

- Java 1.8
- **JUnit 5**
- Eclipse

## C. Ways to run this code

**Note.-** `StudentSolution` has `main()` method.

### 1. Eclipse: Create different run configurations

**Search Students by name**

![Search Students by name](https://raw.githubusercontent.com/alxmcr/SMStudents/master/_assets/Students-FindByName.png)

**Search Students by type**

![Search Students by type](https://raw.githubusercontent.com/alxmcr/SMStudents/master/_assets/Students-FindByType.png)

**Search Students by type and gender**

![Search Students by type and gender](https://raw.githubusercontent.com/alxmcr/SMStudents/master/_assets/Students-FindByTypeAndGender.png)

### 2. Manual: Menu's options 

```
1. Load students from a CSV file
2. List all students registered alphabetically
3. Create a new student
4. Delete a student by name
5. Search students by name
6. Search students by type
7. Search students by gender and type
8. Exit
```

## D. Testing: JUnit Test Cases

**Note.-** For tests, I'm using this file `inputTest.csv`

- **Case 001:** Store students from file (inputTest.csv) 
- **Case 002:** Create a new student
- **Case 003:** Delete an existent student
- **Case 004:** Delete an not existent student
- **Case 005:** Search students by name
- **Case 006:** Search students by type
- **Case 007:** Search students by gender and type

![Testing: JUnit Test Cases](https://raw.githubusercontent.com/alxmcr/SMStudents/master/_assets/Test.png)