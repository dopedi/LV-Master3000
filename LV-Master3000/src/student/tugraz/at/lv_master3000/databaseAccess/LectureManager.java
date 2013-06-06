package student.tugraz.at.lv_master3000.databaseAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import student.tugraz.at.lv_master3000.Lecture;

import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: doris
 * Date: 6/6/13
 * Time: 11:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class LectureManager extends LVMaster3000DBHelper{
    private static String tableName = "lecture";
    private int rowCount = 0;

    public LectureManager(Context context) {
        super(context);
    }

    public Integer insertNewLecture(Lecture lecture){
        String insertStmt = insertInto + tableName;//+ dbname + "." + tableName;
        //insertStmt += " ( name, location, day, time, prof_name, mandatory) " + values;
        insertStmt += " (" + (rowCount+1) + ",";
        insertStmt += lecture.getName() + ",";
        insertStmt += lecture.getPlace() + ",";
        insertStmt += lecture.getDay() + ",";
        insertStmt += lecture.getDate() + ",";
        insertStmt += lecture.getProfessorName() + ",";
        insertStmt += lecture.getMandatory() +");";

        //db.execSQL(insertStmt);

        ContentValues values = new ContentValues();
        values.put("name", lecture.getName());
        values.put("day", lecture.getDay());
        values.put("mandatory", lecture.getMandatory());
        values.put("location", lecture.getPlace());
        values.put("prof_name", lecture.getProfessorName());

        java.util.Date date = lecture.getDate();
        java.sql.Date sqlDate;
        if(date != null){
            sqlDate = new java.sql.Date(date.getTime());
            values.put("exam_date", sqlDate.toString());
        }

        db.insert(tableName, "null",values);

        rowCount++;

        return rowCount-1;
    }



    public Lecture getLectureFromDB(int id){
        Lecture result = null;
        // TODO fetch from DB

        return result;
    }

    public Lecture getLectureFromDBByName(String name) {
        Lecture result = null;

        String[] columns = new String[]{"_id","name", "location", "day", "time", "prof_name", "mandatory"};
        String selection = "name =?";


        Cursor cursor = db.query("lecture", columns,selection,new String[]{name},null, null,null , null);

        if(cursor.moveToFirst()){
            result = new Lecture(cursor.getString(cursor.getColumnIndexOrThrow("name")));
            result.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
            result.setMandatory(cursor.getInt(cursor.getColumnIndexOrThrow("mandatory"))>0);
            result.setPlace(cursor.getString(cursor.getColumnIndexOrThrow("location")));
            result.setProfessorName(cursor.getString(cursor.getColumnIndexOrThrow("prof_name")));
            result.setDay(cursor.getString(cursor.getColumnIndexOrThrow("day")));
            Long dateLong = cursor.getLong(cursor.getColumnIndexOrThrow("time"));
            if(dateLong != null){
                java.sql.Date sqlDate = new java.sql.Date(dateLong);
                java.util.Date date = new java.util.Date(sqlDate.getTime());
                result.setDate(date);
            }
        }

        return result;
    }

}
