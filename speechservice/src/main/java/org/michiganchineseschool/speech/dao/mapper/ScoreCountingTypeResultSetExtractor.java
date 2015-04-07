package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.ScoreCountingType;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ScoreCountingTypeResultSetExtractor implements
		ResultSetExtractor<ScoreCountingType> {

	@Override
	public ScoreCountingType extractData(ResultSet rs) throws SQLException {
		ScoreCountingType record = new ScoreCountingType();
		record.setIdscore_counting_type(rs.getString("IDSCORE_COUNTING_TYPE"));
		record.setName(rs.getString("NAME"));
		return record;
	}
}