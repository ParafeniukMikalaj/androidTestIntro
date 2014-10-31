package com.example.testintro;

import junit.framework.Assert;

import org.junit.Test;

public class CredentialsTest {

    @Test
    public void testCredentialsIsValid() {
        Credentials credentials = new Credentials(Stub.NAME, Stub.PASSWORD);
        Assert.assertTrue("not empty credentials should be valid", credentials.isValid());
        credentials = new Credentials(Stub.NAME, Stub.EMTPY);
        Assert.assertFalse("credentials with empty password shouldn't be valid", credentials.isValid());
        credentials = new Credentials(Stub.EMTPY, Stub.PASSWORD);
        Assert.assertFalse("credentials with empty name shouldn't be valid", credentials.isValid());
    }

}
