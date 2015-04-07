package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.model.ContestLocation;

public interface LocationDao {
	public List<ContestLocation> selectAll() throws Exception;

	public void insert(ContestLocation record) throws Exception;

	public void update(ContestLocation record) throws Exception;

	public void delete(String id) throws Exception;

	public ContestLocation select(String id) throws Exception;
	
	public ContestLocation getContestLocationByName(String name) throws Exception;

}
