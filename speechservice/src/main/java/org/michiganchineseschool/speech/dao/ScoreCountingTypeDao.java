package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.model.ScoreCountingType;

public interface ScoreCountingTypeDao {
	public List<ScoreCountingType> selectAll() throws Exception;

	public void insert(ScoreCountingType record) throws Exception;

	public void update(ScoreCountingType record) throws Exception;

	public void delete(String id) throws Exception;

	public ScoreCountingType select(String id) throws Exception;

	public ScoreCountingType getByLikeName(String name) throws Exception;

}
