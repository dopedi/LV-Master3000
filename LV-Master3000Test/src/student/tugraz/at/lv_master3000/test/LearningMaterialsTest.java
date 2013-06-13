package student.tugraz.at.lv_master3000.test;

import android.test.AndroidTestCase;
import student.tugraz.at.lv_master3000.databaseAccess.ExamManager;
import student.tugraz.at.lv_master3000.databaseAccess.HomeworkManager;
import student.tugraz.at.lv_master3000.databaseAccess.LearningMaterialsManager;
import student.tugraz.at.lv_master3000.domain.Exam;
import student.tugraz.at.lv_master3000.domain.Homework;
import student.tugraz.at.lv_master3000.domain.LearningMaterials;
import student.tugraz.at.lv_master3000.domain.Lecture;

import java.util.Date;
import java.util.List;

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
    private Lecture lecture2;
    @Override
    public void setUp() throws Exception
    {
        super.setUp();
        learningMaterialsManager = new LearningMaterialsManager(this.getContext());
        lecture = new Lecture("NRLA");
        lecture2 = new Lecture("Analysis T1");
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

    public void testGetAllLearningMaterials(){
        String description1 = "taschenrechner tool";
        String link1 = "www.taschenrechner.at";
        String description2 = "cheatsheet";

        LearningMaterials learningMaterials1 = new LearningMaterials(description1);

        int lmId = learningMaterialsManager.insertNewLearningMaterials(learningMaterials1);
        learningMaterials1.setDescription(description1);
        learningMaterials1.setLink(link1);
        assertNotSame(-1, lmId);

        LearningMaterials learningMaterials2 = new LearningMaterials(description2);

        lmId = learningMaterialsManager.insertNewLearningMaterials(learningMaterials2);
        assertNotSame(-1, lmId);

        List<LearningMaterials> list = learningMaterialsManager.getAllLearningMaterials();
        assertEquals(2, list.size());
    }

    public void testGetAllLearningMaterialsOfHomework(){
        LearningMaterials lm1 = new LearningMaterials("link1");
        LearningMaterials lm2 = new LearningMaterials("link2");

        int id1 = learningMaterialsManager.insertNewLearningMaterials(lm1);
        int id2 = learningMaterialsManager.insertNewLearningMaterials(lm2);

        HomeworkManager hwMan = new HomeworkManager(this.getContext());

        Homework hw = new Homework(lecture.getId());
        int hwId = hwMan.insertNewHomework(hw);

        hwMan.addLearningMaterialsToHomework(id1, hwId);
        hwMan.addLearningMaterialsToHomework(id2, hwId);

        List<LearningMaterials> lmList = learningMaterialsManager.getAllLearningMaterialsOfHomework(hwId);

        assertNotNull(lmList);
        if(lmList != null)
            assertEquals(2, lmList.size());
    }

    public void testGetAllLearningMaterialsOfExam(){
        LearningMaterials lm1 = new LearningMaterials("link1");
        LearningMaterials lm2 = new LearningMaterials("link2");

        int id1 = learningMaterialsManager.insertNewLearningMaterials(lm1);
        int id2 = learningMaterialsManager.insertNewLearningMaterials(lm2);

        ExamManager examManager = new ExamManager(this.getContext());

        Exam exam = new Exam(lecture.getId());
        int exId = examManager.insertNewExam(exam);

        examManager.addLearningMaterialsToExam(id1, exId);
        examManager.addLearningMaterialsToExam(id2, exId);

        List<LearningMaterials> lmList = learningMaterialsManager.getAllLearningMaterialsOfExam(exId);

        assertNotNull(lmList);
        if(lmList != null)
            assertEquals(2, lmList.size());
    }
}
