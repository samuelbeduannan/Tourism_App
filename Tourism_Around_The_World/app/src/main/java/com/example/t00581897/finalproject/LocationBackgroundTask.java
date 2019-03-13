package com.example.t00581897.finalproject;

import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by T00581897 on 4/1/2018.
 */

public class LocationBackgroundTask  extends AsyncTask<String, Void, String>{

    String Result;
    @Override
    protected String doInBackground(String... arg0) {
        String link1 = (String) arg0[0];
        Result = link( link1);

    return null;
    }


    public String link(String url)
    {
        String result = "";
        try {
            URL urls = new URL(url.replace(" ","%20"));
            HttpURLConnection conn = (HttpURLConnection) urls.openConnection();
            conn.setReadTimeout(150000)   ; //milliseconds
            conn.setConnectTimeout(15000) ; // milliseconds
            conn.setRequestMethod("POST") ;
            conn.connect();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        conn.getInputStream(), "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                result = sb.toString();
            } else {
                return "error";
            }
        } catch (Exception e) {
            // System.out.println("exception in jsonparser class ........");
            e.printStackTrace();
            return "error";
        }
        return result;
    }


    public Location getlocation(String jsondata) {
        Location loc= new Location("network");
        try {
            JSONObject jobject = new JSONObject(jsondata);
            JSONArray jmain = jobject.optJSONArray("results");
            Double lng,lat;
            for (int i = 0; i < jmain.length(); i++)
            {
                JSONObject jchild = jmain.getJSONObject(i);
                //   Address address = new Address(Locale.getDefault());
                Log.v("location",jchild.toString());
                lat = jchild.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
                lng = jchild.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
                loc.setLongitude(lng);
                loc.setLatitude(lat);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return loc;
    }

    }




