package com.example.mycustomview.view;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

/**
 * Created by PVer on 2017/5/17.
 */

public class SpringbackButton extends AppCompatTextView {

    public SpringbackButton(Context context) {
        this(context, null);
    }

    public SpringbackButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpringbackButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                startBackAnimation();
                break;
        }
        return super.onTouchEvent(event);
    }

    private void startBackAnimation() {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f, 0.5f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f, 0.7f, 0.8f, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f, 0.7f, 0.8f, 1f);
        ObjectAnimator.ofPropertyValuesHolder(this, pvhX, pvhY, pvhZ).setDuration(300).start();
    }
}
