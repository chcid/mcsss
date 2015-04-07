package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.michiganchineseschool.speech.dao.mapper.StaffRowMapper;
import org.michiganchineseschool.speech.model.Staff;
import org.michiganchineseschool.util.StringUtil;
import org.springframework.util.StringUtils;

public class StaffDaoImpl extends BaseDaoImpl implements StaffDao {
	private final static String TableName = "staff";
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public void insert(Staff record) throws Exception {
		String sql = "INSERT INTO "
				+ TableName
				+ " ( CHINESE_LASTNAME, CHINESE_FIRSTNAME, ENGLISH_LASTNAME, ENGLISH_FIRSTNAME, PASSWORD, FAILED_ATTEMPT ) VALUES ( ?,?,?,?,?,? )";
		getJdbcTemplate().update(
				sql,
				new Object[] { record.getChineseLastName(),
						record.getChineseFirstName(),
						record.getEnglishLastName(),
						record.getEnglishFirstName(), record.getPassword(),
						record.getFailedAttempt() });

	}

	@Override
	public void update(Staff record) throws Exception {
		String sql = "UPDATE "
				+ TableName
				+ " SET CHINESE_LASTNAME = ?, CHINESE_FIRSTNAME = ?, ENGLISH_LASTNAME = ?, ENGLISH_FIRSTNAME = ?, PASSWORD = ?, FAILED_ATTEMPT = ? WHERE ID"
				+ TableName + " = ?";
		getJdbcTemplate().update(
				sql,
				new Object[] { record.getChineseLastName(),
						record.getChineseFirstName(),
						record.getEnglishLastName(),
						record.getEnglishFirstName(), record.getPassword(),
						record.getFailedAttempt(), record.getIdstaff() });
	}

	@Override
	public void delete(String id) throws Exception {
		delete(id, TableName);
	}

	@Override
	public List<Staff> selectAll() throws Exception {
		String sql = "SELECT * FROM " + TableName;
		return getJdbcTemplate().query(sql, new StaffRowMapper());
	}

	@Override
	public Staff select(String id) throws Exception {
		if (null == id) {
			return new Staff();
		}
		String sql = "Select * FROM " + TableName + " WHERE ID" + TableName
				+ " = " + id;
		return getJdbcTemplate().queryForObject(sql, new StaffRowMapper());

	}

	@Override
	public List<Staff> selectListForLogin() throws Exception {
		String sql = "SELECT * from "
				+ TableName
				+ " WHERE IDSTAFF in ("
				+ " SELECT distinct idstaff FROM contest c, contest_group cg, judge j"
				+ " where c.idcontest = cg.idcontest"
				+ " and cg.idcontest_group = j.idcontest_group"
				+ " and c.active = 1)";
		List<Staff> list = getJdbcTemplate().query(sql, new StaffRowMapper());
		if (null == list) {
			return null;
		}
		for (Staff staff : list) {
			staff.setPassword(null);
		}
		return list;
	}

	@Override
	public Staff selectByChineseName(String name) throws Exception {
		if (null == name || 0 == name.trim().length()) {
			return null;
		}
		String sql = "Select * from " + TableName
				+ " WHERE CONCAT(chinese_lastname,chinese_firstname) = '"
				+ name.trim() + "' LIMIT 1";
		return getJdbcTemplate().queryForObject(sql, new StaffRowMapper());
	}

	private Staff selectByEnglishName(String name) throws Exception {
		if (null == name || 0 == name.trim().length()) {
			return null;
		}
		String sql = "Select * from "
				+ TableName
				+ " WHERE CONCAT(CONCAT(english_firstname, ' '), english_lastname ) = '"
				+ name.trim() + "' LIMIT 1";
		return getJdbcTemplate().queryForObject(sql, new StaffRowMapper());
	}

	@Override
	public Staff selectByName(String name) throws Exception {
		if (null == name || 0 == name.trim().length()) {
			return null;
		}
		String[] names = name.split("\\|");
		String nameUsed = names[0];
		if (names.length > 1) {
			if (StringUtil.isEnglishName(nameUsed)) {
				nameUsed = names[1];
			}
		}
		if (StringUtil.isEnglishName(nameUsed)) {
			return selectByEnglishName(nameUsed);
		} else {
			return selectByChineseName(nameUsed);
		}
	}

	@Override
	public List<Staff> selectByContestId(String idcontest) throws Exception {
		String sql = "select * from "
				+ TableName
				+ " s where s.idstaff in "
				+ "(select distinct j.idstaff from judge j,"
				+ " contest_group cg, contest c where c.idcontest = cg.idcontest"
				+ " and j.idcontest_group = cg.idcontest_group and c.idcontest = "
				+ idcontest + " )";
		return getJdbcTemplate().query(sql, new StaffRowMapper());

	}

	@Override
	public Staff selectByIdAndPassword(Staff staff) throws Exception {
		Staff sStaff = select(staff.getIdstaff());
		if (StringUtils.isEmpty(sStaff.getPassword())
				|| sStaff.getPassword().trim().length() == 0) {
			return sStaff;
		}
		if (10 < sStaff.getFailedAttempt()) {
			log.error("Login failed too many times: "
					+ sStaff.getChineseLastName() + sStaff.getChineseLastName());
			throw new Exception("Failed attempt too many times!");
		}
		if (sStaff.getPassword().equals(staff.getPassword())) {
			sStaff.setFailedAttempt(0);
			update(sStaff);
			return sStaff;
		}
		sStaff.setFailedAttempt(sStaff.getFailedAttempt() + 1);
		update(sStaff);
		log.error("Login failed: " + sStaff.getChineseLastName()
				+ sStaff.getChineseLastName());
		throw new Exception("Pin for staff is incorrect!");
	}
}
