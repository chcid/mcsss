package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.ScoreRuleItem;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class ScoreRuleItemRowMapper implements RowMapper<ScoreRuleItem> {

	@Override
	public ScoreRuleItem mapRow(ResultSet rs, int line) throws SQLException {
		ResultSetExtractor<ScoreRuleItem> extractor = new ScoreRuleItemResultSetExtractor();
		return extractor.extractData(rs);
	}
}
