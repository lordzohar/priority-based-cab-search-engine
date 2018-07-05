package com.fw.olauberintegration.model;

//import org.json.JSONArray;

public class OlaRideEstimate {

    private String category;
    private String distance;
    private String travel_time_in_minutes;
    private String amount_min;
    private String amount_max;
//    private JSONArray fares= new JSONArray();
//    private String shareprice;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTravel_time_in_minutes() {
        return travel_time_in_minutes;
    }

    public void setTravel_time_in_minutes(String travel_time_in_minutes) {
        this.travel_time_in_minutes = travel_time_in_minutes;
    }

    public String getAmount_min() {
        return amount_min;
    }

    public void setAmount_min(String amount_min) {
        this.amount_min = amount_min;
    }

    public String getAmount_max() {
        return amount_max;
    }

    public void setAmount_max(String amount_max) {
        this.amount_max = amount_max;
    }

//    public String getFares()
//    { return this.shareprice=fares.getJSONArray(0); }
}
