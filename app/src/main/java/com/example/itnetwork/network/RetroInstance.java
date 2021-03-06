package com.example.itnetwork.network;

import static com.example.itnetwork.utils.Credentials.BASE_URL;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroInstance {

    private static Retrofit retrofit;
    private static ITNetworkApiInterface AppApi;

    public static Retrofit getRetroInstance() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if(retrofit == null) {
            retrofit =  new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ITNetworkApiInterface getAppApiInterface(){
        if(AppApi == null){
            AppApi = getRetroInstance().create(ITNetworkApiInterface.class);
        }
        return AppApi;
    }
}
