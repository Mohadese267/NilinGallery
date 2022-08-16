package com.example.nilingallery.Classes.Model.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;


public class Helper {
    public static final String MY_PREFS_NAME = "NilinGalleryPreferences";
    private SharedPreferences preferences;
    public static SharedPreferences.Editor editor;
    private Context context;

    public Helper(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        editor = preferences.edit();
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

    public String getEmail() {
        return preferences.getString("email", "empty");
    }

    public void setEmail(String pass) {
        editor.putString("email", pass).apply();
    }
}
