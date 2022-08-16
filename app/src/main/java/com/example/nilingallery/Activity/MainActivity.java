package com.example.nilingallery.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.nilingallery.Classes.Network.WebServiceHelper;
import com.example.nilingallery.R;
import com.example.nilingallery.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    public static WebServiceHelper webServiceHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webServiceHelper =new WebServiceHelper(getApplicationContext());
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
    }
}