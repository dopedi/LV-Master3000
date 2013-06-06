package student.tugraz.at.lv_master3000.databaseAccess;

import android.content.Context;
import student.tugraz.at.lv_master3000.Lecture;

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
        String insertStmt = insertInto + dbname + "." + tableName;
        insertStmt += " (name, location, day, prof_name, mandatory, time, _id) " + values;
        insertStmt += " (" + lecture.getName() + ",";
        insertStmt += lecture.getPlace() + ",";
        insertStmt += lecture.getDay() + ",";
        insertStmt += lecture.getProfessorName() + ",";
        insertStmt += lecture.getMandatory() + ",";
        insertStmt += lecture.getDate() + ",";
        insertStmt += "null);";

        db.execSQL(insertStmt);

        return null;
    }

    public Lecture getLectureFromDB(int id){
        Lecture result = null;
        // TODO fetch from DB
        return result;
    }

}
