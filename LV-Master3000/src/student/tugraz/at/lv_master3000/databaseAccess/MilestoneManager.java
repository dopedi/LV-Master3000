package student.tugraz.at.lv_master3000.databaseAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import student.tugraz.at.lv_master3000.domain.Milestone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: doris
 * Date: 6/10/13
 * Time: 5:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class MilestoneManager extends LVMaster3000DBHelper{
    private static final String tableName = "milestone";
    private static final String[] columns = new String[]{"_id","milestone_date", "description"};

    public MilestoneManager(Context context) {
        super(context);
    }

    public Integer insertNewMilestone(Milestone milestone){
        ContentValues values = new ContentValues();
        values.put("description", milestone.getDescription());

        java.util.Date date = milestone.getDate();
        java.sql.Date sqlDate;
        if(date != null){
            sqlDate = new java.sql.Date(date.getTime());
            values.put("milestone_date", sqlDate.toString());
        }

        int id = (int)db.insert(tableName, "null", values);
        milestone.setId(id);

        return id;
    }

    public Milestone getMilestoneFromDB(int wmId){
        String selection = "_id =?";

        Cursor cursor = db.query(tableName, columns,selection,new String[]{String.valueOf(wmId)},null, null,null , null);

        return fillQueryResultInMilestone(cursor);
    }

    public List<Milestone> getAllMilestones(){
        String selectQuery = "SELECT  * FROM " + tableName;

        Cursor cursor = db.rawQuery(selectQuery, null);
        return  fillQueryResultListInMilestoneList(cursor);
    }

    public List<Milestone> getAllMilestonesOfLecture(int lecId){
        return null;
    }

    private Milestone fillQueryResultInMilestone(Cursor cursor){
        Milestone result = null;

        if(cursor.moveToFirst()){

            Long dateLong = cursor.getLong(cursor.getColumnIndexOrThrow("milestone_date"));
            if(dateLong != null){
                java.sql.Date sqlDate = new java.sql.Date(dateLong);
                java.util.Date date = new java.util.Date(sqlDate.getTime());
                result = new Milestone(date);
            }

            result.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("description")));
            result.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
        }

        return result;
    }

    private List<Milestone> fillQueryResultListInMilestoneList(Cursor cursor){
        List<Milestone> resultList = new ArrayList<Milestone>();

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Milestone milestone = null;
                Long dateLong = cursor.getLong(cursor.getColumnIndexOrThrow("milestone_date"));
                if(dateLong != null){
                    java.sql.Date sqlDate = new java.sql.Date(dateLong);
                    java.util.Date date = new java.util.Date(sqlDate.getTime());
                    milestone = new Milestone(date);
                }

                if(milestone == null)
                    continue;

                milestone.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("description")));
                milestone.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));

                resultList.add(milestone);
            } while (cursor.moveToNext());
        }

        return  resultList;
    }

    public List<Milestone> getAllMilestonesOfHomework(int hwId){
        return null;
    }

    public List<Milestone> getAllMilestonesOfExam(int exId){
        return null;
    }
}
