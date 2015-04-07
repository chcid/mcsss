package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.dao.mapper.TimeScoreRowMapper;
import org.michiganchineseschool.speech.model.TimeScore;

public class TimeScoreDaoImpl extends BaseDaoImpl implements TimeScoreDao {
	private final static String TableName = "time_score";

	@Override
	public void insert(TimeScore record) throws Exception {
		String sql = "INSERT INTO " + TableName
				+ " ( IDCONTESTOR_SCORE, MINUTE, SECOND ) VALUES ( ?, ?, ? )";
		getJdbcTemplate().update(
				sql,
				new Object[] {
						nullIdFilter(record.getContestorScore(),
								"contestor_score", "ContestorScore"),
						record.getMinute(), record.getSecond() });
	}

	@Override
	public void update(TimeScore record) throws Exception {
		String sql = "UPDATE " + TableName
				+ " SET IDCONTESTOR_SCORE = ?, MINUTE = ?, SECOND = ? WHERE ID"
				+ TableName + " = ?";
		getJdbcTemplate().update(
				sql,
				new Object[] {
						nullIdFilter(record.getContestorScore(),
								"contestor_score", "ContestorScore"),
						record.getMinute(), record.getSecond(),
						record.getIdtime_score() });
	}

	@Override
	public List<TimeScore> selectAll() throws Exception {
		String sql = "SELECT * FROM " + TableName;
		return getJdbcTemplate().query(sql, new TimeScoreRowMapper());
	}

	@Override
	public void delete(String id) throws Exception {
		delete(id, TableName);
	}

	@Override
	public TimeScore select(String id) throws Exception {
		if (null == id) {
			return new TimeScore();
		}
		String sql = "Select * FROM " + TableName + " WHERE ID" + TableName
				+ " = " + id;
		return getJdbcTemplate().queryForObject(sql, new TimeScoreRowMapper());
	}

	@Override
	public TimeScore selectByContestorStaffRole(String idcontestor,
			String idrole, String idstaff) throws Exception {
		String sql = "select ts.* from contestor c, contestor_score sc, judge j, role r, staff s, time_score ts"
				+ " where c.idcontestor = sc.idcontestor"
				+ " and j.idrole = r.idrole"
				+ " and j.idstaff = s.idstaff"
				+ " and sc.idjudge = j.idjudge"
				+ " and ts.idcontestor_score = sc.idcontestor_score"
				+ " and r.idrole = "
				+ idrole
				+ " and s.idstaff = "
				+ idstaff
				+ " and c.idcontestor = " + idcontestor;
		return getJdbcTemplate().queryForObject(sql, new TimeScoreRowMapper());
	}

	@Override
	public TimeScore selectByContestorScore(String id) throws Exception {
		if (null == id) {
			return new TimeScore();
		}
		String sql = "Select * FROM " + TableName
				+ " WHERE IDCONTESTOR_SCORE = " + id;
		return getJdbcTemplate().queryForObject(sql, new TimeScoreRowMapper());
	}
}
