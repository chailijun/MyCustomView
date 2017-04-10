package com.example.mycustomview.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.mycustomview.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by PVer on 2017/4/10.
 * 带下拉选择的popuWindow
 */

public class PullDownMenu extends LinearLayout {

    private static final String ITEM = "item";

    private int selectedPosition = -1;

    // 选中之后是否需要搜索
    private boolean needSearch;

    // 下拉框
    private PopupWindow popupWindow;

    // 下拉框数据
    private List<String> mStringList;

    // 顶部位置
    private TextView tvPullDown;

    // 顶部位置显示的内容
    private String topTitle;

    private ListView listView;

    private View viewList;

    private SimpleAdapter simpleAdapter;

    private MyAdapter adapter;

    private List<HashMap<String, String>> hashMaps;

    public PullDownMenu(Context context) {
        this(context, null);
    }

    public PullDownMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullDownMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.pull_down_menu_layout, null);
        addView(view, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        tvPullDown = (TextView) findViewById(R.id.tvPullDown);

        //设置向下箭头
        setNavDown(context,tvPullDown);

        hashMaps = new ArrayList<HashMap<String, String>>();
//		// 模拟加载数据
//		loadData();
        tvPullDown.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                } else {
                    viewList = LayoutInflater.from(getContext()).inflate(R.layout.pop_menulist, null);
                    listView = (ListView) viewList.findViewById(R.id.menulist);
                    simpleAdapter = new SimpleAdapter(getContext(), hashMaps, R.layout.pop_menuitem,
                            new String[]{"item"}, new int[]{R.id.menuitem});
                    adapter = new MyAdapter();

//                    listView.setAdapter(simpleAdapter);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String stringItem = hashMaps.get(position).get("item");
                            tvPullDown.setText(stringItem);
                            if (popupWindow != null && popupWindow.isShowing()) {
                                popupWindow.dismiss();
                            }

                            selectedPosition = position;

                            // 选择菜单后进行的操作
                            search();
                        }
                    });
                    popupWindow = new PopupWindow(viewList, tvPullDown.getWidth(), LayoutParams.WRAP_CONTENT);
                    ColorDrawable cd = new ColorDrawable(0x00000);
                    popupWindow.setBackgroundDrawable(cd);
                    popupWindow.setAnimationStyle(R.style.PopupAnimation);
                    popupWindow.update();
                    popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
                    popupWindow.setTouchable(true);
                    popupWindow.setOutsideTouchable(true);
                    popupWindow.setFocusable(true);

                    int topHeight = tvPullDown.getBottom();
                    popupWindow.showAsDropDown(tvPullDown, 0, 0);
                    popupWindow.setTouchInterceptor(new OnTouchListener() {

                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                                popupWindow.dismiss();
                                return true;
                            }
                            return false;
                        }
                    });

                    popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            //设置向下箭头
                            setNavDown(context,tvPullDown);
                        }
                    });

                    setNavUp(context,tvPullDown);
                }
            }
        });
    }

    /**
     * 设置向上箭头
     * @param context
     */
    private void setNavUp(Context context,TextView tvPullDown) {
        if (context == null || tvPullDown == null){
            return;
        }
        Drawable nav_up = ContextCompat.getDrawable(context, R.drawable.up);
        nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
        tvPullDown.setCompoundDrawables(null, null, nav_up, null);
    }

    /**
     * 设置向下的箭头
     * @param context
     */
    private void setNavDown(Context context,TextView tvPullDown) {
        if (context == null || tvPullDown == null){
            return;
        }
        Drawable nav_down = ContextCompat.getDrawable(context, R.drawable.down);
        nav_down.setBounds(0, 0, nav_down.getMinimumWidth(), nav_down.getMinimumHeight());
        tvPullDown.setCompoundDrawables(null, null, nav_down, null);
    }

    private void search() {

    }

    public void setData(String title, List<String> strings, boolean needSearch) {
        this.topTitle = title;
        this.mStringList = strings;
        this.needSearch = needSearch;
        tvPullDown.setText(topTitle);
        if (hashMaps != null) {
            hashMaps.clear();
        }
        for (String string : mStringList) {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put(ITEM, string);
            hashMaps.add(hashMap);
        }
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return hashMaps.size();
        }

        @Override
        public Object getItem(int position) {
            return hashMaps.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.pop_menuitem,null);
            }

            TextView textView = (TextView) convertView.findViewById(R.id.menuitem);
            textView.setText(hashMaps.get(position).get(ITEM));

            ImageView iv_check = (ImageView) convertView.findViewById(R.id.iv_check);
            if (position == selectedPosition) {
                iv_check.setVisibility(VISIBLE);
            } else {
                iv_check.setVisibility(GONE);
            }

            return convertView;
        }
    }

}
