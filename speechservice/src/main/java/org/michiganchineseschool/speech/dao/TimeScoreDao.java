package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.model.TimeScore;

public interface TimeScoreDao {
	public List<TimeScore> selectAll() throws Exception;

	public void insert(TimeScore record) throws Exception;

	public void update(TimeScore record) throws Exception;

	public void delete(String id) throws Exception;

	public TimeScore select(String id) throws Exception;

	public TimeScore selectByContestorStaffRole(String idcontestor,
			String idrole, String idstaff) throws Exception;

	public TimeScore selectByContestorScore(String id) throws Exception;

}
