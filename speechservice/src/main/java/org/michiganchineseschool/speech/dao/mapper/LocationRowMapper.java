package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.ContestLocation;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class LocationRowMapper implements RowMapper<ContestLocation> {

	@Override
	public ContestLocation mapRow(ResultSet rs, int line) throws SQLException {
		ResultSetExtractor<ContestLocation> extractor = new LocationResultSetExtractor();
		return extractor.extractData(rs);
	}

}
