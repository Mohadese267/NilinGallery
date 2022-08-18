package com.example.nilingallery.Classes.Network;

import android.content.Context;

public class WebServiceHelper {
    public static GetDataService service;


    public WebServiceHelper(Context context) {
        service = RetrofitClientInstance.getRetrofitInstance(context).create(GetDataService.class);

    }




}

