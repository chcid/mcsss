package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.model.GradYear;

public interface GradYearDao {
	public List<GradYear> selectAll() throws Exception;

	public void insert(GradYear record) throws Exception;

	public void update(GradYear record) throws Exception;

	public void delete(String id) throws Exception;

	public GradYear select(String id) throws Exception;

}
