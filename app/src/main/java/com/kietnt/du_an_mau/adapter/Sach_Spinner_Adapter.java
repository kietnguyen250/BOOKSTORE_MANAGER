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
import com.kietnt.du_an_mau.model.Sach;
import com.kietnt.du_an_mau.model.TheLoai;

import java.util.ArrayList;

public class Sach_Spinner_Adapter extends BaseAdapter {
    Context context;
    ArrayList<Sach> dsSach;
    TheLoaiDAO loaiDAO;

    public Sach_Spinner_Adapter(Context context, ArrayList<Sach> dsSach) {
        this.context = context;
        this.dsSach = dsSach;

    }

    @Override
    public int getCount() {
        return dsSach.size();
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
        convertView = inflater.inflate(R.layout.item_spinner_sach, null);
        TextView tv_spinnerSach = convertView.findViewById(R.id.tv_spinnerSach);
        Sach sach = dsSach.get(position);
        tv_spinnerSach.setText(sach.getMaSach() +" | "+ sach.getTenSach());
        return convertView;
    }
}
