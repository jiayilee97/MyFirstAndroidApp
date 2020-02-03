package com.example.mygame;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.activity3btn:
                Intent i1 = new Intent(v.getContext(),Main3Activity.class);
                startActivity(i1);
                break;
            case R.id.screenSlideActivityBtn:
                Intent i2 = new Intent(v.getContext(),ScreenSlidePagerActivity.class);
                startActivity(i2);
                break;
            case R.id.activity6btn:
                Intent i3 = new Intent(v.getContext(),Main6Activity.class);
                startActivity(i3);
                break;

        }
    }
}
