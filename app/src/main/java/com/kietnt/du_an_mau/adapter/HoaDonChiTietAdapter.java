package com.kietnt.du_an_mau.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import com.kietnt.du_an_mau.dao.HoaDonChiTietDAO;
import com.kietnt.du_an_mau.dao.SachDAO;
import com.kietnt.du_an_mau.model.HoaDonChiTiet;
import com.kietnt.du_an_mau.model.Sach;

import java.util.ArrayList;
import java.util.List;

import static com.kietnt.du_an_mau.ListHoaDonChiTietActivity.rcv_cart;


public class HoaDonChiTietAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    ArrayList<HoaDonChiTiet> list_hdct;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    HoaDonChiTietAdapter hoaDonChiTietAdapter;
    Sach_Spinner_Adapter sach_spinner_adapter;
    SachDAO sachDAO;
    ArrayList<Sach> ds_sach;
    public LayoutInflater inflater;
    public HoaDonChiTietAdapter(Context context, ArrayList<HoaDonChiTiet> list_hdct){
        this.context = context;
        this.list_hdct = list_hdct;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_hoa_don_chi_tiet, parent, false);
        CartAdapter.CartViewHolder holder = new CartAdapter.CartViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, final int position) {
        HoaDonChiTiet item = list_hdct.get(position);
        int vitriTen = Integer.parseInt(item.getMaSach());
        SachDAO sachDAO = new SachDAO(context);
        ds_sach = new ArrayList<Sach>();
        ds_sach = sachDAO.getAllSach();
        holder.tv_maSach.setText("Tên sách: "+ds_sach.get(vitriTen-1).getTenSach());
        holder.tv_so_luong.setText("Số lượng: "+item.getSoLuongMua());


        int vitriGia = Integer.parseInt(item.getMaSach());
        holder.tv_gia_bia.setText("Giá bìa: "+ds_sach.get(vitriGia-1).getGiaBia() +" VNĐ");
        holder.tv_thanh_tien.setText("Thành tiền: "+item.getSoLuongMua()*(ds_sach.get(vitriGia-1).getGiaBia())+" VNĐ");

        holder.iv_xoaHDCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hoaDonChiTietDAO = new HoaDonChiTietDAO(context);

                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Bạn muốn xóa hóa đơn "+list_hdct.get(position).getMaHDCT()+ "?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Có",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                hoaDonChiTietDAO.deleteHoaDonChiTietByID(list_hdct.get(position).getMaHDCT()+"");
                                Toast.makeText(context, "Xóa thành công hóa đơn "+list_hdct.get(position).getMaHDCT(), Toast.LENGTH_SHORT).show();
                                list_hdct.remove(position);
                                changeDataset(list_hdct);
                                dialog.cancel();
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
        return list_hdct.size();
    }

    //Tạo hàm OnItemClickListener ********
    private static CartAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(CartAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
    //*****************

    public static class CartViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        public static TextView tv_maSach, tv_so_luong, tv_gia_bia, tv_thanh_tien;
        ImageView iv_xoaHDCT;
        public CartViewHolder(View view){
            super(view);
            tv_maSach = view.findViewById(R.id.tv_maSach);
            tv_so_luong = view.findViewById(R.id.tv_so_luong_mua);
            tv_gia_bia = view.findViewById(R.id.tv_gia_bia);
            tv_thanh_tien = view.findViewById(R.id.tv_thanh_tien);
            iv_xoaHDCT = view.findViewById(R.id.iv_xoaHDCT);
            cardView = view.findViewById(R.id.itemHDCT);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onItemClick(itemView, getLayoutPosition());
                }
            });
        }
    }

    public void changeDataset(ArrayList<HoaDonChiTiet> items){
        this.list_hdct = items;
        notifyDataSetChanged();
    }
}
