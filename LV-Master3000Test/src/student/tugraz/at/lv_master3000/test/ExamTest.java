package student.tugraz.at.lv_master3000.test;

import android.test.AndroidTestCase;
import student.tugraz.at.lv_master3000.Book;
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
        String bookName = "Book Test";
        String authorName = "Testo Testic";
        Date dueDate = new Date(2013, 6, 6);
        String lenderName = "Lender Test";
        String lenderAddress = "Address Test";

        Lecture lecture = new Lecture("Lecture Test");

        Book book = new Book(bookName, 1);

        book.setAuthorName(authorName);
        assertEquals(authorName, book.getAuthorName());

        assertEquals(bookName, book.getBookName());

        book.setDueDate(dueDate);
        assertEquals(dueDate, book.getDueDate());

        book.setLenderName(lenderName);
        assertEquals(lenderName, book.getLenderName());

        book.setLenderAddress(lenderAddress);
        assertEquals(lenderAddress, book.getLenderAddress());

    }

    @Override
    public void tearDown() throws Exception
    {
        super.tearDown();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
