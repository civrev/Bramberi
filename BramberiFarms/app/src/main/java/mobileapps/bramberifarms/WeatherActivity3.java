package mobileapps.bramberifarms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.DiskLruCache;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class WeatherActivity3 extends AppCompatActivity {

    public static String jsonData = "";
    public static String sData = "";
    public static String sSummary = "";
    public static OkHttpClient client = new OkHttpClient();
    final static String api_key = "a7914435c71e8504f4a666a08aa4c050";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final String URL = "https://api.darksky.net/forecast/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather3);


        final Button backBtn = (Button) findViewById(R.id.backBtn);
        final EditText latTF = (EditText) findViewById(R.id.latTF);
        final EditText lonTF = (EditText) findViewById(R.id.longTF);
        final Button enterBtn = (Button) findViewById(R.id.enterBtn);
        final TextView outTV = (TextView) findViewById(R.id.outTV);

        final String testLat = "37.8267";
        final String testLong = "-122.4233";
        //to 4 decimal places

        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rLat = latTF.getText().toString();
                String rLong = lonTF.getText().toString();
                Log.i("WEATHER3", "ENTER BTN " + rLat + ", " + rLong);

                //begin--------------------------------------------------
                Request request = new Request.Builder()
                        .url(URL + api_key + "/" + rLat + "," + rLong)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            throw new IOException("Unexpected code " + response);
                        } else {
                            jsonData = response.body().string();
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(jsonData);
                                JSONObject currently = jsonObject.getJSONObject("currently");
                                sSummary = currently.getString("summary");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.i("DARK SKY", "PARSEJSON " + sSummary);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //run on UI
                                    outTV.setText(sSummary);
                                }
                            });
                        }
                    }
                });
                //end----------------------------------------------------

            }
        });


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WeatherActivity3.this, MainActivity.class));
            }
        });
    }

}
