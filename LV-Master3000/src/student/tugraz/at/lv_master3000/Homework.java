package student.tugraz.at.lv_master3000;

import java.util.Date;

public class Homework {

	private Date dueDate;
    private String name;
    private int lecture;
    private int id;

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLecture() {
        return lecture;
    }

    public void setLecture(int lecture) {
        this.lecture = lecture;
    }

    public int getIdd() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
