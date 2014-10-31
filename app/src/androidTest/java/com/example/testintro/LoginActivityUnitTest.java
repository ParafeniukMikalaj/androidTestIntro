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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

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
        Collection<View> views = new ArrayList<View>();
        Collections.addAll(views, loginInput, passwordInput, errorText, loginButton);
        assertThat(views, not(hasItem(nullValue(View.class))));
    }

    @Test
    public void testInvalidCredentials() {
        loginInput.setText(Stub.EMTPY);
        passwordInput.setText(Stub.EMTPY);
        loginButton.performClick();
        assertThat("error message should became visible with empty credentials",
                errorText.getVisibility(), equalTo(View.VISIBLE));
    }
}
