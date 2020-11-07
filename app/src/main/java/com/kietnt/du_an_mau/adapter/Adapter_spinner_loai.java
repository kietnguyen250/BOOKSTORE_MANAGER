package com.kietnt.du_an_mau.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kietnt.du_an_mau.R;
import com.kietnt.du_an_mau.dao.TheLoaiDAO;
import com.kietnt.du_an_mau.model.TheLoai;

import java.util.ArrayList;

public class Adapter_spinner_loai extends BaseAdapter {
    Context context;
    ArrayList<TheLoai> dsLoai;
    TheLoaiDAO loaiDAO;

    public Adapter_spinner_loai(Context context, ArrayList<TheLoai> dsLoai) {
        this.context = context;
        this.dsLoai = dsLoai;

    }

    @Override
    public int getCount() {
        return dsLoai.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_spinner_loai, null);
        TextView tv_spinnerLoai = convertView.findViewById(R.id.tv_spinnerLoai);
        TheLoai loai = dsLoai.get(position);
        tv_spinnerLoai.setText(loai.getTenLoai());
        return convertView;
    }
}
