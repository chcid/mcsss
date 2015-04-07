package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.TimeScore;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class TimeScoreRowMapper implements RowMapper<TimeScore> {

	@Override
	public TimeScore mapRow(ResultSet rs, int line) throws SQLException {
		ResultSetExtractor<TimeScore> extractor = new TimeScoreResultSetExtractor();
		return extractor.extractData(rs);
	}

}
