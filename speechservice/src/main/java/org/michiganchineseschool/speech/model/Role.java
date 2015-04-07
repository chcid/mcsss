package org.michiganchineseschool.speech.model;

import java.io.Serializable;

public class Role implements Serializable {
	static final long serialVersionUID = 1l;
	private String idrole;
	private String name;

	public String getIdrole() {
		return idrole;
	}

	public void setIdrole(String idrole) {
		this.idrole = idrole;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
