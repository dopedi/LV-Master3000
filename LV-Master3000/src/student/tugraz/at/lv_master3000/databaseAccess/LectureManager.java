package student.tugraz.at.lv_master3000.databaseAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import student.tugraz.at.lv_master3000.domain.Lecture;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: doris
 * Date: 6/6/13
 * Time: 11:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class LectureManager extends LVMaster3000DBHelper{
    private static String tableName = "lecture";

    public LectureManager(Context context) {
        super(context);
    }

    public Integer insertNewLecture(Lecture lecture){

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
            values.put("time", sqlDate.toString());
        }

        int id = (int)db.insert(tableName, "null", values);
        lecture.setId(id);

        return id;
    }



    public Lecture getLectureFromDB(int id){
        Lecture result = null;

        String[] columns = new String[]{"_id","name", "location", "day", "time", "prof_name", "mandatory"};
        String selection = "_id =?";


        Cursor cursor = db.query(tableName, columns,selection,new String[]{String.valueOf(id)},null, null,null , null);

        result = fillQueryResultInLecture(cursor);

        return result;
    }

    public Lecture getLectureFromDBByName(String name) {
        Lecture result = null;

        String[] columns = new String[]{"_id","name", "location", "day", "time", "prof_name", "mandatory"};
        String selection = "name =?";


        Cursor cursor = db.query(tableName, columns,selection,new String[]{name},null, null,null , null);

        result = fillQueryResultInLecture(cursor);

        return result;
    }

    private Lecture fillQueryResultInLecture(Cursor cursor){
        Lecture result = null;

        if(cursor.moveToFirst()){
            //System.out.println("try reading from lecture");
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

    public List<Lecture> getAllLectures(){

        String selectQuery = "SELECT  * FROM " + tableName;

        Cursor cursor = db.rawQuery(selectQuery, null);
        return  fillQueryResultListIntoExamList(cursor);
    }

    private List<Lecture> fillQueryResultListIntoExamList(Cursor cursor){
        List<Lecture> resultList = new ArrayList<Lecture>();

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Lecture lecture = new Lecture(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                lecture.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
                lecture.setProfessorName(cursor.getString(cursor.getColumnIndexOrThrow("prof_name")));
                lecture.setDay(cursor.getString(cursor.getColumnIndexOrThrow("day")));
                lecture.setMandatory(cursor.getInt(cursor.getColumnIndexOrThrow("mandatory"))>0);
                lecture.setPlace(cursor.getString(cursor.getColumnIndexOrThrow("location")));

                Long dateLong = cursor.getLong(cursor.getColumnIndexOrThrow("time"));
                if(dateLong != null){
                    java.sql.Date sqlDate = new java.sql.Date(dateLong);
                    java.util.Date date = new java.util.Date(sqlDate.getTime());
                    lecture.setDate(date);
                }

                resultList.add(lecture);
            } while (cursor.moveToNext());
        }

        return  resultList;
    }

    /*   THESE METHODS ARE MAYBE UNNECESSARY
    public boolean addExamToLecture(int examId, int lecId){
        return false;
    }

    public boolean addHomeworkToLecture(int hwId, int lecId){
        return false;
    }

    public boolean addBookToLecture(int bookId, int lecId){
        return false;
    } */
}
