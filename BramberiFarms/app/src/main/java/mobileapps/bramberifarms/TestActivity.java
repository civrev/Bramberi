package mobileapps.bramberifarms;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        final Button backBtn = (Button) findViewById(R.id.backBtn);
        final EditText wTxt = (EditText) findViewById(R.id.wTxt);
        final EditText hTxt = (EditText) findViewById(R.id.hTxt);
        final CheckBox metricBox = (CheckBox) findViewById(R.id.metricBox);
        final Button entBtn = (Button) findViewById(R.id.entBtn);
        final TextView objTxt = (TextView) findViewById(R.id.objTxt);
        final TextView subTxt = (TextView) findViewById(R.id.subTxt);
        String pattern = "#.00";
        final DecimalFormat df = new DecimalFormat(pattern);

        entBtn.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                Berry berry = new Berry ("Rosenbloom", "Spring", "RF1");
                BerryTools.inputBerry(berry);
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
