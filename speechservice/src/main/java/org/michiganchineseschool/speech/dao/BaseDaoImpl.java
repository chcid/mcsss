package org.michiganchineseschool.speech.dao;

import java.lang.reflect.Method;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.springframework.jdbc.core.JdbcTemplate;

public class BaseDaoImpl {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		if (null == jdbcTemplate) {
			jdbcTemplate = new JdbcTemplate(dataSource);
		}
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		jdbcTemplate.setFetchSize(2048);
		return jdbcTemplate;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void delete(String id, String tableName) throws Exception {
		String sql = "DELETE FROM " + tableName + " WHERE ID" + tableName
				+ " = " + id;
		getJdbcTemplate().execute(sql);
	}

	static public String nullIdFilter(Object object, String methodName,
			String className) {
		if (null == object) {
			return null;
		}
		// no paramater
		try {
			Class[] noparams = {};
			// load the AppTest at runtime
			Class cls = Class.forName("org.michiganchineseschool.speech.model."
					+ className);
			// Object obj = cls.newInstance();

			// call the printIt method
			Method method = cls.getDeclaredMethod("getId" + methodName,
					noparams);
			Object ret = method.invoke(object);
			if (null != ret) {
				return (String) ret;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
