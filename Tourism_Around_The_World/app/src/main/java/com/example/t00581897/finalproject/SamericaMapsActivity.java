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

public class SamericaMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    static int area;

    private static final int LOCATION_REQUEST = 500;

    ArrayList<LatLng> listPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samerica_maps);
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
                Log.e("SamericaMapsActivity", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("SamericaMapsActivity", "Can't find style. Error: ", e);
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

            LatLng copa = new LatLng(-22.969765, -43.186860);
            mMap.addMarker(new MarkerOptions().position(copa).title("Marker in Copacabana"));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(copa)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));        }

        else if(area == 1)

        {
            LatLng machu = new LatLng(-13.163140, -72.544965);
            mMap.addMarker(new MarkerOptions().position(machu).title("Marker in Machu Picchu"));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(machu)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }

        else if(area == 2)

        {
            LatLng iguazu = new LatLng(-25.695262, -54.436667);
            mMap.addMarker(new MarkerOptions().position(iguazu).title("Marker in Iguazu Falls"));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(iguazu)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }



        else if(area == 3)

        {
            LatLng christ = new LatLng(-22.951917, -43.210487);
            mMap.addMarker(new MarkerOptions().position(christ).title("Marker in  Christ the Redeemer"));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(christ)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }

        else if(area == 4)

        {
            LatLng gold = new LatLng(4.601894, -74.072040);
            mMap.addMarker(new MarkerOptions().position(gold).title("Marker in Gold Museum"));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(gold)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }

        else if(area == 5)

        {
            LatLng lencois = new LatLng(-2.485929, -43.128407);
            mMap.addMarker(new MarkerOptions().position(lencois).title("Marker in Lençóis Maranhenses National Park"));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(lencois)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }

        else if(area == 6)

        {
            LatLng sugarloaf = new LatLng(50.211319, -119.506340);
            mMap.addMarker(new MarkerOptions().position(sugarloaf).title("Marker in SugarLoaf Mountain"));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(sugarloaf)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }

        else if(area == 7)

        {
            LatLng castillo = new LatLng(10.422507, -75.539272);
            mMap.addMarker(new MarkerOptions().position(castillo).title("Marker in Castillo San Felipe de Barajas"));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(castillo)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }

        else if(area == 8)

        {
            LatLng monserrate = new LatLng(4.605679, -74.055526);
            mMap.addMarker(new MarkerOptions().position(monserrate).title("Marker in Monserrate"));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(monserrate)
                    .zoom(15)
                    .bearing(90)
                    .tilt(60)
                    .build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }

        else if(area == 9)

        {
            LatLng torres = new LatLng(-50.942326, -73.406788);
            mMap.addMarker(new MarkerOptions().position(torres).title("Marker in Torres del Paine National Park"));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(torres)
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
