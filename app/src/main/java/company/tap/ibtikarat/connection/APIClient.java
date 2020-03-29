package company.tap.ibtikarat.connection;

import java.util.concurrent.TimeUnit;

import company.tap.ibtikarat.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AhlaamK on 3/29/20.
 * <p>
 * Copyright (c) 2020    Tap Payments.
 * All rights reserved.
 **/
public class APIClient {

    private static Retrofit retrofit = null;
    public static Retrofit getClient() {
        if (retrofit==null) {
            OkHttpClient okHttpClient = getOkHttpClient();
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        httpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
        // add application interceptor to httpClientBuilder
        httpClientBuilder.addInterceptor(chain -> {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader(API_Constants.AUTH_TOKEN_KEY, API_Constants.AUTH_TOKEN_PREFIX + AppInfo.getAuthToken())
                    .addHeader(API_Constants.APPLICATION, AppInfo.getApplicationInfo())
                    .addHeader(API_Constants.ACCEPT_KEY,API_Constants.ACCEPT_VALUE)
                    .addHeader(API_Constants.CONTENT_TYPE_KEY, API_Constants.CONTENT_TYPE_VALUE).build();
            return chain.proceed(request);
        });
        httpClientBuilder.addInterceptor(new HttpLoggingInterceptor().setLevel(!BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.BODY));

        return httpClientBuilder.build();
    }
}