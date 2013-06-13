package student.tugraz.at.lv_master3000.test;

import android.test.AndroidTestCase;
import student.tugraz.at.lv_master3000.databaseAccess.LVMaster3000DBHelper;

public class DatabaseTest extends AndroidTestCase{

    //DBHelperMock dbHelper;
    LVMaster3000DBHelper dbHelper;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        dbHelper  = new LVMaster3000DBHelper(this.getContext());
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testDatabaseConnection(){
        boolean isCreated = false;

        if(dbHelper == null)
            isCreated = false;
        else
            isCreated = true;

        assertEquals(true, isCreated);
        assertEquals(false, dbHelper.isDBNull());
        assertEquals(true, dbHelper.isDBOpen());

        // this works, but i don't know how to open it again
        //dbHelper.closeDB();
        //assertEquals(false, dbHelper.isDBOpen());

    }

    /*  getDatabaseName() --> no such method exception  ???
    public void testDatabaseName(){
        assertEquals("LVMaster3000", dbHelper.getDatabaseName());
    }
     */
}
