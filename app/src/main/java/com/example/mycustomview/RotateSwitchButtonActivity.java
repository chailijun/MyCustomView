package com.example.mycustomview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mycustomview.view.RotateSwitchButton;

public class RotateSwitchButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_switch_button);

        ((RotateSwitchButton) findViewById(R.id.rotate))
                .setSelectListener(new RotateSwitchButton.SelectListener() {
            @Override
            public void leftSelected() {
                Toast.makeText(RotateSwitchButtonActivity.this, "left on", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void centerSelected() {
                Toast.makeText(RotateSwitchButtonActivity.this, "center", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightSelected() {
                Toast.makeText(RotateSwitchButtonActivity.this, "right on", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
