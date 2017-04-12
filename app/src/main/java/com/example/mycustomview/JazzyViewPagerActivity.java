package com.example.mycustomview;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mycustomview.view.JazzyViewPager.JazzyViewPager;
import com.example.mycustomview.view.JazzyViewPager.OutlineContainer;

import java.util.ArrayList;
import java.util.List;

public class JazzyViewPagerActivity extends AppCompatActivity {

    private JazzyViewPager mJazzy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jazzy_view_pager);

        setupJazziness(JazzyViewPager.TransitionEffect.Tablet);
    }

    private void setupJazziness(JazzyViewPager.TransitionEffect effect) {
        mJazzy = (JazzyViewPager) findViewById(R.id.jazzy_pager);
        mJazzy.setTransitionEffect(effect);

        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
        for (int i = 0; i < 10; i++) {
            adapter.addFragment(new MyFragment1());
        }

        mJazzy.setAdapter(adapter);
//        mJazzy.setPageMargin(30);
    }

    /*private class MainAdapter extends PagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            TextView text = new TextView(JazzyViewPagerActivity.this);
            text.setGravity(Gravity.CENTER);
            text.setTextSize(30);
            text.setTextColor(Color.WHITE);
            text.setText("Page " + position);
            text.setPadding(30, 30, 30, 30);
            int bg = Color.rgb((int) Math.floor(Math.random()*128)+64,
                    (int) Math.floor(Math.random()*128)+64,
                    (int) Math.floor(Math.random()*128)+64);
            text.setBackgroundColor(bg);
            container.addView(text, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mJazzy.setObjectForPosition(text, position);
            return text;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object obj) {
            container.removeView(mJazzy.findViewFromObject(position));
        }
        @Override
        public int getCount() {
            return 10;
        }
        @Override
        public boolean isViewFromObject(View view, Object obj) {
            if (view instanceof OutlineContainer) {
                return ((OutlineContainer) view).getChildAt(0) == obj;
            } else {
                return view == obj;
            }
        }
    }*/

    private class MainAdapter extends FragmentStatePagerAdapter {


        private static final String TAG = "Adapter";
        private List<Fragment> mFragments = new ArrayList<>();

        public MainAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment) {
            mFragments.add(fragment);
        }

        public void removeFragment(Fragment fragment) {
            mFragments.remove(fragment);
        }

        public void setFragments(List<Fragment> fragments) {
            mFragments = fragments;
        }

        public List<Fragment> getFragments() {
            return mFragments;
        }

        public void clear() {
            for (Fragment fragment : mFragments) {
                if (fragment != null && fragment.isAdded()) {
                    fragment.onDestroy();
                }
            }
            mFragments.clear();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Object obj = super.instantiateItem(container, position);
            mJazzy.setObjectForPosition(obj, position);
            return obj;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            if (object != null) {
                return ((Fragment) object).getView() == view;
            } else {
                return false;
            }
        }
    }
}
