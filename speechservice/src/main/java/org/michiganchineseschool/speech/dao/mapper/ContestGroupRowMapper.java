package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.ContestGroup;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class ContestGroupRowMapper implements RowMapper<ContestGroup> {

	@Override
	public ContestGroup mapRow(ResultSet rs, int line) throws SQLException {
		ResultSetExtractor<ContestGroup> extractor = new ContestGroupResultSetExtractor();
		return extractor.extractData(rs);
	}

}
