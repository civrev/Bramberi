package mobileapps.bramberifarms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = (ListView) findViewById(R.id.listView);
        int listItems = 1; //size of the array holding the list
        final ListDisplay [] listArry = new ListDisplay[listItems];
        // add intents to the list here. Whenever a new page needs to be gone to from menu
        // all you have to do is give it a name, and use the name of the .class file
        // the test activity is an example, it sends you to the BMI I did earlier
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

    }
}
