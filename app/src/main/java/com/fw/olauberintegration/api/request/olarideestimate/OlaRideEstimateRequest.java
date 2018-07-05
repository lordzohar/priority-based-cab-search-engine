package com.fw.olauberintegration.api.request.olarideestimate;

import android.support.annotation.NonNull;

import com.fw.olauberintegration.api.OlaClient;
import com.fw.olauberintegration.api.request.base.ErrorResponseData;
import com.fw.olauberintegration.api.request.base.Request;
import com.fw.olauberintegration.api.resources.OlaServiceAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OlaRideEstimateRequest extends Request<OlaRideEstimateResponseData> {

    private OlaRideEstimateRequestData mRideEstimateRequest;

    public OlaRideEstimateRequest(@NonNull OlaRideEstimateRequestData olaRideEstimateRequestData, @NonNull RequestDelegate requestDelegate) {
        mRideEstimateRequest = olaRideEstimateRequestData;
        mRequestDelegate = requestDelegate;
    }

    @Override
    public void execute() {
        OlaServiceAPI olaServiceAPI = OlaClient.createBasicAppToken(OlaServiceAPI.class);

        mCall = olaServiceAPI.getRideEstimate(mRideEstimateRequest.getQueryMap());
        mCall.enqueue(new Callback<OlaRideEstimateResponseData>() {
            @Override
            public void onResponse(Call<OlaRideEstimateResponseData> call, Response<OlaRideEstimateResponseData> response) {
                if (response.isSuccessful()) mRequestDelegate.onSuccess(response.body());
                else {
                    ErrorResponseData errorResponseData = getErrorResponse(response.errorBody(), response.code());
                    mRequestDelegate.onError(errorResponseData);
                }
            }

            @Override
            public void onFailure(Call<OlaRideEstimateResponseData> call, Throwable t) {
                mRequestDelegate.onFailed(t);
            }
        });
    }

}
