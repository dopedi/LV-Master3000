package student.tugraz.at.lv_master3000;

/**
 * Created with IntelliJ IDEA.
 * User: doris
 * Date: 6/4/13
 * Time: 5:03 PM
 * To change this template use File | Settings | File Templates.
 */

import android.content.*;
import android.database.sqlite.*;
import student.tugraz.at.lv_master3000.domain.Homework;

public class LVMaster3000DBHelper extends SQLiteOpenHelper{
    private static final String dbname = "LVMaster3000";
    private static int dbversion = 1;
    private static final String createHomework = "create table Homework "
    +"( _id integer primary key,name text, lecture integer not null references lecture(_id), due_date date);";

    private static final String createLecture = "create table lecture (_id integer primary key, name text, "
    +"location text, day text, time text, prof_name text, mandatory boolean);";

    private static final String createBook = "create table book (_id integer primary key, lecture integer not null, "
    +"name text not null, author text, due_date date, lender_name text, lender_address text);";

    private static final String createExam = "create table exam (_id integer primary key, lecture integer not null "
    +"references lecture(_id), location text, exam_date date);";

    private static final String createWorkmate = "create table workmate (_id integer primary key, name text, "
    +"email text, mobile integer);";

    private static final String createLearningMaterials = "create table learning_materials (_id integer primary key, "
    +"link text, description text);";

    private static final String createMilestone = "create table milestone (_id integer primary key, "
    +"milestone_date date, description text);";

    private static final String createExam2Workmate = "create table exam2workmate (exam integer not null references exam(_id), "
    +"workmate integer not null references workmate(_id), primary key (exam, workmate));";

    private static final String createHomework2Workmate = "create table homework2workmate (homework integer not null "
    +"references homework(_id), workmate integer not null references workmate(_id), primary key (homework, workmate));";

    private static final String createExam2LearningMaterials = "create table exam2LearningMaterials (exam integer not null"
    +"references exam(_id), learning_materials integer not null references learning_materials(_id), primary key (exam, learning_materials));";

    private static final String createHomework2LearningMaterials = "create table homework2LearningMaterials (homework integer not null"
            +"references homework(_id), learning_materials integer not null references learning_materials(_id), primary key (homework, learning_materials));";

    private static final String createExam2Milestone = "create table exam2Milestone (exam integer not null references exam(_id), "
            +"milestone integer not null references milestone(_id), primary key (exam, milestone));";

    private static final String createHomework2Milestone = "create table homework2Milestone (homework integer not null references homework(_id), "
            +"milestone integer not null references milestone(_id), primary key (homework, milestone));";

    private SQLiteDatabase db = null;

   // public LVMaster3000DBHelper(Context context){
   //
   //     //db = context.openOrCreateDatabase("LVMaster3000", 0, null);
   // }

    public LVMaster3000DBHelper(Context context) {
        super(context, dbname, null, dbversion);
        db = getReadableDatabase();
    }

    public void saveNewHomework(Homework homework){


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createHomework);
        db.execSQL(createLecture);
        db.execSQL(createBook);
        db.execSQL(createExam);
        db.execSQL(createLearningMaterials);
        db.execSQL(createWorkmate);
        db.execSQL(createExam2Workmate);
        db.execSQL(createExam2LearningMaterials);
        db.execSQL(createExam2Milestone);
        db.execSQL(createHomework2LearningMaterials);
        db.execSQL(createHomework2Milestone);
        db.execSQL(createHomework2Workmate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    //public Homework getHomeworkEarliestOfLecture(){
    //
    //}
    public boolean isDBNull(){
        if (db == null)
            return true;
        else
            return false;
    }

    public boolean isDBOpen(){
        if(db.isOpen())
            return true;
        else
            return false;
    }

    public void closeDB(){
        db.close();
    }

    public String getDatabaseName(){
        String name = super.getDatabaseName();
        return name;
    }

}
