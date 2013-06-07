package student.tugraz.at.lv_master3000.test;

import android.test.AndroidTestCase;
import student.tugraz.at.lv_master3000.Book;
import student.tugraz.at.lv_master3000.Lecture;
import student.tugraz.at.lv_master3000.databaseAccess.BookManager;
import student.tugraz.at.lv_master3000.databaseAccess.LectureManager;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: dzom-dzom
 * Date: 6/4/13
 * Time: 6:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class BookTest extends AndroidTestCase
{
    private Lecture lecture;
    private LectureManager lectureManager;
    private BookManager bookManager;

    @Override
    public void setUp() throws Exception
    {
        super.setUp();
        bookManager = new BookManager(this.getContext());
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
        String bookName = "Book Test";
        String authorName = "Testo Testic";
        Date dueDate = new Date(2013, 6, 6);
        String lenderName = "Lender Test";
        String lenderAddress = "Address Test";

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

    public void testInsertNewBook(){

        assertNotNull(lecture.getId());


        Book book = new Book("testbuch",lecture.getId());
        book.setAuthorName("tanenbaum");
        book.setDueDate(new Date(2013,12,12));

        Integer lecId = null;

        lecId = bookManager.insertNewBook(book);

        assertNotSame(-1, lecId);
    }

    @Override
    public void tearDown() throws Exception
    {
        super.tearDown();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
