package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.Staff;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class StaffRowMapper implements RowMapper<Staff> {

	@Override
	public Staff mapRow(ResultSet rs, int line) throws SQLException {
		ResultSetExtractor<Staff> extractor = new StaffResultSetExtractor();
		return extractor.extractData(rs);
	}

}
