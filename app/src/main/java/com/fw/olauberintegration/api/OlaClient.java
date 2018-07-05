package com.fw.olauberintegration.api;

import com.fw.olauberintegration.BuildConfig;
import com.fw.olauberintegration.api.interceptor.BasicAuthorizationInterceptor;
import com.fw.olauberintegration.api.interceptor.BearerAuthorizationInterceptor;
import com.fw.olauberintegration.api.interceptor.OlaTokenAuthorizationInterceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kaustubh on 18/4/17.
 */
public class OlaClient {

    private static final String API_BASE_URL = "https://sandbox-t1.olacabs.com/v1/";
    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

    private static Retrofit.Builder builder =
        new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit getRetrofit(Interceptor interceptor) {
        Builder httpClientBuilder = new Builder();

        if (interceptor != null) {
            httpClientBuilder.addInterceptor(interceptor);
        }

        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(logging);
        }

        Retrofit retrofit = builder
                .client(httpClientBuilder
                        .build())
                .build();

        return retrofit;
    }

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = getRetrofit(null);
        return retrofit.create(serviceClass);
    }

    public static <S> S createBasicAppToken(Class<S> serviceClass) {
        OlaTokenAuthorizationInterceptor olaAuthInterceptor = new OlaTokenAuthorizationInterceptor();
        Retrofit retrofit = getRetrofit(olaAuthInterceptor);
        return retrofit.create(serviceClass);
    }

}
