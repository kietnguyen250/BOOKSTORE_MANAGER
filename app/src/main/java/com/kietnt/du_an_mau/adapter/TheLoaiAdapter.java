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

import com.kietnt.du_an_mau.ListTheLoaiActivity;
import com.kietnt.du_an_mau.R;

import com.kietnt.du_an_mau.dao.TheLoaiDAO;
import com.kietnt.du_an_mau.model.TheLoai;

import java.util.ArrayList;

import static com.kietnt.du_an_mau.ListTheLoaiActivity.rcv_the_loai;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.TheLoaiViewHolder> {

    Context context;
    ArrayList<TheLoai> list;
    TheLoaiDAO theLoaiDAO;
    TheLoaiAdapter theLoaiAdapter;

    public TheLoaiAdapter(Context context, ArrayList<TheLoai> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public TheLoaiAdapter.TheLoaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_the_loai, parent, false);
        TheLoaiAdapter.TheLoaiViewHolder holder = new TheLoaiAdapter.TheLoaiViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TheLoaiAdapter.TheLoaiViewHolder holder, final int position) {
        TheLoai item = list.get(position);
        holder.tv_maLoai.setText(item.getMaLoai());
        holder.tv_tenLoai.setText(item.getTenLoai());


        holder.iv_xoaTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theLoaiDAO = new TheLoaiDAO(context);

                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Bạn muốn xóa "+list.get(position).getTenLoai()+ "?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Có",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                theLoaiDAO.deleteTheLoai(list.get(position).getMaLoai());
                                Toast.makeText(context, "Xóa thành công "+list.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                                capnhatTheLoai();
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
    private static TheLoaiAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(TheLoaiAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
    //*****************

    public class TheLoaiViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView tv_maLoai, tv_tenLoai;
        ImageView iv_xoaTheLoai;
        public TheLoaiViewHolder(final View view){
            super(view);
            tv_maLoai = view.findViewById(R.id.tv_maLoai);
            tv_tenLoai = view.findViewById(R.id.tv_tenLoai);
            cardView = view.findViewById(R.id.itemTheLoai);
            iv_xoaTheLoai = view.findViewById(R.id.iv_xoaTheLoai);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onItemClick(itemView, getLayoutPosition());
                }
            });
        }
    }
    public void capnhatTheLoai(){
        theLoaiDAO = new TheLoaiDAO(context);
        list = theLoaiDAO.getAllTheLoai();
        theLoaiAdapter = new TheLoaiAdapter(context, list);
        rcv_the_loai.setAdapter(theLoaiAdapter);
    }
}

