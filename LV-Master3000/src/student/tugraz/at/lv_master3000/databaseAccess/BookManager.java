package student.tugraz.at.lv_master3000.databaseAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import student.tugraz.at.lv_master3000.domain.Book;

import java.util.ArrayList;
import java.util.Date;
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
    private static final String[] columns = new String[]{"_id","lecture", "name", "author", "due_date", "lender_name", "lender_address"};

    public BookManager(Context context) {
        super(context);
    }
    public Integer insertNewBook(Book book){

        ContentValues values = new ContentValues();

        if(book.getDueDate() != null)
            values.put("due_date",book.getDueDate().getTime());
        else
            values.put("due_date", 0l);
        values.put("name", book.getBookName());
        values.put("lecture", book.getLecture());
        values.put("author", book.getAuthorName());
        values.put("lender_name", book.getLenderName());
        values.put("lender_address", book.getLenderAddress());

        int id = (int)db.insert(tableName, "null", values);
        book.setId(id);

        return id;

    }



    public Book getBookFromDB(int id){
        String selection = "_id =?";

        Cursor cursor = db.query(tableName, columns,selection,new String[]{String.valueOf(id)},null, null,null , null);

        return fillQueryResultInBook(cursor);
    }

    public Book getBookFromDBByName(String mobapp) {
        Book result = null;

        String selectStmt = "select * from " + tableName;
        //Cursor cursor = db.query();

        return result;
    }

    private Book fillQueryResultInBook(Cursor cursor){
        Book result = null;

        if(cursor.moveToFirst()){
            result = new Book(cursor.getString(cursor.getColumnIndexOrThrow("name")), cursor.getInt(cursor.getColumnIndexOrThrow("lecture")));
            result.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
            result.setAuthorName(cursor.getString(cursor.getColumnIndexOrThrow("author")));
            result.setLenderAddress(cursor.getString(cursor.getColumnIndexOrThrow("lender_address")));
            result.setLenderName(cursor.getString(cursor.getColumnIndexOrThrow("lender_name")));

            Long dateLong = cursor.getLong(cursor.getColumnIndexOrThrow("due_date"));

            if(dateLong == 0l)
                result.setDueDate(null);
            else
                result.setDueDate(new Date(dateLong));
        }

        return result;
    }

    public List<Book> getAllBooks(){
        String selectQuery = "SELECT  * FROM " + tableName;

        Cursor cursor = db.rawQuery(selectQuery, null);
        return  fillQueryResultListInBookList(cursor);
    }

    public List<Book> getAllBooksOfLecture(int lecId){
        List<Book> resultList = new ArrayList<Book>();

        String selectQuery = "SELECT  * FROM " + tableName + " WHERE lecture = " + lecId;

        Cursor cursor = db.rawQuery(selectQuery, null);
        return  fillQueryResultListInBookList(cursor);
    }

    private List<Book> fillQueryResultListInBookList(Cursor cursor){
        List<Book> resultList = new ArrayList<Book>();

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Book result = new Book(cursor.getString(cursor.getColumnIndexOrThrow("name")), cursor.getInt(cursor.getColumnIndexOrThrow("lecture")));
                result.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
                result.setAuthorName(cursor.getString(cursor.getColumnIndexOrThrow("author")));
                result.setLenderAddress(cursor.getString(cursor.getColumnIndexOrThrow("lender_address")));
                result.setLenderName(cursor.getString(cursor.getColumnIndexOrThrow("lender_name")));

                Long dateLong = cursor.getLong(cursor.getColumnIndexOrThrow("due_date"));
                if(dateLong == 0l)
                    result.setDueDate(null);
                else
                    result.setDueDate(new Date(dateLong));

                resultList.add(result);
            } while (cursor.moveToNext());
        }

        return  resultList;
    }

    public List<Book> getNextBooks(){
        long today = new Date().getTime();

        String selectQuery = "SELECT  * FROM " + tableName;
        selectQuery += " WHERE book.due_date >= " + today;
        selectQuery += " ORDER BY book.due_date LIMIT " + MAX_ELEMENTS_FOR_QUERY + ";";

        Cursor cursor = db.rawQuery(selectQuery, null);
        return  fillQueryResultListInBookList(cursor);
    }

    public boolean validateBook(Book book){
        return false;
    }

    public boolean updateBook(int bookId, Book newValues){
        newValues.setId(bookId);

        String updateStmt = " book._id = " + bookId;
        ContentValues values = new ContentValues();
        values.put("_id", newValues.getId());
        values.put("lender_address", newValues.getLenderAddress());
        if(newValues.getDueDate() != null)
            values.put("due_date", newValues.getDueDate().getTime());
        else
            values.put("due_date", 0l);
        values.put("name", newValues.getBookName());
        values.put("lender_name", newValues.getLenderName());
        values.put("author", newValues.getAuthorName());
        values.put("lecture", newValues.getLecture());

        int affectedRows = db.update("book", values,updateStmt , null);

        if(affectedRows == 1)
            return true;
        else
            return false;
    }

    public boolean deleteBook(int bookId){

        String where =  "book._id = " + bookId;
        int affectedRows = db.delete("book",where, null);

        if(affectedRows == 1)
            return true;
        else
            return false;
    }
}
