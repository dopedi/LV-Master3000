package student.tugraz.at.lv_master3000.databaseAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import student.tugraz.at.lv_master3000.R;
import student.tugraz.at.lv_master3000.domain.Book;
import student.tugraz.at.lv_master3000.domain.Exam;
import student.tugraz.at.lv_master3000.domain.Homework;
import student.tugraz.at.lv_master3000.domain.Lecture;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    private Context ownContext = null;

    public LectureManager(Context context) {
        super(context);
        ownContext = context;
    }

    public Integer insertNewLecture(Lecture lecture){

        ContentValues values = new ContentValues();
        values.put("name", lecture.getName());
        values.put("day", lecture.getDay());
        values.put("mandatory", lecture.getMandatory());
        values.put("location", lecture.getPlace());
        values.put("prof_name", lecture.getProfessorName());
        values.put("time", lecture.getDate());

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
            result.setDate(cursor.getString(cursor.getColumnIndexOrThrow("time")));
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
                lecture.setDate(cursor.getString(cursor.getColumnIndexOrThrow("time")));

                resultList.add(lecture);
            } while (cursor.moveToNext());
        }

        return  resultList;
    }

    public List<Lecture> getNextLectures(){
        Calendar calendar = Calendar.getInstance();
        String today = "";

        switch(calendar.get(Calendar.DAY_OF_WEEK)){
            case 0: today = "sunday";break;
            case 1:today = "monday";break;
            case 2:today = "tuesday"; break;
            case 3:today = "wednesday";break;
            case 4:today = "thursday";break;
            case 5:today = "friday"; break;
            case 6:today = "saturday";break;
        }

        String selectQuery = "SELECT  * FROM " + tableName;
        selectQuery += " WHERE lecture.day = \"" + today +"\"";
        selectQuery += " LIMIT " + MAX_ELEMENTS_FOR_QUERY;

        Cursor cursor = db.rawQuery(selectQuery, null);
        return  fillQueryResultListIntoExamList(cursor);
    }

    public boolean validateLecture(Lecture lecture){
        return false;
    }

    public boolean updateLecture(int lecId, Lecture newValues){
        newValues.setId(lecId);

        String updateStmt = " lecture._id = " + lecId;
        ContentValues values = new ContentValues();
        values.put("_id", newValues.getId());
        values.put("time", newValues.getDate());
        values.put("name", newValues.getName());
        values.put("location", newValues.getPlace());
        values.put("mandatory", newValues.getMandatory());
        values.put("day", newValues.getDay());
        values.put("prof_name", newValues.getProfessorName());

        int affectedRows = db.update("lecture", values,updateStmt , null);

        if(affectedRows == 1)
            return true;
        else
            return false;
    }

    public boolean deleteLecture(int lecId){
        HomeworkManager hwMan = new HomeworkManager(ownContext);
        List<Homework> hwToDelete = hwMan.getAllHomeworksOfLecture(lecId);
        ExamManager exMan = new ExamManager(ownContext);
        List<Exam> examsToDelete = exMan.getAllExamsOfLecture(lecId);
        BookManager bookMan = new BookManager(ownContext);
        List<Book> booksToDelete = bookMan.getAllBooksOfLecture(lecId);

        for(Homework hw: hwToDelete){
            hwMan.deleteHomework(hw.getId());
        }

        for(Exam exam:examsToDelete){
            exMan.deleteExam(exam.getId());
        }

        for(Book book: booksToDelete){
            bookMan.deleteBook(book.getId());
        }

        String where =  "lecture._id = " + lecId;
        int affectedRows = db.delete("lecture",where, null);

        if(affectedRows == 1)
            return true;
        else
            return false;
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
