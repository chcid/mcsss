package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.dao.mapper.ScoreRuleItemRowMapper;
import org.michiganchineseschool.speech.model.ScoreRuleItem;
import org.springframework.dao.EmptyResultDataAccessException;

public class ScoreRuleItemDaoImpl extends BaseDaoImpl implements
		ScoreRuleItemDao {
	private final static String TableName = "score_rule_item";

	@Override
	public void insert(ScoreRuleItem record) throws Exception {
		String sql = "INSERT INTO "
				+ TableName
				+ " ( IDSCORE_RULE, NAME, WEIGHT, PRIORITY ) VALUES ( ?, ?, ?, ? )";
		getJdbcTemplate().update(
				sql,
				new Object[] { scoreRuleIdNullFilter(record), record.getName(),
						record.getWeight(), record.getPriority() });
	}

	private String scoreRuleIdNullFilter(ScoreRuleItem record) {
		try {
			return record.getScoreRule().getIdscore_rule();
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public void update(ScoreRuleItem record) throws Exception {
		String sql = "UPDATE "
				+ TableName
				+ " SET IDSCORE_RULE = ?, NAME = ?, WEIGHT = ?, PRIORITY = ? WHERE ID"
				+ TableName + " = ?";
		getJdbcTemplate().update(
				sql,
				new Object[] { scoreRuleIdNullFilter(record), record.getName(),
						record.getWeight(), record.getPriority(),
						record.getIdscore_rule_item() });
	}

	@Override
	public List<ScoreRuleItem> selectAll() throws Exception {
		String sql = "SELECT * FROM " + TableName;
		return getJdbcTemplate().query(sql, new ScoreRuleItemRowMapper());
	}

	@Override
	public void delete(String id) throws Exception {
		delete(id, TableName);
	}

	@Override
	public ScoreRuleItem select(String id) throws Exception {
		String sql = "Select * FROM " + TableName + " WHERE ID" + TableName
				+ " = " + id;
		return getJdbcTemplate().queryForObject(sql,
				new ScoreRuleItemRowMapper());
	}

	@Override
	public List<ScoreRuleItem> selectByContestGroup(String idcontestGroup)
			throws Exception {
		String sql = "select sri.* from score_rule sr, score_rule_item sri, contest_group cg"
				+ " where sr.idscore_rule = sri.idscore_rule"
				+ " and cg.idscore_rule = sr.idscore_rule"
				+ " and cg.idcontest_group = " + idcontestGroup;
		return getJdbcTemplate().query(sql, new ScoreRuleItemRowMapper());
	}
}
