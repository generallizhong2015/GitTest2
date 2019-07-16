package com.gsoft.keyhandover.tools.suredialog;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.gsoft.keyhandover.R;


/**
 * Created by Administrator on 2018/7/18.
 */

public class SureDialog {
    //点击确认按钮回调接口
    public interface OnConfirmListener {
        void onConfirmClick();
    }

    public static void show(Activity activity, String content, final OnConfirmListener confirmListener) {
        // 加载布局文件
        View view = View.inflate(activity, R.layout.suredialog, null);
        TextView text = (TextView) view.findViewById(R.id.text);
        TextView confirm = (TextView) view.findViewById(R.id.confirm);
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        if (!DensityUtil.isNullOrEmpty(content)) {
            text.setText(content);
        }
        // 创建
//        Dialog dialogs;
        final AlertDialog dialog = new AlertDialog.Builder(activity).create();
        dialog.setCancelable(false);
        // 设置点击dialog以外区域不取消
        dialog.show();
        dialog.setContentView(view);
        dialog.getWindow().setLayout(DensityUtil.getWidth(activity) / 3 * 2,
                ActionBar.LayoutParams.WRAP_CONTENT);
        //设置弹出框宽度为屏幕宽度的三分之二 // 确定
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                confirmListener.onConfirmClick();
            }
        });
        // 取消
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


}



