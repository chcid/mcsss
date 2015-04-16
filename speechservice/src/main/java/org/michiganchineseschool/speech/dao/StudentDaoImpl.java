package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.michiganchineseschool.speech.dao.mapper.StudentRowMapper;
import org.michiganchineseschool.speech.model.Staff;
import org.michiganchineseschool.speech.model.Student;
import org.michiganchineseschool.util.StringUtil;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.StringUtils;

public class StudentDaoImpl extends BaseDaoImpl implements StudentDao {
	private final static String TableName = "student";
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public void insert(Student record) throws Exception {
		if (StringUtils.isEmpty(record.getChineseFirstName())
				&& StringUtils.isEmpty(record.getChineseLastName())
				&& StringUtils.isEmpty(record.getEnglishFirstName())
				&& StringUtils.isEmpty(record.getEnglishLastName())) {
			return;
		}
		try {
			String sql = "INSERT INTO "
					+ TableName
					+ " ( CHINESE_LASTNAME, CHINESE_FIRSTNAME, ENGLISH_LASTNAME, ENGLISH_FIRSTNAME, IDGRAD_YEAR ) VALUES ( ?,?,?,?,? )";
			getJdbcTemplate().update(
					sql,
					new Object[] {
							record.getChineseLastName(),
							record.getChineseFirstName(),
							record.getEnglishLastName(),
							record.getEnglishFirstName(),
							nullIdFilter(record.getGradYear(), "grad_year",
									"GradYear") });
		} catch (DuplicateKeyException de) {
			log.info(de);
			throw de;
		} catch (Exception e) {
			log.error(e);
			throw e;
		}

	}

	@Override
	public void update(Student record) throws Exception {
		String sql = "UPDATE "
				+ TableName
				+ " SET CHINESE_LASTNAME = ?, CHINESE_FIRSTNAME = ?, ENGLISH_LASTNAME = ?, ENGLISH_FIRSTNAME = ?, IDGRAD_YEAR = ? WHERE ID"
				+ TableName + " = ?";
		getJdbcTemplate().update(
				sql,
				new Object[] {
						record.getChineseLastName(),
						record.getChineseFirstName(),
						record.getEnglishLastName(),
						record.getEnglishFirstName(),
						nullIdFilter(record.getGradYear(), "grad_year",
								"GradYear"), record.getIdstudent() });
	}

	@Override
	public void delete(String id) throws Exception {
		delete(id, TableName);
	}

	@Override
	public List<Student> selectAll() throws Exception {
		String sql = "SELECT * FROM " + TableName;
		return getJdbcTemplate().query(sql, new StudentRowMapper());
	}

	@Override
	public Student select(String id) throws Exception {
		if (null == id) {
			return new Student();
		}
		String sql = "Select * FROM " + TableName + " WHERE ID" + TableName
				+ " = " + id;
		return getJdbcTemplate().queryForObject(sql, new StudentRowMapper());

	}

	@Override
	public List<Student> selectByContestor(String idcontestor) throws Exception {
		String sql = "select s.* from contestor_individual ci, student s"
				+ " where ci.idstudent = s.idstudent"
				+ " and ci.idcontestor = " + idcontestor + " order by ci.idcontestor_individual";
		return getJdbcTemplate().query(sql, new StudentRowMapper());
	}

	private Student selectByChineseName(String name) throws Exception {
		if (null == name || 0 == name.trim().length()) {
			return null;
		}
		String sql = "Select * from " + TableName
				+ " WHERE CONCAT(chinese_lastname,chinese_firstname) = '"
				+ name.trim() + "' LIMIT 1";
		return getJdbcTemplate().queryForObject(sql, new StudentRowMapper());
	}

	private Student selectByEnglishName(String name) throws Exception {
		if (null == name || 0 == name.trim().length()) {
			return null;
		}
		String sql = "Select * from "
				+ TableName
				+ " WHERE CONCAT(CONCAT(english_firstname, ' '), english_lastname ) = '"
				+ name.trim() + "' LIMIT 1";
		return getJdbcTemplate().queryForObject(sql, new StudentRowMapper());
	}

	@Override
	public Student selectByName(String name) throws Exception {
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
}
