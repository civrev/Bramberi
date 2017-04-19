package mobileapps.bramberifarms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InsertStatActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_stat2);
        final EditText bidStatF = (EditText) findViewById(R.id.bidStatF);
        final EditText yearStatF = (EditText) findViewById(R.id.yearStatF);
        final EditText plantsStatF = (EditText) findViewById(R.id.plantsStatF);
        final EditText yieldStatF = (EditText) findViewById(R.id.yieldStatF);
        final EditText marketStatF = (EditText) findViewById(R.id.marketStatF);
        final EditText priceStatF = (EditText) findViewById(R.id.priceStatF);

        final TextView outTxt = (TextView) findViewById(R.id.outTxt);

        final Button backBtn = (Button) findViewById(R.id.bBtn);
        final Button entBtn = (Button) findViewById(R.id.insertBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InsertStatActivity2.this,
                        MainActivity.class));
            }
        });

        entBtn.setOnClickListener(new View.OnClickListener() {
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

                YieldStat temp = BerryTools.pullStats(brryId);
                if(temp!=null){
                    outTxt.setText("Your Stat was NOT inserted!");
                } else {
                    outTxt.setText("Your Stat was inserted");
                }

            }
        });








    }
}
