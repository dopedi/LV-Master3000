package student.tugraz.at.lv_master3000.databaseAccess;

/**
 * Created with IntelliJ IDEA.
 * User: doris
 * Date: 6/4/13
 * Time: 5:03 PM
 * To change this template use File | Settings | File Templates.
 */
import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import student.tugraz.at.lv_master3000.Homework;
import student.tugraz.at.lv_master3000.Lecture;

import java.io.*;

public class LVMaster3000DBHelper extends SQLiteOpenHelper{
    protected static final String dbname = "LVMaster3000";
    private static int dbversion = 5;
    private static final String createHomework = "create table homework "
    +"( _id integer primary key,name text, due_date date, lecture integer not null );";//references lecture(_id));";

    private static final String createLecture = "create table lecture (_id integer primary key, name text, "
    +"location text, day text, time date, prof_name text, mandatory boolean);";

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

    protected static final String insertInto = "insert into ";
    protected static final String values = " VALUES ";

    protected SQLiteDatabase db = null;
    private static final String goalLocation = "/data/data/student.tugraz.at.lv_master3000/databases/LVMaster3000.db";
    private boolean alreadySetup = false;

    public LVMaster3000DBHelper(Context context) {
        super(context, dbname, null, dbversion);
        db = getWritableDatabase();

        alreadySetup = copyDatabaseToGoalLocation(context);

    }

    private boolean copyDatabaseToGoalLocation(Context context){
        alreadySetup = (new File(goalLocation)).exists();
        if (alreadySetup == false) {

            // Open the .db file in your assets directory
            InputStream is = null;
            try {
                is = new FileInputStream("data/local/tmp/student.tugraz.at.lv_master3000/assets/LVMaster3000.db");
            } catch (IOException e) {
                System.err.println("error copying database");
                alreadySetup = false;
                return false;
            }

            // Copy the database into the destination
            OutputStream os = null;
            try {
                os = new FileOutputStream(goalLocation);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0){
                    os.write(buffer, 0, length);
                }
                os.flush();

                os.close();
                is.close();
            } catch (FileNotFoundException e) {
                return false;
            } catch (IOException e) {
                return false;

            }
        }
        return true;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createLecture);
        db.execSQL(createHomework);
        db.execSQL(createBook);
        db.execSQL(createExam);
        db.execSQL(createLearningMaterials);
        db.execSQL(createWorkmate);
       // db.execSQL(createExam2Workmate);
       // db.execSQL(createExam2LearningMaterials);
       // db.execSQL(createExam2Milestone);
       // db.execSQL(createHomework2LearningMaterials);
       // db.execSQL(createHomework2Milestone);
       // db.execSQL(createHomework2Workmate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table homework");
        db.execSQL("drop table exam");
        db.execSQL("drop table book");
        db.execSQL("drop table lecture");
        db.execSQL("drop table learning_materials");
        db.execSQL("drop table workmate");
        onCreate(db);
    }

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
