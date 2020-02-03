package com.example.mygame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;


import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ScreenSlidePagerActivity extends FragmentActivity {

    String TAG = "ScreenSlidePagerActivity";
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 5;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager2 viewPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private FragmentStateAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        // Tabs
        TabLayout tabLayout = findViewById(R.id.tab_layout);
//        new TabLayoutMediatorCustom(tabLayout, viewPager, new TabLayoutMediatorCustom.TabConfigurationStrategy() {
//            @Override
//            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//                tab.setText("Obj "+(position+1));
//            }
//        }
//        ).attach();
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText("OBJECT " + (position + 1))
        ).attach();

    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
//        new TabLayoutMediatorCustom(tabLayout, viewPager, new TabLayoutMediatorCustom.TabConfigurationStrategy() {
//            @Override
//            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//                tab.setText("Obj "+(position+1));
//            }
//        }
//        ).attach();
//    }


    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
            Bundle args = new Bundle();
            args.putInt(ScreenSlidePageFragment.ARG_OBJECT, position + 1);
            fragment.setArguments(args);
//            TextView fragmentText = fragment.getView().findViewById(R.id.fragmentText);
//            fragmentText.setText("bla");
//            Log.d(TAG, "createFragment: "+fragmentText.getText());
            return fragment;
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.activity4btn:
                Intent i1 = new Intent(v.getContext(),Main4Activity.class);
                startActivity(i1);
                break;
            case R.id.activity6btn:
                Log.d(TAG, "onClick: activity6");
                Intent i2 = new Intent(v.getContext(),Main6Activity.class);
                startActivity(i2);
                break;
        }
    }

}
