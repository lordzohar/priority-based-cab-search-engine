package com.fw.olauberintegration.api.request.olarideestimate;

import com.fw.olauberintegration.model.OlaRideEstimate;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OlaRideEstimateResponseData {

    @SerializedName("ride_estimate")
    private List<OlaRideEstimate> rideEstimateList;

    public List<OlaRideEstimate> getRideEstimateList() {
        return rideEstimateList;
    }

    public void setRideEstimateList(List<OlaRideEstimate> rideEstimateList) {
        this.rideEstimateList = rideEstimateList;
    }

}
