package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.TimeLimitRule;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class TimeLimitRuleRowMapper implements RowMapper<TimeLimitRule> {

	@Override
	public TimeLimitRule mapRow(ResultSet rs, int line) throws SQLException {
		ResultSetExtractor<TimeLimitRule> extractor = new TimeLimitRuleResultSetExtractor();
		return extractor.extractData(rs);
	}
}
