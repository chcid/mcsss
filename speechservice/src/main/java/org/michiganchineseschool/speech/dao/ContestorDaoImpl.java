package org.michiganchineseschool.speech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.michiganchineseschool.speech.dao.mapper.ContestorRowMapper;
import org.michiganchineseschool.speech.model.Contestor;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class ContestorDaoImpl extends BaseDaoImpl implements ContestorDao {
	private final static String TableName = "contestor";

	@Override
	public void insert(Contestor record) throws Exception {
		final String sql = "INSERT INTO "
				+ TableName
				+ " ( IDCONTEST_GROUP, SPEECH_TITLE, CONTEST_ORDER ) VALUES ( "
				+ nullIdFilter(record.getContestGroup(), "contest_group",
						"ContestGroup") + ", '" + record.getSpeechTitle()
				+ "', " + record.getContestOrder() + " )";
		// getJdbcTemplate().update(
		// sql,
		// new Object[] {
		// nullIdFilter(record.getContestGroup(), "contest_group",
		// "ContestGroup"), record.getSpeechTitle(),
		// record.getContestOrder() });

		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				return connection.prepareStatement(sql,
						new String[] { "idcontestor" });
			}
		}, keyHolder);
		record.setIdcontestor(keyHolder.getKey().toString());
	}

	@Override
	public void update(Contestor record) throws Exception {
		String sql = "UPDATE "
				+ TableName
				+ " SET IDCONTEST_GROUP = ?, SPEECH_TITLE = ?, CONTEST_ORDER = ? WHERE ID"
				+ TableName + " = ?";
		getJdbcTemplate().update(
				sql,
				new Object[] {
						nullIdFilter(record.getContestGroup(), "contest_group",
								"ContestGroup"), record.getSpeechTitle(),
						record.getContestOrder(), record.getIdcontestor() });
	}

	@Override
	public List<Contestor> selectAll() throws Exception {
		String sql = "SELECT * FROM " + TableName;
		return getJdbcTemplate().query(sql, new ContestorRowMapper());
	}

	@Override
	public void delete(String id) throws Exception {
		delete(id, TableName);
	}

	@Override
	public Contestor select(String id) throws Exception {
		if (null == id) {
			return new Contestor();
		}
		String sql = "Select * FROM " + TableName + " WHERE ID" + TableName
				+ " = " + id;
		return getJdbcTemplate().queryForObject(sql, new ContestorRowMapper());
	}

	@Override
	public List<Contestor> selectByContestGroup(String idcontestGroup)
			throws Exception {
		String sql = "SELECT * FROM " + TableName + " where idcontest_group = "
				+ idcontestGroup;
		return getJdbcTemplate().query(sql, new ContestorRowMapper());
	}

	@Override
	public List<Contestor> selectByTitleAndContestGroup(String idContestGroup,
			String title) throws Exception {
		if (null == idContestGroup || null == title
				|| idContestGroup.trim().length() == 0
				|| title.trim().length() == 0) {
			return null;
		}
		String sql = "Select * FROM " + TableName + " WHERE IDCONTEST_GROUP"
				+ " = " + idContestGroup + " AND SPEECH_TITLE = '" + title
				+ "'";
		return getJdbcTemplate().query(sql, new ContestorRowMapper());
	}
}
