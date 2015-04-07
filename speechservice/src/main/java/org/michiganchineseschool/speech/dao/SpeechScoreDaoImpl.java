package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.dao.mapper.SpeechScoreRowMapper;
import org.michiganchineseschool.speech.model.SpeechScore;
import org.springframework.dao.EmptyResultDataAccessException;

public class SpeechScoreDaoImpl extends BaseDaoImpl implements SpeechScoreDao {
	private final static String TableName = "speech_score";

	@Override
	public void insert(SpeechScore record) throws Exception {
		String sql = "INSERT INTO "
				+ TableName
				+ " ( IDCONTESTOR_SCORE, IDSCORE_RULE_ITEM, SCORE ) VALUES ( ?, ?, ? )";
		getJdbcTemplate().update(
				sql,
				new Object[] {
						nullIdFilter(record.getContestorScore(),
								"contestor_score", "ContestorScore"),
						nullIdFilter(record.getScoreRuleItem(),
								"score_rule_item", "ScoreRuleItem"),
						record.getScore() });
	}

	@Override
	public void update(SpeechScore record) throws Exception {
		String sql = "UPDATE "
				+ TableName
				+ " SET IDCONTESTOR_SCORE = ?, IDSCORE_RULE_ITEM = ?, SCORE = ? WHERE ID"
				+ TableName + " = ?";
		getJdbcTemplate().update(
				sql,
				new Object[] {
						nullIdFilter(record.getContestorScore(),
								"contestor_score", "ContestorScore"),
						nullIdFilter(record.getScoreRuleItem(),
								"score_rule_item", "ScoreRuleItem"),
						record.getScore(), record.getIdspeech_score() });
	}

	@Override
	public List<SpeechScore> selectAll() throws Exception {
		String sql = "SELECT * FROM " + TableName;
		return getJdbcTemplate().query(sql, new SpeechScoreRowMapper());
	}

	@Override
	public void delete(String id) throws Exception {
		delete(id, TableName);
	}

	@Override
	public SpeechScore select(String id) throws Exception {
		if (null == id) {
			return new SpeechScore();
		}
		String sql = "Select * FROM " + TableName + " WHERE ID" + TableName
				+ " = " + id;
		return getJdbcTemplate()
				.queryForObject(sql, new SpeechScoreRowMapper());
	}

	@Override
	public SpeechScore selectByContestorRoleStaffScoreRuleItem(
			String idcontestor, String idrole, String idstaff,
			String idscoreRuleItem) throws Exception {
		String sql = "select ss.* from speech_score ss, contestor_score cs, judge j, role r, staff s,score_rule_item sri"
				+ " where ss.idcontestor_score = cs.idcontestor_score"
				+ " and ss.idscore_rule_item = sri.idscore_rule_item"
				+ " and cs.idjudge = j.idjudge"
				+ " and j.idstaff = s.idstaff"
				+ " and j.idrole = r.idrole"
				+ " and j.idrole = "
				+ idrole
				+ " and j.idstaff = "
				+ idstaff
				+ " and cs.idcontestor = "
				+ idcontestor
				+ " and sri.idscore_rule_item = "
				+ idscoreRuleItem;

		return getJdbcTemplate()
				.queryForObject(sql, new SpeechScoreRowMapper());
	}

	@Override
	public List<SpeechScore> selectByContestorScore(String id) throws Exception {
		String sql = "SELECT * FROM " + TableName
				+ " WHERE IDCONTESTOR_SCORE = " + id;
		return getJdbcTemplate().query(sql, new SpeechScoreRowMapper());
	}
}
