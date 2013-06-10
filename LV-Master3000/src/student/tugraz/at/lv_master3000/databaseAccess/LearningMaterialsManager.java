package student.tugraz.at.lv_master3000.databaseAccess;

import android.content.Context;

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
}
