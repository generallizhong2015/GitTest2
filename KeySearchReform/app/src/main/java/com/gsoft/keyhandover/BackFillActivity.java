package com.gsoft.keyhandover;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gsoft.keyhandover.buz.RemouldBuz;
import com.gsoft.keyhandover.entity.AtitleEntity2;
import com.gsoft.keyhandover.entity.QtitleEntity2;
import com.gsoft.keyhandover.entity.ServerResponse;
import com.gsoft.keyhandover.util.L;
import com.gsoft.keyhandover.util.StringUtils;

/**
 * Created by Administrator on 2018/2/1.
 */

public class BackFillActivity extends Activity implements View.OnClickListener {

    private TextView carName, carrageName, taskName, atitleName;
    private RadioGroup radioSS, radioGZ;
    private EditText desET;
    private TextView confirmBtn;
    private AtitleEntity2 entity;
    private QtitleEntity2 qentity;
    private String trainOrder;
    private String trainName;
    private String detailNodeId;
    private TextView mtextview_title;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backfill);
        getIntentData();
        initView();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (null != intent) {
            if (BaseApplication.instance().isZJ) {
                qentity = intent.getParcelableExtra("atitle");
            } else {
                entity = intent.getParcelableExtra("atitle");
            }
            detailNodeId = intent.getStringExtra("detailNodeId");
            trainOrder = intent.getStringExtra("trainOrder");
            trainName = intent.getStringExtra("trainName");
        }
    }

    private void initView() {
        findViewById();
        confirmBtn.setOnClickListener(this);
        if (BaseApplication.instance().isZJ) {
            mtextview_title.setText("改造质检");
            radioGZ.setVisibility(View.VISIBLE);
            radioSS.setVisibility(View.GONE);
            if (null != qentity) {
                carName.setText(trainName);
                carrageName.setText(trainOrder);
                taskName.setText(qentity.getRiskTip());
                atitleName.setText(qentity.getNodeName());
            }
        } else {
            mtextview_title.setText("改造实施");
            radioGZ.setVisibility(View.GONE);
            radioSS.setVisibility(View.VISIBLE);
            if (null != entity) {
                carName.setText(trainName);
                carrageName.setText(trainOrder);
                taskName.setText(entity.getRiskTip());
                atitleName.setText(entity.getNodeName());
            }
        }
    }

    public void findViewById() {
        mtextview_title = findViewById(R.id.textview_title);
        carName = findViewById(R.id.car_name);
        carrageName = findViewById(R.id.carrage_name);
        taskName = findViewById(R.id.task_name);
        atitleName = findViewById(R.id.atitle_name);
        radioSS = findViewById(R.id.state_name_shishi);
        radioGZ = findViewById(R.id.state_name_zhijian);
        desET = findViewById(R.id.des_et);
        confirmBtn = findViewById(R.id.confirm_btn);
    }

    @Override
    public void onClick(View view) {
        confirm();
    }

    void confirm() {
        RadioButton radioButton;
        if (BaseApplication.instance().isZJ) {
            radioButton = findViewById(radioGZ.getCheckedRadioButtonId());
        } else {
            radioButton = findViewById(radioSS.
                    getCheckedRadioButtonId());
        }

        if (null == radioButton) {
            Toast.makeText(this, "没有选择完成状态！", Toast.LENGTH_SHORT).show();
            return;
        }
        String qualifiedS = radioButton.getText().toString();
        boolean qualified = false;
        if (StringUtils.isEmpty(qualifiedS)) {
            return;
        }
        if (qualifiedS.equals("不合格") && StringUtils.isEmpty(desET)) {
            Toast.makeText(BackFillActivity.this, "不合格必须要备注哟！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (qualifiedS.equals("不合格") || qualifiedS.equals("未完成")) {
            qualified = false;
        } else if (qualifiedS.equals("合格") || qualifiedS.equals("完成")) {
            qualified = true;
        }
        final boolean QUALIFIED = qualified;
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (BaseApplication.instance().isZJ) {
                    final ServerResponse response = RemouldBuz.backFillQ(detailNodeId,
                            desET.getText().toString().trim(), QUALIFIED,
                            BaseApplication.instance().user.getRoleCode(),
                            BaseApplication.instance().user.getDeptCode(),
                            BaseApplication.instance().user.getStuffName(),
                            "2");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (response.code == 200 || response.code == 500) {
                                    BackFillActivity.this.finish();
                                } else {
                                    Toast.makeText(BackFillActivity.this, response.msg.toString(), Toast.LENGTH_SHORT).show();
                                }
                                Toast.makeText(BackFillActivity.this, response.msg.toString(), Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {

                            }
                        }
                    });
                } else {
                    try {
                        final ServerResponse response = RemouldBuz.backFill(detailNodeId,
                                desET.getText().toString().trim(), QUALIFIED,
                                BaseApplication.instance().user.getRoleCode(),
                                BaseApplication.instance().user.getDeptCode(),
                                BaseApplication.instance().user.getStuffName(),
                                "1");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                L.i("lz", "response.code:" + response.code);
                                if (response.code == 200 || response.code == 500) {
                                    BackFillActivity.this.finish();
                                } else {
                                    Toast.makeText(BackFillActivity.this, response.msg, Toast.LENGTH_SHORT).show();
                                }
                                try {
                                    Toast.makeText(BackFillActivity.this, response.msg.toString(), Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {

                                }
                            }
                        });
                    } catch (Exception e) {
                        return;
                    }
                }
            }
        }).start();


    }
}
