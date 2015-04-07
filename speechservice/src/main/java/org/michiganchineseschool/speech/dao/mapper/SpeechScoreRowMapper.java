package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.SpeechScore;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class SpeechScoreRowMapper implements RowMapper<SpeechScore> {

	@Override
	public SpeechScore mapRow(ResultSet rs, int line) throws SQLException {
		ResultSetExtractor<SpeechScore> extractor = new SpeechScoreResultSetExtractor();
		return extractor.extractData(rs);
	}

}
