package com.example.mygame;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {
    String tag = "MyService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(tag, "onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(tag," onStart, thread id: "+Thread.currentThread().getId());
        stopSelf(); // stopSelf() to stop service
        return super.onStartCommand(intent, flags, startId);
    }
}
