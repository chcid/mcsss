package org.michiganchineseschool.speech.model;

import java.io.Serializable;

public class ScoreRuleItem implements Serializable {
	static final long serialVersionUID = 1l;
	private String idscore_rule_item;
	private String name;
	private int weight;
	private ScoreRule scoreRule;
	private SpeechScore speechScore;
	private int priority;

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public SpeechScore getSpeechScore() {
		return speechScore;
	}

	public void setSpeechScore(SpeechScore speechScore) {
		this.speechScore = speechScore;
	}

	public String getIdscore_rule_item() {
		return idscore_rule_item;
	}

	public void setIdscore_rule_item(String idscore_rule_item) {
		this.idscore_rule_item = idscore_rule_item;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public ScoreRule getScoreRule() {
		return scoreRule;
	}

	public void setScoreRule(ScoreRule scoreRule) {
		this.scoreRule = scoreRule;
	}

}
