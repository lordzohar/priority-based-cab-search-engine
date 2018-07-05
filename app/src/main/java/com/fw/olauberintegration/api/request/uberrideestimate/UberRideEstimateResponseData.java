package com.fw.olauberintegration.api.request.uberrideestimate;

import com.fw.olauberintegration.model.UberRideEstimate;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UberRideEstimateResponseData {

    @SerializedName("prices")
    private List<UberRideEstimate> rideEstimateList;

    public List<UberRideEstimate> getRideEstimateList() {
        return rideEstimateList;
    }

    public void setRideEstimateList(List<UberRideEstimate> rideEstimateList) {
        this.rideEstimateList = rideEstimateList;
    }

}
