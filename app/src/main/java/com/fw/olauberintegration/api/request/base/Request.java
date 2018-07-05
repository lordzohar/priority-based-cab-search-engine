package com.fw.olauberintegration.api.request.base;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;


public abstract class Request<T> {

    public interface RequestDelegate<R> {

        void onSuccess(R result);
        void onError(ErrorResponseData errorResponse);
        void onFailed(Throwable t);

    }

    public Call<T> getCall() {
        return mCall;
    }

    protected Call<T> mCall;
    protected RequestDelegate mRequestDelegate;

    public abstract void execute();

    public void cancel() {
        if (mCall != null && !mCall.isCanceled()) {
            mCall.cancel();
        }
    }

    protected ErrorResponseData getErrorResponse(ResponseBody responseBody, int httpResponseCode) {
        try {
            // TODO : based on responseCode set message over here else parse the response body
            // TODO : handle 404 response gives crash (for result being blank)
            String result = responseBody.string();
            ErrorResponseData errorResponse = new Gson().fromJson(result, ErrorResponseData.class);
            errorResponse.setRawResponse(result);
            return errorResponse;
        } catch (IOException e) {
            Log.e("Response Error",""+e);
            return new ErrorResponseData();
        }
    }


}
