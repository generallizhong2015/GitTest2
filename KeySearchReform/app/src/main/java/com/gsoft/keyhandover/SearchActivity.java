package com.gsoft.keyhandover;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.gsoft.keyhandover.R;
import com.gsoft.keyhandover.fragment.Fragment1;
import com.gsoft.keyhandover.fragment.Fragment2;
import com.gsoft.keyhandover.fragment.Fragment3;

public class SearchActivity extends FragmentActivity {
    // 定义FragmentTabHost对象
    private FragmentTabHost mTabHost;
    // 定义一个布局
    private LayoutInflater layoutInflater;
    private Class fragmentArray[] = {Fragment1.class, Fragment2.class,
            Fragment3.class};
    private int mImageViewArray[] = {R.drawable.dengdingka,
            R.drawable.gongdianlishi, R.drawable.tab_me};
    private String mTextViewArray[] = {"登顶卡", "供电", "断电"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);
        initView();
    }

    /****** 初始化组件 *******/
    private void initView() {
        // 实例化布局对象
        layoutInflater = LayoutInflater.from(this);
        // 实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        try {
            int count = fragmentArray.length;
            for (int i = 0; i < count; i++) {
                TabSpec tabSpec = mTabHost.newTabSpec(mTextViewArray[i])
                        .setIndicator(getTabItemView(i));

                mTabHost.addTab(tabSpec, fragmentArray[i], null);
                mTabHost.getTabWidget().getChildAt(i)
                        .setBackgroundResource(R.drawable.selector_tab_background);
            }
        } catch (Exception e) {

        }
    }

    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);
        ImageView imageview = (ImageView) view.findViewById(R.id.tabimage);
        imageview.setImageResource(mImageViewArray[index]);
        TextView textview = (TextView) view.findViewById(R.id.textview);
        textview.setText(mTextViewArray[index]);
        return view;
    }

}
