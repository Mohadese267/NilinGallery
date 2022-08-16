package com.example.nilingallery.Classes.Model.Base;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Album {

    @SerializedName("userId")
    @Expose
    public Integer userId;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;

    /**
     * No args constructor for use in serialization
     *
     */
    public Album() {
    }

    /**
     *
     * @param id
     * @param title
     * @param userId
     */
    public Album(Integer userId, Integer id, String title) {
        super();
        this.userId = userId;
        this.id = id;
        this.title = title;
    }

}