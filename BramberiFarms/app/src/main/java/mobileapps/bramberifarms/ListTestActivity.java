package mobileapps.bramberifarms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_test);

        final Button allBerryBtn = (Button) findViewById(R.id.allBerriesBtn);
        final Button allStatBtn = (Button) findViewById(R.id.allStatBtn);
        final Button backBtn = (Button) findViewById(R.id.backBtn);
        final ListView listView = (ListView) findViewById(R.id.listView);

        allBerryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Berry> berryAL = BerryTools.allBerry();
                ArrayAdapter<Berry> testAdapter = new ArrayAdapter<Berry>(ListTestActivity.this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, berryAL);
                listView.setAdapter(testAdapter);
            }
        });

        allStatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<YieldStat> statAL = BerryTools.allStats();
                ArrayAdapter<YieldStat> testAdapter = new ArrayAdapter<YieldStat>(ListTestActivity.this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, statAL);
                listView.setAdapter(testAdapter);
            }
        });


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListTestActivity.this,
                        MainActivity.class));
            }
        });

    }
}
