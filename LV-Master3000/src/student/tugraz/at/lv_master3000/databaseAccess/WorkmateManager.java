package student.tugraz.at.lv_master3000.databaseAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import student.tugraz.at.lv_master3000.domain.Workmate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: doris
 * Date: 6/10/13
 * Time: 5:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class WorkmateManager extends LVMaster3000DBHelper{
    private static final String tableName = "workmate";
    private static final String[] columns = new String[]{"_id","name", "email", "mobile"};

    public WorkmateManager(Context context) {
        super(context);
    }

    public Integer insertNewWorkmate(Workmate workmate){
        ContentValues values = new ContentValues();
        values.put("name", workmate.getName());
        values.put("mobile", workmate.getCellPhoneNr());
        values.put("email", workmate.getEmail());

        int id = (int)db.insert(tableName, "null", values);
        workmate.setId(id);

        return id;
    }

    public Workmate getWorkmateFromDB(int wmId){
        String selection = "_id =?";

        Cursor cursor = db.query(tableName, columns,selection,new String[]{String.valueOf(wmId)},null, null,null , null);

        return fillQueryResultInWorkmate(cursor);
    }

    public List<Workmate> getAllWorkmates(){
        String selectQuery = "SELECT  * FROM " + tableName;

        Cursor cursor = db.rawQuery(selectQuery, null);
        return  fillQueryResultListInWorkmateList(cursor);
    }

    public List<Workmate> getAllWorkmatesOfLecture(int lecId){
        return null;
    }

    private Workmate fillQueryResultInWorkmate(Cursor cursor){
        Workmate result = null;

        if(cursor.moveToFirst()){
            result = new Workmate(cursor.getString(cursor.getColumnIndexOrThrow("name")));
            result.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
            result.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("email")));
            result.setCellPhoneNr(cursor.getString(cursor.getColumnIndexOrThrow("mobile")));
        }

        return result;
    }

    private List<Workmate> fillQueryResultListInWorkmateList(Cursor cursor){
        List<Workmate> resultList = new ArrayList<Workmate>();

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Workmate workmate = new Workmate(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                workmate.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
                workmate.setCellPhoneNr(cursor.getString(cursor.getColumnIndexOrThrow("mobile")));
                workmate.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("email")));

                resultList.add(workmate);
            } while (cursor.moveToNext());
        }

        return  resultList;
    }

    public List<Workmate> getAllWorkmatesOfHomework(int hwId){
        return null;
    }

    public List<Workmate> getAllWorkmatesOfExam(int exId){
        return null;
    }
}
