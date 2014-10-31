package com.example.testintro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class MainActivityCase2Test {

    private MainActivity mActivity;
    private Button mainButton;

    @Before
    public void setUp() throws Exception {
        mActivity = Robolectric.buildActivity(MainActivity.class).create().get();
        assertThat("activity shouldn't be null", mActivity, notNullValue());
        mainButton = (Button) mActivity.findViewById(R.id.test_intro_main_button);
        assertThat("main button shouldn't be null", mainButton, notNullValue());
    }

    @Test
    public void testSuccessLogin() {
        Intent successResultIntent = new Intent();
        successResultIntent.putExtra(LoginActivity.KEY_AUTH_TOKEN, Stub.TOKEN);
        mActivity.onActivityResult(MainActivity.AUTH_REQUEST_CODE, Activity.RESULT_OK, successResultIntent);
        assertThat("token value should be parsed", mActivity.getToken(), equalTo(Stub.TOKEN));
        assertThat("should have success status", mActivity.isTokenSuccess(), equalTo(true));
    }

    @Test
    public void testCancelledLogin() {
        mActivity.onActivityResult(MainActivity.AUTH_REQUEST_CODE, Activity.RESULT_CANCELED, null);
        assertThat("token value should be empty", mActivity.getToken(), nullValue());
        assertThat("should have failure status", mActivity.isTokenSuccess(), equalTo(false));
    }

    @Test
    public void testSuccessLoginAnswer() {
        mainButton.performClick();
        Intent expectedIntent = new Intent(mActivity, LoginActivity.class);
        assertThat("main activity should fire login activity on click",
                Robolectric.shadowOf(mActivity).getNextStartedActivity(), equalTo(expectedIntent));

        LoginActivity startedActivity
                = Robolectric.buildActivity(LoginActivity.class).withIntent(expectedIntent).create().get();
        assertThat("started login activity shouldn't be null", startedActivity, notNullValue());

        EditText loginInput = (EditText) startedActivity.findViewById(R.id.test_intro_login_name);
        EditText passwordInput = (EditText) startedActivity.findViewById(R.id.test_intro_login_password);
        Button loginButton = (Button) startedActivity.findViewById(R.id.test_intro_login_button);
        Collection<View> views = new ArrayList<View>();
        Collections.addAll(views, loginInput, passwordInput, loginButton);
        assertThat(views, not(hasItem(CoreMatchers.nullValue(View.class))));

        loginInput.setText(Stub.NAME);
        passwordInput.setText(Stub.PASSWORD);
        loginButton.performClick();

        ShadowActivity loginShadowActivity = Robolectric.shadowOf(startedActivity);
        assertThat("login activity should return success code",
                loginShadowActivity.getResultCode(), equalTo(Activity.RESULT_OK));
        Intent resultIntent = loginShadowActivity.getResultIntent();
        assertThat("result intent shouldn't be empty", resultIntent, notNullValue());
        Bundle extras = resultIntent.getExtras();
        assertThat("result extras shouldn't be empty", extras, notNullValue());
        assertThat("extras should contain token strings", extras.getString(LoginActivity.KEY_AUTH_TOKEN), notNullValue());
    }

}
