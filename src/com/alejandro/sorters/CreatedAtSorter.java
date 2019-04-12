package com.alejandro.sorters;

import java.util.Comparator;

import com.alejandro.classes.Student;

public class CreatedAtSorter implements Comparator<Student> {

	@Override
	public int compare(Student e1, Student e2) {
		// most recent to least recent.
		return e1.getCreatedAt().compareTo(e2.getCreatedAt()) * -1;
	}

}
