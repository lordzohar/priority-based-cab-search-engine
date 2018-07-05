package com.fw.olauberintegration.api.request.uberrideestimate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kaustubh on 18/4/17.
 */
public class UberRideEstimateRequestData {

    private String start_latitude;
    private String start_longitude;
    private String end_latitude;
    private String end_longitude;

    public String getStart_latitude() {
        return start_latitude;
    }

    public void setStart_latitude(String start_latitude) {
        this.start_latitude = start_latitude;
    }

    public String getStart_longitude() {
        return start_longitude;
    }

    public void setStart_longitude(String start_longitude) {
        this.start_longitude = start_longitude;
    }

    public String getEnd_latitude() {
        return end_latitude;
    }

    public void setEnd_latitude(String end_latitude) {
        this.end_latitude = end_latitude;
    }

    public String getEnd_longitude() {
        return end_longitude;
    }

    public void setEnd_longitude(String end_longitude) {
        this.end_longitude = end_longitude;
    }

    public Map<String, String> getQueryMap() {
        HashMap<String, String> queryMap = new HashMap<>();

        queryMap.put("start_latitude", String.valueOf(start_latitude));
        queryMap.put("start_longitude", String.valueOf(start_longitude));
        queryMap.put("end_latitude", String.valueOf(end_latitude));
        queryMap.put("end_longitude", String.valueOf(end_longitude));

        return queryMap;
    }
}
