package student.tugraz.at.lv_master3000.test;

import java.util.Date;

import android.test.AndroidTestCase;
import student.tugraz.at.lv_master3000.Homework;
import student.tugraz.at.lv_master3000.databaseAccess.HomeworkManager;

//@RunWith(JUnit4.class)
public class HomeworkTest extends AndroidTestCase {

	
	private Homework hw;
	private Date due;
    private HomeworkManager dbManager;
	
	protected void setUp() throws Exception {
		super.setUp();
		dbManager = new HomeworkManager(this.getContext());

        hw = new Homework();
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
        hwId = dbManager.insertNewHomework(hw);
        assertNotNull(hwId);


        Homework hwFromDB = dbManager.getHomeworkFromDB(hwId);
        assertEquals("assignment 1", hwFromDB.getName());
    }

}
