package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.dao.mapper.TimeLimitRuleRowMapper;
import org.michiganchineseschool.speech.model.TimeLimitRule;
import org.springframework.util.StringUtils;

public class TimeLimitRuleDaoImpl extends BaseDaoImpl implements
		TimeLimitRuleDao {
	private final static String TableName = "time_limit_rule";

	@Override
	public void insert(TimeLimitRule record) throws Exception {
		String sql = "INSERT INTO " + TableName
				+ " ( MAX_LIMIT, MIN_LIMIT, NAME ) VALUES ( ?, ?, ? )";
		getJdbcTemplate().update(
				sql,
				new Object[] { record.getMaxLimit(), record.getMinLimit(),
						record.getName() });
	}

	@Override
	public void update(TimeLimitRule record) throws Exception {
		String sql = "UPDATE " + TableName
				+ " SET MAX_LIMIT = ? , MIN_LIMIT = ?, NAME = ? WHERE ID"
				+ TableName + " = ?";
		getJdbcTemplate().update(
				sql,
				new Object[] { record.getMaxLimit(), record.getMinLimit(),
						record.getName(), record.getIdtime_limit_rule() });
	}

	@Override
	public List<TimeLimitRule> selectAll() throws Exception {
		String sql = "SELECT * FROM " + TableName;
		return getJdbcTemplate().query(sql, new TimeLimitRuleRowMapper());
	}

	@Override
	public void delete(String id) throws Exception {
		delete(id, TableName);
	}

	@Override
	public TimeLimitRule select(String id) throws Exception {
		if (null == id) {
			return new TimeLimitRule();
		}
		String sql = "Select * FROM " + TableName + " WHERE ID" + TableName
				+ " = " + id;
		return getJdbcTemplate().queryForObject(sql,
				new TimeLimitRuleRowMapper());

	}

	@Override
	public TimeLimitRule getByName(String name) throws Exception {
		if (StringUtils.isEmpty(name) || name.trim().length() == 0) {
			return null;
		}
		String sql = "Select * FROM " + TableName + " WHERE NAME = '" + name
				+ "'";
		return getJdbcTemplate().queryForObject(sql,
				new TimeLimitRuleRowMapper());

	}
}
