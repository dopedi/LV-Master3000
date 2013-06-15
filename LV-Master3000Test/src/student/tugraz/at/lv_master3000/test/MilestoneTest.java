package student.tugraz.at.lv_master3000.test;

import android.test.AndroidTestCase;
import student.tugraz.at.lv_master3000.databaseAccess.ExamManager;
import student.tugraz.at.lv_master3000.databaseAccess.HomeworkManager;
import student.tugraz.at.lv_master3000.databaseAccess.MilestoneManager;
import student.tugraz.at.lv_master3000.domain.Exam;
import student.tugraz.at.lv_master3000.domain.Homework;
import student.tugraz.at.lv_master3000.domain.Lecture;
import student.tugraz.at.lv_master3000.domain.Milestone;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: doris
 * Date: 6/10/13
 * Time: 6:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class MilestoneTest extends AndroidTestCase{
    private MilestoneManager milestoneManager;
    private Lecture lecture;
    private Lecture lecture2;
    @Override
    public void setUp() throws Exception
    {
        super.setUp();
        milestoneManager = new MilestoneManager(this.getContext());
        lecture = new Lecture("NRLA");
        lecture2 = new Lecture("RNO");
    }

    @Override
    public void tearDown() throws Exception
    {
        milestoneManager.cleanAllTables();
        super.tearDown();
    }

    public void testGettersAndSetters(){
        String description = "alle testcases sollten grün laufen";
        int id = 1000;
        Date date = new Date(2013, 6, 6);

        Milestone ms = new Milestone(date);
        ms.setId(id);
        ms.setDescription(description);

        assertEquals(description, ms.getDescription());
        assertEquals(id, ms.getId());
        assertEquals(date, ms.getDate());
    }

    public void testInsertNewMilestone(){
        String description = "alle testcases sollten grün laufen";
        Date date = new Date(2013, 6, 6);

        Milestone ms = new Milestone(date);
        ms.setDescription(description);
        ms.setFinished(true);

        int msId = milestoneManager.insertNewMilestone(ms);

        assertNotSame(-1, msId);
    }

    public void testGetMilestoneFromDB(){
        String description = "alle testcases sollten grün laufen";
        Date date = new Date(2013, 6, 6);

        Milestone milestone = new Milestone(date);
        milestone.setDescription(description);
        milestone.setFinished(true);

        int msId = milestoneManager.insertNewMilestone(milestone);
        assertNotSame(-1, msId);

        Milestone result = milestoneManager.getMilestoneFromDB(msId);

        String resDesc;
        if(result == null)
            resDesc = "";
        else
            resDesc = result.getDescription();

        assertEquals(description, resDesc);
        assertTrue(result.isFinished());

    }

    public void testGetAllMilestones(){
        String description = "datenbankanbindung";
        Date date = new Date(2013, 6, 6);
        String desc2 = "alles fertig";
        Date date2 = new Date(2013, 7, 7);

        Milestone milestone = new Milestone(date);
        milestone.setDescription(description);

        int msId = milestoneManager.insertNewMilestone(milestone);
        assertNotSame(-1, msId);

        Milestone milestone2 = new Milestone(date2);
        milestone2.setDescription(desc2);

        msId = milestoneManager.insertNewMilestone(milestone2);
        assertNotSame(-1, msId);

        List<Milestone> list = milestoneManager.getAllMilestones();

        assertEquals(2, list.size());
    }

    public void testGetAllMilestonesOfHomework(){
        Milestone m1 = new Milestone(new Date(2013, 8, 8));
        Milestone m2 = new Milestone(new Date(2013, 9, 9));

        int id1 = milestoneManager.insertNewMilestone(m1);
        int id2 = milestoneManager.insertNewMilestone(m2);

        HomeworkManager hwMan = new HomeworkManager(this.getContext());

        Homework hw = new Homework(lecture.getId());
        int hwId = hwMan.insertNewHomework(hw);

        hwMan.addMilestoneToHomework(id1, hwId);
        hwMan.addMilestoneToHomework(id2, hwId);

        List<Milestone> msList = milestoneManager.getAllMilestonesOfHomework(hwId);

        assertNotNull(msList);
        if(msList != null)
            assertEquals(2, msList.size());
    }

    public void testGetAllMilestonesOfExam(){
        Milestone m1 = new Milestone(new Date(2013, 8, 8));
        Milestone m2 = new Milestone(new Date(2013, 9, 9));

        int id1 = milestoneManager.insertNewMilestone(m1);
        int id2 = milestoneManager.insertNewMilestone(m2);

        ExamManager examManager = new ExamManager(this.getContext());

        Exam exam = new Exam(lecture.getId());
        int exId = examManager.insertNewExam(exam);

        examManager.addMilestoneToExam(id1, exId);
        examManager.addMilestoneToExam(id2, exId);

        List<Milestone> msList = milestoneManager.getAllMilestonesOfExam(exId);

        assertNotNull(msList);
        if(msList != null)
            assertEquals(2, msList.size());
    }

    public void testValidateMilestone(){

    }

    public void testGetNextMilestones(){

    }

    public void testGetFirstMilestoneOfHomework(){
        String description = "this is the next milestone";

        Milestone expired = new Milestone(new Date(112, 1, 1));
        expired.setDescription("this is the expired milestone");
        Milestone active = new Milestone(new Date(113,9,9));
        active.setDescription(description);
        Milestone late = new Milestone(new Date(114,3,3));
        late.setDescription("this is the later milestone");

        int expiredId = milestoneManager.insertNewMilestone(expired);
        int activeId = milestoneManager.insertNewMilestone(active);
        int lateId = milestoneManager.insertNewMilestone(late);

        Homework homework = new Homework(lecture.getId());
        HomeworkManager hwMan = new HomeworkManager(this.getContext());
        int hwId = hwMan.insertNewHomework(homework);

        hwMan.addMilestoneToHomework(expiredId, hwId);
        hwMan.addMilestoneToHomework(activeId, hwId);
        hwMan.addMilestoneToHomework(lateId, hwId);

        Milestone result = milestoneManager.getFirstMilestoneForHomework(hwId);

        assertNotNull(result);
        if(result != null)
            assertEquals(description, result.getDescription());
    }

    public void testGetFirstMilestoneOfExam(){
        String description = "this is the next milestone";

        Milestone expired = new Milestone(new Date(112, 1, 1));
        expired.setDescription("this is the expired milestone");
        Milestone active = new Milestone(new Date(113,9,9));
        active.setDescription(description);
        Milestone late = new Milestone(new Date(114,3,3));
        late.setDescription("this is the later milestone");

        int expiredId = milestoneManager.insertNewMilestone(expired);
        int activeId = milestoneManager.insertNewMilestone(active);
        int lateId = milestoneManager.insertNewMilestone(late);

        Exam exam = new Exam(lecture.getId());
        ExamManager exMan = new ExamManager(this.getContext());
        int exId = exMan.insertNewExam(exam);

        exMan.addMilestoneToExam(expiredId, exId);
        exMan.addMilestoneToExam(activeId, exId);
        exMan.addMilestoneToExam(lateId, exId);

        Milestone result = milestoneManager.getFirstMilestoneForExam(exId);

        assertNotNull(result);
        if(result != null)
            assertEquals(description, result.getDescription());
    }

    public void testGetExpiredMilestonesOfHomework(){

    }

    public void testGetExpiredMilestonesOfExam(){

    }

    public void testGetFinishedMilestonesOfHomework(){

    }

    public void testGetFinishedMilestonesOfExam(){

    }

    public void testGetActiveMilestonesOfHomework(){

    }

    public void testGetActiveMilestonesOfExam(){

    }

    public void testUpdateMilestone(){
        Milestone ms = new Milestone(new Date(114, 2, 3));
        Homework hw = new Homework(lecture.getId());

        String desc1 = "number one";
        String desc2 = "number two";

        ms.setDescription(desc1);

        int msId = milestoneManager.insertNewMilestone(ms);

        ms.setDescription(desc2);
        ms.setExpired(true);
        boolean worked = milestoneManager.updateMilestone(msId, ms);

        Milestone resultMs = milestoneManager.getMilestoneFromDB(msId);

        assertTrue(worked);
        assertNotNull(resultMs);
        if(resultMs != null)
            assertEquals(desc2, resultMs.getDescription());
        assertTrue(resultMs.isExpired());
    }

    public void testDeleteMilestone(){
        Milestone ms = new Milestone(new Date(113,8,8));
        int id = milestoneManager.insertNewMilestone(ms);

        boolean worked = milestoneManager.deleteMilestone(id);
        assertTrue(worked);

        Milestone resultMs = milestoneManager.getMilestoneFromDB(id);

        assertNull(resultMs);
    }

    public void testGetAllExpiredMilestones(){

    }

    public void testGetAllActiveMilestones(){

    }

    public void testGetAllFinishedMilestones(){

    }

    public void testGetAllNextMilestones(){

    }

    public void testUpdateExpiredForAllMilestones(){
        Milestone expired = new Milestone(new Date(111,2,2));
        int expiredId = milestoneManager.insertNewMilestone(expired);
        Milestone farAway = new Milestone(new Date(120, 3, 3));
        int farAwayId = milestoneManager.insertNewMilestone(farAway);

        int affected = milestoneManager.updateExpiredForAllMilestones();

        Milestone resultExpired = milestoneManager.getMilestoneFromDB(expiredId);
        Milestone resultFarAway = milestoneManager.getMilestoneFromDB(farAwayId);

        assertEquals(1, affected);
        assertEquals(true, resultExpired.isExpired());
        assertEquals(false, resultFarAway.isExpired());

    }
}
