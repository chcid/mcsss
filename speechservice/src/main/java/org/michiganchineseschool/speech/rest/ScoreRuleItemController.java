package org.michiganchineseschool.speech.rest;

import java.util.List;

import org.michiganchineseschool.speech.model.BaseResponse;
import org.michiganchineseschool.speech.model.ScoreRuleItem;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = "/score_rule_item")
public class ScoreRuleItemController extends BaseController {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	ScoreRuleItem getById(@PathVariable(value = "id") String id)
			throws Exception {
		return getDatabaseService().getScoreRuleItemById(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	List<ScoreRuleItem> getAllScoreRuleItems() throws Exception {
		return getDatabaseService().getAllScoreRuleItems();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	BaseResponse deleteScoreRuleItem(@PathVariable(value = "id") String id)
			throws Exception {
		getDatabaseService().deleteScoreRuleItem(id);
		return new BaseResponse();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	BaseResponse insertScoreRuleItem(@RequestBody ScoreRuleItem record)
			throws Exception {
		getDatabaseService().insertScoreRuleItem(record);
		return new BaseResponse();
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	BaseResponse updateScoreRuleItem(@RequestBody ScoreRuleItem record)
			throws Exception {
		getDatabaseService().updateScoreRuleItem(record);
		return new BaseResponse();
	}

}
