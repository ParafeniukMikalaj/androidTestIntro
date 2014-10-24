package com.example.testintro;

public class Credentials {

    private String mLogin;
    private String mPassword;

    public Credentials(String login, String password) {
        mLogin = login;
        mPassword = password;
    }

    public boolean isValid() {
        return !isEmpty(mLogin) && !isEmpty(mPassword);
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().length() == 0;
    }

}
