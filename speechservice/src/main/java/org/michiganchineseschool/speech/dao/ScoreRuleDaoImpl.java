package org.michiganchineseschool.speech.dao;

import java.util.List;

import oracle.jdbc.proxy.annotation.GetDelegate;

import org.michiganchineseschool.speech.dao.mapper.ScoreRuleRowMapper;
import org.michiganchineseschool.speech.model.ScoreRule;
import org.springframework.util.StringUtils;

public class ScoreRuleDaoImpl extends BaseDaoImpl implements ScoreRuleDao {
	private final static String TableName = "score_rule";

	@Override
	public void insert(ScoreRule record) throws Exception {
		String sql = "INSERT INTO " + TableName + " ( NAME ) VALUES ( ? )";
		getJdbcTemplate().update(sql, new Object[] { record.getName() });
	}

	@Override
	public void update(ScoreRule record) throws Exception {
		String sql = "UPDATE " + TableName + " SET NAME = ? WHERE ID"
				+ TableName + " = ?";
		getJdbcTemplate().update(sql,
				new Object[] { record.getName(), record.getIdscore_rule() });
	}

	@Override
	public List<ScoreRule> selectAll() throws Exception {
		String sql = "SELECT * FROM " + TableName;
		return getJdbcTemplate().query(sql, new ScoreRuleRowMapper());
	}

	@Override
	public void delete(String id) throws Exception {
		delete(id, TableName);
	}

	@Override
	public ScoreRule select(String id) throws Exception {
		if (null == id) {
			return new ScoreRule();
		}
		String sql = "Select * FROM " + TableName + " WHERE ID" + TableName
				+ " = " + id;
		return getJdbcTemplate().queryForObject(sql, new ScoreRuleRowMapper());
	}

	@Override
	public ScoreRule getByLikeName(String name) throws Exception {
		if (StringUtils.isEmpty(name) || name.trim().length() == 0) {
			return null;
		}
		String sql = "Select * FROM " + TableName + " WHERE NAME LIKE '" + name
				+ "%'";
		return getJdbcTemplate().queryForObject(sql, new ScoreRuleRowMapper());
	}
}
