package com.example.mycustomview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mycustomview.view.PullDownMenu;

import java.util.ArrayList;
import java.util.List;

public class PullDownMenuActivity extends AppCompatActivity {

    private PullDownMenu pullDownMenu;
    private PullDownMenu pullDownMenu2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_down_menu);

        init();
        init2();
    }

    private void init() {
        pullDownMenu=(PullDownMenu)findViewById(R.id.tvPullDownMenu);
        List<String> stringList=new ArrayList<>();
        stringList.add("语文");
        stringList.add("数学");
        stringList.add("英语");
        stringList.add("物理");
        stringList.add("物理");
        pullDownMenu.setData("选项学科",stringList,false);
    }

    private void init2() {
        pullDownMenu2=(PullDownMenu)findViewById(R.id.tvPullDownMenu2);
        List<String> stringList=new ArrayList<>();
        stringList.add("语文");
        stringList.add("数学");
        stringList.add("英语");
        stringList.add("物理");
        stringList.add("物理");
        pullDownMenu2.setData("选项学科",stringList,false);
    }
}
