package student.tugraz.at.lv_master3000.test.mocks_and_dummies;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import student.tugraz.at.lv_master3000.LVMaster3000DBHelper;

/**
 * Created with IntelliJ IDEA.
 * User: doris
 * Date: 6/4/13
 * Time: 6:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class DBHelperMock extends LVMaster3000DBHelper{

    public DBHelperMock(Context context) {
        super(context);
    }

}
