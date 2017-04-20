package mobileapps.bramberifarms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DeletionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletion);

        final EditText nameTF = (EditText) findViewById(R.id.berryTF);
        final EditText yearTF = (EditText) findViewById(R.id.statTF);
        final Button backBtn = (Button) findViewById(R.id.backBtn);
        final Button berryBtn = (Button) findViewById(R.id.deleteBerryBtn);
        final Button statBtn = (Button) findViewById(R.id.deleteStatBtn);
        final TextView outTxt = (TextView) findViewById(R.id.outTxt);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeletionActivity.this,
                        MainActivity.class));
            }
        });

        berryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameTF.getText().toString();
                int check = BerryTools.deleteBerry(name);
                if(check>0){
                    outTxt.setText("Berry was deleted");
                }else{
                    outTxt.setText("Berry was NOT deleted");
                }
            }
        });

        statBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameTF.getText().toString();
                String year = yearTF.getText().toString();
                int check = BerryTools.deleteStat(name, year);
                if(check>0){
                    outTxt.setText("Stat was deleted");
                }else{
                    outTxt.setText("Stat was NOT deleted");
                }
            }
        });

    }
}
