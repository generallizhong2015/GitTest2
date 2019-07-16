package com.gsoft.keyhandover.adapters;

import android.content.Context;

import java.text.SimpleDateFormat;

import android.os.Build;
import android.support.annotation.RequiresApi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gsoft.keyhandover.R;
import com.gsoft.keyhandover.entity.OutageEntity;


import java.util.ArrayList;

import java.util.List;

/**
 * Created by Administrator on 2018/3/19.
 */
public class Fragment3Adpter extends BaseAdapter {
    List<OutageEntity> lists = new ArrayList<>();
    private LayoutInflater layoutInflater;

    public Fragment3Adpter(Context context,
                           List<OutageEntity> lists) {
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.outage_show_table, null);
            holder = new ViewHolder();
           // holder.aChuZuHao_txt = view.findViewById(R.id.ChuZuHao_txt);
            holder.aGuDao_txt = view.findViewById(R.id.GuDao_txt);
            holder.aDaundianRen_txt = view.findViewById(R.id.GongdianRen_txt);
            holder.aTime_txt = view.findViewById(R.id.Time_txt);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        //TODo  赋值
        try {
           // holder.aChuZuHao_txt.setText(lists.get(i).getTraninNumber().toString());
            holder.aGuDao_txt.setText(lists.get(i).getTrackPosition().toString());
            holder.aDaundianRen_txt.setText(lists.get(i).getBreaker().toString());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            holder.aTime_txt.setText(sdf.format(lists.get(i).getBreakTime()));
        } catch (Exception e) {

        }
        return view;
    }

    public class ViewHolder {
        TextView aChuZuHao_txt, aGuDao_txt, aDaundianRen_txt, aTime_txt;

    }

}
