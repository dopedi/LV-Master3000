package student.tugraz.at.lv_master3000.databaseAccess;

import android.content.ContentValues;
import android.content.Context;
import student.tugraz.at.lv_master3000.Exam;
import student.tugraz.at.lv_master3000.Lecture;

import java.sql.Date;


/**
 * Created with IntelliJ IDEA.
 * User: doris
 * Date: 6/6/13
 * Time: 5:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExamManager extends LVMaster3000DBHelper {
    private static final String tableName = "exam";
    private int rowCount = 0;

    public ExamManager(Context context) {
        super(context);
    }

    public Integer insertNewExam(Exam exam){

        ContentValues values = new ContentValues();
        values.put("lectureId", exam.getLectureId());
        java.util.Date date = exam.getDate();
        java.sql.Date sqlDate;
        if(date != null){
           sqlDate = new java.sql.Date(date.getTime());
           values.put("exam_date", sqlDate.toString());
        }

        values.put("location", exam.getLocation());

        db.insert(tableName, "null", values);

        rowCount++;

        return rowCount-1;
    }



    public Exam getExamFromDB(int id){
        Exam result = null;
        // TODO fetch from DB

        return result;
    }

    public Exam getExamFromDBByName(String mobapp) {
        Exam result = null;

        String selectStmt = "select * from " + tableName;
        //Cursor cursor = db.query();

        return result;
    }


}
