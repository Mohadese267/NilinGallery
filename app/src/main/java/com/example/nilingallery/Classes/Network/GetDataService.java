package com.example.nilingallery.Classes.Network;

import com.example.nilingallery.Classes.Model.Base.Album;
import com.example.nilingallery.Classes.Model.Base.Photo;
import com.example.nilingallery.Classes.Model.Base.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDataService {

    @GET("users")
    Call<ArrayList<User>> getUsers();


    @GET("users/{id}/albums")
    Call<ArrayList<Album>> getAlbums(@Path("id") int userId);


    @GET("albums/{id}/photos")
    Call<ArrayList<Photo>> getPhotos(@Path("id") int albumId);


}
