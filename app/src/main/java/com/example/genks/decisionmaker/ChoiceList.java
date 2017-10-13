package com.example.genks.decisionmaker;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ChoiceList extends FragmentActivity {
    String zip= "22046";
    static LatLng loc = new LatLng(25,25);
    int zC = ChoiceActivity.getZipCode();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getlL(zC);
        setContentView(R.layout.activity_choice_list);
    }
    public void sendMapZip(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
    protected static LatLng getCoorLL(){
        return loc;
    }
    private void getlL(int zC) {
        //GeocoderTask task= new MapsActivity.GeocoderTask();
        //LatLng corrLL= task.execute(zip).get();
        new ChoiceList.GeocoderTask().execute(zC);
    }

    private class GeocoderTask extends AsyncTask<Integer, Void, LatLng> {
        @Override
        protected void onPreExecute(){

        }
        @Override
        protected LatLng doInBackground(Integer... params) {
            int zipCode = params[0];
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
                loc = ll;
                return ll;
            } catch (Exception e) {
                Log.d("BADD", "dIB");
                return new LatLng(0,0);
            }
        }

        //@Override
        protected void onPostExecute(LatLng... result) {
            loc= result[0];
            Log.d("LATLONG", ""+result[0]);
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
                    response = "c";
                }

            } catch (Exception e) {
                Log.d("BADD", "getLLbyURL");
                e.printStackTrace();
            }
            return response;
        }
    }
}

