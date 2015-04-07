package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.model.ContestorScore;

public interface ContestorScoreDao {
	public List<ContestorScore> selectAll() throws Exception;

	public void insert(ContestorScore record) throws Exception;

	public void update(ContestorScore record) throws Exception;

	public void delete(String id) throws Exception;

	public ContestorScore select(String id) throws Exception;

	public ContestorScore select(ContestorScore contestorScore)
			throws Exception;

	public List<ContestorScore> selectByContestGroupRoleJudge(
			String idcontestGroup, String idstaff, String idrole)
			throws Exception;

	public List<ContestorScore> selectByContestor(String idcontestor)
			throws Exception;

}
