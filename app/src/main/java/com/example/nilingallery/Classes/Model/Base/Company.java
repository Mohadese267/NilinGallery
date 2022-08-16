package com.example.nilingallery.Classes.Model.Base;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Company {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("catchPhrase")
    @Expose
    public String catchPhrase;
    @SerializedName("bs")
    @Expose
    public String bs;

    /**
     * No args constructor for use in serialization
     *
     */
    public Company() {
    }

    /**
     *
     * @param bs
     * @param catchPhrase
     * @param name
     */
    public Company(String name, String catchPhrase, String bs) {
        super();
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

}