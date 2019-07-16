package com.gsoft.keyhandover.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gsoft.keyhandover.BaseApplication;
import com.gsoft.keyhandover.assist.MyTextWatcher;
import com.gsoft.keyhandover.R;
import com.gsoft.keyhandover.buz.KeyInfoBuz;
import com.gsoft.keyhandover.entity.KeyInfoEntity;
import com.gsoft.keyhandover.handwriting.DialogListener;
import com.gsoft.keyhandover.handwriting.WritePadDialog;
import com.gsoft.keyhandover.util.L;
import com.gsoft.keyhandover.util.StringUtils;
import com.gsoft.keyhandover.util.UpImgFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/22.
 */

public class KeyFragment1 extends Fragment implements View.OnClickListener {
    TextView keyNameTv, keyNumTv, timeTv;
    TextView carTypeSp, keyTypeSp, carCodeSp;
    TextView confirmBtn;
    TextView userEt;
    TextView jiaochuUser;
    EditText getInfoEt;
    String keyId = "";
    KeyInfoBuz.KeyInfoResult result;
    //V108
    Button mSign_Btn;
    private Bitmap mSignBitmap;
    private ImageView mivSign_img;
    String KeyQzId;//签字图片上传id
    String _path;//图片路径
    public static String end = "\r\n";
    int getimgcode;//图片上传返回值


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_keyhandover, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        keyNameTv = view.findViewById(R.id.KeyName);
        keyNumTv = view.findViewById(R.id.KeyShuLiang_Tv);
        timeTv = view.findViewById(R.id.JiaoJieTime);
        userEt = view.findViewById(R.id.JiaoJieRen_Ext);
        jiaochuUser = view.findViewById(R.id.JiaochuRen_Ext);
        getInfoEt = view.findViewById(R.id.getinfo_et);
        carTypeSp = view.findViewById(R.id.CarType_Sp);
        keyTypeSp = view.findViewById(R.id.KeyType_Sp);
        carCodeSp = view.findViewById(R.id.CheZuHao_Sp);
        (confirmBtn = view.findViewById(R.id.confirm_btn)).setOnClickListener(this);
        getInfoEt.addTextChangedListener(watcher);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timeTv.setText(df.format(new Date()));
        if (null != BaseApplication.instance().user) {
            userEt.setText(BaseApplication.instance().user.getStuffName());
        }
        mSign_Btn = view.findViewById(R.id.Sign_Btn);
        mivSign_img = view.findViewById(R.id.iv_sign_img);
        mSign_Btn.setOnClickListener(this);
        mSign_Btn.setEnabled(false);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm_btn:
                try {
                    final KeyInfoEntity info = result.data;
                    if (BaseApplication.instance().user.getStuffName().equals(info.getJoinName())) {
                        if (!_path.isEmpty()) {
                            submit();
                        } else {
                            Toast.makeText(getActivity(), "签字不能不为空！", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        submit();
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "请查看是否已签字！", Toast.LENGTH_SHORT).show();
                    return;
                }

                break;
            case R.id.Sign_Btn:
                //调用签字版
                WritePadDialog writeTabletDialog = new WritePadDialog(
                        getActivity(), new DialogListener() {
                    public void refreshActivity(Object object) {
                        mSignBitmap = (Bitmap) object;
                        mivSign_img.setImageBitmap(mSignBitmap);
                    }

                    @Override
                    public void refreshActivitypath(Object path) {
                        _path = String.valueOf(path);
                        Log.e("Lz", _path);
                    }
                });
                writeTabletDialog.show();
        }

    }


    private MyTextWatcher watcher = new MyTextWatcher() {
        @Override
        public void onChanged() {
            String code = getInfoEt.getText().toString().trim();
            if (!StringUtils.isEmpty(code)) {
                L.i("lz", "code:" + code);
                getInfoEt.setText("");
                getKeyInfo(code);
            }

        }
    };

    void getKeyInfo(final String s) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                result = KeyInfoBuz.getKeyInfo(s);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (null != result && result.code == 200) {
                            KeyInfoEntity info = result.data;
                            keyNameTv.setText(info.getKeyName());
                            keyNumTv.setText(info.getKeyNumber() + "");
                            keyId = info.getKeyId();
                            carTypeSp.setText(info.getTrainType());
                            keyTypeSp.setText(info.getKeyType());
                            carCodeSp.setText(info.getEmu());
                            jiaochuUser.setText(info.getJoinName());
                            if (BaseApplication.instance().user.getStuffName().equals(info.getJoinName())) {
                                Log.e("LZ判断", BaseApplication.instance().user.getStuffName() + info.getJoinName());
                                mSign_Btn.setEnabled(true);
                            } else {
                                mSign_Btn.setEnabled(false);
                            }
                        } else {
                            Toast.makeText(getActivity(), "获取钥匙信息失败！", Toast.LENGTH_SHORT).show();
                            L.i("lz", "请求失败！。。。。。。。。。");
                            //Toast.makeText(getActivity(), "扫描失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();

    }

    void submit() {
        if (StringUtils.isEmpty(userEt)) {
            Toast.makeText(getActivity(), "交接人不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }

        if (StringUtils.isEmpty(jiaochuUser)) {
            Toast.makeText(getActivity(), "钥匙信息有误！", Toast.LENGTH_SHORT).show();
            return;
        }

        if (StringUtils.isEmpty(keyId)) {
            Toast.makeText(getActivity(), "请获取钥匙信息！", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            final KeyInfoEntity info = result.data;

            new Thread(new Runnable() {
                @Override
                public void run() {

                    final KeyInfoBuz.SubmitInfoResult result = KeyInfoBuz.submitInfo(userEt.getText().toString().trim(), keyId, BaseApplication.instance().user.getStuffId());
                    KeyQzId = result.data;//签字id
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (null != result && result.code == 200) {
                                KeyQzId = result.data;//签字id
                                Toast.makeText(getActivity(), "提交成功！", Toast.LENGTH_SHORT).show();
                                if (BaseApplication.instance().user.getStuffName().equals(info.getJoinName())) {
                                    Log.e("LZ判断", BaseApplication.instance().user.getStuffName() + info.getJoinName());
                                    UpFile(KeyQzId);
                                } else {
                                    getActivity().finish();
                                }
                            } else {
                                L.i("lz", "请求失败！。。。。。。。。。");
                                Toast.makeText(getActivity(), result.msg, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }).start();
        } catch (Exception e) {
            return;
        }
    }

    private void UpFile(final String id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Map<String, Object> paramMap = new HashMap<String, Object>(); //文本数据全部添加到Map里
                paramMap.put("id", id);//参数
                paramMap.put("fileType", "1");//参数

                final File pictureFile = new File(_path); //通过路径获取文件_path是图片地址
                try {
                    getimgcode = UpImgFile.doPostPicture(getActivity(), pictureFile.getPath(), paramMap, pictureFile);//调用方法
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("LZ", "---------" + "e:" + e);
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (getimgcode == 200) {//这里是返回值
                            Toast.makeText(getActivity(), "上传成功！", Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                        } else {
                            Toast.makeText(getActivity(), "上传失败！代号" + getimgcode, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        }).start();
    }

    @Override
    public void onStart() {
        super.onStart();
        getInfoEt.requestFocus();
    }
}
