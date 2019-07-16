package com.gsoft.keyhandover.nodecontrolui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gsoft.keyhandover.BaseApplication;
import com.gsoft.keyhandover.R;
import com.gsoft.keyhandover.buz.NodeControlBuz;
import com.gsoft.keyhandover.entity.DailyPlanEntity;
import com.gsoft.keyhandover.entity.DayPlanPackEntity;
import com.gsoft.keyhandover.entity.IctRwmtUserData;
import com.gsoft.keyhandover.entity.TrianInfoStateEntity;
import com.gsoft.keyhandover.tools.LoadingDialog;
import com.gsoft.keyhandover.util.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/2.
 */

public class TaskListActivity extends Activity implements AdapterView.OnItemClickListener {

    TextView planTV;
    ListView listV;
    MyAdapter adapter;

    private String dayPlanId;
    private List<DayPlanPackEntity> packs = new ArrayList<>();
    private TrianInfoStateEntity info;

    private LoadingDialog loadingDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasklist);

        IctRwmtUserData user = new IctRwmtUserData();

        user.setStuffName("吕武");
        user.setStuffId("00627001637");
        user.setRoleCode("3");

//        user.setStuffName("李沛东");
//        user.setStuffId("00627002362");
//        user.setRoleCode("6");
//
//        user.setDeptName("一级修1班");
//        user.setDeptCode("0062700729");
//        user.setUnitCode("021");
        BaseApplication.instance().user = user;

        initView();
    }

    private void initView() {
        planTV = findViewById(R.id.plan_txt);
        listV = findViewById(R.id.listv);
        listV.setOnItemClickListener(this);

        adapter = new MyAdapter();
        listV.setAdapter(adapter);

        getDaylanId();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        loadingDialog = new LoadingDialog(this, "正在加载中...", null);
        getTrianInfo(packs.get(i));
    }


    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return packs.size();
        }

        @Override
        public Object getItem(int i) {
            return packs.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHodler hodler = null;
            if (view == null) {
                view = View.inflate(TaskListActivity.this, R.layout.item_tasklist, null);
                hodler = new ViewHodler();
                hodler.carName = view.findViewById(R.id.car_name);
                hodler.groupName = view.findViewById(R.id.group_name);
                hodler.postName = view.findViewById(R.id.post_name);
                view.setTag(hodler);
            } else {
                hodler = (ViewHodler) view.getTag();
            }

            hodler.carName.setText(packs.get(i).getTrainsetID());
            hodler.groupName.setText("(" + BaseApplication.instance().user.getDeptName() + ")");
            String postTxt = "";
            for (int j = 0; j < packs.get(i).getPacketInfoList().size(); j++) {
                if (j == packs.get(i).getPacketInfoList().size() - 1) {
                    postTxt = postTxt + packs.get(i).getPacketInfoList().get(j).getPacketName();
                } else {
                    postTxt = postTxt + packs.get(i).getPacketInfoList().get(j).getPacketName() + "|";
                }
            }
            L.i("lz", "postTxt:" + postTxt);
            hodler.postName.setText(postTxt);

            return view;
        }

        class ViewHodler {
            TextView carName, groupName, postName;
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    String s = "(白)";
                    if (dayPlanId.substring(11).equals("00")) {
                        s = "(白)";
                    } else {
                        s = "(夜)";
                    }
                    planTV.setText(dayPlanId.substring(0, 10) + " 日检修计划" + s);
//                    Log.e("LZ----",dayPlanId.substring(0, 10) + " 日检修计划" + s);
                    getDaylanPacks();
                    break;
                case 2:
                    break;
            }
        }
    };


    private void getDaylanId() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    NodeControlBuz.DayPlanIdResult result = NodeControlBuz.getDayPlanId();
                    if (result.code == 200) {
//                        dayPlanId = result.data;
                        dayPlanId = "2018-09-04-00";
                        Message msg = new Message();
                        msg.what = 1;
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {

                }

            }
        }).start();
    }

    private void getDaylanPacks() {
        if (BaseApplication.instance().isDQ()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        final NodeControlBuz.DayPlanPackResult result = NodeControlBuz.getDayPlanPack(dayPlanId, BaseApplication.instance().user.getStuffId(), BaseApplication.instance().user.getDeptCode(), BaseApplication.instance().user.getRoleCode());
                        if (result.Type == 1) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    packs.addAll(result.TaskInfoList);
                                    adapter.notifyDataSetChanged();
                                }
                            });
                        }
                    } catch (Exception e) {

                    }
                }
            }).start();
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    try {
                    NodeControlBuz.DailyPlanResult result = NodeControlBuz.DailyPlanPack(dayPlanId);
                    if (result.Type == 1) {
                        final List<DayPlanPackEntity> taskList = new ArrayList<>();
                        for (DailyPlanEntity entity : result.DailyPlanList) {
                            DayPlanPackEntity enr = new DayPlanPackEntity();
                            enr.setPutInDate(entity.getPutInDate());
                            enr.setPutInTrack(entity.getPutInTrack());
                            enr.setPutInTrainNo(entity.getPutInTrainNo());
                            enr.setTrainsetID(entity.getTrainsetID());
                            enr.setTrainsetNo(entity.getTrainsetNo());

                            List<DayPlanPackEntity.PacketInfoListBean> beanrs = new ArrayList<>();
                            for (DailyPlanEntity.WorkPacketListBean bean : entity.getWorkPacketList()) {
                                DayPlanPackEntity.PacketInfoListBean beanr = new DayPlanPackEntity.PacketInfoListBean();
                                beanr.setDeptCode(bean.getDeptCode());
                                beanr.setDeptName(bean.getDeptName());
                                beanr.setMaintCycCode(bean.getMaintCycCode());
                                beanr.setPacketCode(bean.getPacketCode());
                                beanr.setPacketName(bean.getPacketName());
                                beanr.setPacketType(bean.getPacketType());
                                beanrs.add(beanr);
                            }
                            enr.setPacketInfoList(beanrs);
                            taskList.add(enr);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                packs.addAll(taskList);
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
//                    } catch (Exception e) {
//
//                    }
                }
            }).start();

        }
    }


    private void getTrianInfo(final DayPlanPackEntity pack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final NodeControlBuz.TrianInfoResult result = NodeControlBuz.getTrianInfos();
                    if (result.Type == 1) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                for (TrianInfoStateEntity entity : result.TrainInTrackList) {
                                    if (entity.getTrainsetName().equals(pack.getTrainsetID())) {
//                                    if (entity.getTrainsetName().equals("CRH380D-1545")) {
                                        info = entity;
                                        Intent intent = new Intent(TaskListActivity.this, NodeControlActivity.class);
                                        intent.putExtra("info", info);
                                        intent.putExtra("pack", pack);
                                        intent.putExtra("plan", dayPlanId);
                                        loadingDialog.dismiss();
                                        startActivity(intent);
                                    }
                                }
                            }
                        });
                    }
                } catch (Exception e) {

                }
            }
        }).start();
    }

}
