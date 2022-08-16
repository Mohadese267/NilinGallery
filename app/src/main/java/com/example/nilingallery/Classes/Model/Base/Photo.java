package com.example.nilingallery.Classes.Model.Base;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("albumId")
    @Expose
    public Integer albumId;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("thumbnailUrl")
    @Expose
    public String thumbnailUrl;

    /**
     * No args constructor for use in serialization
     *
     */
    public Photo() {
    }

    /**
     *
     * @param albumId
     * @param id
     * @param title
     * @param url
     * @param thumbnailUrl
     */
    public Photo(Integer albumId, Integer id, String title, String url, String thumbnailUrl) {
        super();
        this.albumId = albumId;
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

}