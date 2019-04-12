package com.alejandro.sorters;

import java.util.Comparator;

import com.alejandro.classes.Student;

public class NameSorter implements Comparator<Student> {

	@Override
	public int compare(Student e1, Student e2) {
		return e1.getName().compareTo(e2.getName());
	}

}
