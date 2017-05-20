package com.example.mycustomview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class CLJDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cljdialog);
    }

    public void startDialog(View view){
//        CLJDialog dialogFragment = new CLJDialog();
//        dialogFragment.show(getFragmentManager(),"dialog");
    }
}
