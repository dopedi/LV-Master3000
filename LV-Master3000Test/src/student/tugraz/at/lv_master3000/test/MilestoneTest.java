package student.tugraz.at.lv_master3000.test;

import android.test.AndroidTestCase;
import student.tugraz.at.lv_master3000.databaseAccess.MilestoneManager;
import student.tugraz.at.lv_master3000.domain.Lecture;
import student.tugraz.at.lv_master3000.domain.Milestone;

import java.util.Date;

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
    @Override
    public void setUp() throws Exception
    {
        super.setUp();
        milestoneManager = new MilestoneManager(this.getContext());
        lecture = new Lecture("NRLA");
    }

    @Override
    public void tearDown() throws Exception
    {
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
}
