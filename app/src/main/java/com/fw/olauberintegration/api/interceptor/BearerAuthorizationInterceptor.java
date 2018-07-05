package com.fw.olauberintegration.api.interceptor;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by macmoham on 26/12/16.
 */

public class BearerAuthorizationInterceptor implements Interceptor {

    private String mToken;

    public BearerAuthorizationInterceptor(@NonNull String token) {
        mToken = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String authToken = "Bearer " + mToken;
        Request original = chain.request();

        Request.Builder requestBuilder = original.newBuilder()
                .header("Authorization", authToken)
                .header("Accept", "application/json")
                .method(original.method(), original.body());

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }

}
