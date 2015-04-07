package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.dao.mapper.LocationRowMapper;
import org.michiganchineseschool.speech.model.ContestLocation;

public class LocationDaoImpl extends BaseDaoImpl implements LocationDao {
	private final static String TableName = "contest_location";

	@Override
	public void insert(ContestLocation record) throws Exception {
		String sql = "INSERT INTO " + TableName + " ( NAME ) VALUES ( ? )";
		getJdbcTemplate().update(sql, new Object[] { record.getName() });
	}

	@Override
	public void update(ContestLocation record) throws Exception {
		String sql = "UPDATE " + TableName + " SET NAME = ? WHERE ID"
				+ TableName + " = ?";
		getJdbcTemplate()
				.update(sql,
						new Object[] { record.getName(),
								record.getIdcontest_location() });
	}

	@Override
	public List<ContestLocation> selectAll() throws Exception {
		String sql = "SELECT * FROM " + TableName;
		return getJdbcTemplate().query(sql, new LocationRowMapper());
	}

	@Override
	public void delete(String id) throws Exception {
		delete(id, TableName);
	}

	@Override
	public ContestLocation select(String id) throws Exception {
		if (null == id) {
			return new ContestLocation();
		}
		String sql = "Select * FROM " + TableName + " WHERE ID" + TableName
				+ " = " + id;
		return getJdbcTemplate().queryForObject(sql, new LocationRowMapper());

	}

	@Override
	public ContestLocation getContestLocationByName(String name)
			throws Exception {
		if (null == name) {
			return null;
		}
		String sql = "Select * FROM " + TableName + " WHERE NAME" + " = '"
				+ name + "'";
		return getJdbcTemplate().queryForObject(sql, new LocationRowMapper());

	}
}
