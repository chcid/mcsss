package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.GradYear;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class GradYearRowMapper implements RowMapper<GradYear> {

	@Override
	public GradYear mapRow(ResultSet rs, int line) throws SQLException {
		ResultSetExtractor<GradYear> extractor = new GradYearResultSetExtractor();
		return extractor.extractData(rs);
	}
}
