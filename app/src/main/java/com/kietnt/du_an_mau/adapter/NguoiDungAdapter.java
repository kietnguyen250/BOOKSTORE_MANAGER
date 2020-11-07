package com.kietnt.du_an_mau.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kietnt.du_an_mau.ListNguoiDungActivity;
import com.kietnt.du_an_mau.R;
import com.kietnt.du_an_mau.dao.NguoiDungDAO;

import com.kietnt.du_an_mau.model.NguoiDung;

import java.util.ArrayList;

import static com.kietnt.du_an_mau.ListNguoiDungActivity.rcv_nguoi_dung;

public class NguoiDungAdapter extends RecyclerView.Adapter<NguoiDungAdapter.NguoiDungViewHolder> {

    Context context;
    ArrayList<NguoiDung> list;
    NguoiDungDAO nguoiDungDAO;
    NguoiDungAdapter nguoiDungAdapter;
    public NguoiDungAdapter(Context context, ArrayList<NguoiDung> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public NguoiDungViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_nguoi_dung, parent, false);
        NguoiDungViewHolder holder = new NguoiDungViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NguoiDungViewHolder holder, final int position) {
        NguoiDung item = list.get(position);
        holder.tv_hoTen.setText(item.getHoTen());
        holder.tv_phone.setText(item.getPhone());

        holder.iv_xoaNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 nguoiDungDAO= new NguoiDungDAO(context);

                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Bạn muốn xóa "+list.get(position).getHoTen()+ "?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Có",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                nguoiDungDAO.deleteNguoiDungByID(list.get(position).getUserName());
                                Toast.makeText(context, "Xóa thành công "+list.get(position).getHoTen(), Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                                capnhatNguoiDung();
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
    private static OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    //*****************

    public class NguoiDungViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView tv_hoTen, tv_phone;
        ImageView iv_xoaNguoiDung;
        public NguoiDungViewHolder(final View view){
            super(view);
            tv_hoTen = view.findViewById(R.id.tv_hoTen);
            tv_phone = view.findViewById(R.id.tv_phone);
            iv_xoaNguoiDung = view.findViewById(R.id.iv_xoaNguoiDung);
            cardView = view.findViewById(R.id.itemNguoiDung);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onItemClick(itemView, getLayoutPosition());
                }
            });
        }


    }
    public void capnhatNguoiDung(){
        nguoiDungDAO = new NguoiDungDAO(context);
        list = nguoiDungDAO.getAllNguoiDung();
        nguoiDungAdapter = new NguoiDungAdapter(context, list);
        rcv_nguoi_dung.setAdapter(nguoiDungAdapter);
    }
}
