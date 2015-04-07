package org.michiganchineseschool.speech.model;

import java.io.Serializable;
import java.util.List;

public class ContestorScore implements Serializable, Comparable<ContestorScore> {
	static final long serialVersionUID = 1l;
	private String idcontestor_score;
	private Contestor contestor;
	private Judge judge;
	private List<SpeechScore> speechScores;
	private ScoreMarking scoreMarking;
	private TimeScore timeScore;
	private float speechScoreTotal;
	private int scoreMarkingTotal;
	private int timeScoreTotal;
	private float totalScore;

	private boolean isJudgeMax;
	private boolean isJudgeMin;

	private int judgeRank;

	// private boolean isAbstained;

	public int getJudgeRank() {
		return judgeRank;
	}

	public void setJudgeRank(int judgeRank) {
		this.judgeRank = judgeRank;
	}

	public boolean isJudgeMax() {
		return isJudgeMax;
	}

	public void setJudgeMax(boolean isJudgeMax) {
		this.isJudgeMax = isJudgeMax;
	}

	public boolean isJudgeMin() {
		return isJudgeMin;
	}

	public void setJudgeMin(boolean isJudgeMin) {
		this.isJudgeMin = isJudgeMin;
	}

	public boolean isAbstained() {
		try {
			return !getScoreMarking().getAbsence().equals("0");
		} catch (NullPointerException e) {
		}
		return false;
	}

	public void setAbstained(boolean isAbstained) {
		// this.isAbstained = isAbstained;
	}

	public float getTotalScore() {
		totalScore = getScoreMarkingTotal() + getSpeechScoreTotal()
				+ getTimeScoreTotal();
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		// this.totalScore = totalScore;
	}

	public float getSpeechScoreTotal() {
		speechScoreTotal = 0;
		try {
			for (SpeechScore speechScore : speechScores) {
				speechScoreTotal += speechScore.getScore()
						* speechScore.getScoreRuleItem().getWeight() / 100f;
			}
		} catch (NullPointerException e) {
			// null pointer is ok here
		}
		speechScoreTotal = Math.round(speechScoreTotal * 100) / 100f;
		return speechScoreTotal;
	}

	public void setSpeechScoreTotal(int speechScoreTotal) {
		// this.speechScoreTotal = speechScoreTotal;
	}

	public int getScoreMarkingTotal() {
		try {
			scoreMarkingTotal = Integer.parseInt(scoreMarking
					.getAudienceHelper())
					+ Integer.parseInt(scoreMarking.getFlashLightMarking())
					+ Integer.parseInt(scoreMarking.getRollCallMarking())
					+ Integer.parseInt(scoreMarking.getPhoneUsed())
					+ Integer.parseInt(scoreMarking.getSamePictureUsed());
		} catch (NullPointerException e) {
			// null pointer is ok here
		}
		return scoreMarkingTotal;
	}

	public void setScoreMarkingTotal(int scoreMarkingTotal) {
		// this.scoreMarkingTotal = scoreMarkingTotal;
	}

	public int getTimeScoreTotal() {
		timeScoreTotal = 0;
		try {
			int totalSecond = timeScore.getMinute() * 60
					+ timeScore.getSecond();
			if (0 == totalSecond) {
				return 0;
			}
			if (totalSecond > getJudge().getContestGroup().getTimeLimitRule()
					.getMaxLimit()
					|| totalSecond < getJudge().getContestGroup()
							.getTimeLimitRule().getMinLimit()) {
				timeScoreTotal = -1;
			}
		} catch (NullPointerException e) {
			// null pointer is OK here
		}
		return timeScoreTotal;
	}

	public void setTimeScoreTotal(int timeScoreTotal) {
		// this.timeScoreTotal = timeScoreTotal;
	}

	public List<SpeechScore> getSpeechScores() {
		return speechScores;
	}

	public void setSpeechScores(List<SpeechScore> speechScores) {
		this.speechScores = speechScores;
	}

	public ScoreMarking getScoreMarking() {
		return scoreMarking;
	}

	public void setScoreMarking(ScoreMarking scoreMarking) {
		this.scoreMarking = scoreMarking;
	}

	public TimeScore getTimeScore() {
		return timeScore;
	}

	public void setTimeScore(TimeScore timeScore) {
		this.timeScore = timeScore;
	}

	public String getIdcontestor_score() {
		return idcontestor_score;
	}

	public void setIdcontestor_score(String idcontestor_score) {
		this.idcontestor_score = idcontestor_score;
	}

	public Contestor getContestor() {
		return contestor;
	}

	public void setContestor(Contestor contestor) {
		this.contestor = contestor;
	}

	public Judge getJudge() {
		return judge;
	}

	public void setJudge(Judge judge) {
		this.judge = judge;
	}

	private float getScoreByPriority(int priority) {
		for (SpeechScore speechScore : getSpeechScores()) {
			if (priority == speechScore.getScoreRuleItem().getPriority()) {
				return speechScore.getScore();
			}
		}
		return 0;
	}

	public int compareTo(ContestorScore in) {
		float inScore = in.getSpeechScoreTotal() * 1000;
		float thisScore = getSpeechScoreTotal() * 1000;
		if (inScore != thisScore) {
			return (int) (inScore - thisScore);
		}
		int priority = 1;
		for (SpeechScore speechScore : getSpeechScores()) {
			thisScore = getScoreByPriority(priority) * 1000;
			inScore = in.getScoreByPriority(priority++) * 1000;
			if (inScore != thisScore) {
				return (int) (inScore - thisScore);
			}
		}
		return 0;
	}
}
