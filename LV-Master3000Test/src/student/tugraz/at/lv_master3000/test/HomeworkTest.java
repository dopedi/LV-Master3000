package student.tugraz.at.lv_master3000.test;

import java.util.Date;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import student.tugraz.at.lv_master3000.Homework;

//@RunWith(JUnit4.class)
public class HomeworkTest extends TestCase {

	
	private Homework hw;
	private Date due;
	
	protected void setUp() throws Exception {
		super.setUp();
		hw = new Homework();
		due = new Date(2012, 3, 12);
		hw.setDueDate(due);
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		hw = null;
	}
	
	public void testDueDate(){
		assertEquals(due, hw.getDueDate());
	} 

}
