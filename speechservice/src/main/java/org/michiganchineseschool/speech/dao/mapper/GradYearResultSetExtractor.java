package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.GradYear;
import org.springframework.jdbc.core.ResultSetExtractor;

public class GradYearResultSetExtractor implements ResultSetExtractor<GradYear> {

	@Override
	public GradYear extractData(ResultSet rs) throws SQLException {
		GradYear record = new GradYear();
		record.setIdgrad_year(rs.getString("IDGRAD_YEAR"));
		record.setName(rs.getString("NAME"));
		return record;
	}
}