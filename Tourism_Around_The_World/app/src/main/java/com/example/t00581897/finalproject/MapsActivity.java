package com.example.t00581897.finalproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;

    static int area;

    private static final int LOCATION_REQUEST = 500;

    ArrayList<LatLng> listPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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
/*
    @Override
    public void getData(int data) {
        area = data;
    }
*/


    @Override
    public void onMapReady (GoogleMap googleMap) {
        mMap = googleMap;

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.mapstyle));

            if (!success) {
                Log.e("MapsActivity", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("MapsActivity", "Can't find style. Error: ", e);
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

            LatLng kili = new LatLng(-3.066439, 37.355026);
            mMap.addMarker(new MarkerOptions().position(kili).title("Marker in Mt. Kilimanjaro"));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(kili)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));        }

        else if(area == 1)

        {
            LatLng kruger = new LatLng(-23.988341, 31.554719);
            mMap.addMarker(new MarkerOptions().position(kruger).title("Marker in Kruger National Park"));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(kruger)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }

        else if(area == 2)

        {
            LatLng victoria = new LatLng(-17.924213, 25.857619);
            mMap.addMarker(new MarkerOptions().position(victoria).title("Marker in Victoria Falls"));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(victoria)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }



        else if(area == 3)

        {
            LatLng giza = new LatLng(29.979434, 31.134577);
            mMap.addMarker(new MarkerOptions().position(giza).title("Marker in The Great Pyramid of Giza"));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(giza)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }

        else if(area == 4)

        {
            LatLng sos = new LatLng(-24.722205, 15.343378);
            mMap.addMarker(new MarkerOptions().position(sos).title("Marker in Sossusvlei"));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(sos)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }

        else if(area == 5)

        {
            LatLng garden = new LatLng(31.641764, -8.002840);
            mMap.addMarker(new MarkerOptions().position(garden).title("Marker in Majorelle Garden"));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(garden)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }

        else if(area == 6)

        {
            LatLng diani = new LatLng(-4.279717, 39.594684);
            mMap.addMarker(new MarkerOptions().position(diani).title("Marker in Diani Beach"));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(diani)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }

        else if(area == 7)

        {
            LatLng waterfront = new LatLng(-33.903621, 18.420528);
            mMap.addMarker(new MarkerOptions().position(waterfront).title("Marker in V and A WaterFront"));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(waterfront)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }

        else if(area == 8)

        {
            LatLng cradle = new LatLng(-25.925385, 27.767381);
            mMap.addMarker(new MarkerOptions().position(cradle).title("Marker in Cradle of Humankind"));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(cradle)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }

        else if(area == 9)

        {
            LatLng archi = new LatLng(-21.789031, 35.452521);
            mMap.addMarker(new MarkerOptions().position(archi).title("Marker in Bazaruto Archipelago"));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(archi)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }

        setupMapUI ();




    }
    private void setupMapUI ()
    {
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setCompassEnabled(true);
        uiSettings.setTiltGesturesEnabled(false);
        uiSettings.setZoomControlsEnabled(true);

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


