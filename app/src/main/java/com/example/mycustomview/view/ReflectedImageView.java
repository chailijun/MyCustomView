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

    private final Bitmap originalImage;
    private String myText;

    private final int resourceId;

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

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ReflectedImageView2);
        resourceId = ta.getResourceId(R.styleable.ReflectedImageView2_background2, R.drawable.test);
        myText = ta.getString(R.styleable.ReflectedImageView2_mytext);
        ta.recycle();
        originalImage = BitmapFactory.decodeResource(context.getResources(), resourceId);

        rootView = View.inflate(getContext(), R.layout.reflectedimageview_layout, this);
        textView = (TextView) rootView.findViewById(R.id.textview);
        imageView = (ImageView) rootView.findViewById(R.id.reflectionImage);

        textView.setBackgroundResource(resourceId);
        textView.setText(myText);
        setReflectedImage(imageView);
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
