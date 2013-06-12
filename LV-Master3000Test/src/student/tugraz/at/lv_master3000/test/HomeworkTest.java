package student.tugraz.at.lv_master3000.test;

import java.util.Date;
import java.util.List;

import android.test.AndroidTestCase;
import student.tugraz.at.lv_master3000.domain.Homework;
import student.tugraz.at.lv_master3000.domain.Lecture;
import student.tugraz.at.lv_master3000.databaseAccess.HomeworkManager;
import student.tugraz.at.lv_master3000.databaseAccess.LectureManager;

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
		due = new Date(2012, 3, 12);
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
        Date date = new Date(2014, 1, 1);
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

    public void testAddMilestoneToExam(){

    }

    public void testAddLearningMaterialsToExam(){

    }

    public void testAddWorkmateToExam(){

    }
}
