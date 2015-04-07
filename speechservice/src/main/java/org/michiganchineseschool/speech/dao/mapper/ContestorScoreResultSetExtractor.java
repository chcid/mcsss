package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.Contestor;
import org.michiganchineseschool.speech.model.ContestorScore;
import org.michiganchineseschool.speech.model.Judge;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ContestorScoreResultSetExtractor implements
		ResultSetExtractor<ContestorScore> {

	@Override
	public ContestorScore extractData(ResultSet rs) throws SQLException {
		ContestorScore record = new ContestorScore();
		record.setIdcontestor_score(rs.getString("IDCONTESTOR_SCORE"));
		Contestor contestor = new Contestor();
		contestor.setIdcontestor(rs.getString("IDCONTESTOR"));
		record.setContestor(contestor);
		Judge judge = new Judge();
		judge.setIdjudge(rs.getString("IDJUDGE"));
		record.setJudge(judge);
		return record;
	}
}