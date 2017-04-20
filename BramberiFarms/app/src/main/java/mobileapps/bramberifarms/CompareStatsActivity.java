package mobileapps.bramberifarms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class CompareStatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_stats);
        Intent intent = getIntent();
        String berryOne = intent.getStringExtra(SpinnerTestActivity.PASS_ONE);
        String berryTwo = intent.getStringExtra(SpinnerTestActivity.PASS_TWO);
        double berryOneVal = Double.parseDouble(intent.getStringExtra(SpinnerTestActivity.PASS_ONEV));
        double berryTwoVal = Double.parseDouble(intent.getStringExtra(SpinnerTestActivity.PASS_TWOV));

        final TextView t1 = (TextView) findViewById(R.id.t1);
        final TextView t2 = (TextView) findViewById(R.id.t2);
        final TextView mid = (TextView) findViewById(R.id.mid);


        if(berryOneVal>berryTwoVal) {
            t1.setText(berryOne);
            t2.setText(berryTwo);
        } else {
            t1.setText(berryTwo);
            t2.setText(berryOne);
        }

        if(berryOneVal==0){
            mid.setText("WAS NOT COMPARED TO");
        } else if (berryOneVal==berryTwoVal){
            mid.setText("IS EQUAL TO");
        }

    }
}
