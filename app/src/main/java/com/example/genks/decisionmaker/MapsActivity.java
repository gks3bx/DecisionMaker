package com.example.genks.decisionmaker;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Double lat;
    Double lon;
    Double buisLat;
    Double buisLong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        lat = intent.getDoubleExtra("lat", 0);
        lon= intent.getDoubleExtra("long", 0);
        buisLat = intent.getDoubleExtra("buisLat", 0);
        buisLong= intent.getDoubleExtra("buisLong", 0);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng current = new LatLng(lat, lon);
        LatLng buisLoc= new LatLng(buisLat, buisLong);
        mMap.addMarker(new MarkerOptions().position(current).title("Marker of Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
        mMap.addMarker(new MarkerOptions().position(buisLoc).title("Marker of Business"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(current, 10.0f));

    }
}
