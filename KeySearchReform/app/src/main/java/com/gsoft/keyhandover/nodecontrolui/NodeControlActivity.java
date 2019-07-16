package com.gsoft.keyhandover.nodecontrolui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gsoft.keyhandover.BaseApplication;
import com.gsoft.keyhandover.R;
import com.gsoft.keyhandover.buz.NodeControlBuz;
import com.gsoft.keyhandover.entity.DayPlanPackEntity;
import com.gsoft.keyhandover.entity.FinishNameEntity;
import com.gsoft.keyhandover.entity.NodeEntity;
import com.gsoft.keyhandover.entity.NodeModleEntity;
import com.gsoft.keyhandover.entity.ServerResponse;
import com.gsoft.keyhandover.entity.TrianInfoStateEntity;
import com.gsoft.keyhandover.tools.LoadingDialog;
import com.gsoft.keyhandover.util.IpUtil;
import com.gsoft.keyhandover.util.L;
import com.gsoft.keyhandover.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/2/2.
 * 节点状态：1、他人未完成（不可做）  2、本人未完成（不可做）  3、他人完成 （不可做） 4、本人完成（不可做）
 * 5、他人未完成（可做）    6、本人未完成（可做）    7、本人完成（可做）
 */

public class NodeControlActivity extends Activity {

    ListView listV;
    MyAdapter adapter;
    List<NodeEntity> lists = new ArrayList<>();

    private DayPlanPackEntity pack;
    private TrianInfoStateEntity info;
    String dayPlan;

    Timer timer;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nodecontrol);
        loadingDialog = new LoadingDialog(this, "正在加载中...", null);

        Intent intent = getIntent();
        if (null != intent) {
            pack = intent.getParcelableExtra("pack");
            info = intent.getParcelableExtra("info");
            dayPlan = intent.getStringExtra("plan");
        }

        TextView nodeName = findViewById(R.id.node_name);
        nodeName.setText(pack.getTrainsetID() + " (" + BaseApplication.instance().user.getDeptName() + ")");
        listV = findViewById(R.id.listv);
        adapter = new MyAdapter();
        listV.setAdapter(adapter);

        getNodes();
    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return lists.size();
        }

        @Override
        public Object getItem(int i) {
            return lists.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHodler hodler = null;
            if (view == null) {
                view = View.inflate(NodeControlActivity.this, R.layout.item_node, null);
                hodler = new ViewHodler();
                hodler.node_state_1 = view.findViewById(R.id.node_state_1);
                hodler.node_state_2 = view.findViewById(R.id.node_state_2);
                hodler.node_state_3 = view.findViewById(R.id.node_state_3);
                hodler.node_state_4 = view.findViewById(R.id.node_state_4);
                hodler.node_state_5 = view.findViewById(R.id.node_state_5);
                hodler.node_state_6 = view.findViewById(R.id.node_state_6);
                hodler.node_state_7 = view.findViewById(R.id.node_state_7);

                hodler.job_name_1 = view.findViewById(R.id.job_name_1);
                hodler.job_name_2 = view.findViewById(R.id.job_name_2);
                hodler.job_name_3 = view.findViewById(R.id.job_name_3);
                hodler.job_name_4 = view.findViewById(R.id.job_name_4);
                hodler.job_name_5 = view.findViewById(R.id.job_name_5);
                hodler.job_name_6 = view.findViewById(R.id.job_name_6);
                hodler.job_name_7 = view.findViewById(R.id.job_name_7);

                hodler.post_name_1 = view.findViewById(R.id.post_name_1);
                hodler.post_name_2 = view.findViewById(R.id.post_name_2);
                hodler.post_name_3 = view.findViewById(R.id.post_name_3);
                hodler.post_name_4 = view.findViewById(R.id.post_name_4);
                hodler.post_name_5 = view.findViewById(R.id.post_name_5);
                hodler.post_name_6 = view.findViewById(R.id.post_name_6);
                hodler.post_name_7 = view.findViewById(R.id.post_name_7);

                hodler.time_3 = view.findViewById(R.id.time_3);
                hodler.time_4 = view.findViewById(R.id.time_4);
                hodler.time_7 = view.findViewById(R.id.time_7);
                hodler.btn = view.findViewById(R.id.btn);
                hodler.btn_1 = view.findViewById(R.id.btn_1);

                view.setTag(hodler);
            } else {
                hodler = (ViewHodler) view.getTag();
            }

            hodler.node_state_1.setVisibility(View.GONE);
            hodler.node_state_2.setVisibility(View.GONE);
            hodler.node_state_3.setVisibility(View.GONE);
            hodler.node_state_4.setVisibility(View.GONE);
            hodler.node_state_5.setVisibility(View.GONE);
            hodler.node_state_6.setVisibility(View.GONE);
            hodler.node_state_7.setVisibility(View.GONE);
            switch (lists.get(i).getState()) {
                case 1:
                    hodler.node_state_1.setVisibility(View.VISIBLE);
                    hodler.job_name_1.setText(lists.get(i).getNodeName());
                    hodler.post_name_1.setText(lists.get(i).getRoleName());

                    break;
                case 2:
                    hodler.node_state_2.setVisibility(View.VISIBLE);
                    hodler.job_name_2.setText(lists.get(i).getNodeName());
                    hodler.post_name_2.setText(lists.get(i).getRoleName());

                    break;
                case 3:
                    hodler.node_state_3.setVisibility(View.VISIBLE);
                    StringBuffer name = new StringBuffer();
                    for (String kv : lists.get(i).getUserName()) {
                        name.append(kv + "、");
                    }
                    name.deleteCharAt(name.length() - 1);
                    hodler.job_name_3.setText(lists.get(i).getNodeName() + "(" + name.toString() + ")");
                    hodler.post_name_3.setText(lists.get(i).getRoleName());
                    hodler.time_3.setText(lists.get(i).getTime());

                    break;
                case 4:
                    hodler.node_state_4.setVisibility(View.VISIBLE);
                    StringBuffer name2 = new StringBuffer();
                    for (String kv : lists.get(i).getUserName()) {
                        name2.append(kv + "、");
                    }
                    name2.deleteCharAt(name2.length() - 1);
                    hodler.job_name_4.setText(lists.get(i).getNodeName() + "(" + name2.toString() + ")");
                    hodler.post_name_4.setText(lists.get(i).getRoleName());
                    hodler.time_4.setText(lists.get(i).getTime());

                    break;
                case 5:
                    hodler.node_state_1.setVisibility(View.VISIBLE);
                    hodler.job_name_1.setText(lists.get(i).getNodeName());
                    hodler.post_name_1.setText(lists.get(i).getRoleName());
//                    hodler.node_state_5.setVisibility(View.VISIBLE);
//                    hodler.job_name_5.setText(lists.get(i).getNodeName());
//                    hodler.post_name_5.setText(lists.get(i).getRoleName());

                    break;
                case 6:
                    hodler.node_state_6.setVisibility(View.VISIBLE);
                    hodler.job_name_6.setText(lists.get(i).getNodeName());
                    hodler.post_name_6.setText(lists.get(i).getRoleName());
                    hodler.btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            submit(i);
                        }
                    });
                    break;

                case 7:
                    hodler.node_state_7.setVisibility(View.VISIBLE);
                    StringBuffer name7 = new StringBuffer();
                    for (String kv : lists.get(i).getUserName()) {
                        name7.append(kv + "、");
                    }
                    name7.deleteCharAt(name7.length() - 1);
                    hodler.job_name_7.setText(lists.get(i).getNodeName() + "(" + name7.toString() + ")");
                    hodler.post_name_7.setText(lists.get(i).getRoleName());
                    hodler.time_7.setText(lists.get(i).getTime());
                    hodler.btn_1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            submit(i);
                        }
                    });
                    break;
            }
            return view;
        }

        class ViewHodler {
            LinearLayout node_state_1, node_state_2, node_state_3, node_state_4, node_state_5, node_state_6, node_state_7;
            TextView job_name_1, job_name_2, job_name_3, job_name_4, job_name_5, job_name_6, job_name_7;
            TextView post_name_1, post_name_2, post_name_3, post_name_4, post_name_5, post_name_6, post_name_7;
            TextView time_3, time_4, time_7;
            TextView btn, btn_1;
        }

    }

    void getNodes() {
        loadingDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final NodeControlBuz.NodesResult result = NodeControlBuz.getNodes(pack.getTrainsetID(),
                            info.getMarshal() + "");
                    if (result.code == 200) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                creatDatas(result.data);
                            }
                        });
                    } else {
                        closeLoading();
                    }
                } catch (Exception e) {
                    closeLoading();
                }
            }
        }).start();
    }

    //获取流程情况
    void getProcessState() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final NodeControlBuz.ProcessResult result = NodeControlBuz.getProcess(pack.getTrainsetID(),
                            dayPlan.substring(0, 10) + " 日检修计划");
                    if (result.code == 200) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updataState(result.data);
                            }
                        });
                    } else {
                        closeLoading();
                    }
                } catch (Exception e) {
                    closeLoading();
                }
            }
        }).start();
    }


    void closeLoading() {
        if (null != loadingDialog) {
            loadingDialog.dismiss();
        }
    }


    void submit(int i) {
//        if (null == loadingDialog) {
        loadingDialog = new LoadingDialog(this, "正在加载中...", null);
        loadingDialog.show();
//        }

        final NodeEntity entity = lists.get(i);
        final String condNodeId = (new Gson()).toJson(entity.getCCode());

        L.i("lz", "condNodeId:" + condNodeId);
        final String dayPlanNo = dayPlan.substring(0, 10) + " 日检修计划";

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final ServerResponse result = NodeControlBuz.submit(entity.getFlowId(),
                            entity.getTemplateId(),
                            entity.getNodeId(),
                            condNodeId,//
                            BaseApplication.instance().user.getRoleCode(),
                            entity.isTypes() ? "1" : "0",
                            entity.isTypes() ? entity.getNodeId().
                                    equals(entity.getBeginNode()) ? "0" : "1" : "",
                            info.getTrackPla(),
                            pack.getTrainsetID(),
                            dayPlanNo,
                            dayPlan.substring(11).equals("00") ? "0" : "1",
                            "01",//
                            "1",
                            "1",
                            info.getTrackCode(),
                            entity.getUserName().size() + 1 + "",
                            BaseApplication.instance().user.getStuffId(),
                            BaseApplication.instance().user.getDeptCode(),
                            pack.getPutInTrainNo(),
                            "1",
                            "1",
                            BaseApplication.instance().user.getDeptName()
                                    + BaseApplication.instance().user.getStuffName()
                                    + "操作" + entity.getNodeName(),//remark
                            BaseApplication.instance().user.getStuffName(),
                            entity.getRoleName(),
                            BaseApplication.instance().user.getDeptName(),
                            info.getTrackName(),
                            "1",
                            "1",
                            BaseApplication.instance().user.getDeptCode(),
                            BaseApplication.instance().user.getDeptName(),
                            BaseApplication.instance().user.getUnitCode(),
                            "1",
                            "1",
                            "1",
                            IpUtil.getHostIP(),
                            getAndroidIMEI(),
                            pack.getTrainsetID(), info.getMarshal() + "");
                    Toast.makeText(NodeControlActivity.this,
                            result.code + result.msg, Toast.LENGTH_SHORT).show();
                    if (result.code == 200) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                getProcessState();
                            }
                        });
                    } else {
                        loadingDialog.dismiss();
                    }
                } catch (Exception e) {

                }
            }
        }).start();

    }

    void creatDatas(List<NodeModleEntity> data) {
        data.get(0).getList1();
        lists.clear();
        for (int i = 0; i < data.get(0).getList1().size(); i++) {
            NodeModleEntity.List1Bean bean1 = data.get(0).getList1().get(i);
            NodeEntity entity = new NodeEntity();
            entity.setRule(i);
            for (NodeModleEntity.List2Bean bean2 : data.get(0).getList2()) {
                if (bean1.getNodeId().equals(bean2.getNodeId())) {
                    entity.setCCode(bean2.getCNodeId());
                    entity.setFlowId(bean2.getFlowId());
                    entity.setFlowName(bean2.getFlowName());
                    entity.setNodeId(bean2.getNodeId());
                    entity.setNodeName(bean2.getNodeName());
                    entity.setRoleCode(bean2.getRoleCode());
                    entity.setRoleName(bean2.getRoleName());
                    entity.setTemplateId(bean2.getTemplateId());
                    entity.setTemplateName(bean2.getTemplateName());
                }
            }
            lists.add(entity);
        }

        for (NodeEntity bean : lists) {
            for (NodeModleEntity.List3Bean bean3 : data.get(0).getList3()) {
                if (bean.getNodeId().equals(bean3.getBeginNode())
                        || bean.getNodeId().equals(bean3.getEndNode())) {
                    bean.setTypes(true);
                    bean.setTemplateName(bean3.getBeginTemplate());
                    bean.setBeginNode(bean3.getBeginNode());
                    bean.setEndTemplate(bean3.getEndTemplate());
                    bean.setEndNode(bean3.getEndNode());
                    break;
                }
                bean.setTypes(false);
            }

        }


        for (NodeEntity ee : lists) {
            String sss = "";
            for (String ss : ee.getCCode()) {
                sss = sss + ss + ",";
            }
            L.i("lz", ee.getNodeName() + "--" + ee.getNodeId()
                    + "：条件节点：" + sss + "：成对节点：" + ee.getBeginNode() + "  " + ee.getEndNode());
        }

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getProcessState();
            }
        }, 0, 5000);
//        getProcessState();
    }

    void updataState(List<FinishNameEntity> datas) {
        closeLoading();
//        Collections.reverse(datas);
        for (NodeEntity en : lists) {
            en.getUserName().clear();
            for (FinishNameEntity entity : datas) {
                if (en.getNodeId().equals(entity.getNodeId())) {
                    en.setUserName(entity.getStuffName());
                    en.setTime(entity.getTimes());
                }
            }
        }

        for (NodeEntity entity : lists) {
            if (StringUtils.isEmpty(entity.getTime())) {
                if (isSubmit(entity)) {
                    if (entity.getRoleCode().equals(BaseApplication.instance().user.getRoleCode())) {
                        entity.setState(6);
                    } else {
                        entity.setState(5);
                    }
                } else {
                    if (entity.getRoleCode().equals(BaseApplication.instance().user.getRoleCode())) {
                        entity.setState(2);
                    } else {
                        entity.setState(1);
                    }
                }
            } else {
                if (entity.getRoleCode().equals(BaseApplication.instance().user.getRoleCode())) {
                    entity.setState(4);
                    if (isLoop(entity)) {
                        entity.setState(7);
                    }
                } else {
                    entity.setState(3);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    //判断是否可循环做
    boolean isLoop(NodeEntity entity) {
        int j = 0; //本节点ID对应位置
        List<Integer> js = new ArrayList<>(); //条件节点id对应的位置
        int t = -1;//成对节点ID对应位置
        if (!entity.isTypes()){
            return false;
        }
        for (int i = 0; i < lists.size(); i++) {
            if (entity.getNodeId().equals(lists.get(i).getNodeId())) {
                j = i;
            }
            for (String id : entity.getCCode()) {
                if (id.equals(lists.get(i).getNodeId())) {
                    js.add(i);
                }
            }
            if (entity.isTypes()) {
                if (entity.getBeginNode().equals(entity.getNodeId())) {
                    for (int k = 0; k < lists.size(); k++) {
                        if (entity.getEndNode().equals(lists.get(k).getNodeId())) {
                            t = k;
                        }
                    }

                } else {
                    for (int k = 0; k < lists.size(); k++) {
                        if (entity.getBeginNode().equals(lists.get(k).getNodeId())) {
                            t = k;
                        }
                    }
                }
            }
        }
        if (j < t && t != -1) {
//            if (entity.getUserName().size() != lists.get(t).getUserName().size()) {
//                return false;
//            }

            if (t + 1 < lists.size() - 1) {
                if (!StringUtils.isEmpty(lists.get(t + 1).getTime())) {
                    return false;
                }
            }
        }

        for (int o : js) {
            if (j < o && lists.get(j).getUserName().size() != lists.get(o).getUserName().size()) {
                return false;
            }
        }

        if (t < j && t != -1) {
            if (lists.get(t).getUserName().size() == lists.get(j).getUserName().size()) {
                return false;
            }
        }

        if (!entity.isTypes()) {
            for (int k : js) {
                if (k < j) {
                    if (lists.get(k).getUserName().size() <= lists.get(j).getUserName().size()) {
                        return false;
                    }
                }
            }
        }

        if (entity.isTypes()) {
            if (j < t) {
                for (String name : entity.getUserName()) {
                    if (name.equals(BaseApplication.instance().user.getStuffName())) {
                        return false;
                    }
                    if (BaseApplication.instance().user.getRoleCode().equals("3")) {
                        return false;
                    }
                }
            } else {
                for (String name : entity.getUserName()) {
                    if (name.equals(BaseApplication.instance().user.getStuffName())) {
                        return false;
                    }
                }
                boolean bb = true;
                for (String name : lists.get(t).getUserName()) {
                    if (name.equals(BaseApplication.instance().user.getStuffName())) {
                        bb = false;
                        break;
                    }
                }
                if (bb) {
                    return false;
                }
            }
        }

        return true;
    }

    //判断是否可提交(位置验证)
    boolean isSubmit(NodeEntity entity) {
        int j = 0; //本节点ID对应位置
        List<Integer> js = new ArrayList<>(); //条件节点id对应的位置
        int t = -1;//成对节点ID对应位置
        for (int i = 0; i < lists.size(); i++) {
            if (entity.getNodeId().equals(lists.get(i).getNodeId())) {
                j = i;
            }
            for (String id : entity.getCCode()) {
                if (id.equals(lists.get(i).getNodeId())) {
                    js.add(i);
                }
            }
            if (entity.isTypes()) {
                if (entity.getBeginNode().equals(entity.getNodeId())) {
                    for (int k = 0; k < lists.size(); k++) {
                        if (entity.getEndNode().equals(lists.get(k).getNodeId())) {
                            t = k;
                        }
                    }

                } else {
                    for (int k = 0; k < lists.size(); k++) {
                        if (entity.getBeginNode().equals(lists.get(k).getNodeId())) {
                            t = k;
                        }
                    }
                }
            }
        }

        for (int i : js) {
            if (i < j) {
                if (StringUtils.isEmpty(lists.get(i).getTime())) {
                    return false;
                }
//                if (!entity.isTypes()){
                    if (lists.get(i).isTypes() && lists.get(i).getEndNode().equals(lists.get(i).getNodeId())) {
                        String id3 = lists.get(i).getNodeId().equals(lists.get(i).
                                getBeginNode()) ? lists.get(i).getEndNode() : lists.get(i).getBeginNode();
                        NodeEntity ty = null;
                        for (NodeEntity tt : lists) {
                            if (tt.getNodeId().equals(id3)) {
                                ty = tt;
                                break;
                            }
                        }

                        if (lists.get(i).getUserName().size() != ty.getUserName().size()) {
                            return false;
                        }
                    }
//                }
            }
        }
        if (t < j && t != -1) {
            for (int k = t; k < j; k++) {
                if (StringUtils.isEmpty(lists.get(k).getTime()) &&
                        lists.get(k).getUserName().size() > entity.getUserName().size()) {
                    return false;
                }
            }
        }

        if (entity.isTypes()) {
            if (j > t) {
                boolean bbb = true;
                for (String name : lists.get(t).getUserName()) {
                    if (name.equals(BaseApplication.instance().user.getStuffName())) {
                        bbb = false;
                        break;
                    }
                }
                if (bbb) {
                    return false;
                }
            }
        }

        return true;
    }

    //获取imei
    private String getAndroidIMEI() {
        String androidIMEI = android.provider.Settings.System.
                getString(getContentResolver(), android.provider.Settings.System.ANDROID_ID);
        L.i("ANDROID_ID", androidIMEI + " ");
        return androidIMEI;
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (null != timer) {
            timer.cancel();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (timer != null) {
            timer.cancel();
            timer = null;
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    getProcessState();
                }
            }, 0, 5000);
        }
    }
}
