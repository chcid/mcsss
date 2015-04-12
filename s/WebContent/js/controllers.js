'use strict';

/* Controllers */

angular
		.module('speechApp.controllers', [])
		.controller(
				'scoreReportController',
				[
						'$scope',
						'dataFactory',
						'$routeParams',
						'$timeout',
						function($scope, dataFactory, $routeParams, $timeout) {
							// var scoreMonitor = null;
							$scope.scoreMonitor = null;
							var startMonitor = function() {
								$scope.scoreMonitor = $timeout(function() {
									selectedContestGroupChanged();
									startMonitor();
								}, 5000);
							};
							$scope.startMonitor = function() {
								startMonitor();
							};
							$scope.stopMonitor = function() {
								$timeout.cancel($scope.scoreMonitor);
								$scope.scoreMonitor = null;
							}
							var loadActivateContestContestGroups = function() {
								dataFactory
										.getActivateContestContestGroups()
										.success(function(resultSet) {
											$scope.contestGroups = resultSet;
											console.log($scope.contestGroups);
										})
										.error(
												function(error) {
													$scope.status = 'Unable to load '
															+ "contest_group"
															+ ' data: '
															+ error.message;
												});

							};
							var selectedContestGroupChanged = function() {
								dataFactory
										.getContestorsByContestGroupId(
												$scope.selectedContestGroup.idcontest_group)
										.success(function(resultSet) {
											$scope.contestors = resultSet;
											console.log($scope.contestors);
										})
										.error(
												function(error) {
													$scope.status = 'Unable to load '
															+ "contestors"
															+ ' data: '
															+ error.message;
												});

							};
							$scope.selectedContestGroupChanged = function() {
								selectedContestGroupChanged();
							};
							loadActivateContestContestGroups();

						} ])
		.controller(
				'judgeScoringController',
				[
						'$scope',
						'dataFactory',
						'stopwatch',
						'$routeParams',
						'$timeout',
						function($scope, dataFactory, stopwatch, $routeParams,
								$timeout) {

							$scope.isCanBeSubmited = false;

							$scope.abstainedUpdater = null;
							var startAbstainedUpdater = function() {
								$scope.abstainedUpdater = $timeout(function() {
									if (!$scope.isUpdating) {
										loadContestorsOnlyForTheAbstained();
									}
									startAbstainedUpdater();
								}, 10000);
							};
							$scope.startAbstainedUpdater = function() {
								if (!$scope.abstainedUpdater) {
									startAbstainedUpdater();
								}
							};
							$scope.stopAbstainedUpdater = function() {
								if ($scope.abstainedUpdater) {
									$timeout.cancel($scope.abstainedUpdater);
									$scope.abstainedUpdater = null;
								}
							}

							$scope.justUpdatedId = 0;
							$scope.isUsingStopwatch = false;
							$scope.scoreRanges = [];
							$scope.isUpdating = false;
							for (var i = 80; i <= 100; i++) {
								$scope.scoreRanges[i - 80] = i;
							}

							$scope.minuteRanges = [];
							for (var i = 0; i <= 9; i++) {
								$scope.minuteRanges[i] = i;
							}

							$scope.secondRanges = [];
							for (var i = 0; i <= 59; i++) {
								$scope.secondRanges[i] = i;
							}

							// console.log($scope.scoreRanges);
							$scope.speechStopwatch = stopwatch;

							$scope.doSignAndSubmit = function() {
								// console.log(record);
								var tableName = "judge";
								var record = angular
										.copy($scope.selectedContestGroup.judge);
								record.submit = true;
								dataFactory
										.updateRecord(record, tableName)
										.success(
												function() {
													$scope.status = 'Updated '
															+ tableName;
													$scope.selectedContestGroup = null;
													$scope
															.selectedStaffChanged();
												})
										.error(
												function(error) {
													$scope.status = 'Unable to update '
															+ tableName
															+ ': '
															+ error.message;
												});
							};

							$scope.doIt = function() {
								alert("Hello");
								return false;
							};

							var loadContestorsOnlyForTheAbstained = function() {
								dataFactory
										.getContestorsForJudgeToScore(
												$scope.selectedContestGroup.idcontest_group,
												$scope.selectedStaff.idstaff,
												$scope.selectedContestGroup.role.idrole)
										.success(
												function(resultSet) {
													// $scope.contestorsForJudgeToScore
													// = resultSet;
													angular
															.forEach(
																	resultSet,
																	function(
																			result) {

																		angular
																				.forEach(
																						$scope.contestorsForJudgeToScore,
																						function(
																								contestor) {
																							if (contestor.idcontestor == result.idcontestor) {
																								if (contestor.abstained != result.abstained) {
																									contestor.abstained = result.abstained;
																								}
																							}
																						});

																	});
													testToSeeIfCanBeSubmited();
												})
										.error(
												function(error) {
													$scope.status = 'Unable to load '
															+ "contestor for only the abstained"
															+ ' data: '
															+ error.message;
													console.log($scope.status);
													$("#networkErrorModal")
															.modal();
													return false;
												});
							};

							var loadContestors = function() {
								loadContestorsForJudgeToScore(
										$scope.selectedContestGroup.idcontest_group,
										$scope.selectedStaff.idstaff,
										$scope.selectedContestGroup.role.idrole);
							};

							var testToSeeIfCanBeSubmited = function() {
								var isCan = true;
								angular
										.forEach(
												$scope.contestorsForJudgeToScore,
												function(contestor) {
													if (!contestor.abstained) {
														if (2 == $scope.selectedContestGroup.role.idrole) {
															// timer
															if (0 == contestor.timeScore.minute
																	&& 0 == contestor.timeScore.second) {
																isCan = false;
															}

														} else {
															// non-timer
															angular
																	.forEach(
																			contestor.scoreRuleItems,
																			function(
																					scoreRuleItem) {
																				if (!scoreRuleItem.speechScore.score
																						|| '0' == scoreRuleItem.speechScore.score) {
																					isCan = false;
																				}
																			});
														}
													}
												});
								$scope.isCanBeSubmited = isCan;
							}

							var loadContestorsForJudgeToScore = function(
									idcontestGroup, idstaff, idrole) {
								dataFactory
										.getContestorsForJudgeToScore(
												idcontestGroup, idstaff, idrole)
										.success(
												function(resultSet) {
													if (!$scope.contestorsForJudgeToScore) {
														$scope.contestorsForJudgeToScore = resultSet;
													}
													// we just want to update
													// what is changed
													angular
															.forEach(
																	resultSet,
																	function(
																			result) {

																		angular
																				.forEach(
																						$scope.contestorsForJudgeToScore,
																						function(
																								contestor) {
																							if (contestor.idcontestor == result.idcontestor) {
																								if (result.idcontestor == $scope.justUpdatedIdContestor) {
																									// console
																									// .log(result);
																									if (result.scoreMarking) {
																										contestor.scoreMarking = result.scoreMarking;
																									}
																									if (result.timeScore) {
																										contestor.timeScore = result.timeScore;
																									}

																									if (result.judgeTimeScore) {
																										contestor.judgeTimeScore = result.judgeTimeScore;
																									}
																									// contestor.scoreRuleItems
																									// =
																									// result.scoreRuleItems;
																									// contestor.finalRank
																									// =
																									// result.finalRank;
																									contestor.totalScore = result.totalScore;

																									// console
																									// .log(contestor);
																								}

																								if (contestor.finalRank != result.finalRank) {
																									contestor.finalRank = result.finalRank;
																								}
																								if (contestor.abstained != result.abstained) {
																									contestor.abstained = result.abstained;
																								}
																							}
																						});

																	});// end
													// of
													// forEach
													// console
													// .log($scope.contestorsForJudgeToScore);

													testToSeeIfCanBeSubmited();
													$scope.isUpdating = false;
												})
										.error(
												function(error) {
													$scope.status = 'Unable to load '
															+ "Contestors for Judge to Score"
															+ ' data: '
															+ error.message;
													$scope.isUpdating = false;
												});
							};

							var loadSelectLoginStaffList = function() {
								dataFactory
										.getSelectLoginStaffList()
										.success(
												function(resultSet) {
													$scope.selectLoginStaffs = resultSet;
												})
										.error(
												function(error) {
													$scope.status = 'Unable to load '
															+ "select login staff list"
															+ ' data: '
															+ error.message;
												});
							};
							$scope.toUseStopwatch = function() {
								$scope.isUsingStopwatch = true;
								$scope.speechStopwatch.reset();
							};
							$scope.applySpeechStopwatch = function() {
								$scope.contestorToScore.timeScore.minute = $scope.speechStopwatch.data.intM;
								$scope.contestorToScore.timeScore.second = $scope.speechStopwatch.data.intS;
								$scope.isUsingStopwatch = false;
							};
							$scope.selectedStaffChanged = function() {
								// if (null != $scope.selectedStaff.password
								// && "" != $scope.selectedStaff.password) {
								// if ($scope.password !=
								// $scope.selectedStaff.password) {
								// $("#passwordIncorrectModal").modal();
								// return false;
								// }
								$scope.selectedStaff.password = $scope.password;
								dataFactory
										.login($scope.selectedStaff)
										.success(
												function(resultSet) {
													console.log(resultSet);
													$scope.contestorsForJudgeToScore = null;
													dataFactory
															.getSelectContestGroupListForLoginedStaff(
																	$scope.selectedStaff.idstaff)
															.success(
																	function(
																			resultSet) {
																		$scope.selectContestGroups = resultSet;
																	})
															.error(
																	function(
																			error) {
																		$scope.status = 'Unable to load '
																				+ "select contest group list for selected staff"
																				+ ' data: '
																				+ error.message;
																	});
												})
										.error(
												function() {
													$("#passwordIncorrectModal")
															.modal();
													return false;
												});
								// }

							};
							$scope.selectedContestGroupChanged = function() {
								console.log($scope.selectedContestGroup);
								if (1 == $scope.selectedContestGroup.role.idrole) {
									$scope.isForJudge = true;
								} else if (2 == $scope.selectedContestGroup.role.idrole) {
									$scope.isForJudge = false;
								}
								$scope.contestorsForJudgeToScore = null;
								loadContestors();
								$scope.startAbstainedUpdater();
							};

							$scope.doScore = function(record) {
								$scope.contestorToScore = {};
								$scope.contestorToScore = angular.copy(record);
								$scope.isScoring = true;
								if ($scope.contestorToScore.timeScore.minute == 0
										&& $scope.contestorToScore.timeScore.second == 0) {
									$scope.isUsingStopwatch = true;
								} else {
									$scope.isUsingStopwatch = false;
								}
								$scope.speechStopwatch.reset();
							};

							$scope.cancelScore = function() {
								$scope.isScoring = false;
								$scope.speechStopwatch.stop();
							};

							$scope.submitScore = function(record, id) {
								$scope.isUpdating = true;
								$scope.justUpdatedId = id;
								$scope.justUpdatedIdContestor = record.idcontestor;
								console.log(record);
								$scope.speechStopwatch.stop();
								var tableName = "speech_score";
								dataFactory
										.updateRecord(record, tableName)
										.success(
												function() {
													$scope.status = 'Updated '
															+ +'! Refreshing '
															+ tableName
															+ ' list.';
													loadContestors();
													// $scope.isUpdating =
													// false;
												})
										.error(
												function(error) {
													$scope.status = 'Unable to update '
															+ tableName
															+ ': '
															+ error.message;
													$scope.isUpdating = false;
													console.log($scope.status);
													$("#networkErrorModal")
															.modal();
													return false;
												});
								$scope.isScoring = false;

							};

							loadSelectLoginStaffList();

						} ])
		.controller(
				'recordController',
				[
						'$scope',
						'dataFactory',
						'$routeParams',
						function($scope, dataFactory, $routeParams) {

							var TableName = $routeParams.tableName;

							var loadGradYears = function() {
								dataFactory.getAllRecords("grad_year").success(
										function(resultSet) {
											$scope.gradYears = resultSet;
										}).error(
										function(error) {
											$scope.status = 'Unable to load '
													+ "grad_year" + ' data: '
													+ error.message;
										});
							};

							var loadStaffs = function() {
								dataFactory.getAllRecords("staff").success(
										function(resultSet) {
											$scope.staffs = resultSet;
										}).error(
										function(error) {
											$scope.status = 'Unable to load '
													+ "staff" + ' data: '
													+ error.message;
										});
							};

							var loadRoles = function() {
								dataFactory.getAllRecords("role").success(
										function(resultSet) {
											$scope.roles = resultSet;
										}).error(
										function(error) {
											$scope.status = 'Unable to load '
													+ "role" + ' data: '
													+ error.message;
										});
							};

							var loadStudents = function() {
								dataFactory.getAllRecords("student").success(
										function(resultSet) {
											$scope.students = resultSet;
										}).error(
										function(error) {
											$scope.status = 'Unable to load '
													+ "student" + ' data: '
													+ error.message;
										});
							};
							var loadContestors = function() {
								dataFactory.getAllRecords("contestor").success(
										function(resultSet) {
											$scope.contestors = resultSet;
										}).error(
										function(error) {
											$scope.status = 'Unable to load '
													+ "contestor" + ' data: '
													+ error.message;
										});
							};
							var loadContestGroups = function() {
								dataFactory
										.getAllRecords("contest_group")
										.success(function(resultSet) {
											$scope.contestGroups = resultSet;
										})
										.error(
												function(error) {
													$scope.status = 'Unable to load '
															+ "contest_group"
															+ ' data: '
															+ error.message;
												});
							};
							var loadScoreRules = function() {
								dataFactory
										.getAllRecords("score_rule")
										.success(function(resultSet) {
											$scope.scoreRules = resultSet;
										})
										.error(
												function(error) {
													$scope.status = 'Unable to load '
															+ "score_rule"
															+ ' data: '
															+ error.message;
												});
							};
							var loadContests = function() {
								dataFactory.getAllRecords("contest").success(
										function(resultSet) {
											$scope.contests = resultSet;
										}).error(
										function(error) {
											$scope.status = 'Unable to load '
													+ "contest" + ' data: '
													+ error.message;
										});
							};
							var loadContestLocatons = function() {
								dataFactory
										.getAllRecords("contest_location")
										.success(
												function(resultSet) {
													$scope.contestLocations = resultSet;
												})
										.error(
												function(error) {
													$scope.status = 'Unable to load '
															+ "contest location"
															+ ' data: '
															+ error.message;
												});
							};
							var loadTimeLimitRules = function() {
								dataFactory
										.getAllRecords("time_limit_rule")
										.success(function(resultSet) {
											$scope.timeLimitRules = resultSet;
										})
										.error(
												function(error) {
													$scope.status = 'Unable to load '
															+ "time_limit_rule"
															+ ' data: '
															+ error.message;
												});
							};
							var loadScoreCountingTypes = function() {
								dataFactory
										.getAllRecords("score_counting_type")
										.success(
												function(resultSet) {
													$scope.scoreCountingTypes = resultSet;
												})
										.error(
												function(error) {
													$scope.status = 'Unable to load '
															+ "score_counting_type"
															+ ' data: '
															+ error.message;
												});
							};
							if ("score_rule_item" == TableName) {
								loadScoreRules();
							} else if ("contest_group" == TableName) {
								loadScoreRules();
								loadContests();
								loadContestLocatons();
								loadScoreCountingTypes();
								loadTimeLimitRules();
							} else if ("contestor" == TableName) {
								loadContestGroups();
							} else if ("contestor_individual" == TableName) {
								loadContestors();
								loadStudents();
							} else if ("judge" == TableName) {
								loadContestGroups();
								loadStaffs();
								loadRoles();
							} else if ("student" == TableName) {
								loadGradYears();
							}
							var IdColName = "id" + TableName;

							$scope.status;
							$scope.records;

							getAll();

							function getAll() {
								dataFactory.getAllRecords(TableName).success(
										function(resultSet) {
											$scope.records = resultSet;
										}).error(
										function(error) {
											$scope.status = 'Unable to load '
													+ TableName + ' data: '
													+ error.message;
										});
							}

							function deleteRecord(id) {
								dataFactory
										.deleteRecord(id, TableName)
										.success(function(result) {
											getAll();
										})
										.error(
												function(error) {
													$scope.status = 'Unable to delete '
															+ TableName
															+ ' data: '
															+ error.message;
												});
							}

							// var refreshData = function() {
							// $scope.students = studentFactory
							// .getAllStudents();
							// };
							// refreshData();
							// $scope.students = dataFactory.getAllStudents();
							var setUpSelectDropdownContestGroup = function() {
								var dom = jsel($scope.contestGroups);
								$scope.recordToUpdate.contestGroup = dom
										.select("//*[@idcontest_group='"
												+ $scope.recordToUpdate.contestGroup.idcontest_group
												+ "']");
							};
							var setUpSelectDropdownScoreRule = function() {
								var dom = jsel($scope.scoreRules);
								$scope.recordToUpdate.scoreRule = dom
										.select("//*[@idscore_rule='"
												+ $scope.recordToUpdate.scoreRule.idscore_rule
												+ "']");
							};
							var setUpSelectDropdownContest = function() {
								var dom = jsel($scope.contests);
								$scope.recordToUpdate.contest = dom
										.select("//*[@idcontest='"
												+ $scope.recordToUpdate.contest.idcontest
												+ "']");
							};
							var setUpSelectDropdownContestLocation = function() {
								var dom = jsel($scope.contestLocations);
								$scope.recordToUpdate.contestLocation = dom
										.select("//*[@idcontest_location='"
												+ $scope.recordToUpdate.contestLocation.idcontest_location
												+ "']");
								// console.log(
								// $scope.recordToUpdate.locationPlace );
							};
							var setUpSelectDropdownTimeLimitRule = function() {
								var dom = jsel($scope.timeLimitRules);
								$scope.recordToUpdate.timeLimitRule = dom
										.select("//*[@idtime_limit_rule='"
												+ $scope.recordToUpdate.timeLimitRule.idtime_limit_rule
												+ "']");
							};
							var setUpSelectDropdownScoreCountingType = function() {
								var dom = jsel($scope.scoreCountingTypes);
								$scope.recordToUpdate.scoreCountingType = dom
										.select("//*[@idscore_counting_type='"
												+ $scope.recordToUpdate.scoreCountingType.idscore_counting_type
												+ "']");
							};
							var setUpSelectDropdownContestGroup = function() {
								var dom = jsel($scope.contestGroups);
								$scope.recordToUpdate.contestGroup = dom
										.select("//*[@idcontest_group='"
												+ $scope.recordToUpdate.contestGroup.idcontest_group
												+ "']");
							};

							var setUpSelectDropdownContestor = function() {
								var dom = jsel($scope.contestors);
								$scope.recordToUpdate.contestor = dom
										.select("//*[@idcontestor='"
												+ $scope.recordToUpdate.contestor.idcontestor
												+ "']");
							};

							var setUpSelectDropdownStudent = function() {
								var dom = jsel($scope.students);
								$scope.recordToUpdate.student = dom
										.select("//*[@idstudent='"
												+ $scope.recordToUpdate.student.idstudent
												+ "']");
							};

							var setUpSelectDropdownStaff = function() {
								var dom = jsel($scope.staffs);
								$scope.recordToUpdate.staff = dom
										.select("//*[@idstaff='"
												+ $scope.recordToUpdate.staff.idstaff
												+ "']");
							};

							var setUpSelectDropdownRole = function() {
								var dom = jsel($scope.roles);
								$scope.recordToUpdate.role = dom
										.select("//*[@idrole='"
												+ $scope.recordToUpdate.role.idrole
												+ "']");
							};

							var setUpSelectDropdownGradYear = function() {
								var dom = jsel($scope.gradYears);
								$scope.recordToUpdate.gradYear = dom
										.select("//*[@idgrad_year='"
												+ $scope.recordToUpdate.gradYear.idgrad_year
												+ "']");
							};

							$scope.doUpdate = function(record) {
								$scope.modalTitle = "Update this " + TableName;
								// console.log(record);
								// var loc = record.locationPlace;
								// console.log(loc);
								// var locCopy = angular.copy(loc);
								// console.log(locCopy);
								$scope.recordToUpdate = angular.copy(record);
								// $scope.recordToUpdate.locationPlace =
								// locCopy;
								// $scope.recordToUpdate = record;
								// console.log($scope.recordToUpdate);
								if (TableName == "score_rule_item") {
									setUpSelectDropdownScoreRule();
								} else if ("contest_group" == TableName) {
									setUpSelectDropdownContest();
									setUpSelectDropdownContestLocation();
									setUpSelectDropdownScoreCountingType();
									setUpSelectDropdownScoreRule();
									setUpSelectDropdownTimeLimitRule();
								} else if ("contestor" == TableName) {
									setUpSelectDropdownContestGroup();
								} else if ("contestor_individual" == TableName) {
									setUpSelectDropdownStudent();
									setUpSelectDropdownContestor();
								} else if ("judge" == TableName) {
									setUpSelectDropdownContestGroup();
									setUpSelectDropdownStaff();
									setUpSelectDropdownRole();
								} else if ("student" == TableName) {
									setUpSelectDropdownGradYear();
								}
							};

							$scope.doCreate = function() {
								$scope.modalTitle = "Create a new " + TableName;
								// if (!$scope.studentToUpdate) {
								$scope.recordToUpdate = {};
								// }
							};

							$scope.submitUpdate = function(record) {
								if (record[IdColName]) {
									updateRecord(record);
									console.log(TableName + " "
											+ record[IdColName] + " updated",
											record);
									$scope.recordToUpdate = {};
								} else {
									insertRecord(record);
									console.log(TableName + " " + "new record"
											+ " inserted", record);
									$scope.recordToUpdate = {};
								}

							};

							$scope.doDelete = function(record) {
								bootbox
										.confirm(
												"Are you sure you want to delete this "
														+ TableName
														+ ": "
														+ JSON
																.stringify(record),
												function(result) {
													if (result) {
														deleteRecord(record[IdColName]);
														console
																.log(
																		TableName
																				+ " "
																				+ record[IdColName]
																				+ " deleted",
																		record);
													}
												});
							};
							// var t = setInterval(refreshData, 5000);
							// //////////////////////////////
							// update
							// //////////////////////////////
							var updateRecord = function(record) {
								dataFactory
										.updateRecord(record, TableName)
										.success(
												function() {
													$scope.status = 'Updated '
															+ TableName
															+ '! Refreshing '
															+ TableName
															+ ' list.';
													getAll();
												})
										.error(
												function(error) {
													$scope.status = 'Unable to update '
															+ TableName
															+ ': '
															+ error.message;
												});
							};
							// ////////////////////////////////
							// insert
							// /////////////////////////////////
							var insertRecord = function(record) {
								dataFactory
										.insertRecord(record, TableName)
										.success(
												function() {
													$scope.status = 'Inserted '
															+ TableName
															+ '! Refreshing '
															+ TableName
															+ ' list.';
													getAll();
												})
										.error(
												function(error) {
													$scope.status = 'Unable to insert '
															+ TableName
															+ ': '
															+ error.message;
												});
							};
						} ]);

// //////////////////////////////////////////////

angular.module('myApp.controllers', []).controller('MyCtrl1',
		[ '$scope', 'studentFactory', function($scope, studentFactory) {
			$scope.students = studentFactory.query();
		} ]).controller('MyCtrl2', [ function() {

} ]);