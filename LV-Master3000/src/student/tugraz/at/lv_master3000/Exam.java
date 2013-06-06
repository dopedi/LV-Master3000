package student.tugraz.at.lv_master3000;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: dzom-dzom
 * Date: 6/4/13
 * Time: 7:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class Exam
{
    private int id;
    private int lectureId;
    private Date date;  // we just fetch here the time
    private String day;
    private String location;

    public Exam(int lectureId)
    {
       this.lectureId = lectureId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLectureId() {
        return lectureId;
    }

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
