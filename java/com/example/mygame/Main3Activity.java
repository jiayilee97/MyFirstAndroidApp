package com.example.mygame;

import android.content.Intent;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Main3Activity extends AppCompatActivity {
    String TAG = "Main3Activity";
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        fragmentManager = getSupportFragmentManager();
        addFragment();
        Log.d(TAG, "FragmentDone");

        Button firstFragment = (Button) findViewById(R.id.firstFragmentbtn);
        firstFragment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addFragment1();
            }
        });

        Button secondFragment = (Button) findViewById(R.id.secondFragmentbtn);
        secondFragment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addFragment2();
            }
        });
/*

        // get the reference of Button's
        Button firstFragment = (Button) findViewById(R.id.firstFragment);
        Button secondFragment = (Button) findViewById(R.id.secondFragment);

        // perform setOnClickListener event on First Button
        firstFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        // load First Fragment
                loadFragment(new FirstFragment());
            }
        });
        // perform setOnClickListener event on Second Button
        secondFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        // load Second Fragment
                loadFragment(new SecondFragment());
            }
        });
*/


    }

    private void addFragment(){
//        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        SampleFragment sampleFragment=new SampleFragment();
        fragmentTransaction.replace(R.id.frameLayout,sampleFragment);
        fragmentTransaction.commit();
        Log.d(TAG, "addFragment: ");
    }

    public void addFragment1(){
//        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        FirstFragment fragment1 = new FirstFragment();
        fragmentTransaction.replace(R.id.frameLayout, fragment1);
        fragmentTransaction.commit();
        Log.d(TAG, "addFragment1: ");

    }
    public void addFragment2(){
//        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        SecondFragment fragment2 = new SecondFragment();
        fragmentTransaction.replace(R.id.frameLayout, fragment2);
        fragmentTransaction.commit();
        Log.d(TAG, "addFragment2: ");

    }
    public void onClick(View v){
        Log.d(TAG, "onClick: "+v.getId());
        switch(v.getId()){

            case R.id.activity2btn:
                Intent i1 = new Intent(v.getContext(),Main2Activity.class);
                startActivity(i1);
                overridePendingTransition(R.anim.enter, R.anim.exit);
                break;
            case R.id.activity4btn:

                Intent i2 = new Intent(v.getContext(),Main4Activity.class);
                startActivity(i2);
                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "onBackPressed: ");
        overridePendingTransition(R.anim.enter, R.anim.exit);
    }

/*    private void loadFragment(Fragment fragment) {
        // create a FragmentManager
        FragmentManager fm = getFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit(); // save the changes
    }*/

}
