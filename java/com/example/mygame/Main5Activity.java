package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Main5Activity extends AppCompatActivity {

    private Intent serviceIntent;
    String TAG = "Main5Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        Log.d(TAG, " thread id: "+Thread.currentThread().getId());

        serviceIntent = new Intent(getApplicationContext(),MyService.class);
//        startService(serviceIntent);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.start_service:
                startService(serviceIntent);
                break;
        }
    }


}
