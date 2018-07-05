package com.fw.olauberintegration.api.request.uberrideestimate;

import android.support.annotation.NonNull;

import com.fw.olauberintegration.api.UberClient;
import com.fw.olauberintegration.api.request.base.ErrorResponseData;
import com.fw.olauberintegration.api.request.base.Request;
import com.fw.olauberintegration.api.resources.UberServiceAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UberRideEstimateRequest extends Request<UberRideEstimateResponseData> {

    private UberRideEstimateRequestData mRideEstimateRequest;

    public UberRideEstimateRequest(@NonNull UberRideEstimateRequestData uberRideEstimateRequestData, @NonNull RequestDelegate requestDelegate) {
        mRideEstimateRequest = uberRideEstimateRequestData;
        mRequestDelegate = requestDelegate;
    }

    @Override
    public void execute() {
        UberServiceAPI uberServiceAPI = UberClient.createBasicAppToken(UberServiceAPI.class);

        mCall = uberServiceAPI.getRideEstimate(mRideEstimateRequest.getQueryMap());
        mCall.enqueue(new Callback<UberRideEstimateResponseData>() {
            @Override
            public void onResponse(Call<UberRideEstimateResponseData> call, Response<UberRideEstimateResponseData> response) {
                if (response.isSuccessful()) {
                    mRequestDelegate.onSuccess(response.body());
                } else {
                    ErrorResponseData errorResponseData = getErrorResponse(response.errorBody(), response.code());
                    mRequestDelegate.onError(errorResponseData);
                }
            }

            @Override
            public void onFailure(Call<UberRideEstimateResponseData> call, Throwable t) {
                mRequestDelegate.onFailed(t);
            }
        });
    }

}
