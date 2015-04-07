package org.michiganchineseschool.speech.dao;

import java.util.List;

import org.michiganchineseschool.speech.model.Staff;
import org.michiganchineseschool.speech.model.Student;

public interface StudentDao {
	public List<Student> selectAll() throws Exception;

	public void delete(String id) throws Exception;

	public void insert(Student record) throws Exception;

	public void update(Student record) throws Exception;

	public Student select(String id) throws Exception;

	public List<Student> selectByContestor(String idcontestor) throws Exception;

	public Student selectByName(String name) throws Exception;

}
