package com.fw.olauberintegration.api.request.base;




import com.google.gson.annotations.Expose;

import java.util.ArrayList;

/**
 * Created by macmoham on 26/12/16.
 */

public class ErrorResponseData extends ResponseData {

    @Expose(serialize = false, deserialize = false)
    private String rawResponse;

    public ErrorResponseData() {
        setSuccess(false);
        setErrorMessages(new ArrayList<String>() {{
            add("Failed to load response");

        }
    });

    }

    public String getRawResponse() {
        return rawResponse;
    }

    public void setRawResponse(String rawResponse) {
        this.rawResponse = rawResponse;
    }
}
