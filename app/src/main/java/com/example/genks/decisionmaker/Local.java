package com.example.genks.decisionmaker;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Local extends AppCompatActivity{
    public static final String EXTRA_MESSAGE = "com.example.genks.decisionmaker.MESSAGE";
    Intent selections;
    private static final int TAKE_PHOTO_PERMISSION = 1;
    String dollars= "";
    LocationManager locationManager;
    Double currentLat;
    Double currentLon;
    Geocoder geocoder;
    String currentZip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        Intent intent = getIntent();
        intent.getStringExtra(binaryChoice.EXTRA_MESSAGE);
        geocoder = new Geocoder(this, Locale.getDefault());
        selections=new Intent(this,ChoiceList.class);
    }
    public void onGPSClicked(View view){
        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION }, TAKE_PHOTO_PERMISSION);
        }
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, );
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
    }
    LocationListener listener= new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            currentLat= location.getLatitude();
            currentLon= location.getLongitude();
            try {
                List<Address> addr = geocoder.getFromLocation(currentLat, currentLon, 1);
                currentZip= addr.get(0).getPostalCode();
                EditText editText = (EditText) findViewById(R.id.zipText);
                editText.setText(currentZip);
            }
            catch(IOException e){
                Log.d("LOCAL", "ERROR");
            }
            Log.d("LOCAL", Double.toString(currentLat));
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
    public void onFoodClicked(View view) {
        //intent.putExtra(EXTRA_MESSAGE, code);
        //res+= code+"-";
        CheckBox foodBox = (CheckBox) findViewById(R.id.checkBox_food);
        boolean checked = foodBox.isChecked();
        //String res = "";
        //checkwhichwasclicked
        if (checked) {
            selections.putExtra("foodSelection", true);
            //res += "food-";
        }
        else {
            selections.putExtra("foodSelection", false);
        }
        }
    public void onShopClicked(View view) {
        //intent.putExtra(EXTRA_MESSAGE, code);
        //res+= code+"-";
        CheckBox shopBox = (CheckBox) findViewById(R.id.checkBox_shop);
        boolean checked = shopBox.isChecked();
        if (checked) {
            selections.putExtra("shopSelection", true);
            //res += "fashion-";
        } else {
            selections.putExtra("shopSelection", false);

        }
        }
    public void onArtClicked(View view) {
        //intent.putExtra(EXTRA_MESSAGE, code);
        //res+= code+"-";
        CheckBox artBox = (CheckBox) findViewById(R.id.checkBox_ent);
        boolean checked = artBox.isChecked();
        if (checked) {
            selections.putExtra("artSelection", true);
            //res += "arts-";
        } else {
            selections.putExtra("artSelection", false);

        }
        }
    public void onRecClicked(View view) {
        //intent.putExtra(EXTRA_MESSAGE, code);
        //res+= code+"-";
        CheckBox recBox = (CheckBox) findViewById(R.id.checkBox_rec);
        boolean checked = recBox.isChecked();
        if (checked) {
            selections.putExtra("recSelection", true);
            //res += "active-";
        } else {
            selections.putExtra("recSelection", false);
        }
        }
    public void onOneDollarClicked(View view) {
        //intent.putExtra(EXTRA_MESSAGE, code);
        //res+= code+"-";
        CheckBox dollarBox = (CheckBox) findViewById(R.id.checkOneDollar);
        boolean checked = dollarBox.isChecked();
        if (checked) {
            //selections.putExtra("dollarOne", "1");
            if(!dollars.equals("")){
                dollars+=",1";
            }
            else {
                dollars+="1";
            }
            //res += "active-";
        } else {
            selections.putExtra("dollarOne", "NULL");
        }
    }
    public void onTwoDollarClicked(View view) {
        //intent.putExtra(EXTRA_MESSAGE, code);
        //res+= code+"-";
        CheckBox dollarBox = (CheckBox) findViewById(R.id.checkTwoDollar);
        boolean checked = dollarBox.isChecked();
        if (checked) {
            //selections.putExtra("dollarTwo", "2");
            if(!dollars.equals("")){
                dollars+=",2";
            }
            else {
                dollars+="2";
            }
            //res += "active-";
        } else {
            selections.putExtra("dollarTwo", "NULL");
        }
    }
    public void onThreeDollarClicked(View view) {
        //intent.putExtra(EXTRA_MESSAGE, code);
        //res+= code+"-";
        CheckBox dollarBox = (CheckBox) findViewById(R.id.checkThreeDollar);
        boolean checked = dollarBox.isChecked();
        if (checked) {
            //selections.putExtra("dollarThree", "3");
            if(!dollars.equals("")){
                dollars+=",3";
            }
            else {
                dollars+="3";
            }
            //res += "active-";
        } else {
            selections.putExtra("dollarThree", "NULL");
        }
    }
    public void sendZip(View view) {
        EditText editText = (EditText) findViewById(R.id.zipText);
        String code= editText.getText().toString();
        selections.putExtra("zipCode", code);
        selections.putExtra("dollarSelection", dollars);
        selections.putExtra("lat", currentLat);
        selections.putExtra("long", currentLon);
        startActivity(selections);
    }

}

