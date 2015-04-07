package org.michiganchineseschool.speech.model;

import java.io.Serializable;

public class TimeScore implements Serializable {
	static final long serialVersionUID = 1l;
	private String idtime_score;
	private ContestorScore contestorScore;
	private int minute;
	private int second;

	public String getIdtime_score() {
		return idtime_score;
	}

	public void setIdtime_score(String idtime_score) {
		this.idtime_score = idtime_score;
	}

	public ContestorScore getContestorScore() {
		return contestorScore;
	}

	public void setContestorScore(ContestorScore contestorScore) {
		this.contestorScore = contestorScore;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

}
