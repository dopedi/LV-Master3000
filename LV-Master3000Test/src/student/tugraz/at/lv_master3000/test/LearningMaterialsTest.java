package student.tugraz.at.lv_master3000.test;

import android.test.AndroidTestCase;
import student.tugraz.at.lv_master3000.databaseAccess.LearningMaterialsManager;
import student.tugraz.at.lv_master3000.domain.LearningMaterials;
import student.tugraz.at.lv_master3000.domain.Lecture;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: doris
 * Date: 6/10/13
 * Time: 6:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class LearningMaterialsTest extends AndroidTestCase{
    private LearningMaterialsManager learningMaterialsManager;
    private Lecture lecture;
    @Override
    public void setUp() throws Exception
    {
        super.setUp();
        learningMaterialsManager = new LearningMaterialsManager(this.getContext());
        lecture = new Lecture("NRLA");
    }

    @Override
    public void tearDown() throws Exception
    {
        learningMaterialsManager.cleanAllTables();
        super.tearDown();
    }

    public void testGettersAndSetters(){
        String link = "iaik.tugraz.at";
        String desc = "lv-unterlagen";
        int id = 1000;

        LearningMaterials lm = new LearningMaterials(desc);
        lm.setId(id);
        lm.setLink(link);

        assertEquals(link, lm.getLink());
        assertEquals(desc, lm.getDescription());
        assertEquals(id, lm.getId());
    }

    public void testInsertNewLearningMaterials(){
        String link = "iaik.tugraz.at";
        String desc = "lv-unterlagen";

        LearningMaterials lm = new LearningMaterials(desc);
        lm.setLink(link);

        int lmId = learningMaterialsManager.insertNewLearningMaterials(lm);

        assertNotSame(-1, lmId);
    }

    public void testGetLearningMaterialsFromDB(){
        String description = "website vom institut";

        LearningMaterials learningMaterials = new LearningMaterials(description);

        int lmId = learningMaterialsManager.insertNewLearningMaterials(learningMaterials);
        learningMaterials.setDescription(description);
        assertNotSame(-1, lmId);

        LearningMaterials result = learningMaterialsManager.getLearningMaterialsFromDB(lmId);

        String resDesc;
        if(result == null)
            resDesc = "";
        else
            resDesc = result.getDescription();

        assertEquals(description, resDesc);

    }
}
