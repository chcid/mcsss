package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.dao.mapper.ContestorIndividualRowMapper;
import org.michiganchineseschool.speech.model.ContestorIndividual;

public class ContestorIndividualDaoImpl extends BaseDaoImpl implements
		ContestorIndividualDao {
	private final static String TableName = "contestor_individual";

	@Override
	public void insert(ContestorIndividual record) throws Exception {
		String sql = "INSERT INTO " + TableName
				+ " ( IDCONTESTOR, IDSTUDENT ) VALUES ( ?, ? )";
		getJdbcTemplate()
				.update(sql,
						new Object[] {
								nullIdFilter(record.getContestor(),
										"contestor", "Contestor"),
								nullIdFilter(record.getStudent(), "student",
										"Student") });
	}

	@Override
	public void update(ContestorIndividual record) throws Exception {
		String sql = "UPDATE " + TableName
				+ " SET IDCONTESTOR = ?, IDSTUDENT = ? WHERE ID" + TableName
				+ " = ?";
		getJdbcTemplate()
				.update(sql,
						new Object[] {
								nullIdFilter(record.getContestor(),
										"contestor", "Contestor"),
								nullIdFilter(record.getStudent(), "student",
										"Student"),
								record.getIdcontestor_individual() });
	}

	@Override
	public List<ContestorIndividual> selectAll() throws Exception {
		String sql = "SELECT * FROM " + TableName;
		return getJdbcTemplate().query(sql, new ContestorIndividualRowMapper());
	}

	@Override
	public void delete(String id) throws Exception {
		delete(id, TableName);
	}

	@Override
	public ContestorIndividual select(String id) throws Exception {
		if (null == id) {
			return new ContestorIndividual();
		}
		String sql = "Select * FROM " + TableName + " WHERE ID" + TableName
				+ " = " + id;
		return getJdbcTemplate().queryForObject(sql,
				new ContestorIndividualRowMapper());
	}
}
