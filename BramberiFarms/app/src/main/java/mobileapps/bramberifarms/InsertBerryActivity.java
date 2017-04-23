package mobileapps.bramberifarms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InsertBerryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_berry);

        final EditText nF = (EditText) findViewById(R.id.nF);
        final EditText sF = (EditText) findViewById(R.id.sF);
        final EditText idF = (EditText) findViewById(R.id.idF);
        final Button entBtn = (Button) findViewById(R.id.entBtn);
        final Button backBtn = (Button) findViewById(R.id.bBtn);
        final TextView outTxt = (TextView) findViewById(R.id.outTxt);

        entBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nF.getText().toString();
                String season = sF.getText().toString();
                String bid = idF.getText().toString();
                Berry berry = new Berry (name, season, bid);
                BerryTools.inputBerry(berry);
                try {
                    wait(1000);
                } catch (Exception e){
                    e.printStackTrace();
                }
                Berry temp = BerryTools.pullBerry(name);
                if(temp.equals(null)){
                    outTxt.setText("Your Berry was NOT inserted!");
                } else {
                    outTxt.setText("Your Berry was inserted");
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InsertBerryActivity.this,
                        InsertMenuActivity.class));
            }
        });
    }
}
