package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.dao.mapper.GradYearRowMapper;
import org.michiganchineseschool.speech.model.GradYear;

public class GradYearDaoImpl extends BaseDaoImpl implements GradYearDao {
	private final static String TableName = "grad_year";

	@Override
	public void insert(GradYear record) throws Exception {
		String sql = "INSERT INTO " + TableName + " ( NAME ) VALUES ( ? )";
		getJdbcTemplate().update(sql, new Object[] { record.getName() });
	}

	@Override
	public void update(GradYear record) throws Exception {
		String sql = "UPDATE " + TableName + " SET NAME = ? WHERE ID"
				+ TableName + " = ?";
		getJdbcTemplate().update(sql,
				new Object[] { record.getName(), record.getIdgrad_year() });
	}

	@Override
	public List<GradYear> selectAll() throws Exception {
		String sql = "SELECT * FROM " + TableName;
		return getJdbcTemplate().query(sql, new GradYearRowMapper());
	}

	@Override
	public void delete(String id) throws Exception {
		delete(id, TableName);
	}

	@Override
	public GradYear select(String id) throws Exception {
		if (null == id) {
			return new GradYear();
		}
		String sql = "Select * FROM " + TableName + " WHERE ID" + TableName
				+ " = " + id;
		return getJdbcTemplate().queryForObject(sql, new GradYearRowMapper());

	}
}
