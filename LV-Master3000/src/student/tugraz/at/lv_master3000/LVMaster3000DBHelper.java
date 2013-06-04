package student.tugraz.at.lv_master3000;

/**
 * Created with IntelliJ IDEA.
 * User: doris
 * Date: 6/4/13
 * Time: 5:03 PM
 * To change this template use File | Settings | File Templates.
 */
import android.content.*;
import android.database.*;
import android.database.sqlite.*;

public class LVMaster3000DBHelper extends SQLiteOpenHelper{
    private static final String dbname = "LVMaster3000";
    private static int dbversion = 0;

    protected SQLiteDatabase db;

   // public LVMaster3000DBHelper(Context context){
   //
   //     //db = context.openOrCreateDatabase("LVMaster3000", 0, null);
   // }

    public LVMaster3000DBHelper(Context context) {
        super(context, dbname, null, dbversion);
    }

    public void saveNewHomework(Homework homework){


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    //public Homework getHomeworkEarliestOfLecture(){
    //
    //}

}
