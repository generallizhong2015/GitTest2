package com.gsoft.keyhandover.tools;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.TextView;

import com.gsoft.keyhandover.R;


public class LoadingDialog extends ProgressDialog {

    String title;
    OnKeyListener keylistener;


    public LoadingDialog(Context context, String title, OnKeyListener keylistener) {
        super(context, R.style.jrDialog);
        this.title=title;
        this.keylistener=keylistener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(getContext());
    }
    private void init(Context context) {
        setCancelable(true);
//        setCanceledOnTouchOutside(false);
        setContentView(R.layout.loading_dialog);//loading的xml文件
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        ((TextView) findViewById(R.id.titleTextView)).setText(title);
        getWindow().setAttributes(params);

//        if(keylistener==null){
//            keylistener = new OnKeyListener(){
//                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//                    if (keyCode== KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0)
//                    {
//                        return true;
//                    }
//                    else
//                    {
//                        return false;
//                    }
//                }
//            };
//        }

        this.setOnKeyListener(keylistener);
    }
    @Override
    public void show() {//开启
        super.show();
    }
    @Override
    public void dismiss() {//关闭
        super.dismiss();
    }
}
