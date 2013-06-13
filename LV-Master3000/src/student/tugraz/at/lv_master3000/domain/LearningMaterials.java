package student.tugraz.at.lv_master3000.domain;

/**
 * Created with IntelliJ IDEA.
 * User: doris
 * Date: 6/10/13
 * Time: 5:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class LearningMaterials {

    public LearningMaterials(String description){
       this.description = description;
    }

    private int id;
    private String link;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
