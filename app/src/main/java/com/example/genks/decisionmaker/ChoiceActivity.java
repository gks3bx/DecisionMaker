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
import com.yelp.fusion.client.connection.YelpFusionApi;
import com.yelp.fusion.client.connection.YelpFusionApiFactory;
import com.yelp.fusion.client.models.SearchResponse;
//import com.yelp.fusion.client.connection.YelpFusionApi;
//import com.yelp.fusion.client.connection.YelpFusionApiFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Response;

public class ChoiceActivity extends FragmentActivity { //AppCompatActivity {
    //static int zipCode= 0;
    public static final String EXTRA_MESSAGE = "com.example.genks.decisionmaker.MESSAGE";
    String zip = "";
    static int zipCode= 0;
    String appId= "L74CpKK2iMAFJ9yIBLiMGg";
    String appSecret = "kP2mjcBB26f7TSuwLChax7Nmo99zlwjeKiG2BdqH3Ci1E82hRVcGmtVvL8IL1FzN";
    /*YelpFusionApiFactory apiFactory = new YelpFusionApiFactory();
    try{
        YelpFusionApi yelpFusionApi = apiFactory.createAPI(appSecret);
    }
    cat{

    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        Intent intent = getIntent();
        String resp = intent.getStringExtra(Local.EXTRA_MESSAGE);
        String[] terms= resp.split("-");
        //String zip = resp.substring(0,5);
        String zip = terms[0];
        Log.d("Local CBx", terms[1]);
        zipCode = new Integer(zip).intValue();
    }
    public void setSearch(){

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
}




