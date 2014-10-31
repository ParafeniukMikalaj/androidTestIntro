package com.example.testintro;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CredentialsTest {

    @Test
    public void testCredentialsIsValid() {
        Credentials credentials = new Credentials(Stub.NAME, Stub.PASSWORD);
        assertThat("not empty credentials should be valid", credentials.isValid(), equalTo(true));
        credentials = new Credentials(Stub.NAME, Stub.EMTPY);
        assertThat("credentials with empty password shouldn't be valid", credentials.isValid(), equalTo(false));
        credentials = new Credentials(Stub.EMTPY, Stub.PASSWORD);
        assertThat("credentials with empty name shouldn't be valid", credentials.isValid(), equalTo(false));
    }

}
