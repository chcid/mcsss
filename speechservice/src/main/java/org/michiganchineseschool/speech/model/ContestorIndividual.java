package org.michiganchineseschool.speech.model;

import java.io.Serializable;

public class ContestorIndividual implements Serializable {
	static final long serialVersionUID = 1l;
	private String idcontestor_individual;
	private Contestor contestor;
	private Student student;

	public String getIdcontestor_individual() {
		return idcontestor_individual;
	}

	public void setIdcontestor_individual(String idcontestor_individual) {
		this.idcontestor_individual = idcontestor_individual;
	}

	public Contestor getContestor() {
		return contestor;
	}

	public void setContestor(Contestor contestor) {
		this.contestor = contestor;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
