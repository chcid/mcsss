package org.michiganchineseschool.speech.model;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Student implements Serializable {
	static final long serialVersionUID = 1l;
	private String idstudent;
	private String chineseLastName;
	private String chineseFirstName;
	private String englishLastName;
	private String englishFirstName;
	private GradYear gradYear;
	private String students;

	public Student() {
	}

	public Student(NameParser nameParser) {
		setChineseFirstName(nameParser.getChineseFirstname().trim());
		setChineseLastName(nameParser.getChineseLastname().trim());
		setEnglishFirstName(nameParser.getEnglishFirstname().trim());
		setEnglishLastName(nameParser.getEnglishLastname().trim());
	}

	public String getStudents() {
		return students;
	}

	public void setStudents(String students) {
		this.students = students;
	}

	public GradYear getGradYear() {
		return gradYear;
	}

	public void setGradYear(GradYear gradYear) {
		this.gradYear = gradYear;
	}

	public String getIdstudent() {
		return idstudent;
	}

	public void setIdstudent(String id) {
		this.idstudent = id;
	}

	public String getChineseLastName() {
		return chineseLastName;
	}

	public void setChineseLastName(String chineseLastName) {
		this.chineseLastName = chineseLastName;
	}

	public String getChineseFirstName() {
		return chineseFirstName;
	}

	public void setChineseFirstName(String chineseFirstName) {
		this.chineseFirstName = chineseFirstName;
	}

	public String getEnglishLastName() {
		return englishLastName;
	}

	public void setEnglishLastName(String englishLastName) {
		this.englishLastName = englishLastName;
	}

	public String getEnglishFirstName() {
		return englishFirstName;
	}

	public void setEnglishFirstName(String englishFirstName) {
		this.englishFirstName = englishFirstName;
	}

}
