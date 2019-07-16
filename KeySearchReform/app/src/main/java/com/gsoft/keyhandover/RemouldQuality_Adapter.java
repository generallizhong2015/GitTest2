package com.gsoft.keyhandover;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/1.
 */

public class RemouldQuality_Adapter extends BaseAdapter {
    List<Map<String, Object>> ZhiJian_list;
    private LayoutInflater layoutInflater;

    public RemouldQuality_Adapter(Context context,
                                  List<Map<String, Object>> data) {
        this.ZhiJian_list = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return ZhiJian_list.size();
    }

    @Override
    public Object getItem(int position) {
        return ZhiJian_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MyViewHolder vh;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.remouldquality_item_, null);
            vh = new MyViewHolder();
            vh.hide_1 = convertView.findViewById(R.id.hide_1);
            vh.ll_hide = convertView.findViewById(R.id.ll_hide);
            vh.A_CheZuHao = convertView.findViewById(R.id.CheZuHao);
            vh.A_LiangXu = convertView.findViewById(R.id.LiangXu);
            vh.A_ZuoYecontent = convertView.findViewById(R.id.ZuoYecontent);
            vh.A_State = convertView.findViewById(R.id.jobstate);
            convertView.setTag(vh);
        } else {
            vh = (MyViewHolder) convertView.getTag();
        }
        vh.A_LiangXu.setText((String) ZhiJian_list.get(position).get("oder"));
        if (ZhiJian_list.get(position).get("statu").toString().equals("1")) {
            vh.A_State.setText("完成");
        } else {
            vh.A_State.setText("未完成");
        }
        return convertView;
    }

    class MyViewHolder {
        View itemView;
        TextView hide_1;
        LinearLayout ll_hide;
        public TextView A_CheZuHao;
        public TextView A_LiangXu;
        public TextView A_ZuoYecontent;
        public TextView A_State;
    }
}




