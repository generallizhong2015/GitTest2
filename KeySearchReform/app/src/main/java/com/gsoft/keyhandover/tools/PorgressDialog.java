package com.gsoft.keyhandover.tools;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.gsoft.keyhandover.R;


/**
 * Created by Administrator on 2018/1/7/007.
 */

public class PorgressDialog extends Dialog {
    private Context mContext;
    RingProgressBar progressBar;

    public PorgressDialog(Context context) {
        super(context);
        getWindow().setDimAmount(0.5f);//设置昏暗度为0
        this.mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialo_porgressg, null);
        progressBar = (RingProgressBar) view.findViewById(R.id.progress_bar);
        setContentView(view);
        setCancelable(false);
        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }


    public void setProgress(int progress) {
        progressBar.setProgress(progress);
    }

}
