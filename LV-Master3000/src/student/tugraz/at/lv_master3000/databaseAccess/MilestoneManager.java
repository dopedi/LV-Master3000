package student.tugraz.at.lv_master3000.databaseAccess;

import android.content.Context;
import android.database.Cursor;
import student.tugraz.at.lv_master3000.domain.Milestone;

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
        return -1;
    }

    public Milestone getMilestoneFromDB(int wmId){
        return null;
    }

    public List<Milestone> getAllMilestones(){
        return null;
    }

    public List<Milestone> getAllMilestonesOfLecture(int lecId){
        return null;
    }

    private Milestone fillQueryResultInMilestone(Cursor cursor){
        return null;
    }

    private List<Milestone> fillQueryResultListInMilestoneList(Cursor cursor){
        return null;
    }

    public List<Milestone> getAllMilestonesOfHomework(int hwId){
        return null;
    }

    public List<Milestone> getAllMilestonesOfExam(int exId){
        return null;
    }
}
