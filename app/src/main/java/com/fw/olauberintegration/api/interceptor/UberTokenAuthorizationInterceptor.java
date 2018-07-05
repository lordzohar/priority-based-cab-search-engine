package com.fw.olauberintegration.api.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;



public class UberTokenAuthorizationInterceptor implements Interceptor {

    public UberTokenAuthorizationInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder requestBuilder = original.newBuilder()
                .header("Accept-Language","en_US")
                .header("Authorization","Token wNnkNbuMhkmteel8adoDxeTV4msk_bUcptsd6u2d")
                .method(original.method(), original.body());

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}