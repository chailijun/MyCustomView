package com.example.mycustomview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.mycustomview.R;

import static android.R.attr.animation;
import static android.R.attr.duration;
import static android.R.attr.level;

/**
 * Created by PVer on 2017/4/13.
 */

public class RotateSwitchButton extends LinearLayout implements View.OnClickListener {

    private static final long DURATION = 500;
    private ImageView leftView;
    private ImageView centerView;
    private ImageView rightView;

    private SelectListener listener;

    public RotateSwitchButton(Context context) {
        this(context,null);
    }

    public RotateSwitchButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RotateSwitchButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.rotate_switch_button_layout, null);
        addView(view, new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        leftView = (ImageView) view.findViewById(R.id.left_view);
        centerView = (ImageView) view.findViewById(R.id.center_view);
        rightView = (ImageView) view.findViewById(R.id.right_view);

        leftView.setOnClickListener(this);
        centerView.setOnClickListener(this);
        rightView.setOnClickListener(this);

        //设置默认状态
        leftView.getDrawable().setLevel(1);
        rightView.getDrawable().setLevel(0);
    }

    @Override
    public void onClick(View view) {
        int leftLevel = leftView.getDrawable().getLevel();
        int rightLevel = rightView.getDrawable().getLevel();

        switch (view.getId()){
            case R.id.left_view:
                if (leftLevel == 0){
                    rightTurnLeftRotateAnimation();
                    leftView.getDrawable().setLevel(1);
                    if (rightLevel == 1){
                        rightView.getDrawable().setLevel(0);
                    }

                    if (listener != null){
                        listener.leftSelected();
                    }
                }

                break;
            case R.id.center_view:
                if (listener != null){
                    listener.centerSelected();
                }
                break;
            case R.id.right_view:
                if (rightLevel == 0){
                    leftTurnRightRotateAnimation();
                    rightView.getDrawable().setLevel(1);

                    if (leftLevel == 1){
                        leftView.getDrawable().setLevel(0);
                    }

                    if (listener != null){
                        listener.rightSelected();
                    }
                }

                break;
            default:
                break;
        }
    }

    /**
     * 从左向右旋转
     */
    private void leftTurnRightRotateAnimation(){
        if (centerView == null){
            return;
        }
        RotateAnimation animation = new RotateAnimation(0f,180f,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animation.setDuration(DURATION);
//        animation.setRepeatCount(1);
        animation.setFillAfter(true);
        animation.setInterpolator(new LinearInterpolator());
        centerView.startAnimation(animation);
    }

    /**
     * 从右向左旋转
     */
    private void rightTurnLeftRotateAnimation(){
        if (centerView == null){
            return;
        }
        RotateAnimation animation = new RotateAnimation(180f,0f,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animation.setDuration(DURATION);
//        animation.setRepeatCount(1);
        animation.setFillAfter(true);
        animation.setInterpolator(new LinearInterpolator());
        centerView.startAnimation(animation);
    }

    public void setSelectListener(SelectListener listener) {
        this.listener = listener;
    }

    public interface SelectListener{
        void leftSelected();
        void centerSelected();
        void rightSelected();
    }
}
