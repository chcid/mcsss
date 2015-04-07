package org.michiganchineseschool.speech.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Contestor implements Serializable, Comparable<Contestor> {
	static final long serialVersionUID = 1l;
	private String idcontestor;
	private ContestGroup contestGroup;
	private String speechTitle;
	private int contestOrder;
	private List<Student> students;
	private List<ScoreRuleItem> scoreRuleItems;
	private float totalScore;
	private TimeScore timeScore;
	private ScoreMarking scoreMarking;

	private int judgeTimeScore;
	// //////////////////////
	// the following are for report
	private List<ContestorScore> contestorScores;
	private float totalSpeechScore;
	private int totalScoreMarking;
	private int totalTimeScore;
	private float finalScore;
	private int finalRank;
	private boolean isAbstained;

	private boolean isJudgeRanking = false;
	private float scoreDiffBefore;
	private float scoreDiffAfter;

	public float getScoreDiffBefore() {
		return roundIt(scoreDiffBefore);
	}

	public void setScoreDiffBefore(float scoreDiffBefore) {
		this.scoreDiffBefore = scoreDiffBefore;
	}

	public float getScoreDiffAfter() {
		return roundIt(scoreDiffAfter);
	}

	public void setScoreDiffAfter(float scoreDiffAfter) {
		this.scoreDiffAfter = scoreDiffAfter;
	}

	public boolean isJudgeRanking() {
		return isJudgeRanking;
	}

	public void setJudgeRanking(boolean isJudgeRanking) {
		this.isJudgeRanking = isJudgeRanking;
	}

	public int getJudgeTimeScore() {
		return judgeTimeScore;
	}

	public void setJudgeTimeScore(int judgeTimeScore) {
		this.judgeTimeScore = judgeTimeScore;
	}

	public boolean isAbstained() {
		return isAbstained;
	}

	public void setAbstained(boolean isAbstained) {
		this.isAbstained = isAbstained;
	}

	public int getFinalRank() {
		return finalRank;
	}

	public void setFinalRank(int finalRank) {
		this.finalRank = finalRank;
	}

	private ScoreRuleItem findTheScoreRuleItem(ScoreRuleItem scoreRuleItem,
			List<ScoreRuleItem> scoreRuleItemSums) {
		if (null == scoreRuleItemSums) {
			return null;
		}
		for (ScoreRuleItem sri : scoreRuleItemSums) {
			if (sri.getIdscore_rule_item().equals(
					scoreRuleItem.getIdscore_rule_item())) {
				return sri;
			}
		}
		return null;
	}

	public List<ScoreRuleItem> getScoreRuleItemSums() {
		List<ScoreRuleItem> scoreRuleItemSums = new ArrayList<ScoreRuleItem>();
		if (null == contestorScores) {
			return null;
		}
		// List<ScoreRuleItem> scoreRuleItemSums = new
		// ArrayList<ScoreRuleItem>();
		for (ContestorScore contestorScore : contestorScores) {
			List<SpeechScore> speechScores = contestorScore.getSpeechScores();
			if (null == speechScores) {
				continue;
				// speechScores = new ArrayList<SpeechScore>();
				// for (ScoreRuleItem scoreRuleItem :
				// contestGroup.getScoreRule()
				// .getScoreRuleItems()) {
				// SpeechScore speechScore = new SpeechScore();
				// speechScore.setScoreRuleItem(scoreRuleItem);
				// speechScores.add(speechScore);
				// }
			}
			for (SpeechScore speechScore : speechScores) {
				// if (0 >= speechScore.getScore()) {
				// continue;
				// }

				ScoreRuleItem sri = findTheScoreRuleItem(
						speechScore.getScoreRuleItem(), scoreRuleItemSums);
				if (null == sri) {
					sri = speechScore.getScoreRuleItem();
					SpeechScore sc = new SpeechScore();
					sc.setScore(speechScore.getScore());
					sc.setMax((int) speechScore.getScore());
					sc.setMin((int) speechScore.getScore());
					sri.setSpeechScore(sc);
					scoreRuleItemSums.add(sri);
				} else {
					sri.getSpeechScore().setScore(
							sri.getSpeechScore().getScore()
									+ speechScore.getScore());
					if (speechScore.getScore() > sri.getSpeechScore().getMax()) {
						sri.getSpeechScore().setMax(
								(int) speechScore.getScore());
					} else if (0 < speechScore.getScore()
							&& speechScore.getScore() < sri.getSpeechScore()
									.getMin()) {
						sri.getSpeechScore().setMin(
								(int) speechScore.getScore());
					}
				}
				if (0 < speechScore.getScore()) {
					sri.getSpeechScore().setNonZeroCount(
							sri.getSpeechScore().getNonZeroCount() + 1);
				}
			}
		}

		// get rid of the MAX and MIN
		for (ScoreRuleItem scoreRuleItem : scoreRuleItemSums) {
			float score = scoreRuleItem.getSpeechScore().getScore();
			int nonZeroCount = scoreRuleItem.getSpeechScore().getNonZeroCount();
			if ("2".equals(getContestGroup().getScoreCountingType()
					.getIdscore_counting_type()) && 3 <= nonZeroCount) {
				score -= (scoreRuleItem.getSpeechScore().getMax() + scoreRuleItem
						.getSpeechScore().getMin());
				nonZeroCount -= 2;
			}
			if (0 != nonZeroCount) {
				score /= nonZeroCount;
			} else {
				score = 0;
			}
			scoreRuleItem.getSpeechScore().setScore(score);
		}

		return scoreRuleItemSums;
	}

	public void setScoreRuleItemSums(List<ScoreRuleItem> scoreRuleItemSums) {
		// this.scoreRuleItemSums = scoreRuleItemSums;
	}

	public float getFinalScore() {
		finalScore = getTotalScoreMarking() + getTotalSpeechScore()
				+ getTotalTimeScore();
		return finalScore;
	}

	public void setFinalScore(int finalScore) {
		// this.finalScore = finalScore;
	}

	public int getTotalScoreMarking() {
		totalScoreMarking = 0;
		if (null == contestorScores) {
			return 0;
		}
		for (ContestorScore contestorScore : contestorScores) {
			try {
				if ("4".equals(contestorScore.getJudge().getRole().getIdrole())) {
					totalScoreMarking += contestorScore.getScoreMarkingTotal();
					setAbstained(contestorScore.isAbstained());
				}
			} catch (NullPointerException e) {
				// null pointer is ok here
			}
		}
		return totalScoreMarking;
	}

	public void setTotalScoreMarking(int totalScoreMarking) {
		// this.totalScoreMarking = totalScoreMarking;
	}

	public int getTotalTimeScore() {
		totalTimeScore = 0;
		if (null == contestorScores) {
			return 0;
		}
		for (ContestorScore contestorScore : contestorScores) {
			try {
				if ("2".equals(contestorScore.getJudge().getRole().getIdrole())) {
					totalTimeScore += contestorScore.getTimeScoreTotal();
				}
			} catch (NullPointerException e) {
				// null pointer is ok here
			}
		}
		return totalTimeScore;
	}

	public void setTotalTimeScore(int totalTimeScore) {
		// this.totalTimeScore = totalTimeScore;
	}

	/**
	 * @return
	 */
	public float getTotalSpeechScore() {
		totalSpeechScore = 0;
		List<ScoreRuleItem> items = getScoreRuleItemSums();
		// if (null == items) {
		// return 0;
		// }
		// for (ScoreRuleItem scoreRuleItem : items) {
		// totalSpeechScore += scoreRuleItem.getSpeechScore().getScore()
		// * scoreRuleItem.getWeight() / 100f;
		// }

		if (null == contestorScores) {
			return 0;
		}
		// int nonZeroCount = 0;
		List<ContestorScore> sortingList = new ArrayList<ContestorScore>();
		float max = -100f;
		float min = 200f;

		for (ContestorScore contestorScore : contestorScores) {
			try {
				if ("1".equals(contestorScore.getJudge().getRole().getIdrole())
						|| "4".equals(contestorScore.getJudge().getRole()
								.getIdrole())) {
					if (0 < contestorScore.getSpeechScoreTotal()) {
						totalSpeechScore += contestorScore
								.getSpeechScoreTotal();
						sortingList.add(contestorScore);
					}
				}
			} catch (NullPointerException e) { // null pointer is ok here
			}
		}
		if (0 == sortingList.size()) {
			return 0;
		}
		// sort the list to get the Max and min
		Collections.sort(sortingList);
		sortingList.get(0).setJudgeMax(true);
		sortingList.get(sortingList.size() - 1).setJudgeMin(true);

		if (!"2".equals(getContestGroup().getScoreCountingType()
				.getIdscore_counting_type()) || 2 >= sortingList.size()) {
			// is not get rid of max and min
			// or too few judge to do the geting rid of max and min
			return roundIt(totalSpeechScore / (float) sortingList.size());
		}

		return roundIt((totalSpeechScore
				- sortingList.get(0).getSpeechScoreTotal() - sortingList.get(
				sortingList.size() - 1).getSpeechScoreTotal())
				/ (float) (sortingList.size() - 2));
	}

	private float roundIt(float in) {
		return Math.round(in * 10000) / 10000f;
	}

	public void setTotalSpeechScore(int totalSpeechScore) {
		// this.totalSpeechScore = totalSpeechScore;
	}

	public int getTotalMarking() {
		return totalMarking;
	}

	public void setTotalMarking(int totalMarking) {
		this.totalMarking = totalMarking;
	}

	private int totalMarking;

	public List<ContestorScore> getContestorScores() {
		return contestorScores;
	}

	public void setContestorScores(List<ContestorScore> contestorScores) {
		this.contestorScores = contestorScores;
	}

	public TimeScore getTimeScore() {
		return timeScore;
	}

	public void setTimeScore(TimeScore timeScore) {
		this.timeScore = timeScore;
	}

	public ScoreMarking getScoreMarking() {
		return scoreMarking;
	}

	public void setScoreMarking(ScoreMarking scoreMarking) {
		this.scoreMarking = scoreMarking;
	}

	private float getAverageRuleItemScoreByPriority(int priority) {
		if (null == contestorScores) {
			return 0;
		}
		float count = 0;
		float total = 0;
		for (ContestorScore contestorScore : contestorScores) {
			try {
				if ("1".equals(contestorScore.getJudge().getRole().getIdrole())
						|| "4".equals(contestorScore.getJudge().getRole()
								.getIdrole())) {
					for (SpeechScore speechScore : contestorScore
							.getSpeechScores()) {
						if (priority == speechScore.getScoreRuleItem()
								.getPriority()) {
							if (0 != speechScore.getScore()) {
								total += speechScore.getScore();
								count++;
							}
						}
					}
				}
			} catch (NullPointerException e) {
				// null pointer is ok here
			}
		}
		if (0 != count) {
			return (total / count);
		}
		return 0;
	}

	public float getTotalScore() {
		totalScore = 0;
		try {
			for (ScoreRuleItem scoreRuleItem : getScoreRuleItems()) {
				totalScore += scoreRuleItem.getSpeechScore().getScore()
						* scoreRuleItem.getWeight() / 100f;
			}
		} catch (NullPointerException e) {
			// null pointer is fine here
		}
		totalScore = roundIt(totalScore);
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<ScoreRuleItem> getScoreRuleItems() {
		return scoreRuleItems;
	}

	public void setScoreRuleItems(List<ScoreRuleItem> scoreRuleItems) {
		this.scoreRuleItems = scoreRuleItems;
	}

	public String getIdcontestor() {
		return idcontestor;
	}

	public void setIdcontestor(String idcontestor) {
		this.idcontestor = idcontestor;
	}

	public ContestGroup getContestGroup() {
		return contestGroup;
	}

	public void setContestGroup(ContestGroup contestGroup) {
		this.contestGroup = contestGroup;
	}

	public String getSpeechTitle() {
		return speechTitle;
	}

	public void setSpeechTitle(String speechTitle) {
		this.speechTitle = speechTitle;
	}

	public int getContestOrder() {
		return contestOrder;
	}

	public void setContestOrder(int contestrder) {
		this.contestOrder = contestrder;
	}

	public int compareTo(Contestor compareContestor) {
		if (isJudgeRanking()) {
			return (int) (compareContestor.getTotalScore() * 10000f)
					- (int) (this.getTotalScore() * 10000f);
		}
		float finalScore = compareContestor.getFinalScore();
		if (finalScore * 10000f != this.getFinalScore() * 10000f) {
			return (int) (finalScore * 10000f)
					- (int) (this.getFinalScore() * 10000f);
		}
		// Compare the Average of #A then #B then #C in the
		for (int priority = 1; priority <= getScoreRuleItemSums().size(); priority++) {

			float pScore = 1000 * compareContestor
					.getAverageRuleItemScoreByPriority(priority);
			float thisPScore = 1000 * this
					.getAverageRuleItemScoreByPriority(priority);
			if (pScore != thisPScore) {
				return (int) (pScore - thisPScore);
			}
		}
		return 0;
	}
}
