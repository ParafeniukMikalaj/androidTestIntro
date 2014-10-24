package com.example.testintro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity implements View.OnClickListener {

    public static final String KEY_AUTH_TOKEN = "token";

    private EditText mLogin, mPassword;
    private TextView mError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initializeViews();
    }

    private void initializeViews() {
        mLogin = (EditText) findViewById(R.id.test_intro_login_name);
        mPassword = (EditText) findViewById(R.id.test_intro_login_password);
        mError = (TextView) findViewById(R.id.test_intro_login_error);
        Button loginButton = (Button) findViewById(R.id.test_intro_login_button);
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.test_intro_login_button) {
            onLoginClicked();
        }
    }

    private void onLoginClicked() {
        String login = mLogin.getText().toString();
        String password = mPassword.getText().toString();
        Credentials credentials = new Credentials(login, password);
        Authenticator authenticator = new Authenticator();
        Bundle result = authenticator.authorize(credentials);
        processResult(result);
    }

    private void processResult(Bundle result) {
        if (result.containsKey(Authenticator.ResultKey.ERROR_MESSAGE)) {
            String errorMessage = result.getString(Authenticator.ResultKey.ERROR_MESSAGE);
            mError.setText(errorMessage);
            mError.setVisibility(View.VISIBLE);
        } else {
            mError.setVisibility(View.GONE);
            mError.setText("");
            String token = result.getString(Authenticator.ResultKey.TOKEN);
            Intent data = new Intent();
            data.putExtra(KEY_AUTH_TOKEN, token);
            setResult(Activity.RESULT_OK, data);
            finish();
        }
    }
}
