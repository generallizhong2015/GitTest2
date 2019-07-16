package com.gsoft.keyhandover.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gsoft.keyhandover.R;

/**
 * Created by lz on 2017/3/15.
 * meail: 992759969@qq.com
 */

public class TitleBarOrdinary extends LinearLayout implements View.OnClickListener{
    private TextView leftText;
    private Context context;
    private TextView titleText;
    LinearLayout rightView;
    private ImageView titleImg;
    private boolean isItentBack;
    private boolean canClick;

    public TitleBarOrdinary(Context context) {
        this(context, null);
    }

    public TitleBarOrdinary(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        LayoutInflater.from(context).inflate(R.layout.view_titlebar_ordinary, this);
        titleText = (TextView) findViewById(R.id.ordinary_titlebar_txt);
        leftText = (TextView) findViewById(R.id.ordinary_titlebar_left_txt);
        titleImg = (ImageView) findViewById(R.id.ordinary_titlebar_msg_img);
        rightView = (LinearLayout) findViewById(R.id.ordinary_titlebar_right);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.OrdinaryTitleBar);
        titleText.setText(typedArray.getString(R.styleable.OrdinaryTitleBar_ordinarytitle));
        boolean showBackImg = typedArray.getBoolean(R.styleable.OrdinaryTitleBar_showBackImg, true);
        isItentBack = typedArray.getBoolean(R.styleable.OrdinaryTitleBar_setintentback, false);
        canClick = typedArray.getBoolean(R.styleable.OrdinaryTitleBar_canclick, true);
        if (!showBackImg)titleImg.setVisibility(GONE);
        if (canClick){
            leftText.setOnClickListener(this);
            titleImg.setOnClickListener(this);
        }
    }

    public void settitle(String title) {
        titleText.setText(title);
    }

    public void setLeftText(String text) {
        leftText.setText(text);
    }

    public ImageView getTitleImg(){
        return titleImg;
    }

    public void setRight(View view) {
        rightView.addView(view);
    }

    @Override
    public void onClick(View v) {
        //返回上一级   也可以是代餐返回
        if (isItentBack){
            ((Activity) context).setResult(Activity.RESULT_OK);
            ((Activity) context).finish();
        }else{
            ((Activity) context).finish();
        }
    }
}