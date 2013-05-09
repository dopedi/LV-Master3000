package test.student.tugraz.at.lv_master3000;

import java.util.Date;
import junit.framework.TestCase;
import student.tugraz.at.lv_master3000.Homework;

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
