package org.michiganchineseschool.speech.rest;

import java.util.List;

import org.michiganchineseschool.speech.model.BaseResponse;
import org.michiganchineseschool.speech.model.ContestLocation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = "/contest_location")
public class LocationController extends BaseController {

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	List<ContestLocation> getAllLocations() throws Exception {
		return getDatabaseService().getAllLocations();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	BaseResponse deleteLocation(@PathVariable(value = "id") String id)
			throws Exception {
		getDatabaseService().deleteLocation(id);
		return new BaseResponse();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	BaseResponse insertLocation(@RequestBody ContestLocation record) throws Exception {
		getDatabaseService().insertLocation(record);
		return new BaseResponse();
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody
	BaseResponse updateStudent(@RequestBody ContestLocation record) throws Exception {
		getDatabaseService().updateLocation(record);
		return new BaseResponse();
	}

}
