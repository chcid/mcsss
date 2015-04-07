package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.model.Role;

public interface RoleDao {
	public List<Role> selectAll() throws Exception;

	public void insert(Role record) throws Exception;

	public void update(Role record) throws Exception;

	public void delete(String id) throws Exception;

	public Role select(String id) throws Exception;

}
