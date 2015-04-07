package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.dao.mapper.RoleRowMapper;
import org.michiganchineseschool.speech.model.Role;

public class RoleDaoImpl extends BaseDaoImpl implements RoleDao {
	private final static String TableName = "role";

	@Override
	public void insert(Role record) throws Exception {
		String sql = "INSERT INTO " + TableName + " ( NAME ) VALUES ( ? )";
		getJdbcTemplate().update(sql, new Object[] { record.getName() });
	}

	@Override
	public void update(Role record) throws Exception {
		String sql = "UPDATE " + TableName + " SET NAME = ? WHERE ID"
				+ TableName + " = ?";
		getJdbcTemplate().update(sql,
				new Object[] { record.getName(), record.getIdrole() });
	}

	@Override
	public List<Role> selectAll() throws Exception {
		String sql = "SELECT * FROM " + TableName;
		return getJdbcTemplate().query(sql, new RoleRowMapper());
	}

	@Override
	public void delete(String id) throws Exception {
		delete(id, TableName);
	}

	@Override
	public Role select(String id) throws Exception {
		if (null == id) {
			return new Role();
		}
		String sql = "Select * FROM " + TableName + " WHERE ID" + TableName
				+ " = " + id;
		return getJdbcTemplate().queryForObject(sql, new RoleRowMapper());

	}
}
