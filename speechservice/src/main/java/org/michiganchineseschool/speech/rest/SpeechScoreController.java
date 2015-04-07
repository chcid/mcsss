package org.michiganchineseschool.speech.rest;

import org.michiganchineseschool.speech.model.BaseResponse;
import org.michiganchineseschool.speech.model.Contestor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = "/speech_score")
public class SpeechScoreController extends BaseController {

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	BaseResponse updateSpeechScore(@RequestBody Contestor record)
			throws Exception {
		getDatabaseService().updateSpeechScoreByContestor(record);
		return new BaseResponse();
	}

}
