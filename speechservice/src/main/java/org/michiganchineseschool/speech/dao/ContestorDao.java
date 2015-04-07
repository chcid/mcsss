package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.model.Contestor;

public interface ContestorDao {
	public List<Contestor> selectAll() throws Exception;

	public void insert(Contestor record) throws Exception;

	public void update(Contestor record) throws Exception;

	public void delete(String id) throws Exception;

	public Contestor select(String id) throws Exception;

	public List<Contestor> selectByContestGroup(String idcontestGroup)
			throws Exception;

}
