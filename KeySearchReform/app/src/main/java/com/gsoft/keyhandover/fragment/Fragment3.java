package com.gsoft.keyhandover.fragment;

//import android.app.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gsoft.keyhandover.adapters.Fragment3Adpter;
import com.gsoft.keyhandover.assist.CustomDatePicker;
import com.gsoft.keyhandover.R;
import com.gsoft.keyhandover.buz.RemouldBuz;
import com.gsoft.keyhandover.buz.SearchBuz;
import com.gsoft.keyhandover.entity.OutageEntity;
import com.gsoft.keyhandover.entity.TrackAsEntity1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Fragment3 extends Fragment implements View.OnClickListener {
    private LinearLayout mis_GongDian;
    private LinearLayout mis_Dudian;
    private RelativeLayout selectDate, selectTime;
    private TextView currentDate, currentTime;
    private CustomDatePicker customDatePicker1, customDatePicker2;
    private Button mSearch_GD_btn;
    private Spinner mtrack_as_sp;
    private RemouldBuz.OutageResult outageResult;
    private ArrayAdapter<String> adapter;
    private List<String> lists = new ArrayList<>();
    private List<OutageEntity> outage_lists = new ArrayList<>();
    private RadioButton myiliewei_rbCheck, merliewei_rbCheck;
    private String LieWei;
    private String getTrack;
    private Fragment3Adpter fragment3Adpter;
    private ListView mPowerSupply_list;
    private TextView mgongdian_duandian_txt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, null);
        mgongdian_duandian_txt = view.findViewById(R.id.gongdian_duandian_txt);
        mgongdian_duandian_txt.setText("断电历史记录");
        mis_GongDian = view.findViewById(R.id.is_GongDian);
        mis_Dudian = view.findViewById(R.id.is_Dudian);
        mis_Dudian.setVisibility(View.VISIBLE);
        mis_GongDian.setVisibility(View.GONE);
        mSearch_GD_btn = view.findViewById(R.id.Search_GD_btn);
        mSearch_GD_btn.setOnClickListener(this);
        myiliewei_rbCheck = view.findViewById(R.id.yiliewei_rbCheck);
        merliewei_rbCheck = view.findViewById(R.id.erliewei_rbRepair);
        selectTime = view.findViewById(R.id.selectTime);
        selectTime.setOnClickListener(this);
        selectDate = view.findViewById(R.id.selectDate);
        selectDate.setOnClickListener(this);
        currentDate = view.findViewById(R.id.currentDate);
        currentTime = view.findViewById(R.id.currentTime);
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
        fragment3Adpter = new Fragment3Adpter(getActivity(), outage_lists);
        mPowerSupply_list.setAdapter(fragment3Adpter);
        initDatePicker();
        getTrackAs();
        return view;
    }

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
                outage_lists.clear();
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            outageResult = RemouldBuz.Outage_DD(getTrack + "-" + LieWei, currentDate.getText().toString() + ":00", currentTime.getText().toString() + ":00");
//                        Log.e("LZ---------", getTrack + "-" + LieWei + currentDate.getText().toString() + ":00" + "  " + currentTime.getText().toString() + ":00");
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (null != outageResult && null != outageResult.data) {
                                        outage_lists.addAll(outageResult.data);
                                        fragment3Adpter.notifyDataSetChanged();
                                    }
                                }
                            });

                        }
                    }).start();
                } catch (Exception e) {
                    return;
                }
                break;
        }
    }
}
