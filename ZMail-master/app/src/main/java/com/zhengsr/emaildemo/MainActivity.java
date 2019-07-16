package com.zhengsr.emaildemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;


public class MainActivity extends Activity {
    RelativeLayout container;
    Button btn;
    ErinieShow erinieShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = (RelativeLayout) findViewById(R.id.container);
        btn = (Button) findViewById(R.id.enterbtn);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showEnrie();
            }

        });
    }

    private void showEnrie() {
        // TODO Auto-generated method stub
        // 移除所有子元素
        container.removeAllViews();
        // 产生一个彩票
        int level = getLevel();
        erinieShow = new ErinieShow(this, level);
        container.addView(erinieShow, new ViewGroup.LayoutParams(-2, -2));
    }

    /**
     * 获取奖励等级
     *
     * @return
     */
    private int getLevel() {
        // TODO Auto-generated method stub
        double d = Math.random() * 100;
        if (d < 50) {
            return 3;
        }
        if (d < 90) {
            return 2;
        }
        return 1;
    }

}
