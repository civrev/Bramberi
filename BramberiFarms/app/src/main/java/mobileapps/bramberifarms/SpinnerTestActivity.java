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

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import static mobileapps.bramberifarms.BerryTools.allBerryNames;

public class SpinnerTestActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    public final static String PASS_ONE = "mobileapps.bramberifarms.BERRYONE";
    public final static String PASS_TWO = "mobileapps.bramberifarms.BERRYTWO";
    public final static String PASS_ONEV = "mobileapps.bramberifarms.BERRYONEV";
    public final static String PASS_TWOV = "mobileapps.bramberifarms.BERRYTWOV";
    final List<String> berryNames = allBerryNames();
    final List<String> berryVals = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_test);

        berryVals.add("0");
        berryVals.add("0");

        final Button backBtn = (Button) findViewById(R.id.bBtn);
        final Button goBtn = (Button) findViewById(R.id.goBtn);
        final Button switchBtn = (Button) findViewById(R.id.switchBtn);
        final Spinner spinnerOne = (Spinner) findViewById(R.id.spinnerOne);
        final Spinner spinnerTwo = (Spinner) findViewById(R.id.spinnerTwo);
        final Spinner spinnerYear = (Spinner) findViewById(R.id.spinnerYear);
        final ListView berryOneLV = (ListView) findViewById(R.id.berryOneLV);
        final ListView berryTwoLV = (ListView) findViewById(R.id.berryTwoLV);
        final NumberFormat cf = NumberFormat.getCurrencyInstance();
        final Intent bigIntent = new Intent(SpinnerTestActivity.this,
                CompareStatsActivity.class);
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

                if(statOne!=null){
                    arOne.add(statOne.getBid());
                    arOne.add(Integer.toString(statOne.getNumPlants()));
                    arOne.add(Double.toString(statOne.getYieldPP()));
                    arOne.add((Double.toString(statOne.getMarketYield())));
                    arOne.add(Double.toString(statOne.getPricePP()));
                    arOne.add(cf.format(statOne.getMarketYield()*statOne.getPricePP()));
                    berryVals.set(0, Double.toString(statOne.getMarketYield()*statOne.getPricePP()));
                } else {
                    arOne.add("NO");
                    arOne.add("STATS");
                    arOne.add("FOR");
                    arOne.add(berryOne.toUpperCase());
                    arOne.add("IN");
                    arOne.add(year);
                }
                YieldStat statTwo = BerryTools.pullStatsByYear(berryTwo, year);

                if(statTwo!=null){
                    arTwo.add(statTwo.getBid());
                    arTwo.add(Integer.toString(statTwo.getNumPlants()));
                    arTwo.add(Double.toString(statTwo.getYieldPP()));
                    arTwo.add((Double.toString(statTwo.getMarketYield())));
                    arTwo.add(Double.toString(statTwo.getPricePP()));
                    arTwo.add(cf.format(statTwo.getMarketYield()*statTwo.getPricePP()));
                    berryVals.set(1, Double.toString(statTwo.getMarketYield()*statTwo.getPricePP()));
                } else {
                    arTwo.add("NO");
                    arTwo.add("STATS");
                    arTwo.add("FOR");
                    arTwo.add(berryTwo.toUpperCase());
                    arTwo.add("IN");
                    arTwo.add(year);
                }


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

        switchBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                String berryOne = spinnerOne.getSelectedItem().toString();
                String berryTwo = spinnerTwo.getSelectedItem().toString();
                bigIntent.putExtra(PASS_ONE, berryOne);
                bigIntent.putExtra(PASS_TWO, berryTwo);
                bigIntent.putExtra(PASS_ONEV, berryVals.get(0));
                bigIntent.putExtra(PASS_TWOV, berryVals.get(1));
                startActivity(bigIntent);
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
