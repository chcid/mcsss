package org.michiganchineseschool.speech.rest;

import java.util.List;

import org.michiganchineseschool.speech.model.BaseResponse;
import org.michiganchineseschool.speech.model.Judge;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = "/judge")
public class JudgeController extends BaseController {

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	List<Judge> getAllJudges() throws Exception {
		return getDatabaseService().getAllJudges();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	BaseResponse deleteJudge(@PathVariable(value = "id") String id)
			throws Exception {
		getDatabaseService().deleteJudge(id);
		return new BaseResponse();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	BaseResponse insertJudge(@RequestBody Judge record) throws Exception {
		getDatabaseService().insertJudge(record);
		return new BaseResponse();
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	BaseResponse updateStudent(@RequestBody Judge record) throws Exception {
		getDatabaseService().updateJudge(record);
		return new BaseResponse();
	}

}
