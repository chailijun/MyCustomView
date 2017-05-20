package com.example.mycustomview.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.TextView;

import com.example.mycustomview.R;

/**
 * Created by PVer on 2017/5/20.
 */

public class ClearableTextView extends TextView {

    public interface OnTextClearListener {
        void onTextClear(ClearableTextView v);
    }

    private StringBuilder sb_input = new StringBuilder();

    private Context mContext;
    private Drawable mDrawableRight;
    private Rect mBounds;

    private OnTextClearListener mOnTextClearListener;

    public ClearableTextView(Context context) {
        super(context);
        initialize(context);
    }

    public ClearableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    private void initialize(Context context) {
        mContext = context;
        mDrawableRight = mContext.getResources().getDrawable(R.drawable.alphabet_delete);
        mDrawableRight.setBounds(0, 0, mDrawableRight.getMinimumWidth(), mDrawableRight.getMinimumWidth());
        setClickable(true);
        setMinWidth(120);
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(8, 8, 8, 8);
        setCompoundDrawablePadding(8);
    }

    @Override
    public void setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        if (right != null) {
            mDrawableRight = right;
        }
        super.setCompoundDrawables(left, top, mDrawableRight, bottom);
    }

    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP && mDrawableRight != null) {

            mBounds = mDrawableRight.getBounds();

            final int x = (int) event.getX();
            final int y = (int) event.getY();

            if (x >= (this.getWidth() - mBounds.width()) && x <= (this.getWidth() - this.getPaddingRight())
                    && y >= this.getPaddingTop() && y <= (this.getHeight() - this.getPaddingBottom())) {
                clear();
                event.setAction(MotionEvent.ACTION_CANCEL);
            }
        }

        return super.onTouchEvent(event);
    }

    public void setTextClearable(CharSequence text) {
        setText(text);
        /*if (text == null || text.length() == 0) {
            super.setCompoundDrawables(null, null, null, null);
        } else {
            super.setCompoundDrawables(null, null, mDrawableRight, null);
        }*/
        if (mDrawableRight != null){
            super.setCompoundDrawables(null, null, mDrawableRight, null);
        }
    }

    private void setInputTextView(String str) {
//        int keywordLen = keyword.length();
        int keywordLen = 10;
        int len = str.length();
        for (int i = 0; i < keywordLen - len; i++) {
            str += "_";
        }
        StringBuilder showStr = new StringBuilder();
        for (int i = 0; i < keywordLen; i++) {
            showStr.append(str.charAt(i));
            showStr.append(" ");
        }
        setText(showStr.toString());
    }

    private void clear() {
//        setTextClearable("");
        String text = getText().toString();
        if (text != null && text.length() > 0){
            /*if (sb_input.length() > 0){
                sb_input.delete(0,sb_input.length()-1);
            }*/
//            sb_input.
//            sb_input.append(text);
//            text.subSequence(0,text.length()-);
            String substring = text.substring(0, text.length() - 1);
            setTextClearable(substring);
        }
        if (mOnTextClearListener != null) {
            mOnTextClearListener.onTextClear(this);
        }

        /*if (mDrawableRight != null){
            super.setCompoundDrawables(null, null, mDrawableRight, null);
        }*/
    }

    public void setOnTextClearListener(OnTextClearListener onTextClearListener) {
        mOnTextClearListener = onTextClearListener;
    }

    public void finalize() throws Throwable {
        mDrawableRight = null;
        mBounds = null;
        super.finalize();
    }
}