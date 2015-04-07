package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.model.ScoreMarking;

public interface ScoreMarkingDao {
	public List<ScoreMarking> selectAll() throws Exception;

	public void insert(ScoreMarking record) throws Exception;

	public void update(ScoreMarking record) throws Exception;

	public void delete(String id) throws Exception;

	public ScoreMarking select(String id) throws Exception;

	public ScoreMarking selectByContestorStaffRole(String idcontestor,
			String idrole, String idstaff) throws Exception;

	public ScoreMarking selectByContestorScore(String id) throws Exception;

}
