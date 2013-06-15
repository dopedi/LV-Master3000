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

import java.util.Calendar;
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
        String lectureDate = "13:30";
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

    public void testGetNextLectures(){
        Lecture active1 = new Lecture("act1");
        Lecture active2 = new Lecture("act2");

        Calendar calendar = Calendar.getInstance();
        String today = "";
        String tomorrow = "";

        switch(calendar.get(Calendar.DAY_OF_WEEK)){
            case 0: today = "sunday"; tomorrow = "monday";break;
            case 1:today = "monday"; tomorrow = "tuesday";break;
            case 2:today = "tuesday";tomorrow = "wednesday";break;
            case 3:today = "wednesday";tomorrow = "thursday";break;
            case 4:today = "thursday";tomorrow = "friday";break;
            case 5: today = "friday";tomorrow = "saturday";break;
            case 6:today = "saturday";tomorrow = "sunday";break;
        }

        active2.setDay(today);

        dbManager.insertNewLecture(active1);
        dbManager.insertNewLecture(active2);

        List<Lecture> resultList = dbManager.getNextLectures();

        assertNotNull(resultList);
        assertNotSame(0, resultList.size());
        if(resultList != null && resultList.size() != 0)
            assertEquals(today, resultList.get(0).getDay());
    }

    public void testValidateLecture(){

    }

    public void testUpdateLecture(){
        String name1  = "russisch";
        String name2 = "spanisch";

        Lecture lecture = new Lecture(name1);

        int id = dbManager.insertNewLecture(lecture);
        lecture.setName(name2);
        boolean worked = dbManager.updateLecture(id, lecture);
        assertTrue(worked);

        Lecture result = dbManager.getLectureFromDB(id);
        assertEquals(name2, result.getName());
    }

    public void testDeleteLecture(){
        Lecture lecture = new Lecture("robot vision");
        int id = dbManager.insertNewLecture(lecture);

        boolean worked = dbManager.deleteLecture(id);
        assertTrue(worked);

        Lecture result = dbManager.getLectureFromDB(id);

        assertNull(result);
    }

    public void testReferentialIntegrityForDelete(){
        Lecture lecture = new Lecture("robot vision");
        int lecId = dbManager.insertNewLecture(lecture);

        Homework hw = new Homework(lecId);
        HomeworkManager hwMan =  new HomeworkManager(this.getContext());
        int hwId = hwMan.insertNewHomework(hw);

        Exam ex = new Exam(lecId);
        ExamManager exMan = new ExamManager(this.getContext());
        int exId = exMan.insertNewExam(ex);

        boolean worked = dbManager.deleteLecture(lecId);
        assertTrue(worked);

        Lecture result = dbManager.getLectureFromDB(lecId);
        assertNull(result);

        Exam resultEx = exMan.getExamFromDB(exId);
        assertNull(resultEx);

        Homework resultHw = hwMan.getHomeworkFromDB(hwId);
        assertNull(resultHw);
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
