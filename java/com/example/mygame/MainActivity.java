package com.example.mygame;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
//import android.os.SystemClock;
//import android.support.annotation.RequiresApi;
//import android.support.design.widget.CoordinatorLayout;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    String tag = "LifeCycleEvents";
    String TAG = "LifeCycleEvents";

    private ImageView box;
    private ImageView orange;
    private ImageView circle;
    private ImageView custom;
    AnimationDrawable wifiAnimation;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_main);
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

        final ImageView bird1 = (ImageView) findViewById(R.id.bird1);
        box = (ImageView) findViewById(R.id.box);
        orange = (ImageView) findViewById(R.id.orange);
        circle = (ImageView) findViewById(R.id.circle);

        // animate bird
        ObjectAnimator animation = ObjectAnimator.ofFloat(bird1, "translationX", 100f);
        animation.setDuration(2000);
        animation.start();

        // set up button
        final Button button = findViewById(R.id.btnUp);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ObjectAnimator animationUp = ObjectAnimator.ofFloat(bird1, "translationY", 100f);
                animationUp.setDuration(2000);
                animationUp.start();
            }
        });

        // animate circle
        // arcTo() and PathInterpolator only available on API 21+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Path path = new Path();
            path.arcTo(0f, 0f, 300f, 300f, 70f, -50f, true);
        // PathInterpolator pathInterpolator = new PathInterpolator(path);
            ObjectAnimator animator2 = ObjectAnimator.ofFloat(circle, View.X, View.Y, path);
            animator2.setDuration(2000);
            animator2.start();
            Toast.makeText(this, "Circle", Toast.LENGTH_SHORT).show();
        }

        // animate custom
        custom = (ImageView) findViewById(R.id.custom_obj);

        // animate wifi
        ImageView imageView = (ImageView) findViewById(R.id.wifi);
        imageView.setBackgroundResource(R.drawable.animation);
        wifiAnimation = (AnimationDrawable) imageView.getBackground();

        // bird xml
        ImageView birdxml = (ImageView) findViewById(R.id.birdxml);
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
                R.animator.property_animator);
        set.setTarget(birdxml);
        set.start();

        // valueAnimator
        ObjectAnimator animX = ObjectAnimator.ofFloat(birdxml,"x",620f);
        ObjectAnimator animY = ObjectAnimator.ofFloat(birdxml,"y",220f);
        ObjectAnimator animAlpha = ObjectAnimator.ofFloat(birdxml,View.ALPHA,1.0f,0.5f);
        ObjectAnimator animRot = ObjectAnimator.ofFloat(birdxml,"rotation",0f,360f);
        animX.setDuration(2000);
        animY.setDuration(2000);
        animAlpha.setDuration(2000);
        animRot.setDuration(2000);
        AnimatorSet as2 = new AnimatorSet();
        as2.playTogether(animX,animY,animAlpha,animRot);
        as2.start();

        // move1
        Animation seq1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move1);
        ImageView move1bird = (ImageView) findViewById(R.id.move1bird);
        move1bird.startAnimation(seq1);

        // animationListener
        final ImageView sim = (ImageView) findViewById(R.id.sim);
        ObjectAnimator animLis = ObjectAnimator.ofFloat(sim,"x",300f);
        animLis.setDuration(2000);
        animLis.addListener(new Animator.AnimatorListener(){
            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d(TAG, "onAnimationEnd: ");
                ObjectAnimator animLis2 = ObjectAnimator.ofFloat(sim,"x",-50f);
                animLis2.setDuration(2000);
                animLis2.start();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                Log.d(TAG, "onAnimationStart: ");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.d(TAG, "onAnimationCancel: ");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.d(TAG, "onAnimationRepeat: ");
            }
        });
        animLis.start();

        // set multiple animator
        ImageView star = (ImageView) findViewById(R.id.star);
        ObjectAnimator popup = ObjectAnimator.ofPropertyValuesHolder(star,
                PropertyValuesHolder.ofFloat("alpha", 0f, 1f),
                PropertyValuesHolder.ofFloat("scaleX", 0f, 1f),
                PropertyValuesHolder.ofFloat("scaleY", 0f, 1f));
        popup.setDuration(2500);
        popup.setInterpolator(new OvershootInterpolator());
        popup.setRepeatCount(-1);
        popup.start();

        // change text color
        TextView hello = (TextView) findViewById(R.id.hello);
        ObjectAnimator animHello = ObjectAnimator.ofArgb(hello, "TextColor", Color.MAGENTA,Color.BLUE,Color.CYAN);
        animHello.setDuration(5000);
        animHello.setRepeatCount(-1);
        ObjectAnimator animBgr = ObjectAnimator.ofArgb(hello, "BackgroundColor", Color.CYAN, Color.GREEN, Color.MAGENTA, Color.BLUE);
        animBgr.setDuration(5000);
        animBgr.setRepeatCount(-1);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(animHello).with(animBgr);
        animSet.start();

        // transition animation (faulty, set to invisible)
        ImageView ivPosition = (ImageView) findViewById(R.id.ivPosition);
        TransitionDrawable drawable = (TransitionDrawable) ivPosition.getDrawable();
        drawable.startTransition(5000);

        // background animation
        CoordinatorLayout layoutbg = findViewById(R.id.parentLayout);
        AnimationDrawable drawable2 = (AnimationDrawable) layoutbg.getBackground();
        drawable2.setEnterFadeDuration(2000);
        drawable2.setExitFadeDuration(4000);
        drawable2.start();

        // go to Main2Activity
        Button activity2btn = findViewById(R.id.activity2btn);
        activity2btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Main2Activity.class);
                i.putExtra("key", "bbc");
                v.getContext().startActivity(i);
            }
        });

    }

    public void animateTriangle(View view){
        // animate triangle
        ImageView triangle1 = (ImageView) findViewById(R.id.triangle);
        Drawable d = triangle1.getDrawable();
        String TAG = "Triangle";
        if (d instanceof AnimatedVectorDrawableCompat){
            Log.d(TAG, "Compat: ");
            AnimatedVectorDrawableCompat avd = (AnimatedVectorDrawableCompat) d;
            avd.start();
        } else if (d instanceof Animatable){
            Log.d(TAG, "Ani");
            ((Animatable) d).start();
        }
    }

    public void onStart()
    {
        super.onStart();
        Log.d(tag, "In the onStart() event");
    }
    public void onRestart()
    {
        super.onRestart();
        Log.d(tag, "In the onRestart() event");
    }

    int taps=0;
    private Timer timer = new Timer();
    private Handler handler = new Handler();
    private int boxY;

//    @Override
    public void onResume() {
        super.onResume();
        Log.d(tag, "In the onResume() event");
        final ImageView bird1 = (ImageView) findViewById(R.id.bird1);


        ObjectAnimator animation = ObjectAnimator.ofFloat(bird1, "translationX", 100f);
        animation.setDuration(2000);
        animation.start();

        final Button button = findViewById(R.id.btnUp);
        final Button button2 = findViewById(R.id.btn2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ObjectAnimator animationUp = ObjectAnimator.ofFloat(bird1, "translationY", taps*100f);
                animationUp.setDuration(2000);
                animationUp.start();
                Toast.makeText(getApplicationContext(), "btn1", Toast.LENGTH_SHORT).show();
                taps++;

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ObjectAnimator animationUp = ObjectAnimator.ofFloat(bird1, "translationY", -100f);
                animationUp.setDuration(2000);
                animationUp.start();
                Toast.makeText(getApplicationContext(), "btn2", Toast.LENGTH_SHORT).show();
                taps--;
            }
        });

        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                handler.post(new Runnable(){
                    @Override
                    public void run(){
                        changePos();
                    }
                });
            }
        },0,20);

    }

    public void changePos(){
        box.setY(boxY);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onTouchEvent(MotionEvent me){
        if (me.getAction() == MotionEvent.ACTION_DOWN){
            boxY -= 20;
        }

        box.setY(boxY);

        Log.d(TAG, "onTouchEvent: ");
//        custom = (ImageView) findViewById(R.id.custom_obj);
        custom.animate().translationY(330).setInterpolator(new DecelerateInterpolator()).setDuration(1000).start();
        custom.animate().translationY(-330).setInterpolator(new AccelerateInterpolator()).setDuration(1000).start();


        return true;
    }


    public void animate(){
        Toast.makeText(getApplicationContext(), "custom", Toast.LENGTH_SHORT).show();
        custom.setScaleX(0);
        custom.setScaleY(0);
        custom.animate().scaleX(1).scaleY(1).start();
        // SystemClock.sleep(500);
        custom.setX(0);
        custom.animate().setInterpolator(new HesitateInterpolator()).setDuration(10000).translationX(620).start();
    }

    public void onTouchCustomBtn(View view){
        animate();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        wifiAnimation.start();
    }

    public void onClick(View v){
        switch(v.getId()){
            case(R.id.activity7btn):
                Intent i1 = new Intent(v.getContext(), Main7Activity.class);
                startActivity(i1);
                break;
            case(R.id.activity5btn):
                Intent i2 = new Intent(v.getContext(),Main5Activity.class);
                startActivity(i2);
                break;
        }
    }
}
