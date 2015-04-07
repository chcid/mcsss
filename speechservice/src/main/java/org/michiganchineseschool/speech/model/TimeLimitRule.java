package org.michiganchineseschool.speech.model;

import java.io.Serializable;

public class TimeLimitRule implements Serializable {
	static final long serialVersionUID = 1l;
	private String idtime_limit_rule;
	private int maxLimit;
	private int minLimit;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdtime_limit_rule() {
		return idtime_limit_rule;
	}

	public void setIdtime_limit_rule(String idtime_limit_rule) {
		this.idtime_limit_rule = idtime_limit_rule;
	}

	public int getMaxLimit() {
		return maxLimit;
	}

	public void setMaxLimit(int maxLimit) {
		this.maxLimit = maxLimit;
	}

	public int getMinLimit() {
		return minLimit;
	}

	public void setMinLimit(int minLimit) {
		this.minLimit = minLimit;
	}

}
