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

import com.kietnt.du_an_mau.HoaDonActivity;
import com.kietnt.du_an_mau.R;
import com.kietnt.du_an_mau.dao.HoaDonChiTietDAO;
import com.kietnt.du_an_mau.dao.HoaDonDAO;
import com.kietnt.du_an_mau.dao.TheLoaiDAO;
import com.kietnt.du_an_mau.model.HoaDon;
import com.kietnt.du_an_mau.model.TheLoai;

import java.util.ArrayList;

import static com.kietnt.du_an_mau.HoaDonActivity.rcv_hoa_don;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.HoaDonViewHolder> {

    Context context;
    static ArrayList<HoaDon> list;
    ArrayList<HoaDon> listSort;
    HoaDonDAO hoaDonDAO;
    HoaDonAdapter hoaDonAdapter;
    public LayoutInflater inflater;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    public HoaDonAdapter(Context context, ArrayList<HoaDon> list){
        this.context = context;
        this.list = list;
        this.listSort = list;
        this.inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        hoaDonDAO = new HoaDonDAO(context);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(context);
    }
    @NonNull
    @Override
    public HoaDonAdapter.HoaDonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_hoa_don, parent, false);
        HoaDonAdapter.HoaDonViewHolder holder = new HoaDonAdapter.HoaDonViewHolder(view);
        return holder;
    }

    public void capnhatHoaDon(){
        hoaDonDAO = new HoaDonDAO(context);
        list = hoaDonDAO.getAllHoaDon();
        hoaDonAdapter = new HoaDonAdapter(context, list);
        rcv_hoa_don.setAdapter(hoaDonAdapter);
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonAdapter.HoaDonViewHolder holder, final int position) {
        HoaDon item = list.get(position);
        holder.tv_maHoaDon.setText(item.getMaHoaDon());
        holder.tv_Date.setText(item.getNgayMua());

        holder.iv_xoaHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hoaDonChiTietDAO.checkHoaDon(list.get(position).getMaHoaDon())){
                    Toast.makeText(context,"Bạn phải xoá hoá đơn chi tiết trước khi xoá hoá đơn này",Toast.LENGTH_LONG).show();
                }else {
                    hoaDonDAO = new HoaDonDAO(context);

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage("Bạn muốn xóa hóa đơn "+list.get(position).getMaHoaDon()+ "?");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Có",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    hoaDonDAO.deleteHoaDon(list.get(position).getMaHoaDon());
                                    Toast.makeText(context, "Xóa thành công hóa đơn "+list.get(position).getMaHoaDon(), Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    capnhatHoaDon();
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


            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //Tạo hàm OnItemClickListener ********
    private static HoaDonAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {


        void onItemClick(View view, final int position);

    }

    public void setOnItemClickListener(HoaDonAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
    //*****************

    //Tạo hàm OnItemClickListener ********
    private static HoaDonAdapter.OnItemLongClickListener longClickListener;

    public interface OnItemLongClickListener {


        void onItemLongClick(View view, final int position);

    }

    public void setOnItemLongClickListener(HoaDonAdapter.OnItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }
    //*****************

    public static class HoaDonViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        public static TextView tv_maHoaDon, tv_Date;
        ImageView iv_xoaHoaDon;
        public HoaDonViewHolder(final View view){
            super(view);
            tv_maHoaDon = view.findViewById(R.id.tv_maHoaDon);
            tv_Date = view.findViewById(R.id.tv_Date);
            iv_xoaHoaDon = view.findViewById(R.id.iv_xoaHoaDon);
            cardView = view.findViewById(R.id.itemHoaDon);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onItemClick(view, getLayoutPosition());
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (longClickListener != null)
                        longClickListener.onItemLongClick(view, getLayoutPosition());
                    return true;
                }
            });
        }
    }
}