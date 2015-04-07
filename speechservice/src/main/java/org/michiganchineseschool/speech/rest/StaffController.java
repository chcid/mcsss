package org.michiganchineseschool.speech.rest;

import java.util.List;

import org.michiganchineseschool.speech.model.BaseResponse;
import org.michiganchineseschool.speech.model.Staff;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = "/staff")
public class StaffController extends BaseController {

	@RequestMapping(value = "/loginList", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody List<Staff> getAllStaffsForLogin() throws Exception {
		return getDatabaseService().selectListForLogin();
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody List<Staff> getAllStaffs() throws Exception {
		return getDatabaseService().getAllStaffs();
	}

	@RequestMapping(value = "/{idstaff}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody BaseResponse deleteStaff(
			@PathVariable(value = "idstaff") String idstaff) throws Exception {
		getDatabaseService().deleteStaff(idstaff);
		return new BaseResponse();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody BaseResponse insertStaff(@RequestBody Staff staff)
			throws Exception {
		getDatabaseService().insertStaff(staff);
		return new BaseResponse();
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody BaseResponse updateStudent(@RequestBody Staff staff)
			throws Exception {
		getDatabaseService().updateStaff(staff);
		return new BaseResponse();
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody Staff login(@RequestBody Staff staff) throws Exception {
		return getDatabaseService().login(staff);
	}

}
