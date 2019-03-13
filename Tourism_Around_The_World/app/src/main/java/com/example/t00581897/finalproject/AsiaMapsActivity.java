package com.example.t00581897.finalproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AsiaMapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;

    Location location;

    String placename = "New Delhi";

    String ptype = "atm";
    int pradius = 1500;
    Marker marker = null;

    private static final int LOCATION_REQUEST = 500;

    ArrayList<LatLng> listPoints;

    String pkey = "AIzaSyDfyrSIwPRbrxqMEVlIrXFhWHmDPSJ2PVg";
    GoogleApiClient client;
    LocationRequest request;


    static int area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asia_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();

        int data = intent.getExtras().getInt("intData", 0);

        area = data;

        listPoints = new ArrayList<>();
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

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.mapstyle));

            if (!success) {
                Log.e("AsiaMapsActivity", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("AsiaMapsActivity", "Can't find style. Error: ", e);
        }

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (listPoints.size() == 2) {
                    listPoints.clear();
                    mMap.clear();
                }

                listPoints.add(latLng);

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);

                if (listPoints.size() == 1) {
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

                } else {

                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

                }

                mMap.addMarker(markerOptions);
                //TODO: request get direction code below

                if (listPoints.size() == 2) {
                    String url = getRequestUrl(listPoints.get(0), listPoints.get(1));

                    TaskRequestDirections taskRequestDirections = new TaskRequestDirections();

                    taskRequestDirections.execute(url);
                }

            }


        });

        if (area == 0) {

            LatLng taj = new LatLng(27.175016, 78.042154);
            mMap.addMarker(new MarkerOptions().position(taj).title("Marker in Taj Mahal"));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(taj)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();

            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        } else if (area == 1)

        {
            LatLng angkor = new LatLng(13.412470, 103.866984);
            mMap.addMarker(new MarkerOptions().position(angkor).title("Marker in Angkor Wat"));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(angkor)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        } else if (area == 2)

        {
            LatLng burj = new LatLng(25.197205, 55.274377);
            mMap.addMarker(new MarkerOptions().position(burj).title("Marker in Burj Khalifa"));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(burj)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        } else if (area == 3)

        {
            LatLng forbid = new LatLng(39.916349, 116.397151);
            mMap.addMarker(new MarkerOptions().position(forbid).title("Marker in Forbidden City"));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(forbid)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        } else if (area == 4)

        {
            LatLng tanah = new LatLng(-8.621208, 115.086808);
            mMap.addMarker(new MarkerOptions().position(tanah).title("Marker in Tanah Lot"));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(tanah)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        } else if (area == 5)

        {
            LatLng red = new LatLng(28.656158, 77.241019);
            mMap.addMarker(new MarkerOptions().position(red).title("Marker in The Red Fort"));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(red)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));




        } else if (area == 6)

        {
            LatLng grand = new LatLng(13.750031, 100.491288);
            mMap.addMarker(new MarkerOptions().position(grand).title("Marker in Grand Palace"));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(grand)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));



        } else if (area == 7)

        {
            LatLng tokyo = new LatLng(35.632900, 139.880387);
            mMap.addMarker(new MarkerOptions().position(tokyo).title("Marker in Tokyo Disneyland"));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(tokyo)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        } else if (area == 8)

        {
            LatLng fushi = new LatLng(34.967142, 135.772672);
            mMap.addMarker(new MarkerOptions().position(fushi).title("Marker in Fushimi Inari-Taisha"));


            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(fushi)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));



        } else if (area == 9)

        {
            LatLng great = new LatLng(40.431915, 116.570375);
            mMap.addMarker(new MarkerOptions().position(great).title("Marker in The Great Wall of China"));


            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(great)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        }
        setupMapUI();

    }




    private String getRequestUrl (LatLng origin, LatLng destination)
    {
        String str_org = "origin=" + origin.latitude + ", "+origin.longitude;

        String str_dest = "destination=" + destination.latitude+ ", " + destination.longitude;

        String sensor = "sensor=false";

        String mode = "mode=driving";

        String param = str_org + "&" + str_dest + "&" + sensor + "&" +mode;
        String output = "json";

        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + param;

        return url;
    }

    private String requestDirection (String reqUrl) {
        String responseString = "";
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;

        try {
            URL url = new URL(reqUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuffer stringBuffer = new StringBuffer();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }

            responseString = stringBuffer.toString();
            bufferedReader.close();
            inputStreamReader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            httpURLConnection.disconnect();


        }
        return responseString;
    }











    private void setupMapUI ()
    {
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setCompassEnabled(true);
        uiSettings.setTiltGesturesEnabled(false);
        uiSettings.setZoomControlsEnabled(true);

    }







    @Override
    public void onLocationChanged(Location location)
    {

        if(location == null)
        {
            Toast.makeText(getApplicationContext(), "Location not found", Toast.LENGTH_SHORT).show();

        }

        else
        {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, 15);
            mMap.animateCamera(update);

            MarkerOptions options = new MarkerOptions();
            options.position(latLng);
            options.title("Current Location");
            mMap.addMarker(options);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        request = new LocationRequest().create();
        request.setInterval(1000);
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(client, request, this);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @SuppressLint("MissingPermission")
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int [] grantResults)
    {
        switch (requestCode)
        {
            case LOCATION_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    mMap.setMyLocationEnabled(true);
                }

                break;
        }

    }

    public class TaskRequestDirections extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String responseString = "";
            responseString = requestDirection(strings[0]);
            return  responseString;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Parse json here
            TaskParser taskParser = new TaskParser();
            taskParser.execute(s);
        }
    }

    public class TaskParser extends AsyncTask<String, Void, List<List<HashMap<String, String>>> > {

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... strings) {
            JSONObject jsonObject = null;
            List<List<HashMap<String, String>>> routes = null;
            try {
                jsonObject = new JSONObject(strings[0]);
                DirectionsParser directionsParser = new DirectionsParser();
                routes = directionsParser.parse(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> lists) {
            //Get list route and display it into the map

            ArrayList points = null;

            PolylineOptions polylineOptions = null;

            for (List<HashMap<String, String>> path : lists) {
                points = new ArrayList();
                polylineOptions = new PolylineOptions();

                for (HashMap<String, String> point : path) {
                    double lat = Double.parseDouble(point.get("lat"));
                    double lon = Double.parseDouble(point.get("lon"));

                    points.add(new LatLng(lat,lon));
                }

                polylineOptions.addAll(points);
                polylineOptions.width(15);
                polylineOptions.color(Color.BLUE);
                polylineOptions.geodesic(true);
            }

            if (polylineOptions!=null) {
                mMap.addPolyline(polylineOptions);
            } else {
                Toast.makeText(getApplicationContext(), "Direction not found!", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
