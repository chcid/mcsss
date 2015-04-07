package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.ScoreRule;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class ScoreRuleRowMapper implements RowMapper<ScoreRule> {

	@Override
	public ScoreRule mapRow(ResultSet rs, int line) throws SQLException {
		ResultSetExtractor<ScoreRule> extractor = new ScoreRuleResultSetExtractor();
		return extractor.extractData(rs);
	}
}
