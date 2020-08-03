package com.brunner.social;

import lombok.Data;

@Data
public class RestResponse<T> {

    public static final int STATUS_SUCCESS_OK = 200;
    public static final int STATUS_NOT_AVAILABLE = -1;
    private boolean success;
    private int statusCode;
    private T data;
    private String error;

    private RestResponse() {
    }

    public static <T> RestResponse<T> createSuccessResponse(T data) {
        RestResponse<T> restResponse = new RestResponse();
        restResponse.data = data;
        restResponse.success = true;
        restResponse.statusCode = 200;
        return restResponse;
    }

    public static RestResponse createFailureResponse(String errorMessage, int statusCode) {
        RestResponse restResponse = new RestResponse();
        restResponse.error = errorMessage;
        restResponse.success = false;
        restResponse.statusCode = statusCode;
        return restResponse;
    }
}
