package student.tugraz.at.lv_master3000.databaseAccess;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import student.tugraz.at.lv_master3000.Homework;

/**
 * Created with IntelliJ IDEA.
 * User: doris
 * Date: 6/6/13
 * Time: 10:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class HomeworkManager extends LVMaster3000DBHelper{
    public LVMaster3000DBHelper dbHelper;

    private static String tableName = "homework";

    public HomeworkManager(Context context){
        super(context);
    }

    public Integer insertNewHomework(Homework homework){

        // TODO insert into database
        return null;
    }

    public Homework getHomeworkFromDB(int hwId) {
        Homework result = null;
        // TODO fetch from database

        return result;
    }
}
