package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.model.ScoreRuleItem;

public interface ScoreRuleItemDao {
	public List<ScoreRuleItem> selectAll() throws Exception;

	public void insert(ScoreRuleItem record) throws Exception;

	public void update(ScoreRuleItem record) throws Exception;

	public void delete(String id) throws Exception;

	public ScoreRuleItem select(String id) throws Exception;

	public List<ScoreRuleItem> selectByContestGroup(String idcontestGroup)
			throws Exception;
}
