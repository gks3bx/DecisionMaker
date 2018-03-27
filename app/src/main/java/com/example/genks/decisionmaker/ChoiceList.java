package com.example.genks.decisionmaker;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.yelp.fusion.client.connection.YelpFusionApi;
import com.yelp.fusion.client.connection.YelpFusionApiFactory;
import com.yelp.fusion.client.models.AutoComplete;
import com.yelp.fusion.client.models.Business;
import com.yelp.fusion.client.models.Coordinates;
import com.yelp.fusion.client.models.Reviews;
import com.yelp.fusion.client.models.SearchResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.DTDHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;

public class ChoiceList extends FragmentActivity {
    String zip= "22046";
    String appId= "PRIVATE";
    String apiKey = "PRIVATE";
    static LatLng loc = new LatLng(25,25);
    Double latitude;
    Double longitude;
    Coordinates buisLoc;
    Double buisLat;
    Double buisLong;
    ArrayList<Business> results;
    Map<String, String> userInupts = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userInupts.put("limit", "50");
        Intent intent = getIntent();
        zip= intent.getStringExtra("zipCode");
        latitude= intent.getDoubleExtra("lat", 0);
        longitude= intent.getDoubleExtra("long", 0);
        userInupts.put("location", zip);
        //boolean food = intent.getBooleanExtra("foodSelection", false);
        if (intent.getBooleanExtra("foodSelection", false)){
            userInupts.put("term", "food");
        }
        if (intent.getBooleanExtra("shopSelection", false)){
            userInupts.put("term", "fashion");
        }
        if (intent.getBooleanExtra("artSelection", false)){
            userInupts.put("term", "arts");
        }
        if (intent.getBooleanExtra("recSelection", false)){
            userInupts.put("term", "active");
        }
        String dollarAmount= intent.getStringExtra("dollarSelection");
        userInupts.put("price", dollarAmount);
        Log.d("LIST", intent.getStringExtra("dollarSelection"));
        //Log.d("LIST",intent.toString());
        //getlL(zC);
        setContentView(R.layout.activity_choice_list);
        GetOptions downloadTask = new GetOptions();
        downloadTask.execute(userInupts);
    }
    public class GetOptions extends AsyncTask<Map<String, String>, Boolean, Boolean> {
        @Override
        protected Boolean doInBackground(Map<String, String>... maps) {
            boolean resp = true;
            YelpFusionApiFactory apiFactory = new YelpFusionApiFactory();
            try {
                Log.d("LIST", "Made it 0");
                Map<String, String> selectedParams = maps[0];
                Log.d("LIST", "Made it");
                YelpFusionApi yelpFusionApi = apiFactory.createAPI(apiKey);
                Log.d("LIST", "Made it 2");
                Call<SearchResponse> call = yelpFusionApi.getBusinessSearch(selectedParams);
                Log.d("LIST", "Made it 3");
                SearchResponse searchResponse = call.execute().body();
                Log.d("LIST", "Made it 4");
                results = searchResponse.getBusinesses();
                Log.d("LIST", "Made it 5");
            } catch (IOException e) {
                Log.d("LIST", "ERROR in BACKGROUND");
                resp = false;
            }
            return resp;
        }
        @Override
        protected void onPostExecute(Boolean meh){
            super.onPostExecute(meh);
            if(meh){
                logIt();
            }
        }
    }
    public void logIt(){
        Random randNum = new Random();
        int randomRes= randNum.nextInt(results.size());
        Business showBuis = results.get(randomRes);
        TextView title = (TextView) findViewById(R.id.businessTitle);
        TextView rating = (TextView) findViewById(R.id.ratingVal);
        TextView category = (TextView) findViewById(R.id.catVal);
        TextView hours = (TextView) findViewById(R.id.hoursVal);
        title.setText(showBuis.getName());
        String displayRating = "Rating: "+Double.toString(showBuis.getRating());
        rating.setText(displayRating);
        String displayCategory = "Catergory: "+ showBuis.getCategories().get(0).getTitle();
        category.setText(displayCategory);
        //String displayHours = "Hours: "+showBuis.getHours().get(0).toString();
        //Log.d("LIST", displayHours);
        buisLoc = showBuis.getCoordinates();
        buisLat= buisLoc.getLatitude();
        buisLong= buisLoc.getLongitude();
        /*for(int i=0; i<results.size(); i++){
            Business business = results.get(i);
            Log.d("LIST", business.getName());
            Log.d("LIST", business.getCategories().get(0).getTitle());
        }*/
    }
    public void tryAgain(View view){
        logIt();
    }
    public void goMap(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("lat", latitude);
        intent.putExtra("long", longitude);
        intent.putExtra("buisLat", buisLat);
        intent.putExtra("buisLong", buisLong);
        startActivity(intent);
        //intent.putExtra("lat", )
    }
}

