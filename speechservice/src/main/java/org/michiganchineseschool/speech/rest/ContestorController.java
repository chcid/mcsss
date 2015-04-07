package org.michiganchineseschool.speech.rest;

import java.util.List;

import org.michiganchineseschool.speech.model.BaseResponse;
import org.michiganchineseschool.speech.model.Contestor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = "/contestor")
public class ContestorController extends BaseController {

	@RequestMapping(value = "/contest_group/{idcontestGroup}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody List<Contestor> getContestorsByContestGroupStaffRole(
			@PathVariable(value = "idcontestGroup") String idcontestGroup)
			throws Exception {
		return getDatabaseService().getContestorScoreReportByContestGroup(
				idcontestGroup);
	}

	private List<Contestor> setScoreMarkingForContestors(
			List<Contestor> contestors) throws Exception {
		for (Contestor contestor : contestors) {
			contestor.getTotalScoreMarking();
		}
		return contestors;
	}

	@RequestMapping(value = "/contest_group/{idcontestGroup}/staff/{idstaff}/role/{idrole}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody List<Contestor> getContestorsByContestGroupStaffRole(
			@PathVariable(value = "idcontestGroup") String idcontestGroup,
			@PathVariable(value = "idstaff") String idstaff,
			@PathVariable(value = "idrole") String idrole) throws Exception {
		List<Contestor> list = setScoreMarkingForContestors(getDatabaseService()
				.selectContestorByContestGroup(idcontestGroup, idstaff, idrole));

		removeContestGroup(list);
		return list;
	}

	private void removeContestGroup(List<Contestor> list) {
		for (Contestor contestor : list) {
			contestor.setContestGroup(null);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody List<Contestor> getAllContestors() throws Exception {
		return getDatabaseService().getAllContestors();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody BaseResponse deleteContestor(
			@PathVariable(value = "id") String id) throws Exception {
		getDatabaseService().deleteContestor(id);
		return new BaseResponse();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody BaseResponse insertContestor(
			@RequestBody Contestor record) throws Exception {
		getDatabaseService().insertContestor(record);
		return new BaseResponse();
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody BaseResponse updateStudent(
			@RequestBody Contestor record) throws Exception {
		getDatabaseService().updateContestor(record);
		return new BaseResponse();
	}
}
