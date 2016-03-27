package org.michiganchineseschool.speech.model;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ScoreCountingType implements Serializable {
	static final long serialVersionUID = 1l;
	private String idscore_counting_type;

	public String getIdscore_counting_type() {
		return idscore_counting_type;
	}

	public void setIdscore_counting_type(String idscore_counting_type) {
		this.idscore_counting_type = idscore_counting_type;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
