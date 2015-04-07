package org.michiganchineseschool.speech.model;

import java.io.Serializable;

public class Staff implements Serializable {
	static final long serialVersionUID = 1l;
	private String idstaff;
	private String chineseLastName;
	private String chineseFirstName;
	private String englishLastName;
	private String englishFirstName;
	private String password;
	private int failedAttempt;

	public int getFailedAttempt() {
		return failedAttempt;
	}

	public void setFailedAttempt(int failedAttempt) {
		this.failedAttempt = failedAttempt;
	}

	public Staff() {
	}

	public Staff(NameParser nameParser) {
		setChineseFirstName(nameParser.getChineseFirstname().trim());
		setChineseLastName(nameParser.getChineseLastname().trim());
		setEnglishFirstName(nameParser.getEnglishFirstname().trim());
		setEnglishLastName(nameParser.getEnglishLastname().trim());
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdstaff() {
		return idstaff;
	}

	public void setIdstaff(String id) {
		this.idstaff = id;
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
