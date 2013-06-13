package student.tugraz.at.lv_master3000.test;

import android.test.AndroidTestCase;
import student.tugraz.at.lv_master3000.databaseAccess.WorkmateManager;
import student.tugraz.at.lv_master3000.domain.Lecture;
import student.tugraz.at.lv_master3000.domain.Workmate;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: doris
 * Date: 6/10/13
 * Time: 6:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class WorkmateTest extends AndroidTestCase{
    private WorkmateManager workmateManager;
    private Lecture lecture;
    @Override
    public void setUp() throws Exception
    {
        super.setUp();
        workmateManager = new WorkmateManager(this.getContext());
        lecture = new Lecture("NRLA");
    }

    @Override
    public void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testGettersAndSetters(){
        String name = "Knecht Ruprecht";
        int id = 1000;
        String email = "littleHelpe@santa.com";
        String mobile = "06803456789";

        Workmate wm = new Workmate(name);
        wm.setId(id);
        wm.setCellPhoneNr(mobile);
        wm.setEmail(email);

        assertEquals(mobile, wm.getCellPhoneNr());
        assertEquals(name, wm.getName());
        assertEquals(id, wm.getId());
        assertEquals(email, wm.getEmail());
    }

    public void testInsertNewWorkmate(){
        String name = "Knecht Ruprecht";
        String email = "littleHelpe@santa.com";
        String mobile = "06803456789";

        Workmate wm = new Workmate(name);
        wm.setCellPhoneNr(mobile);
        wm.setEmail(email);

        int wmId = workmateManager.insertNewWorkmate(wm);

        assertNotSame(-1, wmId); // -1 is errorcase
    }
}
