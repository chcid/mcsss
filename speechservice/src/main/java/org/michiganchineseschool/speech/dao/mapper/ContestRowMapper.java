package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.Contest;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class ContestRowMapper implements RowMapper<Contest> {

	@Override
	public Contest mapRow(ResultSet rs, int line) throws SQLException {
		ResultSetExtractor<Contest> extractor = new ContestResultSetExtractor();
		return extractor.extractData(rs);
	}

}
