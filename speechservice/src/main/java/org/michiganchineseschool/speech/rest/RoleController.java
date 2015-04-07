package org.michiganchineseschool.speech.rest;

import java.util.List;

import org.michiganchineseschool.speech.model.BaseResponse;
import org.michiganchineseschool.speech.model.Role;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController {

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	List<Role> getAllRoles() throws Exception {
		return getDatabaseService().getAllRoles();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	BaseResponse deleteRole(@PathVariable(value = "id") String id)
			throws Exception {
		getDatabaseService().deleteRole(id);
		return new BaseResponse();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	BaseResponse insertRole(@RequestBody Role record) throws Exception {
		getDatabaseService().insertRole(record);
		return new BaseResponse();
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	BaseResponse updateStudent(@RequestBody Role record) throws Exception {
		getDatabaseService().updateRole(record);
		return new BaseResponse();
	}

}
