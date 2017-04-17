package mobileapps.bramberifarms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import static mobileapps.bramberifarms.BerryTools.allBerryNames;

public class SpinnerTestActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    final List<String> berryNames = allBerryNames();
    int selectOne = 0;
    int selectTwo = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_test);

        final Button backBtn = (Button) findViewById(R.id.backBtn);
        final Button goBtn = (Button) findViewById(R.id.goBtn);
        final Spinner spinnerOne = (Spinner) findViewById(R.id.spinnerOne);
        final Spinner spinnerTwo = (Spinner) findViewById(R.id.spinnerTwo);
        spinnerOne.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, berryNames);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOne.setAdapter(aa);
        spinnerTwo.setAdapter(aa);

        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        Toast.makeText(getApplicationContext(), (berryNames.get(position) + " " + id), Toast.LENGTH_LONG).show();
        //berryNames.remove(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
