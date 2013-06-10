package student.tugraz.at.lv_master3000.databaseAccess;

import android.content.Context;

/**
 * Created with IntelliJ IDEA.
 * User: doris
 * Date: 6/10/13
 * Time: 5:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class WorkmateManager extends LVMaster3000DBHelper{
    private static final String tableName = "workmate";
    private static final String[] columns = new String[]{"_id","name", "email", "mobile"};

    public WorkmateManager(Context context) {
        super(context);
    }


}
