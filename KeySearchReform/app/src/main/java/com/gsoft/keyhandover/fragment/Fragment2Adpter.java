package com.gsoft.keyhandover.fragment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.gsoft.keyhandover.R;

import com.gsoft.keyhandover.entity.PowerSupplyEntity;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/17.
 */

public class Fragment2Adpter extends BaseAdapter {
    List<PowerSupplyEntity> lists = new ArrayList<>();
    private LayoutInflater layoutInflater;

    public Fragment2Adpter(Context context,
                           List<PowerSupplyEntity> lists) {
        this.lists = lists;
        this.layoutInflater = LayoutInflater.from(context);
    }

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
        ViewHolder holder = null;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.powersupply_show_table, null);
            holder = new ViewHolder();
            holder.aChuZuHao_txt = view.findViewById(R.id.ChuZuHao_txt);
            holder.aGuDao_txt = view.findViewById(R.id.GuDao_txt);
            holder.aShenQingRen_txt = view.findViewById(R.id.ShenQingRen_txt);
            holder.aTime_txt = view.findViewById(R.id.Time_txt);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        //TODo  赋值
        try {
           // holder.aChuZuHao_txt.setText(lists.get(i).getTraninNumber().toString());
            holder.aGuDao_txt.setText(lists.get(i).getTrackPosition().toString());
            holder.aShenQingRen_txt.setText(lists.get(i).getSupllyApplyName().toString());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            holder.aTime_txt.setText(sdf.format(lists.get(i).getSupplyTime()));
        } catch (Exception e) {

        }

        return view;
    }

    public class ViewHolder {
        TextView aChuZuHao_txt, aGuDao_txt, aShenQingRen_txt, aTime_txt;

    }
}
