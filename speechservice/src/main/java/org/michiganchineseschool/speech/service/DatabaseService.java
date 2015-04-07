package org.michiganchineseschool.speech.service;

import java.util.List;

import org.michiganchineseschool.speech.model.Contest;
import org.michiganchineseschool.speech.model.ContestGroup;
import org.michiganchineseschool.speech.model.ContestLocation;
import org.michiganchineseschool.speech.model.Contestor;
import org.michiganchineseschool.speech.model.ContestorIndividual;
import org.michiganchineseschool.speech.model.GradYear;
import org.michiganchineseschool.speech.model.Judge;
import org.michiganchineseschool.speech.model.Role;
import org.michiganchineseschool.speech.model.ScoreCountingType;
import org.michiganchineseschool.speech.model.ScoreRule;
import org.michiganchineseschool.speech.model.ScoreRuleItem;
import org.michiganchineseschool.speech.model.SpeechScore;
import org.michiganchineseschool.speech.model.Staff;
import org.michiganchineseschool.speech.model.Student;
import org.michiganchineseschool.speech.model.TimeLimitRule;

public interface DatabaseService {
	public Student getStudentById(String id) throws Exception;

	public List<Student> getAllStudents() throws Exception;

	public void deleteStudent(String idstudent) throws Exception;

	public void insertStudent(Student student) throws Exception;

	public void updateStudent(Student student) throws Exception;

	public Staff getStaffById(String id) throws Exception;

	public List<Staff> getAllStaffs() throws Exception;

	public void deleteStaff(String idstaff) throws Exception;

	public void insertStaff(Staff staff) throws Exception;

	public void updateStaff(Staff staff) throws Exception;

	public Contest getContestById(String id) throws Exception;

	public List<Contest> getAllContests() throws Exception;

	public void deleteContest(String id) throws Exception;

	public void insertContest(Contest contest) throws Exception;

	public void updateContest(Contest contest) throws Exception;

	public ContestLocation getLocationById(String id) throws Exception;

	public List<ContestLocation> getAllLocations() throws Exception;

	public void deleteLocation(String id) throws Exception;

	public void insertLocation(ContestLocation record) throws Exception;

	public void updateLocation(ContestLocation record) throws Exception;

	public Role getRoleById(String id) throws Exception;

	public List<Role> getAllRoles() throws Exception;

	public void deleteRole(String id) throws Exception;

	public void insertRole(Role record) throws Exception;

	public void updateRole(Role record) throws Exception;

	public ScoreCountingType getScoreCountingTypeById(String id)
			throws Exception;

	public List<ScoreCountingType> getAllScoreCountingTypes() throws Exception;

	public void deleteScoreCountingType(String id) throws Exception;

	public void insertScoreCountingType(ScoreCountingType record)
			throws Exception;

	public void updateScoreCountingType(ScoreCountingType record)
			throws Exception;

	public ScoreRule getScoreRuleById(String id) throws Exception;

	public List<ScoreRule> getAllScoreRules() throws Exception;

	public void deleteScoreRule(String id) throws Exception;

	public void insertScoreRule(ScoreRule record) throws Exception;

	public void updateScoreRule(ScoreRule record) throws Exception;

	public TimeLimitRule getTimeLimitRuleById(String id) throws Exception;

	public List<TimeLimitRule> getAllTimeLimitRules() throws Exception;

	public void deleteTimeLimitRule(String id) throws Exception;

	public void insertTimeLimitRule(TimeLimitRule record) throws Exception;

	public void updateTimeLimitRule(TimeLimitRule record) throws Exception;

	public ScoreRuleItem getScoreRuleItemById(String id) throws Exception;

	public List<ScoreRuleItem> getAllScoreRuleItems() throws Exception;

	public void deleteScoreRuleItem(String id) throws Exception;

	public void insertScoreRuleItem(ScoreRuleItem record) throws Exception;

	public void updateScoreRuleItem(ScoreRuleItem record) throws Exception;

	public ContestGroup getContestGroupById(String id) throws Exception;

	public List<ContestGroup> getAllContestGroups() throws Exception;

	public void deleteContestGroup(String id) throws Exception;

	public void insertContestGroup(ContestGroup record) throws Exception;

	public void updateContestGroup(ContestGroup record) throws Exception;

	public Contestor getContestorById(String id) throws Exception;

	public List<Contestor> getAllContestors() throws Exception;

	public void deleteContestor(String id) throws Exception;

	public void insertContestor(Contestor record) throws Exception;

	public void updateContestor(Contestor record) throws Exception;

	public ContestorIndividual getContestorIndividualById(String id)
			throws Exception;

	public List<ContestorIndividual> getAllContestorIndividuals()
			throws Exception;

	public void deleteContestorIndividual(String id) throws Exception;

	public void insertContestorIndividual(ContestorIndividual record)
			throws Exception;

	public void updateContestorIndividual(ContestorIndividual record)
			throws Exception;

	public Judge getJudgeById(String id) throws Exception;

	public List<Judge> getAllJudges() throws Exception;

	public void deleteJudge(String id) throws Exception;

	public void insertJudge(Judge record) throws Exception;

	public void updateJudge(Judge record) throws Exception;

	public List<Staff> selectListForLogin() throws Exception;

	public List<ContestGroup> selectContestGroupListForLoginedStaff(
			String idstaff, boolean isUnSubmitOnly) throws Exception;

	public List<Contestor> selectContestorByContestGroup(String idcontestGroup,
			String idstaff, String idrole) throws Exception;

	public void updateSpeechScore(SpeechScore speechScore) throws Exception;

	public void updateSpeechScoreByContestor(Contestor contestor)
			throws Exception;

	public GradYear getGradYearById(String id) throws Exception;

	public List<GradYear> getAllGradYears() throws Exception;

	public void deleteGradYear(String id) throws Exception;

	public void insertGradYear(GradYear record) throws Exception;

	public void updateGradYear(GradYear record) throws Exception;

	public List<Contestor> getContestorScoreReportByContestGroup(String id)
			throws Exception;

	public List<ContestGroup> getActivateContestContestGroup() throws Exception;

	public Staff login(Staff staff) throws Exception;
}
