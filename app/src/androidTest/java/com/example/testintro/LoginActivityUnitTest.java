package com.example.testintro;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class LoginActivityUnitTest {

    private LoginActivity mActivity;
    private EditText loginInput, passwordInput;
    private TextView errorText;
    private Button loginButton;

    @Before
    public void setUp() {
        mActivity = Robolectric.buildActivity(LoginActivity.class).create().get();
        Assert.assertNotNull("mActivity shouldn't be null", mActivity);
        loginInput = (EditText) mActivity.findViewById(R.id.test_intro_login_name);
        passwordInput = (EditText) mActivity.findViewById(R.id.test_intro_login_password);
        errorText = (TextView) mActivity.findViewById(R.id.test_intro_login_error);
        loginButton = (Button) mActivity.findViewById(R.id.test_intro_login_button);
        Assert.assertNotNull("login input shouldn't be null", loginInput);
        Assert.assertNotNull("password input shouldn't be null", passwordInput);
        Assert.assertNotNull("error text shouldn't be null", errorText);
        Assert.assertNotNull("login button shouldn't be null", loginButton);
    }

    @Test
    public void testInvalidCredentials() {
        loginInput.setText(Stub.EMTPY);
        passwordInput.setText(Stub.EMTPY);
        loginButton.performClick();
        Assert.assertEquals(
                "error message should became visible with empty credentials",
                View.VISIBLE, errorText.getVisibility());
    }
}
