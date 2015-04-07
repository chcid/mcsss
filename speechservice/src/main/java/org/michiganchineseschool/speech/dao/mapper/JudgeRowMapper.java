package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.Judge;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class JudgeRowMapper implements RowMapper<Judge> {

	@Override
	public Judge mapRow(ResultSet rs, int line) throws SQLException {
		ResultSetExtractor<Judge> extractor = new JudgeResultSetExtractor();
		return extractor.extractData(rs);
	}

}
