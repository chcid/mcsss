package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.model.ContestorIndividual;

public interface ContestorIndividualDao {
	public List<ContestorIndividual> selectAll() throws Exception;

	public void insert(ContestorIndividual record) throws Exception;

	public void update(ContestorIndividual record) throws Exception;

	public void delete(String id) throws Exception;

	public ContestorIndividual select(String id) throws Exception;

}
