package com.fw.olauberintegration.api.resources;

import com.fw.olauberintegration.api.request.olarideestimate.OlaRideEstimateResponseData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by kaustubh on 18/4/17.
 */
public interface OlaServiceAPI {

    @GET("products")
    Call<OlaRideEstimateResponseData> getRideEstimate(@QueryMap Map<String, String> queryData);

}
