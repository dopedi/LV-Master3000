package student.tugraz.at.lv_master3000;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: dzom-dzom
 * Date: 6/4/13
 * Time: 5:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class Book
{
    private Date dueDate;
    private String bookName;
    private String authorName;
    private Lecture lecture;
    private String lenderName;
    private String lenderAddress;

    public Book(String bookName, Lecture lecture)
    {
        this.bookName = bookName;
        this.lecture = lecture;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public String getLenderName() {
        return lenderName;
    }

    public void setLenderName(String lenderName) {
        this.lenderName = lenderName;
    }

    public String getLenderAddress() {
        return lenderAddress;
    }

    public void setLenderAddress(String lenderAddress) {
        this.lenderAddress = lenderAddress;
    }
}
