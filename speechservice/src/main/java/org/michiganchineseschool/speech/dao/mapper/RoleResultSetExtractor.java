package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.Role;
import org.springframework.jdbc.core.ResultSetExtractor;

public class RoleResultSetExtractor implements ResultSetExtractor<Role> {

	@Override
	public Role extractData(ResultSet rs) throws SQLException {
		Role record = new Role();
		record.setIdrole(rs.getString("IDROLE"));
		record.setName(rs.getString("NAME"));
		return record;
	}
}