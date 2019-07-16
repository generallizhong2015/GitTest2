package com.gsoft.keyhandover.fragment;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gsoft.keyhandover.adapters.Fragment2Adpter;
import com.gsoft.keyhandover.assist.CustomDatePicker;
import com.gsoft.keyhandover.R;
import com.gsoft.keyhandover.buz.RemouldBuz;
import com.gsoft.keyhandover.buz.SearchBuz;
import com.gsoft.keyhandover.entity.PowerSupplyEntity;
import com.gsoft.keyhandover.entity.TrackAsEntity1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Fragment2 extends Fragment implements View.OnClickListener {
    private RelativeLayout selectDate, selectTime;
    private TextView currentDate, currentTime;
    private CustomDatePicker customDatePicker1, customDatePicker2;
    private Button mSearch_GD_btn;
    private Spinner mtrack_as_sp;
    private RemouldBuz.PowerSupplyResult powerSupplyResult;
    private ArrayAdapter<String> adapter;
    private List<String> lists = new ArrayList<>();
    private List<PowerSupplyEntity> power_lists = new ArrayList<>();
    private RadioButton myiliewei_rbCheck, merliewei_rbRepair;
    private String LieWei;
    private String getTrack;
    private Fragment2Adpter fragment2Adpter;
    private ListView mPowerSupply_list;
    private TextView mgongdian_duandian_txt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, null);
        mgongdian_duandian_txt = view.findViewById(R.id.gongdian_duandian_txt);
        mgongdian_duandian_txt.setText("供电历史记录");
        mSearch_GD_btn = view.findViewById(R.id.Search_GD_btn);
        mSearch_GD_btn.setOnClickListener(this);
        myiliewei_rbCheck = view.findViewById(R.id.yiliewei_rbCheck);
        merliewei_rbRepair = view.findViewById(R.id.erliewei_rbRepair);
        selectTime = (RelativeLayout) view.findViewById(R.id.selectTime);
        selectTime.setOnClickListener(this);
        selectDate = (RelativeLayout) view.findViewById(R.id.selectDate);
        selectDate.setOnClickListener(this);
        currentDate = (TextView) view.findViewById(R.id.currentDate);
        currentTime = (TextView) view.findViewById(R.id.currentTime);
        mtrack_as_sp = view.findViewById(R.id.track_as_sp);
        mPowerSupply_list = view.findViewById(R.id.PowerSupply_list);

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, lists);
        mtrack_as_sp.setAdapter(adapter);

        mtrack_as_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                getTrack = mtrack_as_sp.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        fragment2Adpter = new Fragment2Adpter(getActivity(), power_lists);
        mPowerSupply_list.setAdapter(fragment2Adpter);
        initDatePicker();
        getTrackAs();
        return view;
    }

    //时间控件
    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        currentDate.setText(now);
        currentTime.setText(now);

        customDatePicker1 = new CustomDatePicker(getActivity(), new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                currentDate.setText(time);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker1.showSpecificTime(true); // 不显示时和分
        customDatePicker1.setIsLoop(true); // 不允许循环滚动

        customDatePicker2 = new CustomDatePicker(getActivity(), new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                currentTime.setText(time);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker2.showSpecificTime(true); // 显示时和分
        customDatePicker2.setIsLoop(true); // 允许循环滚动
    }

    //获取股道信息
    private void getTrackAs() {
        if (lists.size() > 0) {

        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final TrackAsEntity1 trackAs = SearchBuz.getTrackAsByCard();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (null != trackAs) {
                                for (TrackAsEntity1.TrackInfoListBean bean : trackAs.getTrackInfoList()) {
                                    lists.add(bean.getTrackName());
                                }
                                adapter.notifyDataSetChanged();
                            } else
                                Toast.makeText(getActivity(), "获取股道列位失败！", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).start();
        }

    }

    @SuppressLint("LongLogTag")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selectDate:
                // 日期格式为yyyy-MM-dd
                customDatePicker1.show(currentDate.getText().toString());
                break;

            case R.id.selectTime:
                // 日期格式为yyyy-MM-dd HH:mm
                customDatePicker2.show(currentTime.getText().toString());
                break;
            case R.id.Search_GD_btn:
                if (myiliewei_rbCheck.isChecked()) {
                    LieWei = "";
                    LieWei = "1";
                } else{
                    LieWei = "";
                    LieWei = "2";
                }
                power_lists.clear();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        powerSupplyResult = RemouldBuz.PowerSupply_GD(getTrack + "-" + LieWei, currentDate.getText().toString() + ":00", currentTime.getText().toString() + ":00");
                        Log.e("LZ---------", getTrack + "-" + LieWei + currentDate.getText().toString() + ":00" + "  " + currentTime.getText().toString() + ":00");
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (null != powerSupplyResult && null != powerSupplyResult.data) {
                                    power_lists.addAll(powerSupplyResult.data);
                                    fragment2Adpter.notifyDataSetChanged();
                                }
                            }
                        });

                    }
                }).start();

                break;
        }
    }
}
