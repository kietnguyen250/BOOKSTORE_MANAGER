package com.kietnt.du_an_mau.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kietnt.du_an_mau.R;
import com.kietnt.du_an_mau.dao.SachDAO;
import com.kietnt.du_an_mau.model.Sach;

import java.util.ArrayList;

public class TopSachAdapter extends RecyclerView.Adapter<TopSachAdapter.SachViewHolder> {

    Context context;
    ArrayList<Sach> list;
    SachDAO sachDAO;
    TopSachAdapter sachAdapter;
    public TopSachAdapter(Context context, ArrayList<Sach> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public TopSachAdapter.SachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_top_sach, parent, false);
        TopSachAdapter.SachViewHolder holder = new TopSachAdapter.SachViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull TopSachAdapter.SachViewHolder holder, final int position) {
        Sach item = list.get(position);
        holder.tv_ma_sach.setText(item.getMaSach());
        holder.tv_so_luong.setText(item.getSoLuong()+"");
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class SachViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView tv_ma_sach, tv_so_luong;

        public SachViewHolder(View view){
            super(view);
            tv_ma_sach = view.findViewById(R.id.tv_ma_sach);
            tv_so_luong = view.findViewById(R.id.tv_so_luong);
            cardView = view.findViewById(R.id.itemSach);

        }
    }

}