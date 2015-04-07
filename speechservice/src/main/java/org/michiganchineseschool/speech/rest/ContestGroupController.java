package org.michiganchineseschool.speech.rest;

import java.util.List;

import org.michiganchineseschool.speech.model.BaseResponse;
import org.michiganchineseschool.speech.model.ContestGroup;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = "/contest_group")
public class ContestGroupController extends BaseController {

	@RequestMapping(value = "/onlyActivateContest", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	List<ContestGroup> getSelectListForLoginedStaff() throws Exception {
		return getDatabaseService().getActivateContestContestGroup();
	}

	@RequestMapping(value = "/staff/{idstaff}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	List<ContestGroup> getSelectListForLoginedStaff(
			@PathVariable(value = "idstaff") String idstaff) throws Exception {
		return getDatabaseService().selectContestGroupListForLoginedStaff(
				idstaff, true);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	List<ContestGroup> getAllContestGroups() throws Exception {
		return getDatabaseService().getAllContestGroups();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	BaseResponse deleteContestGroup(@PathVariable(value = "id") String id)
			throws Exception {
		getDatabaseService().deleteContestGroup(id);
		return new BaseResponse();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	BaseResponse insertContestGroup(@RequestBody ContestGroup record)
			throws Exception {
		getDatabaseService().insertContestGroup(record);
		return new BaseResponse();
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	BaseResponse updateStudent(@RequestBody ContestGroup record)
			throws Exception {
		getDatabaseService().updateContestGroup(record);
		return new BaseResponse();
	}

}
