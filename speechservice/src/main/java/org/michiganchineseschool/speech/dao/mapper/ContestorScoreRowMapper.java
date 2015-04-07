package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.ContestorScore;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class ContestorScoreRowMapper implements RowMapper<ContestorScore> {

	@Override
	public ContestorScore mapRow(ResultSet rs, int line) throws SQLException {
		ResultSetExtractor<ContestorScore> extractor = new ContestorScoreResultSetExtractor();
		return extractor.extractData(rs);
	}

}
