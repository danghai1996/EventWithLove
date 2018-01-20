package com.example.nhem.eventwithlove.event.activities.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhem.eventwithlove.R;
import com.example.nhem.eventwithlove.event.activities.Preferences;
import com.example.nhem.eventwithlove.event.activities.network.PostUserRequest;
import com.example.nhem.eventwithlove.event.activities.network.PostUserResponse;
import com.example.nhem.eventwithlove.event.activities.network.PostUserService;
import com.example.nhem.eventwithlove.event.activities.network.RetrofitFactory;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.toString();
    CallbackManager callbackManager = CallbackManager.Factory.create();
    LoginButton loginButton;
    TextView tvUser;

    String name;
    String gender;
    String email;
    String phone;
    String link;

    AccessToken token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();

        loginButton.setReadPermissions("email", "public_profile");
        tvUser.setText("ABCCCC");


        facebookLogin();
        token = AccessToken.getCurrentAccessToken();

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
                Intent intent = new Intent(MainActivity.this, EventActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setupUI() {
        loginButton = findViewById(R.id.login_button);
        tvUser = findViewById(R.id.tv_user);
    }


    private void facebookLogin() {
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Log.d(TAG, "onSuccess: " + loginResult.toString());
                Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                loginButton.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(MainActivity.this, ListEventActivity.class);
                startActivity(intent);

                sendRequest();
                Log.d(TAG, "sendRequest: user" + name + gender + email + phone + link);
//                finish();
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

    private void sendRequest() {
        getFBInfo();


    }

    private void getFBInfo() {
        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            name = object.getString("name");
                            gender = object.getString("gender");
                            email = object.getString("email");
                            link = object.getString("link");
                            Log.d(TAG, "getFBInfo: "+ name + gender + email + link);

                            PostUserRequest userRequest = new PostUserRequest(
                                    name,
                                    gender,
                                    email,
                                    phone,
                                    link
                            );

                            PostUserService userService = RetrofitFactory.getInstance().create(PostUserService.class);
                            userService.postUser(userRequest)
                                    .enqueue(new Callback<PostUserResponse>() {
                                        @Override
                                        public void onResponse(Call<PostUserResponse> call, Response<PostUserResponse> response) {
                                            PostUserResponse userResponse = response.body();
                                            Log.d(TAG, "codeResponse: " +  userResponse.toString());
                                            Preferences.getInstance().putToken(userResponse.getAccessToken());
                                        }

                                        @Override
                                        public void onFailure(Call<PostUserResponse> call, Throwable t) {
                                            Log.d(TAG, "onFailure: sida");

                                        }
                                    });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        Bundle parameters = new Bundle();
        parameters.putString("fields", "name, gender, email, link");
        request.setParameters(parameters);
        request.executeAsync();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

}
