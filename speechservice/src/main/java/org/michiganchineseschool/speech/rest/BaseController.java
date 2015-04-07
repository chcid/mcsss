package org.michiganchineseschool.speech.rest;

import org.michiganchineseschool.speech.service.DatabaseService;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
	private DatabaseService databaseService;

	public DatabaseService getDatabaseService() {
		return databaseService;
	}

	public void setDatabaseService(DatabaseService databaseService) {
		this.databaseService = databaseService;
	}
}
