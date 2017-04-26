package com.example.mycustomview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mycustomview.R;


public class RotateSwitchButton extends LinearLayout implements View.OnClickListener {

    private static final long DURATION = 500;
    private static final int ON = 1;
    private static final int OFF = 0;

    private String textLeft;
    private String textRight;
    private String textCenter;

    private TextView leftView;
    private ImageView centerView;
    private TextView rightView;
    private TextView tv_center;

    private SelectListener listener;
    private Context mContext;

    public RotateSwitchButton(Context context) {
        this(context,null);
    }

    public RotateSwitchButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RotateSwitchButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context,attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {

        mContext = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RotateSwitchButton);
        textLeft = ta.getString(R.styleable.RotateSwitchButton_text_left);
        textRight = ta.getString(R.styleable.RotateSwitchButton_text_right);
        textCenter = ta.getString(R.styleable.RotateSwitchButton_text_center);
        ta.recycle();

        View view = LayoutInflater.from(getContext()).inflate(R.layout.rotate_switch_button_layout, null);
        addView(view, new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        leftView = (TextView) view.findViewById(R.id.left_view);
        centerView = (ImageView) view.findViewById(R.id.center_view);
        rightView = (TextView) view.findViewById(R.id.right_view);
        tv_center = (TextView) view.findViewById(R.id.tv_center);

        leftView.setText(textLeft);
        rightView.setText(textRight);
        tv_center.setText(textCenter);

        leftView.setOnClickListener(this);
        centerView.setOnClickListener(this);
        rightView.setOnClickListener(this);

        //设置默认状态
        leftViewOn();
        rightViewOff();
    }

    @Override
    public void onClick(View view) {

        Integer left = (Integer) leftView.getTag();
        Integer right = (Integer) rightView.getTag();

        switch (view.getId()){
            case R.id.left_view:
                if (left == OFF){
                    rightTurnLeftRotateAnimation();
                    leftViewOn();

                    if (right == ON){
                        rightViewOff();
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
                if (right == OFF){
                    leftTurnRightRotateAnimation();
                    rightViewON();

                    if (left == ON){
                        leftViewOff();
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

    private void rightViewON() {
        if (rightView == null){
            return;
        }
        rightView.setBackgroundResource(R.drawable.right_on);
        rightView.setTag(ON);
        rightView.setTextColor(ContextCompat.getColor(mContext,R.color.on_text_color));
    }

    private void rightViewOff() {
        if (rightView == null){
            return;
        }
        rightView.setBackgroundResource(R.drawable.right_off);
        rightView.setTag(OFF);
        rightView.setTextColor(ContextCompat.getColor(mContext,R.color.white_text_color));
    }

    private void leftViewOn() {
        if (leftView == null){
            return;
        }
        leftView.setBackgroundResource(R.drawable.left_on);
        leftView.setTag(ON);
        leftView.setTextColor(ContextCompat.getColor(mContext,R.color.on_text_color));
    }

    private void leftViewOff() {
        if (leftView == null){
            return;
        }
        leftView.setBackgroundResource(R.drawable.left_off);
        leftView.setTag(OFF);
        leftView.setTextColor(ContextCompat.getColor(mContext,R.color.white_text_color));
    }

    /**
     * 从左向右旋转
     */
    private void leftTurnRightRotateAnimation(){
        if (centerView == null){
            return;
        }
        RotateAnimation animation = new RotateAnimation(0f,180f,
                Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
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
                Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
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
