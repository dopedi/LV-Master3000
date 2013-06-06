package student.tugraz.at.lv_master3000.test;

import java.util.Date;

import android.test.AndroidTestCase;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import student.tugraz.at.lv_master3000.Homework;
import student.tugraz.at.lv_master3000.Lecture;
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
		super.tearDown();
		hw = null;
	}
	
	public void testDueDate(){
		assertEquals(due, hw.getDueDate());
	}

    public void testPersistHomework(){

        Integer hwId = null;
        hwId = homeworkManager.insertNewHomework(hw);
        assertNotNull(hwId);


        Homework hwFromDB = homeworkManager.getHomeworkFromDB(hwId);
        assertEquals("assignment 1", hwFromDB.getName());
    }

}
