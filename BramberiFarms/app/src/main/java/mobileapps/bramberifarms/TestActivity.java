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
                try {
                    double hNum = Double.parseDouble(hTxt.getText().toString());
                    double wNum = Double.parseDouble(wTxt.getText().toString());


                    double outW;
                    double bmi;
                    String outBMI;
                    if (metricBox.isChecked()) {
                        //DO METRIC
                        bmi = wNum / (hNum * hNum);
                    } else {
                        //DO STANDARD
                        outW = wNum * 703;
                        bmi = outW / (hNum * hNum);
                    }

                    objTxt.setText("Your BMI is " + df.format(bmi));
                    String subText;

                    if (bmi <= 18.5) {
                        subText = "Underweight";
                        subTxt.setTextColor(Color.rgb(255,128,0));
                    } else if (bmi < 25) {
                        subText = "Normal";
                        subTxt.setTextColor(Color.rgb(0,160,0));
                    } else if (bmi < 30) {
                        subText = "Overweight";
                        subTxt.setTextColor(Color.rgb(255,128,0));
                    } else {
                        subText = "Obese";
                        subTxt.setTextColor(Color.rgb(255,0,0));
                    }

                    //PRINT OUTPUT OPTION
                    subTxt.setText("The Department of Health classifies you as:\n" + subText);

                } catch (NumberFormatException e){
                    objTxt.setText("Use must enter a number in both fields");
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
