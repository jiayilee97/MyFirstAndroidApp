package com.example.mygame;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class ScreenSlidePageFragment extends Fragment {
    public static final String ARG_OBJECT = "object";

    String TAG = "ScreenSlidePageFragment";
//    int i;
//    ScreenSlidePageFragment(int ix){
//        i = ix;
//        Log.d(TAG, "ScreenSlidePageFragment: " + ix);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        TextView textView = (TextView) getView().findViewById(R.id.fragmentText);
//        textView.setText(i);
//        Log.d(TAG, "onCreateView: "+ i);
        return (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        ((TextView) view.findViewById(R.id.fragmentText)).setText(Integer.toString(args.getInt(ARG_OBJECT)));
    }

}
