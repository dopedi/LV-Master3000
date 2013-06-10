package student.tugraz.at.lv_master3000.databaseAccess;

import android.content.Context;
import android.database.Cursor;
import student.tugraz.at.lv_master3000.domain.LearningMaterials;

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
        return -1;
    }

    public LearningMaterials getLearningMaterialsFromDB(int wmId){
        return null;
    }

    public List<LearningMaterials> getAllLearningMaterials(){
        return null;
    }

    public List<LearningMaterials> getAllLearningMaterialsOfLecture(int lecId){
        return null;
    }

    private LearningMaterials fillQueryResultInLearningMaterials(Cursor cursor){
        return null;
    }

    private List<LearningMaterials> fillQueryResultListInLearningMaterialsList(Cursor cursor){
        return null;
    }

    public List<LearningMaterials> getAllLearningMaterialsOfHomework(int hwId){
        return null;
    }

    public List<LearningMaterials> getAllLearningMaterialsOfExam(int exId){
        return null;
    }
}
