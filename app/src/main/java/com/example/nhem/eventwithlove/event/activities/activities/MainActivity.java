package com.example.nhem.eventwithlove.event.activities.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhem.eventwithlove.R;
import com.example.nhem.eventwithlove.event.activities.events.GetTokenSuccessEvent;
import com.example.nhem.eventwithlove.event.activities.events.LoadingBeginEvent;
import com.example.nhem.eventwithlove.event.activities.events.LoadingEndEvent;
import com.example.nhem.eventwithlove.event.activities.fragment.LoadingFragment;
import com.example.nhem.eventwithlove.event.activities.network.PostUserRequest;
import com.example.nhem.eventwithlove.event.activities.services.LoginService;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.wang.avi.AVLoadingIndicatorView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.toString();

    public static final String mBroadcastLoginSuccessAction = "loginSuccess";

    CallbackManager callbackManager = CallbackManager.Factory.create();
    LoginButton loginButton;
    AVLoadingIndicatorView avi;

    boolean loggedIn;

    private IntentFilter mIntentFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
        loginButton.setReadPermissions("email", "public_profile");
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, 50);
        }

        facebookLogin();

    }
    private void setupUI() {
        loginButton = findViewById(R.id.login_button);
        avi = findViewById(R.id.avi);
    }


    private void facebookLogin() {

        Log.d(TAG, "facebookLogin: ");
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButton.setVisibility(View.INVISIBLE);
            }
        });
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "onSuccess: " + loginResult.getAccessToken().getToken());
                loggedIn = loginResult.getAccessToken() == null;
                Intent intent = new Intent(MainActivity.this, LoginService.class);
                intent.putExtra("fbToken", loginResult.getAccessToken().getToken());
                startService(intent);
                // App code
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "onCancel: ");
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                Log.d(TAG, "onError: " + exception.toString());
                // App code
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onMessageEvent(GetTokenSuccessEvent event) {
        Log.d(TAG, "onMessageEvent: ");
        EventBus.getDefault().post(new LoadingEndEvent());
        Toast.makeText(MainActivity.this, "Login success", Toast.LENGTH_SHORT).show();
        Intent intent1 = new Intent(MainActivity.this, ListEventActivity.class);
        startActivity(intent1);
        Intent stopIntent = new Intent(MainActivity.this, LoginService.class);
        stopService(stopIntent);
    }

    @Subscribe
    public void onMessageEvent(LoadingBeginEvent event) {
        avi.show();
    }

    @Subscribe
    public void onMessageEvent(LoadingEndEvent event) {
        avi.hide();
    }


}
