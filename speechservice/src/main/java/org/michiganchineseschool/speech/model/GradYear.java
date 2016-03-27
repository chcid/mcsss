package org.michiganchineseschool.speech.model;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class GradYear implements Serializable {
	static final long serialVersionUID = 1l;
	private String idgrad_year;
	private String name;

	public String getIdgrad_year() {
		return idgrad_year;
	}

	public void setIdgrad_year(String idgrad_year) {
		this.idgrad_year = idgrad_year;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
