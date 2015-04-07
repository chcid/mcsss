package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.ContestLocation;
import org.springframework.jdbc.core.ResultSetExtractor;

public class LocationResultSetExtractor implements
		ResultSetExtractor<ContestLocation> {

	@Override
	public ContestLocation extractData(ResultSet rs) throws SQLException {
		ContestLocation record = new ContestLocation();
		record.setIdcontest_location(rs.getString("IDCONTEST_LOCATION"));
		record.setName(rs.getString("NAME"));
		return record;
	}
}