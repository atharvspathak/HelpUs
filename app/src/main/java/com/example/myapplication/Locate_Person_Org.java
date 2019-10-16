package com.example.myapplication;

import androidx.fragment.app.FragmentActivity;

import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

public class Locate_Person_Org extends FragmentActivity implements OnMapReadyCallback {
    Button done;
    static double lat,log;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate__person__org);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_org);
        mapFragment.getMapAsync(this);

        done=(Button)findViewById(R.id.button_done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





    }

    public static void setLatLog(double lat1,double log1){

        lat=lat1;
        log=log1;


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
        double latitude=lat;
        double longitude=log;
        googleMap.setMyLocationEnabled(true);

        LatLng position = new LatLng(latitude, longitude);

        MarkerOptions options = new MarkerOptions();

        options.position(position);

        options.title("Position");

        LatLng sydney = new LatLng(latitude, longitude);
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(sydney).title("Person Location"));

        // Setting snippet for the MarkerOptions
        //options.snippet("Latitude:"+latitude+",Longitude:"+longitude);
        // Getting Reference to SupportMapFragment of activity_map.xml
        // SupportMapFragment fm = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);


        // Adding Marker on the Google Map
        //mMap.addMarker(options);

        // Creating CameraUpdate object for position
        float zoomLevel = 16.0f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel));

    }
}
