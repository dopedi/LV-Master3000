package student.tugraz.at.lv_master3000.databaseAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import student.tugraz.at.lv_master3000.domain.LearningMaterials;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: doris
 * Date: 6/10/13
 * Time: 5:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class LearningMaterialsManager extends LVMaster3000DBHelper{
    private static final String tableName = "learning_materials";
    private static final String[] columns = new String[]{"_id","link", "description"};

    public LearningMaterialsManager(Context context) {
        super(context);
    }

    public Integer insertNewLearningMaterials(LearningMaterials learningMaterials){
        ContentValues values = new ContentValues();
        values.put("description", learningMaterials.getDescription());
        values.put("link", learningMaterials.getLink());

        int id = (int)db.insert(tableName, "null", values);
        learningMaterials.setId(id);

        return id;
    }

    public LearningMaterials getLearningMaterialsFromDB(int lmId){
        String selection = "_id =?";

        Cursor cursor = db.query(tableName, columns,selection,new String[]{String.valueOf(lmId)},null, null,null , null);

        return fillQueryResultInLearningMaterials(cursor);
    }

    public List<LearningMaterials> getAllLearningMaterials(){
        String selectQuery = "SELECT  * FROM " + tableName;

        Cursor cursor = db.rawQuery(selectQuery, null);
        return  fillQueryResultListInLearningMaterialsList(cursor);
    }

    public List<LearningMaterials> getAllLearningMaterialsOfLecture(int lecId){
        return null;
    }

    private LearningMaterials fillQueryResultInLearningMaterials(Cursor cursor){
        LearningMaterials result = null;

        if(cursor.moveToFirst()){
            result = new LearningMaterials(cursor.getString(cursor.getColumnIndexOrThrow("description")));
            result.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
            result.setLink(cursor.getString(cursor.getColumnIndexOrThrow("link")));
        }

        return result;
    }

    private List<LearningMaterials> fillQueryResultListInLearningMaterialsList(Cursor cursor){
        List<LearningMaterials> resultList = new ArrayList<LearningMaterials>();

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                LearningMaterials result = new LearningMaterials(cursor.getString(cursor.getColumnIndexOrThrow("description")));
                result.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
                result.setLink(cursor.getString(cursor.getColumnIndexOrThrow("link")));

                resultList.add(result);
            } while (cursor.moveToNext());
        }

        return  resultList;
    }

    public List<LearningMaterials> getAllLearningMaterialsOfHomework(int hwId){
        return null;
    }

    public List<LearningMaterials> getAllLearningMaterialsOfExam(int exId){
        return null;
    }
}
