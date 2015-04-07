package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.ScoreRule;
import org.michiganchineseschool.speech.model.ScoreRuleItem;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ScoreRuleItemResultSetExtractor implements
		ResultSetExtractor<ScoreRuleItem> {

	@Override
	public ScoreRuleItem extractData(ResultSet rs) throws SQLException {
		ScoreRuleItem record = new ScoreRuleItem();
		record.setIdscore_rule_item(rs.getString("IDSCORE_RULE_ITEM"));
		record.setName(rs.getString("NAME"));
		record.setWeight(rs.getInt("WEIGHT"));
		ScoreRule scoreRule = new ScoreRule();
		scoreRule.setIdscore_rule(rs.getString("IDSCORE_RULE"));
		record.setScoreRule(scoreRule);
		record.setPriority(rs.getInt("PRIORITY"));
		return record;
	}
}