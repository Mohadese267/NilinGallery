package com.example.nilingallery.Classes.Model.Base;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geo {

    @SerializedName("lat")
    @Expose
    public String lat;
    @SerializedName("lng")
    @Expose
    public String lng;

    /**
     * No args constructor for use in serialization
     *
     */
    public Geo() {
    }

    /**
     *
     * @param lng
     * @param lat
     */
    public Geo(String lat, String lng) {
        super();
        this.lat = lat;
        this.lng = lng;
    }

}