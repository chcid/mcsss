package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.dao.mapper.ContestorScoreRowMapper;
import org.michiganchineseschool.speech.model.ContestorScore;

public class ContestorScoreDaoImpl extends BaseDaoImpl implements
		ContestorScoreDao {
	private final static String TableName = "contestor_score";

	@Override
	public void insert(ContestorScore record) throws Exception {
		String sql = "INSERT INTO " + TableName
				+ " ( IDCONTESTOR, IDJUDGE ) VALUES ( ?, ?)";
		getJdbcTemplate().update(
				sql,
				new Object[] {
						nullIdFilter(record.getContestor(), "contestor",
								"Contestor"),
						nullIdFilter(record.getJudge(), "judge", "Judge") });
	}

	@Override
	public void update(ContestorScore record) throws Exception {
		String sql = "UPDATE " + TableName
				+ " SET IDCONTESTOR = ?, IDJUDGE = ? WHERE ID" + TableName
				+ " = ?";
		getJdbcTemplate().update(
				sql,
				new Object[] {
						nullIdFilter(record.getContestor(), "contestor",
								"Contestor"),
						nullIdFilter(record.getJudge(), "judge", "Judge"),
						record.getIdcontestor_score() });
	}

	@Override
	public List<ContestorScore> selectAll() throws Exception {
		String sql = "SELECT * FROM " + TableName;
		return getJdbcTemplate().query(sql, new ContestorScoreRowMapper());
	}

	@Override
	public void delete(String id) throws Exception {
		delete(id, TableName);
	}

	@Override
	public ContestorScore select(String id) throws Exception {
		if (null == id) {
			return new ContestorScore();
		}
		String sql = "Select * FROM " + TableName + " WHERE ID" + TableName
				+ " = " + id;
		return getJdbcTemplate().queryForObject(sql,
				new ContestorScoreRowMapper());
	}

	@Override
	public ContestorScore select(ContestorScore contestorScore)
			throws Exception {
		String sql = "Select * FROM " + TableName + " WHERE IDCONTESTOR = "
				+ contestorScore.getContestor().getIdcontestor()
				+ " and IDJUDGE = " + contestorScore.getJudge().getIdjudge();
		return getJdbcTemplate().queryForObject(sql,
				new ContestorScoreRowMapper());
	}

	@Override
	public List<ContestorScore> selectByContestGroupRoleJudge(
			String idcontestGroup, String idstaff, String idrole)
			throws Exception {
		String sql = "select null as idcontestor_score, c.idcontestor, j.idjudge from contestor c, contest_group cg, judge j, role r, staff s"
				+ " where c.idcontest_group = cg.idcontest_group"
				+ " and j.idcontest_group = cg.idcontest_group"
				+ " and j.idrole = r.idrole"
				+ " and j.idstaff = s.idstaff"
				+ " and r.idrole = "
				+ idrole
				+ " and s.idstaff = "
				+ idstaff
				+ " and cg.idcontest_group = " + idcontestGroup;
		return getJdbcTemplate().query(sql, new ContestorScoreRowMapper());
	}

	@Override
	public List<ContestorScore> selectByContestor(String idcontestor)
			throws Exception {
		if (null == idcontestor) {
			return null;
		}
		String sql = "SELECT * FROM " + TableName + " WHERE IDCONTESTOR = "
				+ idcontestor;
		return getJdbcTemplate().query(sql, new ContestorScoreRowMapper());
	}
}
