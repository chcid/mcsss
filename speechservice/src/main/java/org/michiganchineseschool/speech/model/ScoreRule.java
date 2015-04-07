package org.michiganchineseschool.speech.model;

import java.io.Serializable;
import java.util.List;

public class ScoreRule implements Serializable {
	static final long serialVersionUID = 1l;
	private String idscore_rule;
	private List<ScoreRuleItem> scoreRuleItems;

	public List<ScoreRuleItem> getScoreRuleItems() {
		return scoreRuleItems;
	}

	public void setScoreRuleItems(List<ScoreRuleItem> scoreRuleItems) {
		this.scoreRuleItems = scoreRuleItems;
	}

	public String getIdscore_rule() {
		return idscore_rule;
	}

	public void setIdscore_rule(String idscore_rule) {
		this.idscore_rule = idscore_rule;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
