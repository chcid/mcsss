package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.ContestGroup;
import org.michiganchineseschool.speech.model.Judge;
import org.michiganchineseschool.speech.model.Role;
import org.michiganchineseschool.speech.model.Staff;
import org.springframework.jdbc.core.ResultSetExtractor;

public class JudgeResultSetExtractor implements ResultSetExtractor<Judge> {

	@Override
	public Judge extractData(ResultSet rs) throws SQLException {
		Judge record = new Judge();
		record.setIdjudge(rs.getString("IDJUDGE"));
		ContestGroup contestGroup = new ContestGroup();
		contestGroup.setIdcontest_group(rs.getString("IDCONTEST_GROUP"));
		record.setContestGroup(contestGroup);
		Staff staff = new Staff();
		staff.setIdstaff(rs.getString("IDSTAFF"));
		record.setStaff(staff);
		Role role = new Role();
		role.setIdrole(rs.getString("IDROLE"));
		record.setRole(role);
		record.setSubmit(1 == rs.getInt("IS_SUBMIT"));
		return record;
	}
}