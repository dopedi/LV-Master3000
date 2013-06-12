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
    private static final String[] columns = new String[]{"_id","lecture", "name", "author", "due_date", "lender_name", "lender_address"};

    public BookManager(Context context) {
        super(context);
    }
    public Integer insertNewBook(Book book){

        ContentValues values = new ContentValues();

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
            if(dateLong != null){
                java.sql.Date sqlDate = new java.sql.Date(dateLong);
                java.util.Date date = new java.util.Date(sqlDate.getTime());
                result.setDueDate(date);
            }
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
                if(dateLong != null){
                    java.sql.Date sqlDate = new java.sql.Date(dateLong);
                    java.util.Date date = new java.util.Date(sqlDate.getTime());
                    result.setDueDate(date);
                }

                resultList.add(result);
            } while (cursor.moveToNext());
        }

        return  resultList;
    }
}
