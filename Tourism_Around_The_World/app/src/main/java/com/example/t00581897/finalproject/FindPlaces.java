package com.example.t00581897.finalproject;

import org.json.JSONObject;

/**
 * Created by T00581897 on 3/31/2018.
 */

public class FindPlaces {

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JSONObject getTypes() {
        return types;
    }

    public void setTypes(JSONObject types) {
        this.types = types;
    }

    public JSONObject getLoc() {
        return loc;
    }

    public void setLoc(JSONObject loc) {
        this.loc = loc;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int id;
    public String name;
    public String vicinity;
    public String token;
    public JSONObject types;
    private JSONObject loc;
    private Double lat;
    private Double lng;
}
