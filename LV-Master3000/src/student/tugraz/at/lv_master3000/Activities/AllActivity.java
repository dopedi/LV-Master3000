package student.tugraz.at.lv_master3000.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import student.tugraz.at.lv_master3000.R;

/**
 * Created with IntelliJ IDEA.
 * User: dzom-dzom
 * Date: 6/13/13
 * Time: 7:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class AllActivity extends BaseActivity implements View.OnClickListener
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.all_activity);

        Button lecturesBtn = (Button)findViewById(R.id.lecture_btn);
        lecturesBtn.setOnClickListener(this);

        Button homeworkBtn = (Button)findViewById(R.id.homework_btn);
        homeworkBtn.setOnClickListener(this);

        Button examsBtn = (Button)findViewById(R.id.exams_btn);
        examsBtn.setOnClickListener(this);

        Button booksBtn = (Button)findViewById(R.id.books_btn);
        booksBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.lecture_btn:
                startActivity(new Intent(AllActivity.this, MainActivity.class));
                break;
            case R.id.homework_btn:
                startActivity(new Intent(AllActivity.this, MainActivity.class));
                break;
            case R.id.exams_btn:
                startActivity(new Intent(AllActivity.this, MainActivity.class));
                break;
            case R.id.books_btn:
                startActivity(new Intent(AllActivity.this, MainActivity.class));
                break;
        }
    }
}