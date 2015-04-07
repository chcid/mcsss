package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.model.ScoreRule;

public interface ScoreRuleDao {
	public List<ScoreRule> selectAll() throws Exception;

	public void insert(ScoreRule record) throws Exception;

	public void update(ScoreRule record) throws Exception;

	public void delete(String id) throws Exception;

	public ScoreRule select(String id) throws Exception;

	public ScoreRule getByLikeName(String name) throws Exception;

}
