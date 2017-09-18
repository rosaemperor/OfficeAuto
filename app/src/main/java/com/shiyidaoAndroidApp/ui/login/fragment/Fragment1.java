package com.shiyidaoAndroidApp.ui.login.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Adminidtrator on 2017/3/10.
 */

public class Fragment1 extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        TextView view=new TextView(getContext());
        view.setText("Fragment1");
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("onStart","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        if(this.isVisible()){
            Log.d("AAA","onResume");
        }

    }
}
