package org.michiganchineseschool.speech.rest;

import org.michiganchineseschool.speech.model.BaseResponse;
import org.michiganchineseschool.speech.model.Contestor;
import org.michiganchineseschool.speech.model.ScoreMarking;
import org.michiganchineseschool.speech.model.SpeechScore;
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
	public @ResponseBody BaseResponse updateSpeechScore(
			@RequestBody Contestor record) throws Exception {
		getDatabaseService().updateSpeechScoreByContestor(record);
		return new BaseResponse();
	}

	@RequestMapping(value = "/one_record", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody BaseResponse updateOneSpeechScore(
			@RequestBody SpeechScore speechScore) throws Exception {
		getDatabaseService().updateSpeechScore(speechScore);
		return new BaseResponse();
	}

	@RequestMapping(value = "/score_marking", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody BaseResponse updateOneSpeechScore(
			@RequestBody ScoreMarking scoreMarking) throws Exception {
		getDatabaseService().updateScoreMarking(scoreMarking);
		return new BaseResponse();
	}

}
