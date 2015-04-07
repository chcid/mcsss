package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.ScoreMarking;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class ScoreMarkingRowMapper implements RowMapper<ScoreMarking> {

	@Override
	public ScoreMarking mapRow(ResultSet rs, int line) throws SQLException {
		ResultSetExtractor<ScoreMarking> extractor = new ScoreMarkingResultSetExtractor();
		return extractor.extractData(rs);
	}

}
