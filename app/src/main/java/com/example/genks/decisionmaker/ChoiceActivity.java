package com.example.genks.decisionmaker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ChoiceActivity extends FragmentActivity { //AppCompatActivity {
    //static int zipCode= 0;
    public static final String EXTRA_MESSAGE = "com.example.genks.decisionmaker.MESSAGE";
    String zip = "";
    static int zipCode= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        Intent intent = getIntent();
        String zip = intent.getStringExtra(Local.EXTRA_MESSAGE);
        Log.d("Choice ZipCode", zip);
        zipCode = new Integer(zip).intValue();
        //String[] arr= getlL();
        //getlL();
    }

    public static int getZipCode(){
        if (zipCode == 0){
            return 22903;
        }
        else{
            return zipCode;
        }
    }
    public void sendList(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, ChoiceList.class);
        //intent.putExtra(EXTRA_MESSAGE, zip);
        startActivity(intent);
    }
    /*
    protected LatLng getArea(LatLng result){
        return result;
    }
    private void getlL() {
        new YelpTask().execute(zip);
    }


    private class YelpTask extends AsyncTask<String, Void, LatLng> {

        @Override
        protected LatLng doInBackground(String... params) {
            int zipCode = new Integer(params[0]).intValue();
            String response;
            try {
                response = getLatLongByURL("http://maps.google.com/maps/api/geocode/json?address="
                        + zipCode + "&ka&sensor=false");
                Log.d("response", "" + response);
                String[] str = {response};
                JSONObject jsonObject = new JSONObject(str[0]);

                double lng = ((JSONArray) jsonObject.get("results")).getJSONObject(0)
                        .getJSONObject("geometry").getJSONObject("location")
                        .getDouble("lng");

                double lat = ((JSONArray) jsonObject.get("results")).getJSONObject(0)
                        .getJSONObject("geometry").getJSONObject("location")
                        .getDouble("lat");

                Log.d("latitude", "" + lat);
                Log.d("longitude", "" + lng);
                LatLng ll= new LatLng(lat ,lng);
                return ll;
            } catch (Exception e) {
                return new LatLng(0,0);
            }
        }

        //@Override
        protected void onPostExecute(LatLng... result) {
                getArea(result[0]);
                super.onPostExecute(result[0]);
        }


        public String getLatLongByURL(String requestURL) {
            URL url;
            String response = "";
            try {
                url = new URL(requestURL);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                conn.setDoOutput(true);
                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        response += line;
                    }
                } else {
                    response = "";
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }
    }
    */
}




