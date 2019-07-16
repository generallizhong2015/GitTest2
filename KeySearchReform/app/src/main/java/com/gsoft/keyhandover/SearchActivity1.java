package com.gsoft.keyhandover;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gsoft.keyhandover.buz.SearchBuz;
import com.gsoft.keyhandover.entity.SearchInfoEntity;
import com.gsoft.keyhandover.entity.TrackAsEntity1;
import com.gsoft.keyhandover.util.L;
import com.gsoft.keyhandover.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class SearchActivity1 extends Activity implements View.OnClickListener {
    Spinner trackasSp;
    TextView ddkLendNum, ddkReturnNum, aqdLendNum, aqdReturnNum;
    Button searchBtn;
    ArrayAdapter<String> adapter;
    List<String> lists = new ArrayList<>();
    RadioButton myiliewei_rbCheck, erliewei_rbRepair;
    String LieWei;
    public List<TrackAsEntity1> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        findViewById();
        (searchBtn = findViewById(R.id.btn)).setOnClickListener(this);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, lists);
        trackasSp.setAdapter(adapter);
        getTrackAs();
    }

    public void findViewById() {
        trackasSp = findViewById(R.id.track_as);
        ddkLendNum = findViewById(R.id.jie_deng_num);
        ddkReturnNum = findViewById(R.id.jie_an_num);
        aqdLendNum = findViewById(R.id.gui_deng_num);
        aqdReturnNum = findViewById(R.id.gui_an_num);
        myiliewei_rbCheck = findViewById(R.id.yiliewei_rbCheck);
        erliewei_rbRepair = findViewById(R.id.erliewei_rbRepair);
    }

    @Override
    public void onClick(View view) {
        try {
            if (view == searchBtn) {
                if (!StringUtils.isEmpty(trackasSp.getSelectedItem().toString())) {
                    submit(trackasSp.getSelectedItem().toString());
                } else {
                    Toast.makeText(this, "股道不能为空！", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            return;
        }
    }

    //获取股道信息
    private void getTrackAs() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final TrackAsEntity1 trackAs = SearchBuz.getTrackAsByCard();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (null != trackAs) {
                            for (TrackAsEntity1.TrackInfoListBean bean : trackAs.getTrackInfoList()) {
                                lists.add(bean.getTrackName());
                            }
                            adapter.notifyDataSetChanged();
                        } else
                            Toast.makeText(SearchActivity1.this, "获取股道列位失败！", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    //  请求安全带登顶卡信息
    void submit(final String tracka) {
        ddkLendNum.setText("");
        ddkReturnNum.setText("");
        aqdLendNum.setText("");
        aqdReturnNum.setText("");

        if (myiliewei_rbCheck.isChecked()) {
            LieWei = "";
            LieWei = "1";
        } else if (erliewei_rbRepair.isChecked()) {
            LieWei = "";
            LieWei = "2";
        } else {
            Toast.makeText(SearchActivity1.this, "列位还未选择", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                final SearchBuz.SearchInfoResult result = SearchBuz.submit(tracka + "-" + LieWei);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (null != result && result.code == 200) {
                            SearchInfoEntity info = result.data;
                            ddkLendNum.setText(info.getCardOutNumber() + "");
                            ddkReturnNum.setText(info.getCardInNumber() + "");
                            aqdLendNum.setText(info.getBeltOutNumber() + "");
                            aqdReturnNum.setText(info.getBeltInNumber() + "");
                        } else {
                            L.i("lz", "请求失败！。。。。。。。。。");
                        }
                    }
                });
            }
        }).start();
    }
}
