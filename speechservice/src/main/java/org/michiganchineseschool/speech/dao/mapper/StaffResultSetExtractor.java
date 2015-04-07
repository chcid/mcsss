package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.Staff;
import org.springframework.jdbc.core.ResultSetExtractor;

public class StaffResultSetExtractor implements ResultSetExtractor<Staff> {

	@Override
	public Staff extractData(ResultSet rs) throws SQLException {
		Staff record = new Staff();
		record.setIdstaff(rs.getString("IDSTAFF"));
		record.setChineseFirstName(rs.getString("CHINESE_FIRSTNAME"));
		record.setChineseLastName(rs.getString("CHINESE_LASTNAME"));
		record.setEnglishFirstName(rs.getString("ENGLISH_FIRSTNAME"));
		record.setEnglishLastName(rs.getString("ENGLISH_LASTNAME"));
		record.setPassword(rs.getString("PASSWORD"));
		record.setFailedAttempt(rs.getInt("FAILED_ATTEMPT"));
		return record;
	}
}