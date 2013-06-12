package student.tugraz.at.lv_master3000.test;

import android.test.AndroidTestCase;
import student.tugraz.at.lv_master3000.domain.Book;
import student.tugraz.at.lv_master3000.domain.Lecture;
import student.tugraz.at.lv_master3000.databaseAccess.BookManager;
import student.tugraz.at.lv_master3000.databaseAccess.LectureManager;

import java.util.Date;
import java.util.List;

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


    @Override
    public void tearDown() throws Exception
    {
        bookManager.cleanAllTables();
        super.tearDown();
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

        Integer bookId = bookManager.insertNewBook(book);

        assertNotSame(-1, bookId);
    }

    public void testGetBookFromDB(){
        String name = "zaubern für anfänger";
        Date date = new Date(2013, 6, 6);

        Book book = new Book(name, lecture.getId());
        book.setDueDate(date);

        int bookId = bookManager.insertNewBook(book);
        assertNotSame(-1, bookId);

        Book result = bookManager.getBookFromDB(bookId);

        String resName;
        if(result == null)
            resName = "";
        else
            resName = result.getBookName();

        assertEquals(name, resName);

    }

    public void testGetAllBooks(){
        assertNotNull(lecture.getId());

        Book b1 = new Book("Heimwerkeralarm", lecture.getId());

        int id = bookManager.insertNewBook(b1);
        assertNotSame(-1, id);

        Book b2 = new Book("Kochen leicht gemacht", lecture.getId());

        id = bookManager.insertNewBook(b2);
        assertNotSame(-1, id);

        List<Book> list = bookManager.getAllBooks();

        assertEquals(2, list.size());
    }

    public void testGetAllBooksOfLecture(){
        assertNotNull(lecture.getId());

        Book b1 = new Book("Deutsche Grammatik 1", lecture.getId());

        Lecture lecture2 = new Lecture("HCI");
        int id = lectureManager.insertNewLecture(lecture2);
        assertNotSame(-1, id);

        Book b2 = new Book("Deutsche Grammatik 2", id);

        id = bookManager.insertNewBook(b1);
        assertNotSame(-1, id);

        id = bookManager.insertNewBook(b2);
        assertNotSame(-1, id);

        List<Book> list = bookManager.getAllBooksOfLecture(lecture.getId());

        assertEquals(1, list.size());
    }

}
