package com.example.testintro;

import android.os.Bundle;

public class Authenticator {

    public class ResultKey {
        public static final String TOKEN = "token";
        public static final String ERROR_MESSAGE = "error";
    }

    public class ErrorMessage {
        public static final String INVALID_CREDENTIALS = "invalid_credentials";
    }

    private static final String STUB_TOKEN = "12345678123456781234567812345678";

    public Bundle authorize(Credentials credentials) {
        Bundle result = new Bundle();
        if (credentials.isValid()) {
            result.putString(ResultKey.TOKEN, STUB_TOKEN);
        } else {
            result.putString(ResultKey.ERROR_MESSAGE, ErrorMessage.INVALID_CREDENTIALS);
        }
        return result;
    }


}
