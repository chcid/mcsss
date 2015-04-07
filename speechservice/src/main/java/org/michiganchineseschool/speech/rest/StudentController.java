package org.michiganchineseschool.speech.rest;

import java.util.List;

import org.michiganchineseschool.speech.model.BaseResponse;
import org.michiganchineseschool.speech.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class StudentController extends BaseController {

	@RequestMapping(value = "/student", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	List<Student> getAllStudents() throws Exception {
		return getDatabaseService().getAllStudents();
	}

	@RequestMapping(value = "/student/{idstudent}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	BaseResponse deleteStudent(
			@PathVariable(value = "idstudent") String idstudent)
			throws Exception {
		getDatabaseService().deleteStudent(idstudent);
		return new BaseResponse();
	}

	@RequestMapping(value = "/student", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	BaseResponse insertStudent(@RequestBody Student student) throws Exception {
		getDatabaseService().insertStudent(student);
		return new BaseResponse();
	}

	@RequestMapping(value = "/student", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	BaseResponse updateStudent(@RequestBody Student student) throws Exception {
		getDatabaseService().updateStudent(student);
		return new BaseResponse();
	}

}
