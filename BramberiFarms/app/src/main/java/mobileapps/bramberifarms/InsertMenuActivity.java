package mobileapps.bramberifarms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class InsertMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_menu);
        final ListView listView = (ListView) findViewById(R.id.listView);
        final Button backBtn = (Button) findViewById(R.id.bBtn);
        int listItems = 2; //size of the array holding the list
        final ListDisplay [] listArry = new ListDisplay[listItems];
        listArry[0] = new ListDisplay("Insert Berry", 8, new Intent(InsertMenuActivity.this, InsertBerryActivity.class));
        listArry[1] = new ListDisplay("Insert Stats", 8, new Intent(InsertMenuActivity.this, InsertStatActivity2.class));

        ArrayAdapter<ListDisplay> testAdapter = new ArrayAdapter<ListDisplay>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listArry);
        listView.setAdapter(testAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(listArry[position].getIntentPlus());
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InsertMenuActivity.this,
                        MainActivity.class));
            }
        });

    }
}
