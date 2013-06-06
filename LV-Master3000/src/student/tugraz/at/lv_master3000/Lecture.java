package student.tugraz.at.lv_master3000;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: dzom-dzom
 * Date: 6/4/13
 * Time: 5:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Lecture
{
    private String name;
    private String place;
    private String day;
    private String professorName;
    private Date date;
    private boolean isMandatory;
    private int id;

    public Lecture(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean getMandatory() {
        return isMandatory;
    }

    public void setMandatory(Boolean mandatory) {
        isMandatory = mandatory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
