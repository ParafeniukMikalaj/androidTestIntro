package com.example.testintro;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Example of usage of {@link android.test.ActivityUnitTestCase}
 * for teting activity in isolation.
 */
public class LoginActivityUnitTest extends ActivityUnitTestCase<LoginActivity> {

    private LoginActivity mActivity;
    private EditText loginInput, passwordInput;
    private TextView errorText;
    private Button loginButton;

    public LoginActivityUnitTest() {
        super(LoginActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // UGLY without this line getActivity() will return null
        startActivity(new Intent(getInstrumentation().getTargetContext(), MainActivity.class), null, null);
        mActivity = getActivity();
        assertNotNull("mActivity shouldn't be null", mActivity);
        loginInput = (EditText) mActivity.findViewById(R.id.test_intro_login_name);
        passwordInput = (EditText) mActivity.findViewById(R.id.test_intro_login_password);
        errorText = (TextView) mActivity.findViewById(R.id.test_intro_login_error);
        loginButton = (Button) mActivity.findViewById(R.id.test_intro_login_button);
        assertNotNull("login input shouldn't be null", loginInput);
        assertNotNull("password input shouldn't be null", passwordInput);
        assertNotNull("error text shouldn't be null", errorText);
        assertNotNull("login button shouldn't be null", loginButton);
    }

    public void testInvalidCredentials() {
        loginInput.setText(Stub.EMTPY);
        passwordInput.setText(Stub.EMTPY);
        loginButton.performClick();
        assertEquals(
                "error message should became visible with empty credentials",
                View.VISIBLE, errorText.getVisibility());
    }
}
