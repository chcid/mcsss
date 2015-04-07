package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.ContestorIndividual;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class ContestorIndividualRowMapper implements
		RowMapper<ContestorIndividual> {

	@Override
	public ContestorIndividual mapRow(ResultSet rs, int line)
			throws SQLException {
		ResultSetExtractor<ContestorIndividual> extractor = new ContestorIndividualResultSetExtractor();
		return extractor.extractData(rs);
	}

}
