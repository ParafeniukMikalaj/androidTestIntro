package com.example.testintro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class MainActivityCase2Test {

    private MainActivity mActivity;
    private Button mainButton;

    @Before
    public void setUp() throws Exception {
        mActivity = Robolectric.buildActivity(MainActivity.class).create().get();
        Assert.assertNotNull("activity shouldn't be null", mActivity);
        mainButton = (Button) mActivity.findViewById(R.id.test_intro_main_button);
        Assert.assertNotNull("main button shouldn't be null", mainButton);
    }

    @Test
    public void testSuccessLogin() {
        Intent successResultIntent = new Intent();
        successResultIntent.putExtra(LoginActivity.KEY_AUTH_TOKEN, Stub.TOKEN);
        mActivity.onActivityResult(MainActivity.AUTH_REQUEST_CODE, Activity.RESULT_OK, successResultIntent);
        Assert.assertEquals("token value should be parsed", Stub.TOKEN, mActivity.getToken());
        Assert.assertTrue("should have success status", mActivity.isTokenSuccess());
    }

    @Test
    public void testCancelledLogin() {
        mActivity.onActivityResult(MainActivity.AUTH_REQUEST_CODE, Activity.RESULT_CANCELED, null);
        Assert.assertNull("token value should be empty", mActivity.getToken());
        Assert.assertFalse("should have failure status", mActivity.isTokenSuccess());
    }

    @Test
    public void testSuccessLoginAnswer() {
        mainButton.performClick();
        Intent expectedIntent = new Intent(mActivity, LoginActivity.class);
        Assert.assertEquals("main activity should fire login activity on click",
                expectedIntent, Robolectric.shadowOf(mActivity).getNextStartedActivity());

        LoginActivity startedActivity
                = Robolectric.buildActivity(LoginActivity.class).withIntent(expectedIntent).create().get();
        Assert.assertNotNull("started login activity shouldn't be null", startedActivity);

        EditText loginInput = (EditText) startedActivity.findViewById(R.id.test_intro_login_name);
        EditText passwordInput = (EditText) startedActivity.findViewById(R.id.test_intro_login_password);
        Button loginButton = (Button) startedActivity.findViewById(R.id.test_intro_login_button);
        Assert.assertNotNull("login input shouldn't be null", loginInput);
        Assert.assertNotNull("password input shouldn't be null", passwordInput);
        Assert.assertNotNull("login button shouldn't be null", loginButton);

        loginInput.setText(Stub.NAME);
        passwordInput.setText(Stub.PASSWORD);
        loginButton.performClick();

        ShadowActivity loginShadowActivity = Robolectric.shadowOf(startedActivity);
        Assert.assertEquals("login activity should return success code",
                Activity.RESULT_OK, loginShadowActivity.getResultCode());
        Intent resultIntent = loginShadowActivity.getResultIntent();
        Assert.assertNotNull("result intent shouldn't be empty", resultIntent);
        Bundle extras = resultIntent.getExtras();
        Assert.assertNotNull("result extras shouldn't be empty", extras);
        Assert.assertNotNull("extras should contain token strings", extras.getString(LoginActivity.KEY_AUTH_TOKEN));
    }

}
