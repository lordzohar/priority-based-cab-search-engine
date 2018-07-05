package com.fw.olauberintegration.api.resources;

import com.fw.olauberintegration.api.request.uberrideestimate.UberRideEstimateResponseData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by kaustubh on 18/4/17.
 */
public interface UberServiceAPI {

    @GET("estimates/price")
    Call<UberRideEstimateResponseData> getRideEstimate(@QueryMap Map<String, String> queryData);

}
