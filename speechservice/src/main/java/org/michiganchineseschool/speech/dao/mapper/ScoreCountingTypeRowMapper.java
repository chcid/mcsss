package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.ScoreCountingType;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class ScoreCountingTypeRowMapper implements RowMapper<ScoreCountingType> {

	@Override
	public ScoreCountingType mapRow(ResultSet rs, int line) throws SQLException {
		ResultSetExtractor<ScoreCountingType> extractor = new ScoreCountingTypeResultSetExtractor();
		return extractor.extractData(rs);
	}
}
