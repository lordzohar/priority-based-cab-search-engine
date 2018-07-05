package com.fw.olauberintegration.api;

import android.support.annotation.NonNull;

import com.fw.olauberintegration.api.request.base.Request;
import com.fw.olauberintegration.api.request.olarideestimate.OlaRideEstimateRequest;
import com.fw.olauberintegration.api.request.olarideestimate.OlaRideEstimateRequestData;
import com.fw.olauberintegration.api.request.olarideestimate.OlaRideEstimateResponseData;
import com.fw.olauberintegration.api.request.uberrideestimate.UberRideEstimateRequest;
import com.fw.olauberintegration.api.request.uberrideestimate.UberRideEstimateRequestData;
import com.fw.olauberintegration.api.request.uberrideestimate.UberRideEstimateResponseData;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by kaustubh on 18/4/17.
 */
public class ApiRequestHandler {

    public static OlaRideEstimateRequest fetchAllOlaRideEstimate(LatLng pickLatLng, LatLng dropLatLng, String categories, @NonNull Request.RequestDelegate<OlaRideEstimateResponseData> requestDelegate){

        OlaRideEstimateRequestData olaRideEstimateRequestData = new OlaRideEstimateRequestData();
        olaRideEstimateRequestData.setPickup_lat(String.valueOf(pickLatLng.latitude));
        olaRideEstimateRequestData.setPickup_lng(String.valueOf(pickLatLng.longitude));
        olaRideEstimateRequestData.setDrop_lat(String.valueOf(dropLatLng.latitude));
        olaRideEstimateRequestData.setDrop_lng(String.valueOf(dropLatLng.longitude));
        olaRideEstimateRequestData.setCategories(categories);

        OlaRideEstimateRequest request = new OlaRideEstimateRequest(olaRideEstimateRequestData, requestDelegate);
        request.execute();

        return request;
    }

    public static UberRideEstimateRequest fetchAllUberRideEstimate(LatLng pickLatLng, LatLng dropLatLng, @NonNull Request.RequestDelegate<UberRideEstimateResponseData> requestDelegate){

        UberRideEstimateRequestData uberRideEstimateRequestData = new UberRideEstimateRequestData();
        uberRideEstimateRequestData.setStart_latitude(String.valueOf(pickLatLng.latitude));
        uberRideEstimateRequestData.setStart_longitude(String.valueOf(pickLatLng.longitude));
        uberRideEstimateRequestData.setEnd_latitude(String.valueOf(dropLatLng.latitude));
        uberRideEstimateRequestData.setEnd_longitude(String.valueOf(dropLatLng.longitude));

        UberRideEstimateRequest request = new UberRideEstimateRequest(uberRideEstimateRequestData, requestDelegate);
        request.execute();

        return request;
    }

}
