package org.michiganchineseschool.speech.rest;

import java.util.List;

import org.michiganchineseschool.speech.model.BaseResponse;
import org.michiganchineseschool.speech.model.ScoreRule;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = "/score_rule")
public class ScoreRuleController extends BaseController {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	ScoreRule getById(@PathVariable(value = "id") String id) throws Exception {
		return getDatabaseService().getScoreRuleById(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	List<ScoreRule> getAllScoreRules() throws Exception {
		return getDatabaseService().getAllScoreRules();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	BaseResponse deleteScoreRule(@PathVariable(value = "id") String id)
			throws Exception {
		getDatabaseService().deleteScoreRule(id);
		return new BaseResponse();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	BaseResponse insertScoreRule(@RequestBody ScoreRule record)
			throws Exception {
		getDatabaseService().insertScoreRule(record);
		return new BaseResponse();
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	BaseResponse updateStudent(@RequestBody ScoreRule record) throws Exception {
		getDatabaseService().updateScoreRule(record);
		return new BaseResponse();
	}

}
