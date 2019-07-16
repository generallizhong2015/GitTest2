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

import com.gsoft.keyhandover.adapters.RemouldImplement_Adapter;
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

public class RemouldImplement_Activty extends Activity implements View.OnClickListener {
    /*
* 改造实施作业数据
*/
    private Spinner mGaoZaoShiShi_CheZu_sp;
    private Spinner mGaoZaoShiShi_XiangMu_sp;
    private ImageView mChaXun_img;
    private TextView mbutton_back;

    private Button mTarins_search_btn;
    private ListView mListView;
    private RelativeLayout selectDate, selectTime;
    private TextView currentDate, currentTime;
    private CustomDatePicker customDatePicker1, customDatePicker2;
    private TextView mtextview_title;
    private RemouldImplement_Adapter remouldImplement_adapter;
    private ImageView mattachment_download;
    //车组号
    private CheZu_dapter cheZu_dapter;
    private ArrayAdapter<String> CarNum_Adapter;//车组号适配器
    private List<String> CarNum_data_list = new ArrayList<>();
    private String carscode;//车组赋值
    private String getCarCode;//获取车组
    private RemouldBuz.TrainsResult carnum_data; //车组号获取
    //项目
    private String Project;//项目名称赋值
    private String getProjectID;//获取项目名称
    private List<String> Project_data_list = new ArrayList<>();//项目列表
    private ArrayAdapter<String> Project_adapter;//项目适配器
    private RemouldBuz.ProjectResult Project_data;//项目名称

    //查询结果
    private ArrayList<Map<String, Object>> ZuoYe_list = new ArrayList<Map<String, Object>>();//查询结果
    //一键回填
    private Button mA_keybackfill_btn;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_remouldimplemen);
        InStantiation();
    }

    //实例化
    public void InStantiation() {
        findViewById();
        setOnClickListener();
        loadingDialog = new LoadingDialog(this, "正在处理，请稍等...", null);
        TrainsNum();
        remouldImplement_adapter = new RemouldImplement_Adapter(RemouldImplement_Activty.this, ZuoYe_list);
        mListView.setAdapter(remouldImplement_adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent startjob = new Intent(RemouldImplement_Activty.this, RemouldImplement_StartJob_Activty.class);
                startjob.putExtra("trainOrder", ZuoYe_list.get(i).get("oder").toString());
                startjob.putExtra("trainCode", getCarCode);
                startjob.putExtra("trainName", carscode);
                startjob.putExtra("operationId", getProjectID);
                startjob.putExtra("detailId", ZuoYe_list.get(i).get("detailId").toString());
                startActivity(startjob);
            }
        });
        initDatePicker();
        HttpTrainsNum(currentDate.getText().toString());
    }
    public void findViewById() {
        mattachment_download = findViewById(R.id.attachment_download);
        mGaoZaoShiShi_CheZu_sp = findViewById(R.id.GaoZaoShiShi_CheZu_sp);
        mGaoZaoShiShi_XiangMu_sp = findViewById(R.id.GaoZaoShiShi_XiangMu_sp);
        mbutton_back = findViewById(R.id.button_back);
        selectTime = findViewById(R.id.selectTime);
        selectDate = findViewById(R.id.selectDate);
        currentDate = findViewById(R.id.currentDate);
        currentTime = findViewById(R.id.currentTime);
        mListView = findViewById(R.id.RemouldImplement_list);
        mChaXun_img = findViewById(R.id.ChaXun_img);
        mtextview_title = findViewById(R.id.textview_title);
        mTarins_search_btn = findViewById(R.id.Tarins_search_btn);
        mA_keybackfill_btn = findViewById(R.id.A_keybackfill_btn);
        mtextview_title.setText("改造实施");
    }

    public void setOnClickListener() {
        mattachment_download.setOnClickListener(this);
        selectTime.setOnClickListener(this);
        selectDate.setOnClickListener(this);
        mChaXun_img.setOnClickListener(this);
        mTarins_search_btn.setOnClickListener(this);
        mA_keybackfill_btn.setOnClickListener(this);
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
                    Project_data_list.clear();
                    Project_data.data.clear();
                    Project_adapter.notifyDataSetChanged();
                    remouldImplement_adapter.notifyDataSetChanged();
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
        cheZu_dapter = new CheZu_dapter(RemouldImplement_Activty.this, CarNum_data_list);
        mGaoZaoShiShi_CheZu_sp.setAdapter(cheZu_dapter);  //lz
        mGaoZaoShiShi_CheZu_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    carscode = mGaoZaoShiShi_CheZu_sp.getSelectedItem().toString();
                    for (TrainEntity data : carnum_data.data) {
                        if (data.getTrainName().equals(carscode)) {
                            getCarCode = data.getTrainCode();
                            break;
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

        new Thread(new Runnable() {
            @Override
            public void run() {
                //车组号数据请求
                try {
                    carnum_data = RemouldBuz.getTracks(time,
                            BaseApplication.instance().user.getRoleCode(),
                            BaseApplication.instance().user.getDeptCode(),
                            BaseApplication.instance().user.getStuffName(),
                            "1");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                for (TrainEntity data : carnum_data.data) {
                                    CarNum_data_list.add(data.getTrainName());
                                }
                            } catch (Exception e) {
                                return;
                            }
                            cheZu_dapter.notifyDataSetChanged();


                        }
                    });
                } catch (Exception e) {
                    return;
                }
            }
        }).start();
    }

    //项目适配器初始化
    public void HttpProJectName(final String TrainCode, final String date) {
        Project_data_list.clear();
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Project_data = RemouldBuz.getProject(TrainCode,
                            date,
                            BaseApplication.instance().user.getRoleCode(),
                            BaseApplication.instance().user.getDeptCode(),
                            BaseApplication.instance().user.getStuffName(),
                            "1");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                for (ProjectEntity data : Project_data.data) {
                                    Project_data_list.add(data.getProjectName());
                                    Log.e("Lz----Project_data_list", data.getOperationId());
                                }
                                Project_adapter.notifyDataSetChanged();
                            } catch (Exception e) {
                                return;
                            }


                        }
                    });
                }
            }).start();
        } catch (Exception e) {

        }

        ProJectName();
    }

    //下拉项目适配器初始化
    public void ProJectName() {
        Project_adapter = new ArrayAdapter<String>(this, R.layout.project_option_item, R.id.project_item_text, Project_data_list);
        mGaoZaoShiShi_XiangMu_sp.setAdapter(Project_adapter);
        mGaoZaoShiShi_XiangMu_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    getProjectID = "";
                    Project = mGaoZaoShiShi_XiangMu_sp.getSelectedItem().toString();
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
                        Intent attDownload = new Intent(RemouldImplement_Activty.this, FileShowActivity.class);
                        attDownload.putExtra("operationId", getProjectID);
                        startActivity(attDownload);
                    } else {
                        Toast.makeText(RemouldImplement_Activty.this, "请选择项目！", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(RemouldImplement_Activty.this, "有选项未选择！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ChaXun_img:
                HttpChaXun();
                break;
            case R.id.A_keybackfill_btn:
                SureDialog.show(this, "确定将所有辆序全部回填？", new SureDialog.OnConfirmListener() {
                    @Override
                    public void onConfirmClick() {
                        if (ZuoYe_list.size() != 0) {
//                            loadingDialog.show();
                            for (int i = 0; i < ZuoYe_list.size(); i++) {
                                Cars_RkeybackfillHttp(ZuoYe_list.get(i).get("detailId").toString(), i);
                            }
                        } else {
                            Log.e("LZ----", "-------");
                            Toast.makeText(RemouldImplement_Activty.this, "没有数据可回填。", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
                break;
        }
    }

    //请求查询结果
    public void HttpChaXun() {
        try {
            String project = mGaoZaoShiShi_XiangMu_sp.getSelectedItem().toString();
            if (project.isEmpty()) {
                Toast.makeText(RemouldImplement_Activty.this, "有选项未选择！", Toast.LENGTH_SHORT).show();
            } else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final RemouldBuz.SequenceOrStateResult sequenceOrState_data = RemouldBuz.getSequenceOrState(getProjectID,
                                getCarCode,
                                BaseApplication.instance().user.getRoleCode(),
                                BaseApplication.instance().user.getDeptCode(),
                                BaseApplication.instance().user.getStuffName(),
                                "1");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ZuoYe_list.clear();
                                try {
                                    for (SequenceOrState data : sequenceOrState_data.data) {
                                        Map<String, Object> map = new HashMap<String, Object>();
                                        map.put("detailId", data.getDetailId());
                                        map.put("oder", data.getTrainOrder());
                                        map.put("statu", data.getStatus());
                                        ZuoYe_list.add(map);
                                        remouldImplement_adapter.notifyDataSetChanged();
                                    }
                                } catch (Exception e) {
                                    return;
                                }
                            }
                        });
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

    /**
     * 车组适配器
     */
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
     * 实施全部辆序一键回填
     */

    public void Cars_RkeybackfillHttp(final String detailId, final int i) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final ServerResponse response = RemouldBuz.AllbackFill(
                        detailId,
                        "",
                        BaseApplication.instance().user.getRoleCode(),
                        BaseApplication.instance().user.getDeptCode(),
                        BaseApplication.instance().user.getStuffName(),
                        "1");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (response.code == 200) {
                                Toast.makeText(RemouldImplement_Activty.this, "已完成" + i + "条，" + "共" + ZuoYe_list.size() + "条数据。", Toast.LENGTH_SHORT).show();
                                remouldImplement_adapter.notifyDataSetChanged();
                            } else if (response.code == 500) {
                                Toast.makeText(RemouldImplement_Activty.this, "" + response.msg.toString(), Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(RemouldImplement_Activty.this, "" + response.msg.toString(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
//                            if (loadingDialog != null) {
//                                loadingDialog.dismiss();
//                            }
                        }
                    }
                });
            }
        }).start();
    }
}
