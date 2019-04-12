package com.alejandro.classes;

import java.util.Calendar;

public class Student {
	private String type;
	private String name;
	private String gender;
	private Calendar createdAt;

	public Student(String name, String gender, String type, Calendar createdAt) {
		this.name = name;
		this.gender = gender;
		this.type = type;
		this.createdAt = createdAt;
	}

	public Student(String name, String gender, String type) {
		this.name = name;
		this.gender = gender;
		this.type = type;
		this.createdAt = Calendar.getInstance();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Calendar getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		if (type != null) {
			builder.append(type);
			builder.append(", ");
		}

		if (name != null) {
			builder.append(name);
			builder.append(", ");
		}
		if (gender != null) {
			builder.append(gender);
			builder.append(", ");
		}

		if (createdAt != null) {
			int year = createdAt.get(Calendar.YEAR);
			int month = createdAt.get(Calendar.MONTH); // 0 to 11
			int day = createdAt.get(Calendar.DAY_OF_MONTH);
			int hour = createdAt.get(Calendar.HOUR_OF_DAY);
			int minute = createdAt.get(Calendar.MINUTE);
			int second = createdAt.get(Calendar.SECOND);

			String monthStr = "";
			String dayStr = "";
			String hourStr = "";
			String minuteStr = "";
			String secondStr = "";

			if (month < 10) {
				monthStr = "0" + month;
			} else {
				monthStr = Integer.toString(month);
			}

			if (day < 10) {
				dayStr = "0" + day;
			} else {
				dayStr = Integer.toString(day);
			}

			if (hour < 10) {
				hourStr = "0" + hour;
			} else {
				hourStr = Integer.toString(hour);
			}

			if (minute < 10) {
				minuteStr = "0" + minute;
			} else {
				minuteStr = Integer.toString(minute);
			}

			if (second < 10) {
				secondStr = "0" + second;
			} else {
				secondStr = Integer.toString(second);
			}

			String createdAtStr = year + "" + monthStr + "" + dayStr + "" + hourStr + "" + minuteStr + "" + secondStr;

			builder.append(createdAtStr);

		}

		return builder.toString();
	}

}
