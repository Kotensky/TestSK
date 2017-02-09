package com.kotensky.testsk.activity.login.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kotensky.testsk.R;
import com.kotensky.testsk.activity.login.presenter.IPresenterLogin;
import com.kotensky.testsk.activity.login.presenter.PresenterLogin;
import com.kotensky.testsk.activity.user.presenter.IPresenterUser;
import com.kotensky.testsk.activity.user.view.UserActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements IViewLogin {

    @Bind(R.id.editTextUsername)
    EditText editTextUsername;

    @Bind(R.id.editTextPass)
    EditText editTextPass;

    @Bind(R.id.buttonLoginEnter)
    Button buttonOK;

    private SharedPreferences sharedPref;
    private String basic;

    private IPresenterLogin presenterLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        presenterLogin = new PresenterLogin(this);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextUsername.getText().toString().trim().length() == 0) {
                    editTextUsername.setError(getString(R.string.edittx_is_empty_error));
                } else if (editTextPass.getText().toString().trim().length() == 0) {
                    editTextPass.setError(getString(R.string.edittx_is_empty_error));
                } else {
                    basic = "Basic " + Base64.encodeToString((editTextUsername.getText().toString() + ":" + editTextPass.getText().toString()).getBytes(), Base64.NO_WRAP);
                    presenterLogin.onLoginClick();
                }
            }
        });
    }

    @Override
    public void authOk() {
        sharedPref.edit().putString(getString(R.string.preference_basic_key), basic).apply();
        Intent intent = new Intent(LoginActivity.this, UserActivity.class);
        finish();
        startActivity(intent);
    }

    @Override
    public void showError() {
        Toast.makeText(getApplicationContext(), R.string.response_classic, Toast.LENGTH_LONG).show();
    }

    @Override
    public String getBasic() {
        return basic;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenterLogin != null) {
            presenterLogin.onStop();
        }
    }
}
