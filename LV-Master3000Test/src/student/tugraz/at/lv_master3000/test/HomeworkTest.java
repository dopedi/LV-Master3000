package student.tugraz.at.lv_master3000.test;

import java.util.Date;
import java.util.List;

import android.test.AndroidTestCase;
import org.apache.http.HttpHost;
import student.tugraz.at.lv_master3000.databaseAccess.*;
import student.tugraz.at.lv_master3000.domain.*;

//@RunWith(JUnit4.class)
public class HomeworkTest extends AndroidTestCase {

	
	private Homework hw;
	private Date due;
    private HomeworkManager homeworkManager;
    private LectureManager lectureManager;
    private Lecture lecture;
	
	protected void setUp() throws Exception {
		super.setUp();
		lectureManager = new LectureManager(this.getContext());
        lecture = new Lecture("ESP");
        lecture.setProfessorName("Kappe");
        int lecId = lectureManager.insertNewLecture(lecture);

        homeworkManager = new HomeworkManager(this.getContext());

        hw = new Homework(lecId);
		due = new Date(112, 3, 12);
		hw.setDueDate(due);
		hw.setName("assignment 1");
        hw.setLecture(1);
	}

	protected void tearDown() throws Exception {
        hw = null;
        homeworkManager.cleanAllTables();
		super.tearDown();

	}
	
	public void testDueDate(){
		assertEquals(due, hw.getDueDate());
	}

    public void testGettersAndSetters(){
        String name = "ass 1";
        Date date = new Date(114, 1, 1);
        int id = 2001;
        int lecId = 1;

        Homework hw = new Homework(lecId);
        hw.setName(name);
        hw.setDueDate(date);
        hw.setId(id);

        assertEquals(id, hw.getId());
        assertEquals(lecId, hw.getLecture());
        assertEquals(date, hw.getDueDate());
        assertEquals(name, hw.getName());
    }

    public void testInsertNewHomework(){

        Integer hwId = null;
        hwId = homeworkManager.insertNewHomework(hw);
        assertNotNull(hwId);


        Homework hwFromDB = homeworkManager.getHomeworkFromDB(hwId);
        assertEquals("assignment 1", hwFromDB.getName());
    }

    public void testGetHomeworkFromDB(){
        String name = "assignment 2";

        Homework homework = new Homework(lecture.getId());
        homework.setName(name);

        int hwId = homeworkManager.insertNewHomework(homework);
        assertNotSame(-1, hwId);

        Homework result = homeworkManager.getHomeworkFromDB(hwId);

        String resName;
        if(result == null)
            resName = "";
        else
            resName = result.getName();

        assertEquals(name, resName);

    }

    public void testGetAllHomeworks(){
        assertNotNull(lecture.getId());

        Homework h1 = new Homework(lecture.getId());
        Homework h2 = new Homework(lecture.getId());

        int id = homeworkManager.insertNewHomework(h1);
        assertNotSame(-1, id);

        id = homeworkManager.insertNewHomework(h2);
        assertNotSame(-1, id);

        List<Homework> list = homeworkManager.getAllHomeworks();

        assertEquals(2, list.size());
    }

    public void testGetAllHomeworksOfLecture(){
        assertNotNull(lecture.getId());

        Homework h1 = new Homework(lecture.getId());

        Lecture lecture2 = new Lecture("HCI");
        int id = lectureManager.insertNewLecture(lecture2);
        assertNotSame(-1, id);

        Homework h2 = new Homework(id);

        id = homeworkManager.insertNewHomework(h1);
        assertNotSame(-1, id);

        id = homeworkManager.insertNewHomework(h2);
        assertNotSame(-1, id);

        List<Homework> list = homeworkManager.getAllHomeworksOfLecture(lecture.getId());

        assertEquals(1, list.size());
    }

    public void testAddMilestoneToHomework(){
        Homework hw = new Homework(lecture.getId());
        Milestone ms = new Milestone(new Date(113, 7, 7));

        int hwId = homeworkManager.insertNewHomework(hw);
        int msId = new MilestoneManager(this.getContext()).insertNewMilestone(ms);

        boolean hw2msWorked = homeworkManager.addMilestoneToHomework(msId, hwId);

        assertTrue(hw2msWorked);
    }

    public void testAddLearningMaterialsToHomework(){
        Homework hw = new Homework(lecture.getId());
        LearningMaterials lm = new LearningMaterials("best practices");

        int hwId = homeworkManager.insertNewHomework(hw);
        int lmId = new LearningMaterialsManager(this.getContext()).insertNewLearningMaterials(lm);

        boolean hw2msWorked = homeworkManager.addLearningMaterialsToHomework(lmId, hwId);

        assertTrue(hw2msWorked);
    }

    public void testAddWorkmateToHomework(){
        Homework hw = new Homework(lecture.getId());
        Workmate wm = new Workmate("Peter");

        int hwId = homeworkManager.insertNewHomework(hw);
        int wmId = new WorkmateManager(this.getContext()).insertNewWorkmate(wm);

        boolean hw2msWorked = homeworkManager.addWorkmateToHomework(wmId, hwId);

        assertTrue(hw2msWorked);
    }

    public void testGetNextHomeworks(){
        Homework expired = new Homework(lecture.getId());
        expired.setDueDate(new Date(112, 1, 1));
        Homework active1 = new Homework(lecture.getId());
        active1.setDueDate(new Date(114, 1, 1));
        Homework active2 = new Homework(lecture.getId());
        active2.setDueDate(new Date(114, 2, 2));

        homeworkManager.insertNewHomework(expired);
        homeworkManager.insertNewHomework(active1);
        homeworkManager.insertNewHomework(active2);

        List<Homework> resultList = homeworkManager.getNextHomeworks();

        assertNotNull(resultList);
        if(resultList != null)
            assertEquals(2, resultList.size());
    }

    // tests the referential integrity: if reference is deleted, then also the referee
    public void testDeleteHomework2Milestone(){
        Milestone ms = new Milestone(new Date(114,3,3));
        Homework hw = new Homework(lecture.getId());

        int hwId = homeworkManager.insertNewHomework(hw);
        MilestoneManager msMan = new MilestoneManager(this.getContext());
        int msId = msMan.insertNewMilestone(ms);

        boolean worked = homeworkManager.addMilestoneToHomework(msId, hwId);

        assertTrue(worked);

        msMan.deleteMilestone(msId);
        List<Milestone> resultList = msMan.getAllMilestonesOfHomework(hwId);

        assertEquals(0, resultList.size());

    }

    public void testUpdateHomework2Milestone(){

    }

    // tests the referential integrity: if reference is deleted, then also the referee
    public void testDeleteHomework2LearningMaterials(){
        LearningMaterials lm = new LearningMaterials("test");
        Homework hw = new Homework(lecture.getId());

        int hwId = homeworkManager.insertNewHomework(hw);
        LearningMaterialsManager lmMan = new LearningMaterialsManager(this.getContext());
        int lmId = lmMan.insertNewLearningMaterials(lm);

        boolean worked = homeworkManager.addLearningMaterialsToHomework(lmId, hwId);

        assertTrue(worked);

        lmMan.deleteLearningMaterials(lmId);
        List<LearningMaterials> resultList = lmMan.getAllLearningMaterialsOfHomework(hwId);

        assertEquals(0, resultList.size());
    }

    public void testUpdateHomework2LearningMaterials(){

    }

    // tests the referential integrity: if reference is deleted, then also the referee
    public void testDeleteHomework2Workmate(){
        Workmate workmate = new Workmate("hansi");
        Homework hw = new Homework(lecture.getId());

        int hwId = homeworkManager.insertNewHomework(hw);
        WorkmateManager wmMan = new WorkmateManager(this.getContext());
        int wmId = wmMan.insertNewWorkmate(workmate);

        boolean worked = homeworkManager.addWorkmateToHomework(wmId, hwId);

        assertTrue(worked);

        wmMan.deleteWorkmate(wmId);
        List<Workmate> resultList = wmMan.getAllWorkmatesOfHomework(hwId);

        assertEquals(0, resultList.size());
    }

    public void testUpdateHomework2Workmate(){

    }

    public void testValidateHomework(){

    }

    public void testUpdateHomework(){
        String desc1 = "assignment 1";
        String desc2 = "assignment 2";

        Homework homework = new Homework(lecture.getId());
        homework.setName(desc1);
        int id = homeworkManager.insertNewHomework(homework);
        homework.setName(desc2);

        boolean worked = homeworkManager.updateHomework(id, homework);
        assertTrue(worked);

        Homework result = homeworkManager.getHomeworkFromDB(id);
        assertEquals(desc2, result.getName());
    }

    public void testDeleteHomework(){
        Homework hw = new Homework(lecture.getId());

        int hwId = homeworkManager.insertNewHomework(hw);

        boolean worked = homeworkManager.deleteHomework(hwId);
        assertTrue(worked);

        Homework result = homeworkManager.getHomeworkFromDB(hwId);
        assertNull(result);
    }

}
