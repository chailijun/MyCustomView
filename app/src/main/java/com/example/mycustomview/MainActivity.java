package com.example.mycustomview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listview;

    private List<String> datas;

    private String[] allItem = {"带倒影的ImageView",
            "带下拉选择的popuWindow",
    "ViewPager切换动画",
    "带旋转指针的switchButton",
    "TextView中数字带动画",
    "可订制的下拉选择框",
    "自定义样式Dialog",
    "点击Button回弹效果",
    "TestActivity",
            "结尾带删除图标的TextView"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.listview);

        datas = new ArrayList<>();
        //添加数据
        for (String item : allItem) {
            datas.add(item);
        }

        MyAdapter adapter = new MyAdapter();
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0://带倒影的ImageView
                        startActivity(new Intent(MainActivity.this,ReflectedImageViewActivity.class));
                        break;
                    case 1://带下拉选择的popuWindow
                        startActivity(new Intent(MainActivity.this,PullDownMenuActivity.class));
                        break;
                    case 2://ViewPager切换动画
                        startActivity(new Intent(MainActivity.this,JazzyViewPagerActivity.class));
                        break;
                    case 3://带旋转指针的switchButton
                        startActivity(new Intent(MainActivity.this,RotateSwitchButtonActivity.class));
                        break;
                    case 4://TextView中数字带动画
                        startActivity(new Intent(MainActivity.this,NumberAnimTextViewActivity.class));
                        break;
                    case 5://可订制的下拉选择框，可设置一列选择条件或多列选择条件
                        startActivity(new Intent(MainActivity.this,PullDownMenuActivity2.class));
                        break;
                    case 6://自定义样式Dialog
                        startActivity(new Intent(MainActivity.this,CLJDialogActivity.class));
                        break;
                    case 7://点击Button回弹效果
                        startActivity(new Intent(MainActivity.this,SpringbackButtonActivity.class));
                        break;
                    case 8://TestActivity
                        startActivity(new Intent(MainActivity.this,TestActivity.class));
                        break;
                    case 9://结尾带删除图标的TextView
                        startActivity(new Intent(MainActivity.this,ClearableTextViewActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.main_list_item, null);
            }

            TextView textView = (TextView) convertView.findViewById(R.id.menuitem);
            textView.setText(datas.get(position));


            return convertView;
        }
    }
}
