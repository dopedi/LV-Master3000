package student.tugraz.at.lv_master3000.test;

import android.test.AndroidTestCase;
import student.tugraz.at.lv_master3000.databaseAccess.BookManager;
import student.tugraz.at.lv_master3000.databaseAccess.ExamManager;
import student.tugraz.at.lv_master3000.databaseAccess.HomeworkManager;
import student.tugraz.at.lv_master3000.domain.Book;
import student.tugraz.at.lv_master3000.domain.Exam;
import student.tugraz.at.lv_master3000.domain.Homework;
import student.tugraz.at.lv_master3000.domain.Lecture;
import student.tugraz.at.lv_master3000.databaseAccess.LectureManager;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dzom-dzom
 * Date: 6/4/13
 * Time: 7:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class LectureTest extends AndroidTestCase
{
    private LectureManager dbManager;
    @Override
    public void setUp() throws Exception
    {
        super.setUp();
        dbManager = new LectureManager(this.getContext());
    }

    @Override
    public void tearDown() throws Exception
    {
        dbManager.cleanAllTables();
        super.tearDown();
    }


    public void testGetterAndSetters() throws Exception
    {
        String lectureName = "Lecture Test";
        String professorName = "Testo Testic";
        String lecturePlace = "Somewhere";
        String lectureDay = "MTWTFSS";
        Date lectureDate = new Date(2013, 6, 6);
        boolean isMandatory = false;


        Lecture lecture = new Lecture(lectureName);

        assertEquals(lectureName, lecture.getName());

        lecture.setDate(lectureDate);
        assertEquals(lectureDate, lecture.getDate());

        lecture.setProfessorName(professorName);
        assertEquals(professorName, lecture.getProfessorName());

        lecture.setPlace(lecturePlace);
        assertEquals(lecturePlace, lecture.getPlace());

        lecture.setDay(lectureDay);
        assertEquals(lectureDay, lecture.getDay());

        lecture.setMandatory(isMandatory);
        assertEquals(isMandatory, lecture.getMandatory());

    }

    public void testInsertNewLecture(){
        Lecture lecture = new Lecture("mobapp");
        lecture.setDay("Donnerstag");
        lecture.setMandatory(true);
        lecture.setPlace("i13");

        Integer lecId = null;

        lecId = dbManager.insertNewLecture(lecture);

        assertNotSame(-1, lecId);
    }

    public void testGetLectureFromDB(){

        Lecture lecture = new Lecture("mobapp");
        lecture.setDay("Donnerstag");
        lecture.setMandatory(true);
        lecture.setPlace("i13");

        Integer lecId = null;

        lecId = dbManager.insertNewLecture(lecture);

        Lecture result = dbManager.getLectureFromDBByName("mobapp");

        String resultName = null;
        if(result != null)
            resultName = result.getName();

        assertEquals("mobapp", resultName);
    }

    public void testGetAllLectures(){
        Lecture lecture1 = new Lecture("mobapp");
        lecture1.setDay("Donnerstag");
        lecture1.setMandatory(true);
        lecture1.setPlace("i13");
        dbManager.insertNewLecture(lecture1);

        Lecture lecture2 = new Lecture("ESP");
        lecture2.setDay("Mittwoch");
        lecture2.setMandatory(true);
        lecture2.setPlace("i12");
        dbManager.insertNewLecture(lecture2);

        List<Lecture> allLectures = dbManager.getAllLectures();

        assertEquals(2, allLectures.size());
        assertEquals("i13", allLectures.get(0).getPlace());
        assertEquals("i12", allLectures.get(1).getPlace());
        assertEquals("Donnerstag", allLectures.get(0).getDay());
        assertEquals("Mittwoch", allLectures.get(1).getDay());

    }

    /*  THESE TESTS ARE MAYBE UNNECESSARY
    public void testAddHomework(){
        Lecture lecture = new Lecture("RKN");
        int lecId = dbManager.insertNewLecture(lecture);
        assertNotSame(-1, lecId);

        Homework homework = new Homework(lecId);
        HomeworkManager hwManager = new HomeworkManager(this.getContext());
        int hwId = hwManager.insertNewHomework(homework);
        assertNotSame(-1, hwId);

        boolean worked = false;
        worked = dbManager.addHomeworkToLecture(hwId, lecId);

        assertTrue(worked);
    }

    public void testAddExam(){
        Lecture lecture = new Lecture("RKN");
        int lecId = dbManager.insertNewLecture(lecture);
        assertNotSame(-1, lecId);

        Exam exam = new Exam(lecId);
        ExamManager examManager = new ExamManager(this.getContext());
        int exId = examManager.insertNewExam(exam);
        assertNotSame(-1, exId);

        boolean worked = false;
        worked = dbManager.addExamToLecture(exId, lecId);

        assertTrue(worked);
    }

    public void testAddBook(){
        Lecture lecture = new Lecture("RKN");
        int lecId = dbManager.insertNewLecture(lecture);
        assertNotSame(-1, lecId);

        Book book = new Book("sql for dummies", lecId);
        BookManager bookManager = new BookManager(this.getContext());
        int bookId = bookManager.insertNewBook(book);
        assertNotSame(-1, bookId);

        boolean worked = false;
        worked = dbManager.addBookToLecture(bookId, lecId);

        assertTrue(worked);
    }  */
}
