package student.tugraz.at.lv_master3000.databaseAccess;

import android.content.Context;
import android.database.Cursor;
import student.tugraz.at.lv_master3000.domain.Workmate;

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
        return -1;
    }

    public Workmate getWorkmateFromDB(int wmId){
        return null;
    }

    public List<Workmate> getAllWorkmates(){
        return null;
    }

    public List<Workmate> getAllWorkmatesOfLecture(int lecId){
        return null;
    }

    private Workmate fillQueryResultInWorkmate(Cursor cursor){
        return null;
    }

    private List<Workmate> fillQueryResultListInWorkmateList(Cursor cursor){
        return null;
    }

    public List<Workmate> getAllWorkmatesOfHomework(int hwId){
        return null;
    }

    public List<Workmate> getAllWorkmatesOfExam(int exId){
        return null;
    }
}
