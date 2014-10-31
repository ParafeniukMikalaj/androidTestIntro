package com.example.testintro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    public static final int AUTH_REQUEST_CODE = 1;

    private String mToken;
    private String mError;
    private boolean mIsTokenSuccess;

    public boolean isTokenSuccess() {
        return mIsTokenSuccess;
    }

    public String getToken() {
        return mToken;
    }

    public String getError() {
        return mError;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initializeViews();
        // UGLY
        initializeVars();
    }

    private void initializeVars() {
        mToken = null;
        mError = null;
        mIsTokenSuccess = false;
    }

    private void initializeViews() {
        Button authButton = (Button) findViewById(R.id.test_intro_main_button);
        authButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.test_intro_main_button) {
            onAuthorizeClicked();
        }
    }

    private void onAuthorizeClicked() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, AUTH_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTH_REQUEST_CODE) {
            processAuthResult(resultCode, data);
        }
    }

    private void processAuthResult(int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            mToken = data.getStringExtra(LoginActivity.KEY_AUTH_TOKEN);
            mIsTokenSuccess = true;
            Toast.makeText(this, "Got token " + mToken, Toast.LENGTH_LONG).show();
        } else {
            mIsTokenSuccess = false;
            Toast.makeText(this, "Token not retrieved", Toast.LENGTH_LONG).show();
        }
    }
}
