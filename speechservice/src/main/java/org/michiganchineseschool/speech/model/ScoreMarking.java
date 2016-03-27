package org.michiganchineseschool.speech.model;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ScoreMarking implements Serializable {
	static final long serialVersionUID = 1l;
	private String idscore_marking;
	private ContestorScore contestorScore;
	private int rollCallMarking;
	private int flashLightMarking;
	private int audienceHelper;
	private int absence;
	private int phoneUsed;
	private int samePictureUsed;

	public String getSamePictureUsed() {
		return String.valueOf(samePictureUsed);
	}

	public void setSamePictureUsed(String samePictureUsed) {
		this.samePictureUsed = Integer.parseInt(samePictureUsed);
	}

	public String getPhoneUsed() {
		return String.valueOf(phoneUsed);
	}

	public void setPhoneUsed(String phoneUsed) {
		this.phoneUsed = Integer.parseInt(phoneUsed);
	}

	public String getIdscore_marking() {
		return idscore_marking;
	}

	public void setIdscore_marking(String idscore_marking) {
		this.idscore_marking = idscore_marking;
	}

	public ContestorScore getContestorScore() {
		return contestorScore;
	}

	public void setContestorScore(ContestorScore contestorScore) {
		this.contestorScore = contestorScore;
	}

	public String getRollCallMarking() {
		return String.valueOf(rollCallMarking);
	}

	public void setRollCallMarking(String rollCallMarking) {
		this.rollCallMarking = Integer.parseInt(rollCallMarking);
	}

	public String getFlashLightMarking() {
		return String.valueOf(flashLightMarking);
	}

	public void setFlashLightMarking(String flash_light_marking) {
		this.flashLightMarking = Integer.parseInt(flash_light_marking);
	}

	public String getAudienceHelper() {
		return String.valueOf(audienceHelper);
	}

	public void setAudienceHelper(String audience_helper) {
		this.audienceHelper = Integer.parseInt(audience_helper);
	}

	public String getAbsence() {
		return String.valueOf(this.absence);
	}

	public void setAbsence(String absence) {
		this.absence = Integer.parseInt(absence);
	}

	public String getAbsenceText() {
		if (0 == this.absence) {
			return "有到";
		}
		return "棄權";
	}

	public void setAbsenceText(String tex) {
	}

}
