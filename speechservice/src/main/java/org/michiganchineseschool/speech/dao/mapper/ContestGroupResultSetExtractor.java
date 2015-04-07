package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.Contest;
import org.michiganchineseschool.speech.model.ContestGroup;
import org.michiganchineseschool.speech.model.ContestLocation;
import org.michiganchineseschool.speech.model.Judge;
import org.michiganchineseschool.speech.model.Role;
import org.michiganchineseschool.speech.model.ScoreCountingType;
import org.michiganchineseschool.speech.model.ScoreRule;
import org.michiganchineseschool.speech.model.TimeLimitRule;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ContestGroupResultSetExtractor implements
		ResultSetExtractor<ContestGroup> {

	@Override
	public ContestGroup extractData(ResultSet rs) throws SQLException {
		ContestGroup record = new ContestGroup();
		record.setIdcontest_group(rs.getString("IDCONTEST_GROUP"));
		Contest contest = new Contest();
		contest.setIdcontest(rs.getString("IDCONTEST"));
		record.setContest(contest);
		ContestLocation contestLocation = new ContestLocation();
		contestLocation.setIdcontest_location(rs.getString("IDLOCATION"));
		record.setContestLocation(contestLocation);
		TimeLimitRule timeLimitRule = new TimeLimitRule();
		timeLimitRule.setIdtime_limit_rule(rs.getString("IDTIME_LIMIT_RULE"));
		record.setTimeLimitRule(timeLimitRule);
		record.setName(rs.getString("NAME"));
		ScoreRule scoreRule = new ScoreRule();
		scoreRule.setIdscore_rule(rs.getString("IDSCORE_RULE"));
		record.setScoreRule(scoreRule);
		ScoreCountingType scoreCountingType = new ScoreCountingType();
		scoreCountingType.setIdscore_counting_type(rs
				.getString("IDSCORE_COUNTING_TYPE"));
		record.setScoreCountingType(scoreCountingType);
		try {
			Role role = new Role();
			role.setIdrole(rs.getString("IDROLE"));
			record.setRole(role);
		} catch (Exception e) {
			// ok to throw exception here
		}
		try {
			Judge judge = new Judge();
			judge.setIdjudge(rs.getString("IDJUDGE"));
			record.setJudge(judge);
		} catch (Exception e) {
			// ok to throw exception here
		}
		return record;
	}
}