package student.tugraz.at.lv_master3000.domain;

/**
 * Created with IntelliJ IDEA.
 * User: doris
 * Date: 6/10/13
 * Time: 5:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class Workmate {

    public Workmate(String name){
       this.name = name;
    }

    private int id;
    private String name;
    private String email;
    private int cellPhoneNr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCellPhoneNr() {
        return cellPhoneNr;
    }

    public void setCellPhoneNr(int cellPhoneNr) {
        this.cellPhoneNr = cellPhoneNr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
