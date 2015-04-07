package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.model.SpeechScore;

public interface SpeechScoreDao {
	public List<SpeechScore> selectAll() throws Exception;

	public void insert(SpeechScore record) throws Exception;

	public void update(SpeechScore record) throws Exception;

	public void delete(String id) throws Exception;

	public SpeechScore select(String id) throws Exception;

	public SpeechScore selectByContestorRoleStaffScoreRuleItem(
			String idcontestor, String idrole, String idstaff,
			String idscoreRuleItem) throws Exception;

	public List<SpeechScore> selectByContestorScore(String id) throws Exception;

}
