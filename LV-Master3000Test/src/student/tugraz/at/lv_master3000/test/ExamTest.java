package student.tugraz.at.lv_master3000.test;

import android.test.AndroidTestCase;
import student.tugraz.at.lv_master3000.Book;
import student.tugraz.at.lv_master3000.Exam;
import student.tugraz.at.lv_master3000.Lecture;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: dzom-dzom
 * Date: 6/4/13
 * Time: 7:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExamTest extends AndroidTestCase
{
    @Override
    public void setUp() throws Exception
    {
        super.setUp();    //To change body of overridden methods use File | Settings | File Templates.
    }


    public void testGetterAndSetters() throws Exception
    {
        Date examDate = new Date(2013, 6, 6);
        String examDay = "SomeDay";
        String examLocation = "Address Test";
        int lectureId = 1;
        int examId = 2;

        Lecture lecture = new Lecture("Lecture Test");

<<<<<<< HEAD
        Exam exam = new Exam(lectureId);
=======
        Book book = new Book(bookName, 1);
>>>>>>> 10be1358f96563dd62f2b38b94649fe2099a5a33

        assertEquals(lectureId, exam.getLectureId());

        exam.setId(examId);
        assertEquals(examId, exam.getId());

        exam.setDate(examDate);
        assertEquals(examDate, exam.getDate());

        exam.setDay(examDay);
        assertEquals(examDay, exam.getDay());

        exam.setLocation(examLocation);
        assertEquals(examLocation, exam.getLocation());

    }

    @Override
    public void tearDown() throws Exception
    {
        super.tearDown();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
