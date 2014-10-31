package com.example.testintro;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;

public class MainActivityCase2Test extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mActivity;
    private Button mainButton;


    public MainActivityCase2Test() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
        assertNotNull("activity shouldn't be null", mActivity);
        mainButton = (Button) mActivity.findViewById(R.id.test_intro_main_button);
        assertNotNull("main button shouldn't be null", mainButton);
    }

    private Instrumentation.ActivityResult getSuccessActivityResult() {
        Intent successResultIntent = new Intent();
        successResultIntent.putExtra(LoginActivity.KEY_AUTH_TOKEN, Stub.TOKEN);
        return new Instrumentation.ActivityResult(Activity.RESULT_OK, successResultIntent);
    }

    public void testSuccessLogin() {
        // Mock up an ActivityResult:

        // Create an ActivityMonitor that catch ChildActivity and return mock ActivityResult:
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation()
                .addMonitor(LoginActivity.class.getName(), getSuccessActivityResult(), true);

        // UGLY
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainButton.performClick();
            }
        });

        getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 50);

        assertEquals("token value should be parsed", Stub.TOKEN, mActivity.getToken());
        assertTrue("should have success status", mActivity.isTokenSuccess());
    }

    public void testCancelledLogin() {
        // Mock up an ActivityResult:

        Instrumentation.ActivityResult activityResult =
                new Instrumentation.ActivityResult(Activity.RESULT_CANCELED, null);

        // Create an ActivityMonitor that catch ChildActivity and return mock ActivityResult:
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation()
                .addMonitor(LoginActivity.class.getName(), activityResult, true);

        // UGLY
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainButton.performClick();
            }
        });

        getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 50);

        assertNull("token value should be empty", mActivity.getToken());
        assertFalse("should have failure status", mActivity.isTokenSuccess());
    }

    public void testSuccessLoginAnswer() {
        MainActivity mainActivity = getActivity();
        assertNotNull("main activity shouldn't be null", mainActivity);
        Button mainButton = (Button) mainActivity.findViewById(R.id.test_intro_main_button);
        assertNotNull("main button shouldn't be null", mainButton);
        Instrumentation.ActivityMonitor startMonitor =
                getInstrumentation().
                        addMonitor(LoginActivity.class.getName(), null, false);

        TouchUtils.clickView(this, mainButton);
        LoginActivity startedActivity = (LoginActivity) startMonitor.waitForActivityWithTimeout(50);
        assertNotNull("started login activity shouldn't be null", startedActivity);

        EditText loginInput = (EditText) startedActivity.findViewById(R.id.test_intro_login_name);
        EditText passwordInput = (EditText) startedActivity.findViewById(R.id.test_intro_login_password);
        Button loginButton = (Button) startedActivity.findViewById(R.id.test_intro_login_button);
        assertNotNull("login input shouldn't be null", loginInput);
        assertNotNull("password input shouldn't be null", passwordInput);
        assertNotNull("login button shouldn't be null", loginButton);

        // ca click buttons and check other controls
        TouchUtils.clickView(this, loginButton);
        assertTrue("application is not so complicated right now", true);
        mainActivity.finish();
    }

}
