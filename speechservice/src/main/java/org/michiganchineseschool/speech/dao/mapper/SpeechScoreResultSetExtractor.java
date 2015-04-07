package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.ContestorScore;
import org.michiganchineseschool.speech.model.ScoreRuleItem;
import org.michiganchineseschool.speech.model.SpeechScore;
import org.springframework.jdbc.core.ResultSetExtractor;

public class SpeechScoreResultSetExtractor implements
		ResultSetExtractor<SpeechScore> {

	@Override
	public SpeechScore extractData(ResultSet rs) throws SQLException {
		SpeechScore record = new SpeechScore();
		record.setIdspeech_score(rs.getString("IDSPEECH_SCORE"));
		ContestorScore contestorScore = new ContestorScore();
		contestorScore.setIdcontestor_score(rs.getString("IDCONTESTOR_SCORE"));
		record.setContestorScore(contestorScore);
		ScoreRuleItem scoreRuleItem = new ScoreRuleItem();
		scoreRuleItem.setIdscore_rule_item(rs.getString("IDSCORE_RULE_ITEM"));
		record.setScoreRuleItem(scoreRuleItem);
		record.setScore(rs.getInt("SCORE"));
		return record;
	}
}