package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.ContestorScore;
import org.michiganchineseschool.speech.model.TimeScore;
import org.springframework.jdbc.core.ResultSetExtractor;

public class TimeScoreResultSetExtractor implements
		ResultSetExtractor<TimeScore> {

	@Override
	public TimeScore extractData(ResultSet rs) throws SQLException {
		TimeScore record = new TimeScore();
		record.setIdtime_score(rs.getString("IDTIME_SCORE"));
		ContestorScore contestorScore = new ContestorScore();
		contestorScore.setIdcontestor_score(rs.getString("IDCONTESTOR_SCORE"));
		record.setContestorScore(contestorScore);
		record.setMinute(rs.getInt("MINUTE"));
		record.setSecond(rs.getInt("SECOND"));
		return record;
	}
}