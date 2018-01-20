package com.example.nhem.eventwithlove.event.activities.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.nhem.eventwithlove.event.activities.Preferences;
import com.example.nhem.eventwithlove.event.activities.activities.ListEventActivity;
import com.example.nhem.eventwithlove.event.activities.activities.MainActivity;
import com.example.nhem.eventwithlove.event.activities.models.requests.UserRequest;
import com.example.nhem.eventwithlove.event.activities.network.RetrofitFactory;
import com.example.nhem.eventwithlove.event.activities.network.api_routes.UserRoute;
import com.example.nhem.eventwithlove.event.activities.network.response.UserDataResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hau on 1/20/2018.
 */

public class LoginService extends Service {

    private static final String TAG = LoginService.class.toString();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        String fbToken = null;
        if (intent != null && intent.getExtras() != null) {
            fbToken = intent.getStringExtra("fbToken");
        }
        Log.d(TAG, "fbToken: " + fbToken);
        UserRoute userService = RetrofitFactory.getInstance().create(UserRoute.class);
        userService.login(new UserRequest(fbToken))
                .enqueue(new Callback<UserDataResponse>() {
                    @Override
                    public void onResponse(Call<UserDataResponse> call, Response<UserDataResponse> response) {
                        UserDataResponse data = response.body();
                        Log.d( "","onResponse: " + data.toString());
                        Preferences.getInstance().putToken(data.getResult().getAccessToken());
                        Intent broadcastIntent = new Intent();
                        broadcastIntent.setAction(ListEventActivity.getDataFromFirebaseSuccess);
                        sendBroadcast(intent);
                    }

                    @Override
                    public void onFailure(Call<UserDataResponse> call, Throwable t) {

                    }
                });

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean stopService(Intent name) {
        stopSelf();
        return super.stopService(name);
    }
}
