package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.model.Staff;

public interface StaffDao {
	public List<Staff> selectAll() throws Exception;

	public void delete(String id) throws Exception;

	public void insert(Staff record) throws Exception;

	public void update(Staff record) throws Exception;

	public Staff select(String id) throws Exception;

	public List<Staff> selectListForLogin() throws Exception;

	public Staff selectByChineseName(String name) throws Exception;

	public Staff selectByName(String name) throws Exception;

	public List<Staff> selectByContestId(String idcontest) throws Exception;

	public Staff selectByIdAndPassword(Staff staff) throws Exception;

}
