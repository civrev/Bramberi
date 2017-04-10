package mobileapps.bramberifarms;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static mobileapps.bramberifarms.BerryTools.allBerry;
import static mobileapps.bramberifarms.BerryTools.allStats;
import static mobileapps.bramberifarms.BerryTools.pullBerry;
import static mobileapps.bramberifarms.BerryTools.pullStats;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        final Button backBtn = (Button) findViewById(R.id.backBtn);
        //for berry
        final EditText nF = (EditText) findViewById(R.id.nameField);
        final EditText sF = (EditText) findViewById(R.id.seasonField);
        final EditText idF = (EditText) findViewById(R.id.idField);
        final Button entBtn = (Button) findViewById(R.id.entBtn);
        final Button pullBtn = (Button) findViewById(R.id.pullBtn);
        final TextView outL = (TextView) findViewById(R.id.outLabel);
        //for stats
        final EditText bidStatF = (EditText) findViewById(R.id.bidStatF);
        final EditText yearStatF = (EditText) findViewById(R.id.yearStatF);
        final EditText plantsStatF = (EditText) findViewById(R.id.plantsStatF);
        final EditText yieldStatF = (EditText) findViewById(R.id.yieldStatF);
        final EditText marketStatF = (EditText) findViewById(R.id.marketStatF);
        final EditText priceStatF = (EditText) findViewById(R.id.priceStatF);
        final Button statInBtn = (Button) findViewById(R.id.statInBtn);
        final Button statOutBtn = (Button) findViewById(R.id.statOutBtn);
        final TextView statOut = (TextView) findViewById(R.id.statOut);
        //for testing pulling a whole table select * from TABLE
        final Button allBBtn = (Button) findViewById(R.id.allBBtn);
        final Button allSBtn = (Button) findViewById(R.id.allSBtn);


        


        entBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nF.getText().toString();
                String season = sF.getText().toString();
                String bid = idF.getText().toString();
                Berry berry = new Berry (name, season, bid);
                BerryTools.inputBerry(berry);
            }
        });

        pullBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nF.getText().toString();
                Berry berry = pullBerry(name);
                if (berry!=null) {
                    outL.setText(berry.toString());
                } else {
                    outL.setText("No berry found");
                }
            }
        });

        statInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String brryId = bidStatF.getText().toString();
                int year = Integer.parseInt(yearStatF.getText().toString());
                int numplants = Integer.parseInt(plantsStatF.getText().toString());
                double yieldpp = Double.parseDouble(yieldStatF.getText().toString());
                double marketyield = Double.parseDouble(marketStatF.getText().toString());
                double pricepp = Double.parseDouble(priceStatF.getText().toString());
                YieldStat ystat = new YieldStat(brryId, year, numplants, yieldpp, marketyield, pricepp);
                BerryTools.inputStats(ystat);
            }
        });

        statOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nF.getText().toString();
                YieldStat ys = pullStats(name);
                if (ys!=null) {
                    statOut.setText(ys.toString());
                } else {
                    statOut.setText("No stat for that berry");
                }
            }
        });

        allBBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList berryList = allBerry();
                if (berryList!=null) {
                    Log.i("TEST ACTIVITY", "allBerry size: " + berryList.size());
                } else {
                    statOut.setText("No berries");
                }
            }
        });

        allSBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList statsList = allStats();
                if (statsList!=null) {
                    Log.i("TEST ACTIVITY", "allStats size: " + statsList.size());
                } else {
                    statOut.setText("No stats");
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestActivity.this,
                        MainActivity.class));
            }
        });
    }
}
