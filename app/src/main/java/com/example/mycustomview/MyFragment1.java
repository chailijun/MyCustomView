package com.example.mycustomview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by PVer on 2017/4/12.
 */

public class MyFragment1 extends Fragment{

    private TextView text;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_layout, container, false);
        text = (TextView) rootView.findViewById(R.id.text);

        int bg = Color.rgb((int) Math.floor(Math.random()*128)+64,
                (int) Math.floor(Math.random()*128)+64,
                (int) Math.floor(Math.random()*128)+64);
        text.setBackgroundColor(bg);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
