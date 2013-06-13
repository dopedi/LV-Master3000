package student.tugraz.at.lv_master3000.domain;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: doris
 * Date: 6/10/13
 * Time: 5:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class Milestone {

    public Milestone(Date date){
       this.date = date;
    }

    private int id;
    private Date date;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
