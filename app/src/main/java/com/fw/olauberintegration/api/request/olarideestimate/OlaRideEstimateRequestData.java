package com.fw.olauberintegration.api.request.olarideestimate;

import java.util.HashMap;
import java.util.Map;


public class OlaRideEstimateRequestData {

    private String pickup_lat;
    private String pickup_lng;
    private String drop_lat;
    private String drop_lng;
    private String categories;

    public String getPickup_lat() {
        return pickup_lat;
    }

    public void setPickup_lat(String pickup_lat) {
        this.pickup_lat = pickup_lat;
    }

    public String getPickup_lng() {
        return pickup_lng;
    }

    public void setPickup_lng(String pickup_lng) {
        this.pickup_lng = pickup_lng;
    }

    public String getDrop_lat() {
        return drop_lat;
    }

    public void setDrop_lat(String drop_lat) {
        this.drop_lat = drop_lat;
    }

    public String getDrop_lng() {
        return drop_lng;
    }

    public void setDrop_lng(String drop_lng) {
        this.drop_lng = drop_lng;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Map<String, String> getQueryMap() {
        HashMap<String, String> queryMap = new HashMap<>();

        queryMap.put("pickup_lat", String.valueOf(pickup_lat));
        queryMap.put("pickup_lng", String.valueOf(pickup_lng));
        queryMap.put("drop_lat", String.valueOf(drop_lat));
        queryMap.put("drop_lng", String.valueOf(drop_lng));
        queryMap.put("categories", String.valueOf(categories));

        return queryMap;
    }
}
