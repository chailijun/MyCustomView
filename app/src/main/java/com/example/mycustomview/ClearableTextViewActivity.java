package com.example.mycustomview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mycustomview.view.ClearableTextView;


public class ClearableTextViewActivity extends AppCompatActivity {


    private ClearableTextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_delete_icon_text_view);

        textview = (ClearableTextView) findViewById(R.id.ctvTest);
        textview.setTextClearable("Hello World!");

        textview.setOnTextClearListener(new ClearableTextView.OnTextClearListener() {

            @Override
            public void onTextClear(ClearableTextView v) {
                Toast.makeText(ClearableTextViewActivity.this, "On Text Cleared ", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
