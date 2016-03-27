package org.michiganchineseschool.speech.model;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ContestLocation implements Serializable {
	static final long serialVersionUID = 1l;
	private String idcontest_location;
	private String name;

	public String getIdcontest_location() {
		return idcontest_location;
	}

	public void setIdcontest_location(String idcontest_location) {
		this.idcontest_location = idcontest_location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
