package student.tugraz.at.lv_master3000.databaseAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import student.tugraz.at.lv_master3000.domain.Exam;

import java.util.ArrayList;
import java.util.Date;
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

        long time = 0l;
        if(exam.getDate() != null)
            time = exam.getDate().getTime();
        values.put("exam_date", time);

        int id = (int)db.insert(tableName, "null", values);
        exam.setId(id);

        return id;
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
            result.setDate(new Date(dateLong));
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
                exam.setDate(new Date(dateLong));

                resultList.add(exam);
            } while (cursor.moveToNext());
        }

        return  resultList;
    }

    public boolean addWorkmateToExam(int wmId, int examId){
        ContentValues values = new ContentValues();
        values.put("exam", examId);
        values.put("workmate", wmId);


        int id = (int)db.insert("exam2workmate", "null", values);

        if(id != -1)
            return true;
        else
            return false;
    }

    public boolean addMilestoneToExam(int msId, int examId){
        ContentValues values = new ContentValues();
        values.put("exam", examId);
        values.put("milestone", msId);


        int id = (int)db.insert("exam2milestone", "null", values);

        if(id != -1)
            return true;
        else
            return false;
    }

    public boolean addLearningMaterialsToExam(int lmId, int examId){
        ContentValues values = new ContentValues();
        values.put("exam", examId);
        values.put("learning_materials", lmId);


        int id = (int)db.insert("exam2learning_materials", "null", values);

        if(id != -1)
            return true;
        else
            return false;
    }

    public List<Exam> getNextExams(){
        long today = new Date().getTime();

        String selectQuery = "SELECT  * FROM " + tableName;
        selectQuery += " WHERE exam.exam_date >= " + today;
        selectQuery += " ORDER BY exam.exam_date LIMIT " + MAX_ELEMENTS_FOR_QUERY;

        Cursor cursor = db.rawQuery(selectQuery, null);
        return  fillQueryResultListIntoExamList(cursor);
    }

    public boolean validateExam(Exam exam){
        return false;
    }

    public boolean updateExam(int exId, Exam newValues){
        return false;
    }

    public boolean deleteExam(int exId){
        return false;
    }
}
