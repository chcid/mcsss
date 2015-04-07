package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.dao.mapper.ContestRowMapper;
import org.michiganchineseschool.speech.model.Contest;

public class ContestDaoImpl extends BaseDaoImpl implements ContestDao {
	private final static String TableName = "contest";

	@Override
	public void insert(Contest record) throws Exception {
		String sql = "INSERT INTO " + TableName
				+ " ( NAME, ACTIVE ) VALUES ( ?, ? )";
		getJdbcTemplate().update(sql,
				new Object[] { record.getName(), record.getActive() });
	}

	@Override
	public void update(Contest record) throws Exception {
		String sql = "UPDATE " + TableName
				+ " SET NAME = ?, ACTIVE = ? WHERE ID" + TableName + " = ?";
		getJdbcTemplate().update(
				sql,
				new Object[] { record.getName(), record.getActive(),
						record.getIdcontest() });
	}

	@Override
	public List<Contest> selectAll() throws Exception {
		String sql = "SELECT * FROM " + TableName;
		return getJdbcTemplate().query(sql, new ContestRowMapper());
	}

	@Override
	public void delete(String id) throws Exception {
		// delete(id, TableName);
		String sql0 = "idcontest = " + id;
		String sql1 = "(select idcontest_group from contest_group cg where cg."
				+ sql0 + ")";
		String sql2 = "(select idcontestor from contestor where idcontest_group in "
				+ sql1 + ")";
		String sql3 = "(select idcontestor_score from contestor_score cs where cs.idcontestor in "
				+ sql2 + ")";
		String sql4 = " WHERE idcontestor_score in " + sql3;
		String deleteScoreMarkingSql = "DELETE FROM score_marking" + sql4;
		String deleteTimeScoreSql = "DELETE FROM time_score" + sql4;
		String deleteSpeechScoreSql = "DELETE FROM speech_score" + sql4;

		getJdbcTemplate().execute(deleteScoreMarkingSql);
		getJdbcTemplate().execute(deleteTimeScoreSql);
		getJdbcTemplate().execute(deleteSpeechScoreSql);

		String deleteContestorScoreSql = "DELETE FROM contestor_score WHERE idcontestor in "
				+ sql2;
		String deleteContestorIndividualSql = "DELETE FROM contestor_individual WHERE idcontestor in "
				+ sql2;

		getJdbcTemplate().execute(deleteContestorScoreSql);
		getJdbcTemplate().execute(deleteContestorIndividualSql);

		String deleteContestorSql = "DELETE FROM contestor WHERE idcontest_group in "
				+ sql1;
		String deleteJudgeSql = "DELETE FROM judge WHERE idcontest_group in "
				+ sql1;

		getJdbcTemplate().execute(deleteContestorSql);
		getJdbcTemplate().execute(deleteJudgeSql);

		String deleteContestGroupSql = "DELETE FROM contest_group WHERE "
				+ sql0;
		String deleteContestSql = "DELETE FROM contest WHERE " + sql0;
		getJdbcTemplate().execute(deleteContestGroupSql);
		getJdbcTemplate().execute(deleteContestSql);
	}

	@Override
	public Contest select(String id) throws Exception {
		if (null == id) {
			return new Contest();
		}
		String sql = "Select * FROM " + TableName + " WHERE ID" + TableName
				+ " = " + id;
		return getJdbcTemplate().queryForObject(sql, new ContestRowMapper());

	}
}
