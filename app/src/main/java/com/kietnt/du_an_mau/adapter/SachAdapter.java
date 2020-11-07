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
import com.kietnt.du_an_mau.dao.TheLoaiDAO;
import com.kietnt.du_an_mau.model.Sach;


import java.util.ArrayList;

import static com.kietnt.du_an_mau.BookManager.rcv_sach;
import static com.kietnt.du_an_mau.ListTheLoaiActivity.rcv_the_loai;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.SachViewHolder> {

    Context context;
    ArrayList<Sach> list;
    SachDAO sachDAO;
    SachAdapter sachAdapter;
    public SachAdapter(Context context, ArrayList<Sach> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public SachAdapter.SachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sach, parent, false);
        SachAdapter.SachViewHolder holder = new SachAdapter.SachViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SachAdapter.SachViewHolder holder, final int position) {
        Sach item = list.get(position);
        holder.tv_tenSach.setText(item.getTenSach());
        holder.tv_soLuong.setText(item.getSoLuong()+"");
        holder.tv_giaBia.setText(item.getGiaBia()+"");


        holder.iv_xoaSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sachDAO = new SachDAO(context);

                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Bạn muốn xóa "+list.get(position).getTenSach()+ "?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Có",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                sachDAO.deleteSach(list.get(position).getMaSach());
                                Toast.makeText(context, "Xóa thành công "+list.get(position).getTenSach(), Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                                capnhatSach();
                            }
                        });

                builder1.setNegativeButton(
                        "Không",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //Tạo hàm OnItemClickListener ********
    private static SachAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(SachAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
    //*****************

    public class SachViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView tv_tenSach, tv_giaBia, tv_soLuong;
        ImageView iv_xoaSach;
        public SachViewHolder(View view){
            super(view);
            tv_tenSach = view.findViewById(R.id.tv_tenSach);
            tv_giaBia = view.findViewById(R.id.tv_giaBia);
            tv_soLuong = view.findViewById(R.id.tv_soLuong);
            iv_xoaSach = view.findViewById(R.id.iv_xoaSach);
            cardView = view.findViewById(R.id.itemSach);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onItemClick(itemView, getLayoutPosition());
                }
            });
        }
    }

    public void capnhatSach(){
        sachDAO = new SachDAO(context);
        list = sachDAO.getAllSach();
        sachAdapter = new SachAdapter(context, list);
        rcv_sach.setAdapter(sachAdapter);
    }
}

