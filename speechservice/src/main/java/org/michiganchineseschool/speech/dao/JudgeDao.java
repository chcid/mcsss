package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.model.Judge;

public interface JudgeDao {
	public List<Judge> selectAll() throws Exception;

	public void insert(Judge record) throws Exception;

	public void update(Judge record) throws Exception;

	public void delete(String id) throws Exception;

	public Judge select(String id) throws Exception;

}
