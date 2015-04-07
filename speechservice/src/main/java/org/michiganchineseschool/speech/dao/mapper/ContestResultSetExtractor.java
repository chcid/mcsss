package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.Contest;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ContestResultSetExtractor implements ResultSetExtractor<Contest> {

	@Override
	public Contest extractData(ResultSet rs) throws SQLException {
		Contest record = new Contest();
		record.setIdcontest(rs.getString("IDCONTEST"));
		record.setName(rs.getString("NAME"));
		record.setActive(rs.getInt("ACTIVE"));
		return record;
	}
}