package com.gsoft.keyhandover;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/20.
 */

public class RemouldImplement_Adapter extends BaseAdapter implements View.OnClickListener, ListAdapter {
    List<Map<String, Object>> sequenceOrState_data_list;
    private LayoutInflater layoutInflater;

    public RemouldImplement_Adapter(Context context, List<Map<String, Object>> sequenceOrState_data_list) {
        this.sequenceOrState_data_list = sequenceOrState_data_list;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return sequenceOrState_data_list.size();
    }

    @Override
    public Object getItem(int position) {
        return sequenceOrState_data_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final MyViewHolder vh;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.remouldimplemen_item, null);
            vh = new MyViewHolder();
            vh.A_LiangXu = convertView.findViewById(R.id.LiangXu);
            vh.A_state_txt = convertView.findViewById(R.id.state_txt);
            convertView.setTag(vh);
        } else {
            vh = (MyViewHolder) convertView.getTag();
        }
        vh.A_LiangXu.setText(sequenceOrState_data_list.get(position).get("oder") + "");
        if (sequenceOrState_data_list.get(position).get("statu").toString().equals("1")) {
            vh.A_state_txt.setText("完成");
        } else {
            vh.A_state_txt.setText("未完成");
        }
        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hide_1:
                break;

        }
    }


    class MyViewHolder {
        public TextView A_LiangXu;
        public TextView A_state_txt;

    }
}
