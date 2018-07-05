package com.fw.olauberintegration.api.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;



public class OlaTokenAuthorizationInterceptor implements Interceptor {

    public OlaTokenAuthorizationInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder requestBuilder = original.newBuilder()
                .header("X-APP-TOKEN", "0798b17db0054b1d9eaff9fcdc018b67")
                .method(original.method(), original.body());

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}