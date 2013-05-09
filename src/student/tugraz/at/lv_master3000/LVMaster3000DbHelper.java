package student.tugraz.at.lv_master3000;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;

public class LVMaster3000DbHelper {
	private SQLiteDatabase db;
	
	public LVMaster3000DbHelper(Context context){	
		db = context.openOrCreateDatabase("LVMaster3000", 0, null);
	}
}
