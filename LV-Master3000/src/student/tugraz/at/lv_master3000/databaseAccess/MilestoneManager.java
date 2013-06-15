package student.tugraz.at.lv_master3000.databaseAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import student.tugraz.at.lv_master3000.domain.Milestone;

import java.util.ArrayList;
import java.util.Date;
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
    private static final String[] columns = new String[]{"_id","milestone_date", "description", "expired", "finished"};

    public MilestoneManager(Context context) {
        super(context);
    }

    public Integer insertNewMilestone(Milestone milestone){
        ContentValues values = new ContentValues();
        values.put("description", milestone.getDescription());
        values.put("expired", milestone.isExpired());
        values.put("finished", milestone.isFinished());
        if(milestone.getDate() == null)
            values.put("milestone_date", 0l);
        else
            values.put("milestone_date", milestone.getDate().getTime());

        int id = (int)db.insert(tableName, "null", values);
        milestone.setId(id);

        return id;
    }

    public Milestone getMilestoneFromDB(int msId){
        String selection = "_id =?";

        Cursor cursor = db.query(tableName, columns,selection,new String[]{String.valueOf(msId)},null, null,null , null);

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
            if(dateLong == 0l)
                return null;
            else
                result = new Milestone(new Date(dateLong));

            result.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("description")));
            result.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
            result.setExpired(cursor.getInt(cursor.getColumnIndexOrThrow("expired")) > 0);
            result.setExpired(cursor.getInt(cursor.getColumnIndexOrThrow("finished")) > 0);
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
                if(dateLong == 0l)
                    return null;
                else
                    milestone = new Milestone(new Date(dateLong));

                if(milestone == null)
                    continue;

                milestone.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("description")));
                milestone.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
                milestone.setExpired(cursor.getInt(cursor.getColumnIndexOrThrow("expired")) > 0);
                milestone.setExpired(cursor.getInt(cursor.getColumnIndexOrThrow("finished")) > 0);

                resultList.add(milestone);
            } while (cursor.moveToNext());
        }

        return  resultList;
    }

    public List<Milestone> getAllMilestonesOfHomework(int hwId){
        String selectQuery = "SELECT  * FROM milestone ";
        selectQuery += " INNER JOIN homework2milestone WHERE homework2milestone.homework = " + hwId;
        selectQuery += " AND milestone._id = homework2milestone.milestone;";

        Cursor cursor = db.rawQuery(selectQuery, null);
        return fillQueryResultListInMilestoneList(cursor);
    }

    public List<Milestone> getAllMilestonesOfExam(int exId){
        String selectQuery = "SELECT  * FROM milestone ";
        selectQuery += " INNER JOIN exam2milestone WHERE exam2milestone.exam = " + exId;
        selectQuery += " AND milestone._id = exam2milestone.milestone;";

        Cursor cursor = db.rawQuery(selectQuery, null);
        return fillQueryResultListInMilestoneList(cursor);
    }

    public List<Milestone> getNextMilestonesForExam(int exId){
        return null;
    }

    public List<Milestone> getNextMilestonesForHomework(int hwId){
        return null;
    }

    public Milestone getFirstMilestoneForExam(int exId){
        long today = new Date().getTime();

        String selectQuery = "SELECT  * FROM milestone";
        selectQuery += " INNER JOIN exam2milestone WHERE exam2milestone.exam = " + exId;
        selectQuery += " AND milestone._id = exam2milestone.milestone AND milestone.milestone_date >= " + today;
        selectQuery += " ORDER BY milestone.milestone_date ASC;";

        Cursor cursor = db.rawQuery(selectQuery, null);


        List<Milestone> resultList = fillQueryResultListInMilestoneList(cursor);
        if(resultList == null || resultList.size() == 0)
            return null;

        return resultList.get(0);
    }

    public Milestone getFirstMilestoneForHomework(int hwId){
        long today = new Date().getTime();

        String selectQuery = "SELECT  * FROM milestone";
        selectQuery += " INNER JOIN homework2milestone WHERE homework2milestone.homework = " + hwId;
        selectQuery += " AND milestone._id = homework2milestone.milestone AND milestone.milestone_date >= " + today;
        selectQuery += " ORDER BY milestone.milestone_date ASC;";

        Cursor cursor = db.rawQuery(selectQuery, null);


        List<Milestone> resultList = fillQueryResultListInMilestoneList(cursor);
        if(resultList == null || resultList.size() == 0)
            return null;

        return resultList.get(0);
    }

    public int updateExpiredForAllMilestones(){
        long today = new Date().getTime();
        String where = "milestone.milestone_date < " + today;

        ContentValues values = new ContentValues();
        values.put("expired", true);

        return db.update("milestone", values,where,  null);
    }

    public List<Milestone> getExpiredMilestonesForExam(int exId){
        long today = new Date().getTime();

        String selectQuery = "SELECT  * FROM milestone";
        selectQuery += " INNER JOIN exam2milestone WHERE exam2milestone.exam = " + exId;
        selectQuery += " AND milestone._id = exam2milestone.milestone AND milestone.milestone_date < " + today;
        selectQuery += " ORDER BY milestone.milestone_date ASC;";

        Cursor cursor = db.rawQuery(selectQuery, null);

        return fillQueryResultListInMilestoneList(cursor);
    }

    public List<Milestone> getExpiredMilestonesForHomework(int hwId){
        long today = new Date().getTime();

        String selectQuery = "SELECT  * FROM milestone";
        selectQuery += " INNER JOIN homework2milestone WHERE homework2milestone.homework = " + hwId;
        selectQuery += " AND milestone._id = homework2milestone.milestone AND milestone.milestone_date < " + today;
        selectQuery += " ORDER BY milestone.milestone_date ASC;";

        Cursor cursor = db.rawQuery(selectQuery, null);
        return fillQueryResultListInMilestoneList(cursor);
    }

    public List<Milestone> getFinishedMilestonesForExam(int exId){
        return null;
    }

    public List<Milestone> getFinishedMilestonesForHomework(int hwId){
        return null;
    }

    public List<Milestone> getActiveMilestonesForExam(int exId){
        long today = new Date().getTime();

        String selectQuery = "SELECT  * FROM milestone";
        selectQuery += " INNER JOIN exam2milestone WHERE exam2milestone.exam = " + exId;
        selectQuery += " AND milestone._id = exam2milestone.milestone AND milestone.milestone_date >= " + today;
        selectQuery += " ORDER BY milestone.milestone_date ASC;";

        Cursor cursor = db.rawQuery(selectQuery, null);


        return fillQueryResultListInMilestoneList(cursor);
    }

    public List<Milestone> getActiveMilestonesForHomework(int hwId){
        return null;
    }

    public List<Milestone> getAllNextMilestones(){
        return null;
    }

    public List<Milestone> getAllExpiredMilestones(){
        return null;
    }

    public List<Milestone> getAllFinishedMilestones(){
        return null;
    }

    public List<Milestone> getAllActiveMilestones(){
        return null;
    }

    public boolean validateMileStone(Milestone milestone){
        return false;
    }

    public boolean updateMilestone(int msId, Milestone newValues){
        newValues.setId(msId);

        String updateStmt = " milestone._id = " + msId;
        ContentValues values = new ContentValues();
        values.put("_id", newValues.getId());
        values.put("description", newValues.getDescription());
        values.put("milestone_date", newValues.getDate().getTime());
        values.put("expired", newValues.isExpired());
        values.put("finished", newValues.isFinished());

        int affectedRows = db.update("milestone", values,updateStmt , null);

        if(affectedRows == 1)
            return true;
        else
            return false;
    }

    public boolean deleteMilestone(int msId){

        String whereHw2Ms = "homework2milestone.milestone = " + msId;
        String whereEx2Ms = "exam2milestone.milestone = " + msId;
        db.delete("homework2milestone", whereHw2Ms, null);
        db.delete("exam2milestone", whereEx2Ms, null);

        String where =  "milestone._id = " + msId;
        int affectedRows = db.delete("milestone",where, null);

        if(affectedRows == 1)
            return true;
        else
            return false;
    }
}
