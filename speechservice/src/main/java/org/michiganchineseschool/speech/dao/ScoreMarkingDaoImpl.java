package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.dao.mapper.ScoreMarkingRowMapper;
import org.michiganchineseschool.speech.model.ScoreMarking;

public class ScoreMarkingDaoImpl extends BaseDaoImpl implements ScoreMarkingDao {
	private final static String TableName = "score_marking";

	@Override
	public void insert(ScoreMarking record) throws Exception {
		String sql = "INSERT INTO "
				+ TableName
				+ " ( IDCONTESTOR_SCORE, ROLL_CALL_MARKING, FLASH_LIGHT_MARKING, AUDIENCE_HELPER, ABSENCE, PHONE_USED, SAME_PICTURE_USED ) VALUES ( ?, ?, ?, ?, ?, ?, ? )";
		getJdbcTemplate().update(
				sql,
				new Object[] {
						nullIdFilter(record.getContestorScore(),
								"contestor_score", "ContestorScore"),
						record.getRollCallMarking(),
						record.getFlashLightMarking(),
						record.getAudienceHelper(), record.getAbsence(),
						record.getPhoneUsed(), record.getSamePictureUsed() });
	}

	@Override
	public void update(ScoreMarking record) throws Exception {
		String sql = "UPDATE "
				+ TableName
				+ " SET IDCONTESTOR_SCORE = ?, ROLL_CALL_MARKING = ?, FLASH_LIGHT_MARKING = ?, AUDIENCE_HELPER = ?, ABSENCE = ?, PHONE_USED = ?, SAME_PICTURE_USED = ? WHERE ID"
				+ TableName + " = ?";
		getJdbcTemplate().update(
				sql,
				new Object[] {
						nullIdFilter(record.getContestorScore(),
								"contestor_score", "ContestorScore"),
						record.getRollCallMarking(),
						record.getFlashLightMarking(),
						record.getAudienceHelper(), record.getAbsence(),
						record.getPhoneUsed(), record.getSamePictureUsed(),
						record.getIdscore_marking() });
	}

	@Override
	public List<ScoreMarking> selectAll() throws Exception {
		String sql = "SELECT * FROM " + TableName;
		return getJdbcTemplate().query(sql, new ScoreMarkingRowMapper());
	}

	@Override
	public void delete(String id) throws Exception {
		delete(id, TableName);
	}

	@Override
	public ScoreMarking select(String id) throws Exception {
		if (null == id) {
			return new ScoreMarking();
		}
		String sql = "Select * FROM " + TableName + " WHERE ID" + TableName
				+ " = " + id;
		return getJdbcTemplate().queryForObject(sql,
				new ScoreMarkingRowMapper());
	}

	public ScoreMarking selectByContestorStaffRole(String idcontestor,
			String idrole, String idstaff) throws Exception {
		String sql = "select sm.* from contestor c, contestor_score sc, judge j, role r, staff s, score_marking sm"
				+ " where c.idcontestor = sc.idcontestor"
				+ " and j.idrole = r.idrole"
				+ " and j.idstaff = s.idstaff"
				+ " and sc.idjudge = j.idjudge"
				+ " and sm.idcontestor_score = sc.idcontestor_score"
				+ " and r.idrole = "
				+ idrole
				+ " and s.idstaff = "
				+ idstaff
				+ " and c.idcontestor = " + idcontestor;
		return getJdbcTemplate().queryForObject(sql,
				new ScoreMarkingRowMapper());
	}

	@Override
	public ScoreMarking selectByContestorScore(String id) throws Exception {
		if (null == id) {
			return new ScoreMarking();
		}
		String sql = "Select * FROM " + TableName
				+ " WHERE IDCONTESTOR_SCORE = " + id;
		return getJdbcTemplate().queryForObject(sql,
				new ScoreMarkingRowMapper());
	}
}
