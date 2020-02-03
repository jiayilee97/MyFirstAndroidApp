package com.example.mygame;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Bundle;
//import android.support.annotation.RequiresApi;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

//import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class Main2Activity extends AppCompatActivity {
    String TAG = "Main2Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // btn to Activity1
//        Button activity1btn = findViewById(R.id.activity1btn);
//        activity1btn.setOnClickListener((View.OnClickListener) this);
        Intent intent = getIntent();
        String value = intent.getStringExtra("key");
        EditText et1 = (EditText) findViewById(R.id.enter);
        et1.setText(value, TextView.BufferType.EDITABLE);

        // transition button
        Button transBtn = (Button) findViewById(R.id.transBtn);
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        transBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TransitionDrawable)imageView.getDrawable()).startTransition(2000);
            }
        });

        // explosde transition
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Log.d(TAG, "onCreate: set Explode Exit Transition");
            getWindow().setExitTransition(new Explode());
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void onClick(View v){
        switch(v.getId()){
            case R.id.activity1btn:
                Intent i1 = new Intent(v.getContext(),MainActivity.class);
                v.getContext().startActivity(i1);
                overridePendingTransition(R.anim.enter, R.anim.exit);
                break;
            case R.id.activity3btn:
                Intent i2 = new Intent(v.getContext(),Main3Activity.class);
                startActivity(i2, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                Log.d(TAG, "onClick: makeSceneTransitionAnimation");
                break;
            default:
                break;
        }
    }

}
