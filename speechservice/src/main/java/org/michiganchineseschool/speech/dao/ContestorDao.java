package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.model.Contestor;
import org.michiganchineseschool.speech.model.ContestorIndividual;

public interface ContestorDao {
	public List<Contestor> selectAll() throws Exception;

	public void insert(Contestor record) throws Exception;

	public void update(Contestor record) throws Exception;

	public void delete(String id) throws Exception;

	public Contestor select(String id) throws Exception;

	public List<Contestor> selectByContestGroup(String idcontestGroup)
			throws Exception;

	public List<Contestor> selectByTitleAndContestGroup(String idContestGroup,
			String title) throws Exception;

	public Contestor selectByIdstudentAndContestGroupId(
			String idcontestGroup, String idstudent) throws Exception;

}
