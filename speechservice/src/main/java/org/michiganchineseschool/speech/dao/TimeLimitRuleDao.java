package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.model.TimeLimitRule;

public interface TimeLimitRuleDao {
	public List<TimeLimitRule> selectAll() throws Exception;

	public void insert(TimeLimitRule record) throws Exception;

	public void update(TimeLimitRule record) throws Exception;

	public void delete(String id) throws Exception;

	public TimeLimitRule select(String id) throws Exception;

	public TimeLimitRule getByName(String name) throws Exception;

}
