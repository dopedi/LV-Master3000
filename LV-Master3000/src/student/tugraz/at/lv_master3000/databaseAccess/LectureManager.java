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
        //Date date = new Date(lecture.getDate().getTime());
        //values.put("time",date);
        db.insert(tableName, "null",values);

        rowCount++;

        return rowCount-1;
    }



    public Lecture getLectureFromDB(int id){
        Lecture result = null;
        // TODO fetch from DB

        return result;
    }

    public Lecture getLectureFromDBByName(String mobapp) {
        Lecture result = null;

        String selectStmt = "select * from " + tableName;
        //Cursor cursor = db.query();

        return result;
    }

}
