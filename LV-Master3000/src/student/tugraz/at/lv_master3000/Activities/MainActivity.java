package student.tugraz.at.lv_master3000.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import student.tugraz.at.lv_master3000.R;

/**
 * Created with IntelliJ IDEA.
 * User: dzom-dzom
 * Date: 6/13/13
 * Time: 8:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainActivity extends Activity {
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Button top3Button = (Button)findViewById(R.id.top3Button);
        top3Button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, MainActivity.class));//To change body of implemented methods use File | Settings | File Templates.
            }
        });

        Button allButton = (Button)findViewById(R.id.allButton);
        allButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, AllActivity.class));//To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }
}