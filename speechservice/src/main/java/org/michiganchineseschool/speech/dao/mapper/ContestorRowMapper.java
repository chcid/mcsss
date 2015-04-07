package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.Contestor;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class ContestorRowMapper implements RowMapper<Contestor> {

	@Override
	public Contestor mapRow(ResultSet rs, int line) throws SQLException {
		ResultSetExtractor<Contestor> extractor = new ContestorResultSetExtractor();
		return extractor.extractData(rs);
	}

}
