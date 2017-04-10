package com.example.mycustomview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mycustomview.R;

/**
 * Created by PVer on 2017/4/10.
 * 带倒影的ImageView
 */

public class ReflectedImageView extends LinearLayout {

    private static final float DEFAULT_TEXT_SIZE = 12.0f;
    private Bitmap originalImage;
    private String myText;
    private int textColor;
    private float textSize;

    private int resourceId = -1;

    private View rootView = null;

    private TextView textView = null;
    private ImageView imageView = null;

    public ReflectedImageView(Context context) {
        this(context, null);
    }

    public ReflectedImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ReflectedImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);

        if (resourceId != -1) {
            originalImage = BitmapFactory.decodeResource(context.getResources(), resourceId);

            rootView = View.inflate(getContext(), R.layout.reflectedimageview_layout, this);
            textView = (TextView) rootView.findViewById(R.id.textview);
            imageView = (ImageView) rootView.findViewById(R.id.reflectionImage);

            textView.setBackgroundResource(resourceId);
            textView.setText(myText);
            textView.setTextColor(textColor);
            textView.setTextSize(textSize);
            setReflectedImage(imageView);
        }
    }

    private void init(Context context, @Nullable AttributeSet attrs) {

        // 将SP单位默认值转为PX
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        textSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                DEFAULT_TEXT_SIZE, displayMetrics);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ReflectedImageView);
        resourceId = ta.getResourceId(R.styleable.ReflectedImageView_background, -1);
        myText = ta.getString(R.styleable.ReflectedImageView_text);
        textColor = ta.getColor(R.styleable.ReflectedImageView_textColor, 0x000000);
        textSize = ta.getDimension(R.styleable.ReflectedImageView_textSize, textSize);
        ta.recycle();
    }

    private void setReflectedImage(ImageView imageView) {
        if (originalImage == null || imageView == null) {
            return;
        }

        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        //倒影图片的高度
        int reflectionImageHeight = height / 5;

        Matrix matrix = new Matrix();
        // 图片矩阵变换（从低部向顶部的倒影）
        matrix.preScale(1, -1);
        // 截取原图下半部分
        Bitmap reflectionImage =
                Bitmap.createBitmap(originalImage, 0, height - reflectionImageHeight,
                        width, reflectionImageHeight, matrix, false);
        // 创建倒影图片（高度为原图1/2）  
        Bitmap bitmapWithReflection =
                Bitmap.createBitmap(width, reflectionImageHeight, Bitmap.Config.ARGB_8888);

        // 绘制倒影图 
        Canvas canvas = new Canvas(bitmapWithReflection);
        // 绘制倒影图
        canvas.drawBitmap(reflectionImage, 0, 0, null);

        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, 0, 0, bitmapWithReflection.getHeight(),
                0x70ffffff, 0x00ffffff, Shader.TileMode.CLAMP);
        // 线性渐变效果
        paint.setShader(shader);
        // 倒影遮罩效果
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        // 绘制倒影的阴影效果
        canvas.drawRect(0, 0, width, bitmapWithReflection.getHeight(), paint);

        // 设置倒影图片
        imageView.setImageBitmap(bitmapWithReflection);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
    }
}
