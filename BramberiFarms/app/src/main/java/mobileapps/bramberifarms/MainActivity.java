package mobileapps.bramberifarms;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private static Context mContext;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //database stuff
        mContext = this.getApplicationContext();
        db = new DBHelper(mContext).getWritableDatabase();
        Log.i("DATABASE_DIRECTORY:", db.toString()); //tells you where db is stored
        //end database stuff

        //listView stuff
        final ListView listView = (ListView) findViewById(R.id.listView);
        int listItems = 1; //size of the array holding the list
        final ListDisplay [] listArry = new ListDisplay[listItems];
        listArry[0] = new ListDisplay("BMI", 8, new Intent(MainActivity.this, TestActivity.class));
        ArrayAdapter<ListDisplay> testAdapter = new ArrayAdapter<ListDisplay>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listArry);
        listView.setAdapter(testAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(listArry[position].getIntentPlus());
            }
        });
        //end listView stuff

    }

    public static Context getmContext() {
        return mContext;
    }
}
