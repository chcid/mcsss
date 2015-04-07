package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.model.ContestGroup;

public interface ContestGroupDao {
	public List<ContestGroup> selectAll() throws Exception;

	public void insert(ContestGroup record) throws Exception;

	public void update(ContestGroup record) throws Exception;

	public void delete(String id) throws Exception;

	public ContestGroup select(String id) throws Exception;

	public List<ContestGroup> selectListForLoginedStaff(String idstaff,
			boolean isUnSubmitOnly) throws Exception;
	
	public List<ContestGroup> selectByActivateContest() throws Exception;

}
