package student.tugraz.at.lv_master3000.databaseAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import student.tugraz.at.lv_master3000.domain.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: doris
 * Date: 6/6/13
 * Time: 6:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class BookManager extends LVMaster3000DBHelper{
    public static  final String tableName = "book";
    private int rowCount = 0;

    public BookManager(Context context) {
        super(context);
    }
    public Integer insertNewBook(Book book){

        ContentValues values = new ContentValues();
        values.put("lectureId", book.getLecture());

        java.util.Date date = book.getDueDate();
        java.sql.Date sqlDate;
        if(date != null){
            sqlDate = new java.sql.Date(date.getTime());
            values.put("due_date", sqlDate.toString());
        }

        values.put("name", book.getBookName());
        values.put("lecture", book.getLecture());
        values.put("author", book.getAuthorName());
        values.put("lender_name", book.getLenderName());
        values.put("lender_address", book.getLenderAddress());

        return (int)db.insert(tableName, "null", values);

    }



    public Book getBookFromDB(int id){
        Book result = null;
        // TODO fetch from DB

        return result;
    }

    public Book getBookFromDBByName(String mobapp) {
        Book result = null;

        String selectStmt = "select * from " + tableName;
        //Cursor cursor = db.query();

        return result;
    }

    private Book fillQueryResultInBook(Cursor cursor){
        return null;
    }

    public List<Book> getAllBooks(){
        return new ArrayList<Book>();
    }

    public List<Book> getAllBooksOfLecture(int lecId){
        return new ArrayList<Book>();
    }

    private List<Book> fillQueryResultListInBookList(){
        return new ArrayList<Book>();
    }
}
