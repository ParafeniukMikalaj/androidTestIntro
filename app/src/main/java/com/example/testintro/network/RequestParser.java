package com.example.testintro.network;

import org.apache.http.HttpStatus;

public class RequestParser {

    private boolean mIsSuccess;

    public RequestParser(RequestPerformer requestPerformer) {
        int statusCode = requestPerformer.getStatusCode();
        if (statusCode == HttpStatus.SC_OK) {
            mIsSuccess = true;
        }
    }

    public boolean isSuccess() {
        return mIsSuccess;
    }
}
