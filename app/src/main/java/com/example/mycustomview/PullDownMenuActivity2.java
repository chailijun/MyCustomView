package com.example.mycustomview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mycustomview.view.PullDownMenu;
import com.example.mycustomview.view.PullDownMenu2;

import java.util.ArrayList;
import java.util.List;

public class PullDownMenuActivity2 extends AppCompatActivity {

    private PullDownMenu2 pullDownMenu;
    private PullDownMenu2 pullDownMenu2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_down_menu_2);

        init();
        init2();
    }

    private void init() {
        pullDownMenu=(PullDownMenu2)findViewById(R.id.tvPullDownMenu);
        List<String> stringList=new ArrayList<>();
        stringList.add("语文");
        stringList.add("数学");
        stringList.add("英语");
        stringList.add("物理");
        stringList.add("物理");
        pullDownMenu.setData("选项学科",stringList,false);
    }

    private void init2() {
        pullDownMenu2=(PullDownMenu2)findViewById(R.id.tvPullDownMenu2);
        List<String> stringList=new ArrayList<>();
        stringList.add("语文");
        stringList.add("数学");
        stringList.add("英语");
        stringList.add("物理");
        stringList.add("物理");
        pullDownMenu2.setData("选项学科",stringList,false);
    }
}
