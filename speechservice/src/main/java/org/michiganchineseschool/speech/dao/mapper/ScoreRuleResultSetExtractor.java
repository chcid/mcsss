package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.ScoreRule;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ScoreRuleResultSetExtractor implements
		ResultSetExtractor<ScoreRule> {

	@Override
	public ScoreRule extractData(ResultSet rs) throws SQLException {
		ScoreRule record = new ScoreRule();
		record.setIdscore_rule(rs.getString("IDSCORE_RULE"));
		record.setName(rs.getString("NAME"));
		return record;
	}
}