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

import static mobileapps.bramberifarms.BerryTools.pullBerry;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        final Button backBtn = (Button) findViewById(R.id.backBtn);
        final EditText nF = (EditText) findViewById(R.id.nameField);
        final EditText sF = (EditText) findViewById(R.id.seasonField);
        final EditText idF = (EditText) findViewById(R.id.idField);
        final Button entBtn = (Button) findViewById(R.id.entBtn);
        final Button pullBtn = (Button) findViewById(R.id.pullBtn);
        final TextView outL = (TextView) findViewById(R.id.outLabel);


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

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestActivity.this,
                        MainActivity.class));
            }
        });
    }
}
