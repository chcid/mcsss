package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.Role;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class RoleRowMapper implements RowMapper<Role> {

	@Override
	public Role mapRow(ResultSet rs, int line) throws SQLException {
		ResultSetExtractor<Role> extractor = new RoleResultSetExtractor();
		return extractor.extractData(rs);
	}
}
