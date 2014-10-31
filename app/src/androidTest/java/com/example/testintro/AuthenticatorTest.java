package com.example.testintro;

import android.os.Bundle;
import android.text.TextUtils;

import junit.framework.Assert;
import junit.framework.TestCase;

public class AuthenticatorTest extends TestCase {

    public void testValidCredentials() {
        Credentials credentials = new Credentials(Stub.NAME, Stub.PASSWORD);
        Bundle bundle = new Authenticator().authorize(credentials);
        Assert.assertNotNull("result shouldn't be null", bundle);
        Assert.assertFalse("token shouldn't be empty", isTokenEmpty(bundle));
    }

    public void testInvalidCredentials() {
        Credentials credentials = new Credentials(Stub.EMTPY, Stub.EMTPY);
        Bundle bundle = new Authenticator().authorize(credentials);
        Assert.assertNotNull("result shouldn't be null", bundle);
        Assert.assertFalse("error shouldn't be empty", isErrorEmpty(bundle));
    }

    private boolean isTokenEmpty(Bundle bundle) {
        return TextUtils.isEmpty(bundle.getString(Authenticator.ResultKey.TOKEN).trim());
    }

    private boolean isErrorEmpty(Bundle bundle) {
        return TextUtils.isEmpty(bundle.getString(Authenticator.ResultKey.ERROR_MESSAGE).trim());
    }

}
