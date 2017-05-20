package com.example.mycustomview;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import static android.R.attr.animation;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        iv_download = (ImageView) findViewById(R.id.iv_download);
        iv_download.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        animation.setDuration(800);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
//        v.startAnimation(animation);

        Animation alphaAnimation=new AlphaAnimation(0.1f,1.0f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setInterpolator(new DecelerateInterpolator());
        alphaAnimation.setRepeatCount(Animation.INFINITE);

        
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(animation);
        animationSet.addAnimation(alphaAnimation);
        v.setAnimation(animationSet);
        v.startAnimation(animationSet);
//        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f, 0.5f, 1f);
//        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f, 0.7f, 0.8f, 1f);
//        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("Y", 1f, 0.7f, 0.8f, 1f);
//
//        ObjectAnimator.ofPropertyValuesHolder(v, pvhX, pvhY, pvhZ).setDuration(300).start();
    }
}
