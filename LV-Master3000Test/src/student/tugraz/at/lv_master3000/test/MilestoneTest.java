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

        int msId = milestoneManager.insertNewMilestone(ms);

        assertNotSame(-1, msId);
    }

    public void testGetMilestoneFromDB(){
        String description = "alle testcases sollten grün laufen";
        Date date = new Date(2013, 6, 6);

        Milestone milestone = new Milestone(date);
        milestone.setDescription(description);

        int msId = milestoneManager.insertNewMilestone(milestone);
        assertNotSame(-1, msId);

        Milestone result = milestoneManager.getMilestoneFromDB(msId);

        String resDesc;
        if(result == null)
            resDesc = "";
        else
            resDesc = result.getDescription();

        assertEquals(description, resDesc);

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

    }

    public void testDeleteMilestone(){

    }

    public void testGetAllExpiredMilestones(){

    }

    public void testGetAllActiveMilestones(){

    }

    public void testGetAllFinishedMilestones(){

    }

    public void testGetAllNextMilestones(){

    }
}
