package org.michiganchineseschool.speech.model;

import java.io.Serializable;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Contest implements Serializable {
	static final long serialVersionUID = 1l;
	private String idcontest;
	private String name;
	private int active;
	private String contestGroups;
	private String command;

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getContestGroups() {
		return contestGroups;
	}

	public void setContestGroups(String contestGroups) {
		this.contestGroups = contestGroups;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getIdcontest() {
		return idcontest;
	}

	public void setIdcontest(String idcontest) {
		this.idcontest = idcontest;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
