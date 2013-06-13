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

        int id = (int)db.insert(tableName, "null", values);
        homework.setId(id);

        return id;
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
        String selectQuery = "SELECT  * FROM " + tableName;

        Cursor cursor = db.rawQuery(selectQuery, null);
        return  fillQueryResultListIntoHomeworkList(cursor);
    }

    public List<Homework> getAllHomeworksOfLecture(int lecId){
        String selectQuery = "SELECT  * FROM " + tableName + " WHERE " + tableName + ".lecture = " +lecId;

        Cursor cursor = db.rawQuery(selectQuery, null);
        return  fillQueryResultListIntoHomeworkList(cursor);
    }

    private List<Homework> fillQueryResultListIntoHomeworkList(Cursor cursor){
        List<Homework> resultList = new ArrayList<Homework>();

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Homework result = new Homework(cursor.getInt(cursor.getColumnIndexOrThrow("lecture")));
                result.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
                result.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));

                Long dateLong = cursor.getLong(cursor.getColumnIndexOrThrow("due_date"));
                if(dateLong != null){
                    java.sql.Date sqlDate = new java.sql.Date(dateLong);
                    java.util.Date date = new java.util.Date(sqlDate.getTime());
                    result.setDueDate(date);
                }

                resultList.add(result);
            } while (cursor.moveToNext());
        }

        return  resultList;
    }

    public boolean addWorkmateToHomework(int wmId, int hwId){
        ContentValues values = new ContentValues();
        values.put("homework", hwId);
        values.put("workmate", wmId);


        int id = (int)db.insert("homework2workmate", "null", values);

        if(id != -1)
            return true;
        else
            return false;
    }

    public boolean addMilestoneToHomework(int msId, int hwId){
        ContentValues values = new ContentValues();
        values.put("homework", hwId);
        values.put("milestone", msId);


        int id = (int)db.insert("homework2milestone", "null", values);

        if(id != -1)
            return true;
        else
            return false;
    }

    public boolean addLearningMaterialsToHomework(int lmId, int hwId){
        ContentValues values = new ContentValues();
        values.put("homework", hwId);
        values.put("learning_materials", lmId);


        int id = (int)db.insert("homework2learning_materials", "null", values);

        if(id != -1)
            return true;
        else
            return false;
    }

    public List<Homework> getNextHomeworks(){
        return null;
    }

    public boolean validateHomework(Homework homework){
        return false;
    }

    public boolean updateHomework(int hwId, Homework newValues){
        return false;
    }

    public boolean deleteHomework(int hwId){
        return false;
    }

}
