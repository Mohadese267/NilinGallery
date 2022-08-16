package com.example.nilingallery.Classes.Network;

import android.os.SystemClock;

import java.io.IOException;

import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";


    public static Retrofit getRetrofitInstance() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(1);

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                SystemClock.sleep(0);
                return chain.proceed(chain.request());
            }
        };
        httpClient.addNetworkInterceptor(interceptor);
        httpClient.dispatcher(dispatcher);


        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();




        }
        return retrofit;
    }

}