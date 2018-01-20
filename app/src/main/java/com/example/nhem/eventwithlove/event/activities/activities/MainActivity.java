package com.example.nhem.eventwithlove.event.activities.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhem.eventwithlove.R;
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
    TextView tvUser;

    boolean loggedIn;

    private IntentFilter mIntentFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
        loginButton.setReadPermissions("email", "public_profile");
        tvUser.setText("ABCCCC");

        facebookLogin();

        tvUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (token == null) {
//                    Toast.makeText(MainActivity.this, "Login please", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(MainActivity.this, "Logined", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(MainActivity.this, ListEventActivity.class);
//                    startActivity(intent);
//                }
            }
        });

    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: ");
            if (intent.getAction().equals(mBroadcastLoginSuccessAction)) {
                getFragmentManager().popBackStack();
                Toast.makeText(MainActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(MainActivity.this, ListEventActivity.class);
                startActivity(intent1);
                Intent stopIntent = new Intent(MainActivity.this, LoginService.class);
                stopService(stopIntent);
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(mBroadcastLoginSuccessAction);
        registerReceiver(mReceiver, mIntentFilter);
    }

    @Override
    protected void onPause() {
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
            mReceiver = null;
        }
        super.onPause();
    }

    private void setupUI() {
        loginButton = findViewById(R.id.login_button);
        tvUser = findViewById(R.id.tv_user);
    }


    private void facebookLogin() {

        Log.d(TAG, "facebookLogin: ");
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment loadingFragment = new LoadingFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentLoading, loadingFragment);
                transaction.commit();
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

}
