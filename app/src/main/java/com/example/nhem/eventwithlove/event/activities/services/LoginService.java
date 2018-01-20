package com.example.nhem.eventwithlove.event.activities.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.nhem.eventwithlove.event.activities.Preferences;
import com.example.nhem.eventwithlove.event.activities.activities.ListEventActivity;
import com.example.nhem.eventwithlove.event.activities.activities.MainActivity;
import com.example.nhem.eventwithlove.event.activities.events.GetTokenSuccessEvent;
import com.example.nhem.eventwithlove.event.activities.events.LoadingBeginEvent;
import com.example.nhem.eventwithlove.event.activities.models.domain.Event;
import com.example.nhem.eventwithlove.event.activities.models.requests.UserRequest;
import com.example.nhem.eventwithlove.event.activities.models.responses.UserResponse;
import com.example.nhem.eventwithlove.event.activities.network.RetrofitFactory;
import com.example.nhem.eventwithlove.event.activities.network.api_routes.UserRoute;

import org.greenrobot.eventbus.EventBus;

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
        EventBus.getDefault().post(new LoadingBeginEvent());
        String fbToken = null;
        if (intent != null && intent.getExtras() != null) {
            fbToken = intent.getStringExtra("fbToken");
        }
        Log.d(TAG, "fbToken: " + fbToken);
        UserRoute userService = RetrofitFactory.getInstance().create(UserRoute.class);
        userService.login(new UserRequest(fbToken))
                .enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        UserResponse data = response.body();
                        if (data != null) {
                            Log.d( "","onResponse: " + data.toString());
                            Preferences.getInstance().putToken(data.getAccessToken());
                            EventBus.getDefault().post(new GetTokenSuccessEvent());
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
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
