package com.gsoft.keyhandover;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.gsoft.keyhandover.fragment.D_A_ReturnFragment;
import com.gsoft.keyhandover.fragment.D_A_LoanFragmnet;
import com.gsoft.keyhandover.fragment.KeyFragment1;
import com.gsoft.keyhandover.fragment.KeyFragment2;

/**
 * Created by Administrator on 2018/11/7.
 */

public class Loan_Activty extends FragmentActivity {
    // 定义FragmentTabHost对象
    private FragmentTabHost mTabHost;
    // 定义一个布局
    private LayoutInflater layoutInflater;
    private Class fragmentArray[] = {D_A_LoanFragmnet.class, D_A_ReturnFragment.class};
    private int mImageViewArray[] = {R.drawable.tab_finding, R.drawable.tab_recording};
    private String mTextViewArray[] = {"借用", "归还"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_a_tabhoast);
        initView();
    }

    /****** 初始化组件 *******/
    private void initView() {
        layoutInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        int count = fragmentArray.length;
        for (int i = 0; i < count; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextViewArray[i])
                    .setIndicator(getTabItemView(i));

            mTabHost.addTab(tabSpec, fragmentArray[i], null);

            mTabHost.getTabWidget().getChildAt(i)
                    .setBackgroundResource(R.drawable.selector_tab_background);
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
