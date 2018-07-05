package com.fw.olauberintegration.api.request.base;

import java.util.ArrayList;



public abstract class ResponseData {

    private boolean isSuccess;
    private String responseDate;
    private ArrayList<String> errorMessages;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
    }

    public ArrayList<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(ArrayList<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public String getErrorMsgString() {
        StringBuilder errorBuilder = new StringBuilder();
        int count = getErrorMessages().size();

        for (int i = 0; i < count; i++) {
            if (i > 0) {
                errorBuilder.append("\n");
            }
            errorBuilder.append(getErrorMessages().get(i));
        }
        return errorBuilder.toString();
    }
}
