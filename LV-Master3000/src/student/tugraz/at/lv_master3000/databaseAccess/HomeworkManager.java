package student.tugraz.at.lv_master3000.databaseAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import student.tugraz.at.lv_master3000.domain.Homework;

import java.util.ArrayList;
import java.util.List;

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
        ContentValues values = new ContentValues();
        values.put("name", homework.getName());
        values.put("lecture", homework.getLecture());

        java.util.Date date = homework.getDueDate();
        java.sql.Date sqlDate;
        if(date != null){
            sqlDate = new java.sql.Date(date.getTime());
            values.put("due_date", sqlDate.toString());
        }

        return (int)db.insert(tableName, "null",values);
    }

    public Homework getHomeworkFromDB(int hwId) {
        Homework result = null;

        String[] columns = new String[]{"_id","name", "due_date", "lecture"};
        String selection = "_id =?";


        Cursor cursor = db.query(tableName, columns,selection,new String[]{String.valueOf(hwId)},null, null,null , null);

        result = fillQueryResultInHomework(cursor);

        return result;
    }

    public Homework getHomeworkFromDBByName(String name) {
        Homework result = null;

        String[] columns = new String[]{"_id","name", "due_date", "lecture"};
        String selection = "name =?";


        Cursor cursor = db.query(tableName, columns,selection,new String[]{name},null, null,null , null);

        result = fillQueryResultInHomework(cursor);

        return result;
    }

    private Homework fillQueryResultInHomework(Cursor cursor){
        Homework result = null;

        if(cursor.moveToFirst()){
            result = new Homework(cursor.getInt(cursor.getColumnIndexOrThrow("lecture")));
            result.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
            result.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));

            Long dateLong = cursor.getLong(cursor.getColumnIndexOrThrow("due_date"));
            if(dateLong != null){
                java.sql.Date sqlDate = new java.sql.Date(dateLong);
                java.util.Date date = new java.util.Date(sqlDate.getTime());
                result.setDueDate(date);
            }
        }

        return result;
    }

    public List<Homework> getAllHomeworks(){
        return new ArrayList<Homework>();
    }

    public List<Homework> getAllHomeworksOfLecture(int lecId){
        return new ArrayList<Homework>();
    }

    private List<Homework> fillQueryResultListIntoHomeworkList(Cursor cursor){
        return new ArrayList<Homework>();
    }

    public boolean addWorkmate(int wmId){
        return true;
    }

    public boolean addMilestone(int msId){
        return true;
    }

    public boolean addLearningMaterials(int lmId){
        return true;
    }

}
