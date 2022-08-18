package com.example.nilingallery.Classes.Model.Utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.util.Log;

import java.net.InetAddress;

import static android.content.Context.MODE_PRIVATE;


public class Helper {
    public static final String MY_PREFS_NAME = "NilinGalleryPreferences";
    private final SharedPreferences preferences;
    public static SharedPreferences.Editor editor;
    private final Context context;

    public Helper(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        editor = preferences.edit();
    }
    public boolean isInternetAvailable() {
        if (isNetworkConnected())
            try {
                InetAddress ipAddress = InetAddress.getByName("google.com");
                return !ipAddress.equals("");

            } catch (Exception e) {
                Log.e("helper",e.toString()+"");
                return false;
            }
        else {
            return false;
        }

    }
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }


    public void setUserId(int userId) {
        editor.putInt("user_id", userId).apply();
    }

    public int getUserId() {
        return preferences.getInt("user_id", 0);
    }


    public void setAlbumId(int albumId) {
        editor.putInt("album_id", albumId).apply();
    }

    public int getAlbumId() {
        return preferences.getInt("album_id", 0);
    }

    public void setPhotoUrl(String photoUrl) {
        editor.putString("photo_url", photoUrl).apply();
    }

    public String getPhotoUrl() {
        return preferences.getString("photo_url", "empty");
    }





}
