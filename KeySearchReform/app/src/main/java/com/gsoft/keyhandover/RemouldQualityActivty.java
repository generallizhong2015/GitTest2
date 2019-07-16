package com.gsoft.keyhandover;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gsoft.keyhandover.adapters.RemouldQuality_Adapter;
import com.gsoft.keyhandover.assist.CustomDatePicker;
import com.gsoft.keyhandover.buz.RemouldBuz;
import com.gsoft.keyhandover.entity.ProjectEntity;
import com.gsoft.keyhandover.entity.SequenceOrState;
import com.gsoft.keyhandover.entity.ServerResponse;
import com.gsoft.keyhandover.entity.TrainEntity;
import com.gsoft.keyhandover.tools.LoadingDialog;
import com.gsoft.keyhandover.tools.suredialog.SureDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/17.
 */

public class RemouldQualityActivty extends Activity implements View.OnClickListener {
    /**
     * 改造质检
     */
    private Spinner mGaoZhiJian_CheZu_sp;
    private Spinner mGaoZhiJian_XiangMu_sp;
    private ImageView mattachment_download;
    private RelativeLayout selectDate, selectTime;
    private TextView currentDate, currentTime;
    private CustomDatePicker customDatePicker1, customDatePicker2;//时间
    private TextView mtextview_title;
    private RemouldQuality_Adapter remouldQuality_adapter;

    //车组号
    private ArrayAdapter<String> CarNum_Adapter;//车组号适配器
    private List<String> CarNum_data_list = new ArrayList<>();
    private String carscode;//车组赋值
    private String getCarCode;//获取车组
    private RemouldBuz.TrainsResult carnum_data; //车组号获取
    CheZu_dapter cheZu_dapter;
    //项目
    private String Project;//项目名称赋值
    private String getProjectID;//获取项目名称
    private List<String> Project_data_list = new ArrayList<>();//项目列表
    private ArrayAdapter<String> Project_adapter;//项目适配器
    private RemouldBuz.ProjectResult Project_data;//项目名称
    //查询结果
    ArrayList<Map<String, Object>> ZuoYe_list = new ArrayList<Map<String, Object>>();//查询辆序与状态结果
    private ListView mRemouldQuality_list;
    private ImageView mRemouldQuality_ChaXun_img;//查询

    private Button mR_Q_keybackfill_btn;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_remouldquality);
        InStantiation();
    }

    //实例化
    public void InStantiation() {
        findViewById();
        setOnClickListener();
        TrainsNum();
        remouldQuality_adapter = new RemouldQuality_Adapter(RemouldQualityActivty.this, ZuoYe_list);
        mRemouldQuality_list.setAdapter(remouldQuality_adapter);
        mRemouldQuality_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (BaseApplication.instance().user.getRoleCode().equals("8") || BaseApplication.instance().user.getRoleCode().equals("z")) {
                    Intent startjob = new Intent(RemouldQualityActivty.this, RemouldImplement_StartJob_Activty.class);
                    startjob.putExtra("trainOrder", ZuoYe_list.get(i).get("oder").toString());
                    startjob.putExtra("trainCode", getCarCode);
                    startjob.putExtra("trainName", carscode);
                    startjob.putExtra("operationId", getProjectID);
                    startjob.putExtra("detailId", ZuoYe_list.get(i).get("detailId").toString());
                    startActivity(startjob);
                    //finish();
//                    ZuoYe_list.clear();
//                    remouldQuality_adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(RemouldQualityActivty.this, "非质检员无权限进入！", Toast.LENGTH_SHORT).show();
                }
            }
        });

        initDatePicker();
        HttpTrainsNum(currentDate.getText().toString());//车组号
    }


    public void findViewById() {
        mGaoZhiJian_CheZu_sp = findViewById(R.id.GaoZhiJian_CheZu_sp);
        mGaoZhiJian_XiangMu_sp = findViewById(R.id.GaoZhiJian_XiangMu_sp);
        mattachment_download = findViewById(R.id.attachment_download);
        selectTime = findViewById(R.id.selectTime);
        selectDate = findViewById(R.id.selectDate);
        currentDate = findViewById(R.id.currentDate);
        currentTime = findViewById(R.id.currentTime);
        mRemouldQuality_list = findViewById(R.id.RemouldQuality_list);
        mRemouldQuality_ChaXun_img = findViewById(R.id.RemouldQuality_ChaXun_img);
        mtextview_title = findViewById(R.id.textview_title);
        mR_Q_keybackfill_btn = findViewById(R.id.R_Q_keybackfill_btn);
        mtextview_title.setText("改造质检");
    }

    public void setOnClickListener() {
        mattachment_download.setOnClickListener(this);
        selectTime.setOnClickListener(this);
        selectDate.setOnClickListener(this);
        mRemouldQuality_ChaXun_img.setOnClickListener(this);
        mR_Q_keybackfill_btn.setOnClickListener(this);


    }

    //时间选择
    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        currentDate.setText(now.split(" ")[0]);
        currentTime.setText(now);

        customDatePicker1 = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                currentDate.setText(time.split(" ")[0]);
                try {
                    HttpTrainsNum(currentDate.getText().toString());
                    ZuoYe_list.clear();
                    CarNum_data_list.clear();
                    cheZu_dapter.notifyDataSetChanged();
                    Project_data_list.clear();
                    Project_adapter.notifyDataSetChanged();
                    remouldQuality_adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    return;
                }
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker1.showSpecificTime(false); // 不显示时和分
        customDatePicker1.setIsLoop(true); // 不允许循环滚动

    }


    //车组号适配器初始化
    public void TrainsNum() {
        //下拉车组号适配器
        cheZu_dapter = new CheZu_dapter(RemouldQualityActivty.this, CarNum_data_list);
        mGaoZhiJian_CheZu_sp.setAdapter(cheZu_dapter);  //lz
        mGaoZhiJian_CheZu_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    carscode = mGaoZhiJian_CheZu_sp.getSelectedItem().toString();
                    for (TrainEntity data : carnum_data.data) {
                        if (data.getTrainName().equals(carscode)) {
                            getCarCode = data.getTrainCode();
                            continue;
                        }
                    }
                    Log.e("lz", "code" + getCarCode);
                    Project_data_list.clear();
                    HttpProJectName(getCarCode, currentDate.getText().toString());
                    Project_adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    return;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //车组号数据请求
    public void HttpTrainsNum(final String time) {
        CarNum_data_list = new ArrayList<String>();//车组号列表
        CarNum_data_list.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //车组号数据请求
                try {
                    carnum_data = RemouldBuz.getTracks(time,
                            BaseApplication.instance().user.getRoleCode(),
                            BaseApplication.instance().user.getDeptCode(),
                            BaseApplication.instance().user.getStuffName(),
                            "2");
                    if (carnum_data.data.size() > 0) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                for (TrainEntity data : carnum_data.data) {
                                    CarNum_data_list.add(data.getTrainName());
                                }
                                cheZu_dapter.notifyDataSetChanged();
                            }
                        });
                    } else {
                        return;
                    }

                } catch (Exception e) {
                    return;
                }
            }
        }).start();
    }

    //项目数据请求
    public void HttpProJectName(final String TrainCode, final String date) {
        Project_data_list.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Project_data = RemouldBuz.getProject(TrainCode,
                        date,
                        BaseApplication.instance().user.getRoleCode(),
                        BaseApplication.instance().user.getDeptCode(),
                        BaseApplication.instance().user.getStuffName(),
                        "2");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            for (ProjectEntity data : Project_data.data) {
                                Project_data_list.add(data.getProjectName());
                            }
                            Project_adapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            return;
                        }
                    }
                });


            }
        }).start();
        ProJectName();
    }

    //下拉项目适配器初始化
    public void ProJectName() {
        Project_adapter = new ArrayAdapter<String>(this, R.layout.project_option_item, R.id.project_item_text, Project_data_list);
        mGaoZhiJian_XiangMu_sp.setAdapter(Project_adapter);
        mGaoZhiJian_XiangMu_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    Project = mGaoZhiJian_XiangMu_sp.getSelectedItem().toString();
                    for (ProjectEntity data : Project_data.data) {
                        if (data.getProjectName().equals(Project)) {
                            getProjectID = data.getOperationId().toString();
                            continue;
                        }
                    }
                    Project_adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    return;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selectDate:
                customDatePicker1.show(currentDate.getText().toString());
                break;
            case R.id.selectTime:
                customDatePicker2.show(currentTime.getText().toString());
                break;
            case R.id.attachment_download:
                try {
                    if (!getProjectID.isEmpty()) {
                        Intent attDownload = new Intent(RemouldQualityActivty.this, FileShowActivity.class);
                        attDownload.putExtra("operationId", getProjectID);
                        startActivity(attDownload);
                    } else {
                        Toast.makeText(RemouldQualityActivty.this, "请选择项目！", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(RemouldQualityActivty.this, "有选项未选择！", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
            case R.id.RemouldQuality_ChaXun_img:
                HttpChaXun();
                break;
            case R.id.R_Q_keybackfill_btn:
                SureDialog.show(this, "确定将所有辆序全部回填？", new SureDialog.OnConfirmListener() {
                    @Override
                    public void onConfirmClick() {
                        if (ZuoYe_list.size() != 0) {
                            for (int i = 0; i < ZuoYe_list.size(); i++) {
                                Cars_AkeybackfillHttp(ZuoYe_list.get(i).get("detailId").toString(), ZuoYe_list.get(i).get("oder").toString());
                            }
                            remouldQuality_adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(RemouldQualityActivty.this, "没有可回填的数据。", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
                break;
        }
    }

    //请求查询结果

    public void HttpChaXun() {
        ZuoYe_list.clear();
        try {
            String project = mGaoZhiJian_XiangMu_sp.getSelectedItem().toString();
            if (project.isEmpty()) {
                Toast.makeText(RemouldQualityActivty.this, "有选项未选择！", Toast.LENGTH_SHORT).show();
            } else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final RemouldBuz.SequenceOrStateResult sequenceOrState_data = RemouldBuz.getSequenceOrState(
                                getProjectID,
                                getCarCode,
                                BaseApplication.instance().user.getRoleCode(),
                                BaseApplication.instance().user.getDeptCode(),
                                BaseApplication.instance().user.getStuffName(),
                                "2");
                        if (sequenceOrState_data.data.size() > 0) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    for (SequenceOrState data : sequenceOrState_data.data) {
                                        Map<String, Object> map = new HashMap<String, Object>();
                                        map.put("detailId", data.getDetailId());
                                        map.put("oder", data.getTrainOrder());
                                        map.put("statu", data.getStatus());
                                        map.put("nodeTotal", data.getNodeTotal());
                                        map.put("qaCompleteCount", data.getQaCompleteCount());
                                        ZuoYe_list.add(map);
                                        remouldQuality_adapter.notifyDataSetChanged();
                                    }
                                }
                            });
                        } else {
                            return;
                        }

                    }
                }).start();
            }

        } catch (Exception e) {
            return;
        }
    }


    @Override
    protected void onResume() {
        HttpChaXun();
        super.onResume();
    }

    class CheZu_dapter extends BaseAdapter {

        private Activity activity = null;

        /**
         * 自定义构造方法
         *
         * @param activity
         */
        public CheZu_dapter(Activity activity, List<String> CarNum_data_list) {
            this.activity = activity;
        }

        @Override
        public int getCount() {
            return CarNum_data_list.size();
        }

        @Override
        public Object getItem(int position) {
            return CarNum_data_list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new CheZu_dapter.ViewHolder();
                // 下拉项布局
                convertView = LayoutInflater.from(activity).inflate(
                        R.layout.option_item, null);
                holder.textView = convertView
                        .findViewById(R.id.CheZhuHao_item_text);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.textView.setText(CarNum_data_list.get(position).toString());
            return convertView;
        }

        class ViewHolder {
            TextView textView;
        }

    }
    /**
     * 质检所有车型组辆序全部回填
     * qualified 默认为true
     * String remark,备注
     * String roleCode, String deptCode, String stuffName,
     * String userType类型质检
     */
    public void Cars_AkeybackfillHttp(final String detailId, final String oder) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final ServerResponse response = RemouldBuz.OneStepBackFill(
                        detailId,
                        true,
                        "",
                        BaseApplication.instance().user.getRoleCode(),
                        BaseApplication.instance().user.getDeptCode(),
                        BaseApplication.instance().user.getStuffName(),
                        "2");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (response.code == 200) {
                                Toast.makeText(RemouldQualityActivty.this, oder + "提交成功！" + "共" + ZuoYe_list.size() + "条数据。", Toast.LENGTH_SHORT).show();
                                remouldQuality_adapter.notifyDataSetChanged();
                            } else if (response.code == 500) {
                                Toast.makeText(RemouldQualityActivty.this, "" + response.msg.toString(), Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(RemouldQualityActivty.this, "" + response.msg.toString(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {

                        }
                    }
                });
            }
        }).start();
    }
}