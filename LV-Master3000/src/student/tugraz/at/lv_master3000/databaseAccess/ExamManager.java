package student.tugraz.at.lv_master3000.databaseAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import student.tugraz.at.lv_master3000.Exam;
import student.tugraz.at.lv_master3000.Exam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: doris
 * Date: 6/6/13
 * Time: 5:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExamManager extends LVMaster3000DBHelper {
    private static final String tableName = "exam";
    private static final String[] columns = new String[]{"_id","lecture", "location", "exam_date"};


    public ExamManager(Context context) {
        super(context);
    }

    public Integer insertNewExam(Exam exam){

        ContentValues values = new ContentValues();
        values.put("lecture", exam.getLectureId());
        values.put("location", exam.getLocation());

        java.util.Date date = exam.getDate();
        java.sql.Date sqlDate;
        if(date != null){
            sqlDate = new java.sql.Date(date.getTime());
            values.put("exam_date", sqlDate.toString());
        }

        return (int)db.insert(tableName, "null",values);
    }



    public Exam getExamFromDB(int id){

        String selection = "_id =?";

        Cursor cursor = db.query(tableName, columns,selection,new String[]{String.valueOf(id)},null, null,null , null);

        return fillQueryResultInExam(cursor);
    }

    private Exam fillQueryResultInExam(Cursor cursor){
        Exam result = null;

        if(cursor.moveToFirst()){
            //System.out.println("try reading from Exam");
            result = new Exam(cursor.getInt(cursor.getColumnIndexOrThrow("lecture")));
            result.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
            result.setLocation(cursor.getString(cursor.getColumnIndexOrThrow("location")));

            Long dateLong = cursor.getLong(cursor.getColumnIndexOrThrow("exam_date"));
            if(dateLong != null){
                java.sql.Date sqlDate = new java.sql.Date(dateLong);
                java.util.Date date = new java.util.Date(sqlDate.getTime());
                result.setDate(date);
            }
        }

        return result;
    }

    public List<Exam> getAllExams(){

        String selectQuery = "SELECT  * FROM " + tableName;

        Cursor cursor = db.rawQuery(selectQuery, null);
        return  fillQueryResultListIntoExamList(cursor);
    }

    public List<Exam> getAllExamsOfLecture(int lectureId){
        List<Exam> resultList = new ArrayList<Exam>();

        String selectQuery = "SELECT  * FROM " + tableName + " WHERE lecture = " + lectureId;

        Cursor cursor = db.rawQuery(selectQuery, null);
        return  fillQueryResultListIntoExamList(cursor);
    }

    public List<Exam> fillQueryResultListIntoExamList(Cursor cursor){
        List<Exam> resultList = new ArrayList<Exam>();

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Exam exam = new Exam(cursor.getInt(cursor.getColumnIndexOrThrow("lecture")));
                exam.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
                exam.setLocation(cursor.getString(cursor.getColumnIndexOrThrow("location")));

                Long dateLong = cursor.getLong(cursor.getColumnIndexOrThrow("exam_date"));
                if(dateLong != null){
                    java.sql.Date sqlDate = new java.sql.Date(dateLong);
                    java.util.Date date = new java.util.Date(sqlDate.getTime());
                    exam.setDate(date);
                }

                resultList.add(exam);
            } while (cursor.moveToNext());
        }

        return  resultList;
    }

}
