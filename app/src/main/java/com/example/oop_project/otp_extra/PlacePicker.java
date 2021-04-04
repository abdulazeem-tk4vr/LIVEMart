//package com.example.oop_project;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//
//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.content.pm.PackageManager;
//import android.location.Address;
//import android.location.Geocoder;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.List;
//import java.util.Locale;
//
//import static androidx.constraintlayout.motion.widget.Debug.getLocation;
//
//public class PlacePicker extends AppCompatActivity implements LocationListener {
//
//
//    Button button_location;
//    TextView textView_location;
//    LocationManager locationManager;
//
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_place_picker);
//
//        textView_location = findViewById(R.id.text_location);
//        button_location = findViewById(R.id.button_location);
//
//        if (ContextCompat.checkSelfPermission(PlacePicker.this, Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(PlacePicker.this,new String[]{
//                    Manifest.permission.ACCESS_FINE_LOCATION
//            },100);
//        }
//
//        button_location.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                double alat1 = 25.27530750496071;
//                double alon1 = 51.55206794532757;
//                double alat2 = 25.271387943401542;
//                double alon2 = 51.57271022598347;
//
//
//                Location startPoint=new Location("locationA");
//                startPoint.setLatitude(alat1);
//                startPoint.setLongitude(alon1);
//
//                Location endPoint=new Location("locationA");
//                endPoint.setLatitude(alat2);
//                endPoint.setLongitude(alon2);
//
//                double distance=startPoint.distanceTo(endPoint)/1000;
//
//                String s=String.valueOf(distance);
//                textView_location.setText(s + " km");
//            }
//        });
//
//
//    }
//
//    @SuppressLint("MissingPermission")
//    private void getLocation(){
//
//        try {
//            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,500,5,PlacePicker.this);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//
//    }
//
//    @Override
//    public void onLocationChanged(@NonNull Location location) {
//
//
//        double longitiude = location.getLongitude();
//        double latitude = location.getLatitude();
//        Toast.makeText(this, ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();
//
//        try {
//            Geocoder geocoder = new Geocoder(PlacePicker.this, Locale.getDefault());
//            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
//            String address = addresses.get(0).getAddressLine(0);
//
//
//
//            textView_location.setText(address);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//
//    }
//
//    @Override
//    public void onProviderEnabled(@NonNull String provider) {
//
//    }
//
//    @Override
//    public void onProviderDisabled(@NonNull String provider) {
//
//    }
//
//    private double distance(double lat1, double lon1, double lat2, double lon2) {
//        double theta = lon1 - lon2;
//        double dist = Math.sin(deg2rad(lat1))
//                * Math.sin(deg2rad(lat2))
//                + Math.cos(deg2rad(lat1))
//                * Math.cos(deg2rad(lat2))
//                * Math.cos(deg2rad(theta));
//        dist = Math.acos(dist);
//        dist = rad2deg(dist);
//        dist = dist * 60 * 1.1515;
//        return (dist);
//    }
//
//    private double deg2rad(double deg) {
//        return (deg * Math.PI / 180.0);
//    }
//
//    private double rad2deg(double rad) {
//        return (rad * 180.0 / Math.PI);
//    }
//}