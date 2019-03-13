package com.example.t00581897.finalproject;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by T00581897 on 3/31/2018.
 */

public class BackgroundTask extends AsyncTask<String, Void, String> {
    String Result = null;
    StringBuilder sb = new StringBuilder();
    @Override
    protected String doInBackground(String... arg0) {
        String link1 = (String) arg0[0];
        Result = link (link1);
        return Result;
    }

    public String link(String link1)
    {

        try {
            URL url = new URL(link1);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setDoOutput(true);

            OutputStreamWriter wr = new OutputStreamWriter(httpURLConnection.getOutputStream());

            wr.write("");
            wr.flush();
            wr.close();

            String line = null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            while((line = reader.readLine()) != null)
            {
                sb.append(line);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();

    }

    public List<FindPlaces> getplaces(String jsondata, String [] data)
    {
        List<FindPlaces> allplaces = new LinkedList<FindPlaces>();

        try
        {
            JSONObject jsonObject = new JSONObject(jsondata);
            JSONArray jsonArray = jsonObject.optJSONArray("results");
            //token = jsonObject.optString("next_page_token");

            Double lng, lat;

            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jchild = jsonArray.getJSONObject(i);
                FindPlaces findPlaces = new FindPlaces();

                Log.v("location", jchild.toString());
                findPlaces.setId(i);
                findPlaces.setName(jchild.optString(data[1]));
                findPlaces.setVicinity(jchild.optString(data[2]));
                lat = jchild.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
                lng = jchild.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
                findPlaces.setLat(lat);
                findPlaces.setLng(lng);

                findPlaces.setTypes(jchild.optJSONObject(data[5]));
                allplaces.add(findPlaces);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return allplaces;
    }


}
