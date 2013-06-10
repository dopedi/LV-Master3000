package student.tugraz.at.lv_master3000.databaseAccess;

import android.content.Context;

/**
 * Created with IntelliJ IDEA.
 * User: doris
 * Date: 6/10/13
 * Time: 5:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class MilestoneManager extends LVMaster3000DBHelper{
    private static final String tableName = "milestone";
    private static final String[] columns = new String[]{"_id","milestone_date", "description"};

    public MilestoneManager(Context context) {
        super(context);
    }
}
