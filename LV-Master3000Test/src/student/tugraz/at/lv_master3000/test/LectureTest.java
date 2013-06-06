package student.tugraz.at.lv_master3000.test;

import android.test.AndroidTestCase;
import student.tugraz.at.lv_master3000.Book;
import student.tugraz.at.lv_master3000.Lecture;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: dzom-dzom
 * Date: 6/4/13
 * Time: 7:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class LectureTest extends AndroidTestCase
{
    @Override
    public void setUp() throws Exception
    {
        super.setUp();    //To change body of overridden methods use File | Settings | File Templates.
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

    @Override
    public void tearDown() throws Exception
    {
        super.tearDown();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
