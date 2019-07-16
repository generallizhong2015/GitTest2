package com.zhengsr.emaildemo;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2019/7/15.
 */

public class ErinieShow extends RelativeLayout {
    int level;
    Context mContext;
    RelativeLayout rubberBG;// 最底层奖励等级
    RubberShow mRubberShow;// 橡皮擦
    Button mButton;
    int rubberBGID = 10001;
    int mButtonID = 10002;

    public ErinieShow(Context context, int level) {
        super(context);
        // TODO Auto-generated constructor stub
        this.mContext = context;
        this.level = level;
        getElement();// 得到子元素
        setElementLP();// 设置布局参数

        // 初始化彩票了
        setElementStyle();

        // 设置橡皮檫了
        setElement();
    }

    private void setElement() {
        // 第一步在彩票上面画一个图层
        mRubberShow.beginRubber(Color.parseColor("#d3d3d3"), 30, 10);
    }

    private void setElementStyle() {
        switch (level) {
            case 1:
                rubberBG.setBackgroundResource(R.mipmap.ic_launcher);
                break;
            case 2:
                rubberBG.setBackgroundResource(R.mipmap.ic_launcher);
                break;
            case 3:
                rubberBG.setBackgroundResource(R.mipmap.ic_launcher);
                break;

            default:
                break;
        }
    }

    /**
     * 给布局的子元素设置布局参数
     */
    private void setElementLP() {
        // TODO Auto-generated method stub
        RelativeLayout.LayoutParams rubber_bg_lp = new RelativeLayout.LayoutParams(
                350, 80);
        rubberBG.setLayoutParams(rubber_bg_lp);
        mRubberShow.setLayoutParams(rubber_bg_lp);
        // rubber_bg_lp正下方
        RelativeLayout.LayoutParams rubber_btn_lp = new RelativeLayout.LayoutParams(
                -2, -2);
        rubber_btn_lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        rubber_btn_lp.addRule(RelativeLayout.BELOW, rubberBGID);
        mButton.setLayoutParams(rubber_btn_lp);
        mButton.setClickable(false);
    }

    /**
     * 获取布局的子元素
     */
    private void getElement() {
        // TODO Auto-generated method stub
        rubberBG = new RelativeLayout(mContext);// 得到彩票
        mRubberShow = new RubberShow(mContext, level);// 得到橡皮擦
        mButton = new Button(mContext);
        rubberBG.setId(rubberBGID);
        mButton.setId(mButtonID);
        mButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

            }
        });
        rubberBG.addView(mRubberShow);
        addView(rubberBG);
        addView(mButton);
    }
}
