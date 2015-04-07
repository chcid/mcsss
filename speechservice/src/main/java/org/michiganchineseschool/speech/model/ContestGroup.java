package org.michiganchineseschool.speech.model;

import java.io.Serializable;

public class ContestGroup implements Serializable {
	static final long serialVersionUID = 1l;
	private String idcontest_group;
	private Contest contest;
	private ContestLocation location;
	private TimeLimitRule timeLimitRule;
	private String name;
	private ScoreRule scoreRule;
	private ScoreCountingType scoreCountingType;
	private Role role;
	private Judge judge;
	private String judges;
	private String contestors;
	

	public String getContestors() {
		return contestors;
	}

	public void setContestors(String contestors) {
		this.contestors = contestors;
	}

	public String getJudges() {
		return judges;
	}

	public void setJudges(String judges) {
		this.judges = judges;
	}

	public Judge getJudge() {
		return judge;
	}

	public void setJudge(Judge judge) {
		this.judge = judge;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getIdcontest_group() {
		return idcontest_group;
	}

	public void setIdcontest_group(String idcontest_group) {
		this.idcontest_group = idcontest_group;
	}

	public Contest getContest() {
		return contest;
	}

	public void setContest(Contest contest) {
		this.contest = contest;
	}

	public ContestLocation getContestLocation() {
		return location;
	}

	public void setContestLocation(ContestLocation location) {
		this.location = location;
	}

	public TimeLimitRule getTimeLimitRule() {
		return timeLimitRule;
	}

	public void setTimeLimitRule(TimeLimitRule timeLimitRule) {
		this.timeLimitRule = timeLimitRule;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ScoreRule getScoreRule() {
		return scoreRule;
	}

	public void setScoreRule(ScoreRule scoreRule) {
		this.scoreRule = scoreRule;
	}

	public ScoreCountingType getScoreCountingType() {
		return scoreCountingType;
	}

	public void setScoreCountingType(ScoreCountingType scoreCountingType) {
		this.scoreCountingType = scoreCountingType;
	}

}
