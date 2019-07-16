package com.gsoft.keyhandover.fragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gsoft.keyhandover.R;
import com.gsoft.keyhandover.entity.Loan_RetrunEntity;
import com.gsoft.keyhandover.entity.PowerSupplyEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/7.
 */

public class D_A_Loan_Fragment1Adpter extends BaseAdapter {
    private List<List<Loan_RetrunEntity>> lists = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private int flag;

    public D_A_Loan_Fragment1Adpter(Context context,
                                    List<List<Loan_RetrunEntity>> lists, int flag) {
        this.lists = lists;
        this.layoutInflater = LayoutInflater.from(context);
        this.flag = flag;
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
        D_A_Loan_Fragment1Adpter.ViewHolder holder = null;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.d_a_loan, null);
            holder = new D_A_Loan_Fragment1Adpter.ViewHolder();
            holder.nJieChuRen_txt = view.findViewById(R.id.JieChuRen_txt);
            holder.nDengDingka_txt = view.findViewById(R.id.DengDingka_txt);
            holder.nAnQuandai_txt = view.findViewById(R.id.AnQuandai_txt);
            holder.nTime_txt = view.findViewById(R.id.Time_txt);
            view.setTag(holder);
        } else {
            holder = (D_A_Loan_Fragment1Adpter.ViewHolder) view.getTag();
        }
        if (flag == 1) {
            for (int k = 0; k < lists.size(); k++) {
                for (int j = 0; j <k; j++) {
                    holder.nJieChuRen_txt.setText(lists.get(k).get(j).getGetName());
                    holder.nDengDingka_txt.setText(lists.get(k).get(j).getCard().toString());
                    holder.nAnQuandai_txt.setText(lists.get(k).get(j).getBelt().toString());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    holder.nTime_txt.setText(sdf.format(lists.get(k).get(j).getGTime()));
                }
            }
        }else {
            for (int k = 0; k < lists.size(); k++) {
                for (int j = 0; j < k; j++) {
                    holder.nJieChuRen_txt.setText(lists.get(k).get(j).getGetName());
                    holder.nDengDingka_txt.setText(lists.get(k).get(j).getCard().toString());
                    holder.nAnQuandai_txt.setText(lists.get(k).get(j).getBelt().toString());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    holder.nTime_txt.setText(sdf.format(lists.get(k).get(j).getRTime()));
                }
            }
        }
        return view;
    }

    public class ViewHolder {
        TextView nJieChuRen_txt, nDengDingka_txt, nAnQuandai_txt, nTime_txt;

    }
}
