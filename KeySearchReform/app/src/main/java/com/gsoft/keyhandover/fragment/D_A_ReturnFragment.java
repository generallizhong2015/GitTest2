package com.gsoft.keyhandover.fragment;

import android.annotation.SuppressLint;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gsoft.keyhandover.R;
import com.gsoft.keyhandover.adapters.D_A_Loan_Fragment1Adpter;
import com.gsoft.keyhandover.assist.CustomDatePicker;
import com.gsoft.keyhandover.buz.RemouldBuz;
import com.gsoft.keyhandover.buz.SearchBuz;
import com.gsoft.keyhandover.entity.Loan_RetrunEntity;
import com.gsoft.keyhandover.entity.TrackAsEntity1;

import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by lz on 2018/11/7.
 */

public class D_A_ReturnFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout selectDate, selectTime;
    private TextView currentDate, currentTime;
    private CustomDatePicker customDatePicker1, customDatePicker2;
    private RemouldBuz.D_A_D_A_Loan_retrun_Result d_a_d_a_loan_retrun_result;
    private String LieWei;
    private String getTrack;
    private RadioButton myiliewei_rbCheck, merliewei_rbRepair;
    private List<String> lists = new ArrayList<>();
    private List<List<Loan_RetrunEntity>> loan_lists = new ArrayList<>();
    private Spinner mtrack_as_sp;
    private Button mSearch_GD_btn;
    private ArrayAdapter<String> adapter;
    private D_A_Loan_Fragment1Adpter d_a_loan_fragment1Adpter;

    private ListView md_a_loan__list;
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    ;
    Map map;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.d_a_fragmentxml, null);
//        mgongdian_duandian_txt = view.findViewById(R.id.gongdian_duandian_txt);
//        mgongdian_duandian_txt.setText("归还");
        mSearch_GD_btn = view.findViewById(R.id.d_a_loan_Search_GD_btn);
        mSearch_GD_btn.setOnClickListener(this);
        myiliewei_rbCheck = view.findViewById(R.id.d_a_loan_yiliewei_rbCheck);
        merliewei_rbRepair = view.findViewById(R.id.d_a_loan_erliewei_rbCheck);
        selectTime = view.findViewById(R.id.d_a_loan_selectTime);
        selectTime.setOnClickListener(this);
        selectDate =  view.findViewById(R.id.d_a_loan_selectDate);
        selectDate.setOnClickListener(this);
        currentDate =  view.findViewById(R.id.d_a_loan_currentDate);
        currentTime = view.findViewById(R.id.d_a_loan_currentTime);
        mtrack_as_sp = view.findViewById(R.id.d_a_loan_track_as_sp);
        md_a_loan__list = view.findViewById(R.id.d_a_loan_list);

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
        //1借出时间2归还时间
        d_a_loan_fragment1Adpter = new D_A_Loan_Fragment1Adpter(getActivity(), list, 2);
        md_a_loan__list.setAdapter(d_a_loan_fragment1Adpter);

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
            case R.id.d_a_loan_selectDate:
                // 日期格式为yyyy-MM-dd
                customDatePicker1.show(currentDate.getText().toString());
                break;

            case R.id.d_a_loan_selectTime:
                // 日期格式为yyyy-MM-dd HH:mm
                customDatePicker2.show(currentTime.getText().toString());
                break;
            case R.id.d_a_loan_Search_GD_btn:
                if (myiliewei_rbCheck.isChecked()) {
                    LieWei = "";
                    LieWei = "1";
                } else {
                    LieWei = "";
                    LieWei = "2";
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        d_a_d_a_loan_retrun_result = RemouldBuz.D_A_D_A_Loan_retrun(getTrack + "-" + LieWei, currentDate.getText().toString() + ":00", currentTime.getText().toString() + ":00");
//                        Log.e("LZ---------", getTrack + "-" + LieWei + currentDate.getText().toString() + ":00" + "  " + currentTime.getText().toString() + ":00");
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (null != d_a_d_a_loan_retrun_result && null != d_a_d_a_loan_retrun_result.data) {
//                                    loan_lists.addAll(d_a_d_a_loan_retrun_result.data);
                                    for (int k = 0; k < loan_lists.size(); k++) {
                                        for (int j = 0; j < loan_lists.get(k).size(); j++) {
                                            map = new HashMap<String, Object>();
                                            map.put("GetName", loan_lists.get(k).get(j).getGetName());
                                            map.put("Card", loan_lists.get(k).get(j).getCard().toString());
                                            map.put("Belt", loan_lists.get(k).get(j).getBelt());
                                            map.put("GTime", loan_lists.get(k).get(j).getRTime());
                                            list.add(map);
                                            Log.e("LZ----总数据", list.size() + "");
                                        }
                                    }

                                    d_a_loan_fragment1Adpter.notifyDataSetChanged();
                                }

                            }
                        });

                    }
                }).start();


//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        powerSupplyResult = RemouldBuz._PowerSupply_GD(getTrack + "-" + LieWei, currentDate.getText().toString() + ":00", currentTime.getText().toString() + ":00");
////                        Log.e("LZ---------", getTrack + "-" + LieWei + currentDate.getText().toString() + ":00" + "  " + currentTime.getText().toString() + ":00");
//                        getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//                                Log.e("LZ----111",powerSupplyResult.data.                             size()+"");
////                                if (null != powerSupplyResult && null != powerSupplyResult.data) {
////                                    Log.e("LZ----111","11111111111111111");
////                                    power_lists.addAll(powerSupplyResult.data);
////                                    Log.e("LZ----111",power_lists.size()+"");
////                                    d_a_loan_fragment1Adpter.notifyDataSetChanged();
////                                }
//                            }
//                        });
//
//                    }
//                }).start();

                break;
        }
    }
}
