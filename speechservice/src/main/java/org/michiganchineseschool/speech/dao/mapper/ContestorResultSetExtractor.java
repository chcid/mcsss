package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.ContestGroup;
import org.michiganchineseschool.speech.model.Contestor;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ContestorResultSetExtractor implements
		ResultSetExtractor<Contestor> {

	@Override
	public Contestor extractData(ResultSet rs) throws SQLException {
		Contestor record = new Contestor();
		record.setIdcontestor(rs.getString("IDCONTESTOR"));
		ContestGroup contestGroup = new ContestGroup();
		contestGroup.setIdcontest_group(rs.getString("IDCONTEST_GROUP"));
		record.setContestGroup(contestGroup);
		record.setSpeechTitle(rs.getString("SPEECH_TITLE"));
		record.setContestOrder(rs.getInt("CONTEST_ORDER"));
		return record;
	}
}