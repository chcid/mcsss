package org.michiganchineseschool.speech.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.michiganchineseschool.speech.model.Contestor;
import org.michiganchineseschool.speech.model.ContestorIndividual;
import org.michiganchineseschool.speech.model.Student;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ContestorIndividualResultSetExtractor implements
		ResultSetExtractor<ContestorIndividual> {

	@Override
	public ContestorIndividual extractData(ResultSet rs) throws SQLException {
		ContestorIndividual record = new ContestorIndividual();
		record.setIdcontestor_individual(rs.getString("IDCONTESTOR_INDIVIDUAL"));
		Contestor contestor = new Contestor();
		contestor.setIdcontestor(rs.getString("IDCONTESTOR"));
		record.setContestor(contestor);
		Student student = new Student();
		student.setIdstudent(rs.getString("IDSTUDENT"));
		record.setStudent(student);
		return record;
	}
}