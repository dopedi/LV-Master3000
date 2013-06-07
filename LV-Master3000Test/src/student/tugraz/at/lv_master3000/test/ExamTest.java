package student.tugraz.at.lv_master3000.test;

import android.test.AndroidTestCase;
import student.tugraz.at.lv_master3000.Book;
import student.tugraz.at.lv_master3000.Exam;
import student.tugraz.at.lv_master3000.Lecture;
import student.tugraz.at.lv_master3000.databaseAccess.ExamManager;
import student.tugraz.at.lv_master3000.databaseAccess.LectureManager;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dzom-dzom
 * Date: 6/4/13
 * Time: 7:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExamTest extends AndroidTestCase
{
    private Lecture lecture;
    private LectureManager lectureManager;
    private ExamManager examManager;

    @Override
    public void setUp() throws Exception
    {
        super.setUp();
        examManager = new ExamManager(this.getContext());
        lectureManager = new LectureManager(this.getContext());
        lecture = new Lecture("it-sec");
        lecture.setDay("Montag");
        lecture.setPlace("i9");
        lecture.setMandatory(false);
        lecture.setProfessorName("wenger");
        lecture.setId(lectureManager.insertNewLecture(lecture));
    }


    public void testGetterAndSetters() throws Exception
    {
        Date examDate = new Date(2013, 6, 6);
        String examDay = "SomeDay";
        String examLocation = "Address Test";
        int lectureId = 1;
        int examId = 2;

        Lecture lecture = new Lecture("Lecture Test");

        Exam exam = new Exam(lectureId);

        assertEquals(lectureId, exam.getLectureId());

        exam.setId(examId);
        assertEquals(examId, exam.getId());

        exam.setDate(examDate);
        assertEquals(examDate, exam.getDate());

        exam.setLocation(examLocation);
        assertEquals(examLocation, exam.getLocation());

    }

    public void testInsertNewExam(){

        assertNotNull(lecture.getId());


        Exam exam = new Exam(lecture.getId());
        exam.setLocation("i12");

        Integer exId = examManager.insertNewExam(exam);

        assertNotSame(-1, exId);
    }

    public void testGetAllExams(){
        assertNotNull(lecture.getId());

        Exam exam1 = new Exam(lecture.getId());
        exam1.setLocation("i12");

        examManager.insertNewExam(exam1);

        Exam exam2 = new Exam(lecture.getId());
        exam2.setLocation("i9");

        examManager.insertNewExam(exam2);

        List<Exam> allExams = examManager.getAllExamsOfLecture(lecture.getId());

        assertEquals(2, allExams.size());
        assertEquals("i12", allExams.get(0).getLocation());
        assertEquals("i9", allExams.get(1).getLocation());
    }

    @Override
    public void tearDown() throws Exception
    {
        super.tearDown();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
