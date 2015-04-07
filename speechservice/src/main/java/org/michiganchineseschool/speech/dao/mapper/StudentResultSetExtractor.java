package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.GradYear;
import org.michiganchineseschool.speech.model.Student;
import org.springframework.jdbc.core.ResultSetExtractor;

public class StudentResultSetExtractor implements ResultSetExtractor<Student> {

	@Override
	public Student extractData(ResultSet rs) throws SQLException {
		Student record = new Student();
		record.setIdstudent(rs.getString("IDSTUDENT"));
		record.setChineseFirstName(rs.getString("CHINESE_FIRSTNAME"));
		record.setChineseLastName(rs.getString("CHINESE_LASTNAME"));
		record.setEnglishFirstName(rs.getString("ENGLISH_FIRSTNAME"));
		record.setEnglishLastName(rs.getString("ENGLISH_LASTNAME"));
		GradYear gradYear = new GradYear();
		gradYear.setIdgrad_year(rs.getString("IDGRAD_YEAR"));
		record.setGradYear(gradYear);
		return record;
	}
}