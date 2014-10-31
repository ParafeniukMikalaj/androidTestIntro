package com.example.testintro;

import android.os.Bundle;
import android.text.TextUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class AuthenticatorTest {

    @Test
    public void testValidCredentials() {
        Credentials credentials = new Credentials(Stub.NAME, Stub.PASSWORD);
        Bundle bundle = new Authenticator().authorize(credentials);
        assertThat("result shouldn't be null", bundle, notNullValue());
        assertThat("token shouldn't be empty", isTokenEmpty(bundle), equalTo(false));
    }

    @Test
    public void testInvalidCredentials() {
        Credentials credentials = new Credentials(Stub.EMTPY, Stub.EMTPY);
        Bundle bundle = new Authenticator().authorize(credentials);
        assertThat("result shouldn't be null", bundle, notNullValue());
        assertThat("error shouldn't be empty", isErrorEmpty(bundle), equalTo(false));
    }

    private boolean isTokenEmpty(Bundle bundle) {
        return TextUtils.isEmpty(bundle.getString(Authenticator.ResultKey.TOKEN).trim());
    }

    private boolean isErrorEmpty(Bundle bundle) {
        return TextUtils.isEmpty(bundle.getString(Authenticator.ResultKey.ERROR_MESSAGE).trim());
    }

}
