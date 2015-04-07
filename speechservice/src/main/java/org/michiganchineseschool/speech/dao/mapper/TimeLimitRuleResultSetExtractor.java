package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.TimeLimitRule;
import org.springframework.jdbc.core.ResultSetExtractor;

public class TimeLimitRuleResultSetExtractor implements
		ResultSetExtractor<TimeLimitRule> {

	@Override
	public TimeLimitRule extractData(ResultSet rs) throws SQLException {
		TimeLimitRule record = new TimeLimitRule();
		record.setIdtime_limit_rule(rs.getString("IDTIME_LIMIT_RULE"));
		record.setMaxLimit(rs.getInt("MAX_LIMIT"));
		record.setMinLimit(rs.getInt("MIN_LIMIT"));
		record.setName(rs.getString("NAME"));
		return record;
	}
}