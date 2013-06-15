package student.tugraz.at.lv_master3000.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import student.tugraz.at.lv_master3000.R;

/**
 * Created with IntelliJ IDEA.
 * User: dzom-dzom
 * Date: 6/14/13
 * Time: 6:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class BaseActivity extends Activity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_top_3:
                startActivity(new Intent(BaseActivity.this, MainActivity.class));
                return true;
            case R.id.menu_all:
                startActivity(new Intent(BaseActivity.this, AllActivity.class));
                return true;
        }

        return false;
    }
}