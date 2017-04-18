package mobileapps.bramberifarms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static mobileapps.bramberifarms.BerryTools.allBerryNames;

public class SpinnerTestActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    final List<String> berryNames = allBerryNames();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_test);

        final Button backBtn = (Button) findViewById(R.id.backBtn);
        final Button goBtn = (Button) findViewById(R.id.goBtn);
        final Spinner spinnerOne = (Spinner) findViewById(R.id.spinnerOne);
        final Spinner spinnerTwo = (Spinner) findViewById(R.id.spinnerTwo);
        final Spinner spinnerYear = (Spinner) findViewById(R.id.spinnerYear);
        final ListView berryOneLV = (ListView) findViewById(R.id.berryOneLV);
        final ListView berryTwoLV = (ListView) findViewById(R.id.berryTwoLV);
        spinnerOne.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, berryNames);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOne.setAdapter(aa);
        spinnerTwo.setAdapter(aa);
        ArrayList<String> yal = new ArrayList<String>();
        for (int i = 1990; i<2020; i++){
            yal.add(Integer.toString(i));
        }
        ArrayAdapter aa2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, yal);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(aa2);




        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> arOne = new ArrayList<String>();
                ArrayList<String> arTwo = new ArrayList<String>();
                String year = spinnerYear.getSelectedItem().toString();
                String berryOne = spinnerOne.getSelectedItem().toString();
                String berryTwo = spinnerTwo.getSelectedItem().toString();
                Log.i("SpinnerTest", " goBtn BERRY ONE: " + berryOne);
                Log.i("SpinnerTest", " goBtn BERRY TWO: " + berryTwo);
                Log.i("SpinnerTest", " goBtn YEAR: " + year);
                YieldStat statOne = BerryTools.pullStatsByYear(berryOne, year);
                YieldStat statTwo = BerryTools.pullStatsByYear(berryTwo, year);
                arOne.add(statOne.getBid());
                arOne.add(Integer.toString(statOne.getNumPlants()));
                arOne.add(Double.toString(statOne.getYieldPP()));
                arOne.add((Double.toString(statOne.getMarketYield())));
                arOne.add(Double.toString(statOne.getPricePP()));

                arTwo.add(statTwo.getBid());
                arTwo.add(Integer.toString(statTwo.getNumPlants()));
                arTwo.add(Double.toString(statTwo.getYieldPP()));
                arTwo.add((Double.toString(statTwo.getMarketYield())));
                arTwo.add(Double.toString(statTwo.getPricePP()));

                Log.i("SpinnerTest", " goBtn: " + 77);
                ArrayAdapter<String> aaOne = new ArrayAdapter<String>(SpinnerTestActivity.this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, arOne);
                berryOneLV.setAdapter(aaOne);
                ArrayAdapter<String> aaTwo = new ArrayAdapter<String>(SpinnerTestActivity.this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, arTwo);
                berryTwoLV.setAdapter(aaTwo);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SpinnerTestActivity.this,
                        MainActivity.class));
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
