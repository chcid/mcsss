package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.model.Contest;
import org.michiganchineseschool.speech.model.ContestGroup;

public interface ContestDao {
	public List<Contest> selectAll() throws Exception;

	public void insert(Contest record) throws Exception;

	public void update(Contest record) throws Exception;

	public void delete(String id) throws Exception;

	public Contest select(String id) throws Exception;

}
