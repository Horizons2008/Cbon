package com.example.cbon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends AppCompatActivity implements OnMapReadyCallback, LocationListener {
    GoogleMap GMap;
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    ImageButton BTN_Current;
    Button BTN_ValideGPS;
    Double lat = 0.0, lng = 0.0;
    LatLng CurrPoint=new LatLng(0,0),SelectedPoint=new LatLng(0,0);
    TextView TV_lat,TV_lgt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        BTN_Current = findViewById(R.id.BTN_Current);
        BTN_ValideGPS = findViewById(R.id.BTN_ValidGPS);
        TV_lat=findViewById(R.id.TV_lat);
        TV_lgt=findViewById(R.id.TV_lgt);

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_maps);
        client = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(Maps.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            curentposition();
            SelectPosition();


            BTN_Current.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // getCurrentLocation();
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {


                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(CurrPoint,15));



                        }
                    });


                }
            });


        } else {
            ActivityCompat.requestPermissions(Maps.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
        BTN_ValideGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent();
                i2.putExtra("lat", String.valueOf(lat));
                i2.putExtra("lgt", String.valueOf(lng));
                Maps.this.setResult(1, i2);
                Maps.this.finish();
            }
        });

    }
    //*******************************************************************
    void curentposition() {
        LocationManager locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        boolean isGPS = locationManager.isProviderEnabled (LocationManager.GPS_PROVIDER);



    }

    //*******************************************************************
    private void SelectPosition() {
        supportMapFragment.getMapAsync(Maps.this);


    }

//****************************************************
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        GMap=googleMap;

        GMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions options=new MarkerOptions();
                SelectedPoint=latLng;
                lat=latLng.latitude;
                lng=latLng.longitude;

                options.position(SelectedPoint);
                options.title(latLng.latitude+" : "+ latLng.longitude);
                options.icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_RED));
                GMap.clear();
                // GMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15) );
                GMap.addMarker(options);
                options.position(CurrPoint);
                options.title(latLng.latitude+" : "+ latLng.longitude);
                options.icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                GMap.addMarker(options);

            }
        });

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        TV_lat.setText("lat: "+String.valueOf(location.getLatitude()));
        TV_lgt.setText("lgt: "+String.valueOf(location.getLongitude()));

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                CurrPoint=new LatLng(location.getLatitude(),location.getLongitude());

                MarkerOptions options= new MarkerOptions().position(CurrPoint).title("I am Here");
                options.icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                //  googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,20));
                GMap.clear();
                googleMap.addMarker(options);
                if (SelectedPoint.longitude!=0) {
                    options= new MarkerOptions().position(SelectedPoint).title("I am Here");
                    options.icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    googleMap.addMarker(options);

                }


            }
        });



    }
}