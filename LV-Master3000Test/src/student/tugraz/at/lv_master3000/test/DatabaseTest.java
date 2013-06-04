package student.tugraz.at.lv_master3000.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import junit.framework.TestCase;
import student.tugraz.at.lv_master3000.LVMaster3000DBHelper;
import student.tugraz.at.lv_master3000.test.mocks_and_dummies.DBHelperMock;

public class DatabaseTest extends AndroidTestCase{

    DBHelperMock dbHelper;


    @Override
    public void setUp() throws Exception {
        super.setUp();
        dbHelper  = new DBHelperMock(this.getContext());
    }

    @Override
    public void tearDown() throws Exception {
        dbHelper.closeDatabaseConnection();
        super.tearDown();
    }

    public void testDatabaseConnection(){
        assertEquals(true, dbHelper.isDatabaseOpen());


        //now close
        dbHelper.closeDatabaseConnection();
        assertEquals(false,dbHelper.isDatabaseOpen());

        dbHelper.openDatabaseConnection(this.getContext());
    }
}
