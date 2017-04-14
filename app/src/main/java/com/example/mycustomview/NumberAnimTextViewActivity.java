package com.example.mycustomview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mycustomview.view.NumberAnimTextView;

public class NumberAnimTextViewActivity extends AppCompatActivity {

    private NumberAnimTextView mNumberAnimTextView;

    private NumberAnimTextView mNumberAnimTextView1;

    private NumberAnimTextView mNumberAnimTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_anim_text_view);

        mNumberAnimTextView = (NumberAnimTextView) findViewById(R.id.text);

        mNumberAnimTextView1 = (NumberAnimTextView) findViewById(R.id.text1);

        mNumberAnimTextView2 = (NumberAnimTextView) findViewById(R.id.text2);
    }

    public void start(View view) {

        mNumberAnimTextView.setPrefixString("¥");

        mNumberAnimTextView.setNumberString("99998.123456789");



//        mNumberAnimTextView1.setEnableAnim(true);

        mNumberAnimTextView1.setNumberString("350");

        mNumberAnimTextView2.setPostfixString("%");

        mNumberAnimTextView2.setNumberString("99.75");



    }
}
