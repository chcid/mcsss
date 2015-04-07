package org.michiganchineseschool.speech.rest;

import java.util.List;

import org.michiganchineseschool.speech.model.BaseResponse;
import org.michiganchineseschool.speech.model.TimeLimitRule;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = "/time_limit_rule")
public class TimeLimitRuleController extends BaseController {

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	List<TimeLimitRule> getAllTimeLimitRules() throws Exception {
		return getDatabaseService().getAllTimeLimitRules();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	BaseResponse deleteTimeLimitRule(@PathVariable(value = "id") String id)
			throws Exception {
		getDatabaseService().deleteTimeLimitRule(id);
		return new BaseResponse();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	BaseResponse insertTimeLimitRule(@RequestBody TimeLimitRule record)
			throws Exception {
		getDatabaseService().insertTimeLimitRule(record);
		return new BaseResponse();
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	BaseResponse updateStudent(@RequestBody TimeLimitRule record)
			throws Exception {
		getDatabaseService().updateTimeLimitRule(record);
		return new BaseResponse();
	}

}
