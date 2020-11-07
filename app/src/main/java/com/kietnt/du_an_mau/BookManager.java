package com.kietnt.du_an_mau;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kietnt.du_an_mau.adapter.Adapter_spinner_loai;
import com.kietnt.du_an_mau.adapter.SachAdapter;
import com.kietnt.du_an_mau.adapter.TheLoaiAdapter;
import com.kietnt.du_an_mau.dao.SachDAO;
import com.kietnt.du_an_mau.dao.TheLoaiDAO;
import com.kietnt.du_an_mau.model.Sach;
import com.kietnt.du_an_mau.model.TheLoai;


import java.util.ArrayList;

public class BookManager extends AppCompatActivity {
    ImageView iv_add_sach;
    public static RecyclerView rcv_sach;
    public static ArrayList<Sach> ds_sach;
    SachAdapter sachAdapter;
    ArrayList<TheLoai> dsLoai;
    Adapter_spinner_loai spinner_loai;
    SachDAO sachDAO;
    public static TheLoaiDAO loaiDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manager);
        setTitle("BOOK MANAGER");

        rcv_sach = findViewById(R.id.rcv_sach);
        rcv_sach.setLayoutManager(new LinearLayoutManager(BookManager.this));

        Toolbar mytb = (Toolbar) findViewById(R.id.my_tb);
        iv_add_sach = findViewById(R.id.iv_add_sach);

        mytb.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(mytb);
        mytb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ds_sach = new ArrayList<>();
        sachDAO = new SachDAO(BookManager.this);
        ds_sach = sachDAO.getAllSach();
        sachAdapter = new SachAdapter(BookManager.this, ds_sach);
        rcv_sach.setAdapter(sachAdapter);


        //Edit sách*******
        sachAdapter.setOnItemClickListener(new SachAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                AlertDialog.Builder dialog1 = new AlertDialog.Builder(BookManager.this);
                LayoutInflater inflater1 =getLayoutInflater();
                View dialogView1 = inflater1.inflate(R.layout.dialog_edit_sach, null);
                dialog1.setView(dialogView1);

                final EditText edt_maSach = dialogView1.findViewById(R.id.edt_maSach_edit);
                final Spinner sp_TheLoai = dialogView1.findViewById(R.id.sp_TheLoai_edit);
                final EditText edt_tenSach = dialogView1.findViewById(R.id.edt_tenSach_edit);
                final EditText edt_tacGia = dialogView1.findViewById(R.id.edt_tacGia_edit);
                final TextView edt_nhaXB = dialogView1.findViewById(R.id.edt_nhaXB_edit);
                final EditText edt_giaBia = dialogView1.findViewById(R.id.edt_giaBia_edit);
                final EditText edt_soLuong = dialogView1.findViewById(R.id.edt_soLuong_edit);

                Button btn_edit_sach = dialogView1.findViewById(R.id.btn_edit_sach);
                Button btn_cancel = dialogView1.findViewById(R.id.btn_cancel);
                //Đưa dữ liệu vào ô nhập

//                sp_TheLoai.setText(ds_sach.get(position).getMaLoai());
                edt_maSach.setText(ds_sach.get(position).getMaSach());
                edt_tenSach.setText(ds_sach.get(position).getTenSach());
                edt_tacGia.setText(ds_sach.get(position).getTacGia());
                edt_nhaXB.setText(ds_sach.get(position).getNXB());
                edt_giaBia.setText(ds_sach.get(position).getGiaBia()+"");
                edt_soLuong.setText(ds_sach.get(position).getSoLuong()+"");

                loaiDAO = new TheLoaiDAO(BookManager.this);
                dsLoai = loaiDAO.getAllTheLoai();
                spinner_loai =new Adapter_spinner_loai(BookManager.this, dsLoai);
                sp_TheLoai.setAdapter(spinner_loai);
                int indexx =Integer.parseInt(ds_sach.get(position).getMaLoai());
                sp_TheLoai.setSelection(indexx-1);


                final AlertDialog alertDialog1 = dialog1.create();
                alertDialog1.show();
                btn_edit_sach.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        sachDAO = new SachDAO(BookManager.this);

                        String maSach = ds_sach.get(position).getMaSach();
                        String tenSach = edt_tenSach.getText().toString().trim();
                        String tacGia = edt_tacGia.getText().toString().trim();
                        String nhaXB = edt_nhaXB.getText().toString().trim();
                        Double giaBia = Double.parseDouble(edt_giaBia.getText().toString().trim());
                        int soLuong = Integer.parseInt(edt_soLuong.getText().toString().trim());

                        int index1 = sp_TheLoai.getSelectedItemPosition();

                        String maLoai = dsLoai.get(index1).getMaLoai();


                        sachDAO.updateSach(new Sach(maSach, maLoai, tenSach, tacGia, nhaXB, giaBia, soLuong));
                        ds_sach = sachDAO.getAllSach();
                        sachAdapter = new SachAdapter(BookManager.this, ds_sach);
                        rcv_sach.setAdapter(sachAdapter);
                        alertDialog1.cancel();
                        Toast.makeText(BookManager.this, "Sửa thông tin sách thành công!", Toast.LENGTH_LONG).show();

                    }
                });
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog1.cancel();

                    }
                });
            }
        });
        //**********

        iv_add_sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog1 = new AlertDialog.Builder(BookManager.this);
                LayoutInflater inflater1 = getLayoutInflater();
                View dialogView1 = inflater1.inflate(R.layout.dialog_them_sach, null);
                dialog1.setView(dialogView1);

                final Spinner sp_TheLoai = dialogView1.findViewById(R.id.sp_TheLoai);
                final EditText edt_tenSach = dialogView1.findViewById(R.id.edt_tenSach);
                final EditText edt_tacGia = dialogView1.findViewById(R.id.edt_tacGia);
                final TextView edt_nhaXB = dialogView1.findViewById(R.id.edt_nhaXB);
                final EditText edt_giaBia = dialogView1.findViewById(R.id.edt_giaBia);
                final EditText edt_soLuong = dialogView1.findViewById(R.id.edt_soLuong);

                Button btn_them_sach = dialogView1.findViewById(R.id.btn_them_sach);
                Button btn_cancel = dialogView1.findViewById(R.id.btn_cancel);

                loaiDAO = new TheLoaiDAO(BookManager.this);
                dsLoai = loaiDAO.getAllTheLoai();
                spinner_loai =new Adapter_spinner_loai(BookManager.this, dsLoai);
                sp_TheLoai.setAdapter(spinner_loai);

                final AlertDialog alertDialog1 = dialog1.create();
                alertDialog1.show();
                btn_them_sach.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tenSach = edt_tenSach.getText().toString().trim();
                        String tacGia = edt_tacGia.getText().toString().trim();
                        String nhaXB = edt_nhaXB.getText().toString().trim();
                        Double giaBia = Double.parseDouble(edt_giaBia.getText().toString().trim());
                        int soLuong = Integer.parseInt(edt_soLuong.getText().toString().trim());

                        int index_ = sp_TheLoai.getSelectedItemPosition();

                        String maLoai = dsLoai.get(index_).getMaLoai();
                        sachDAO = new SachDAO(BookManager.this);
                        sachDAO.insertSach(new Sach(null, maLoai, tenSach, tacGia, nhaXB, giaBia, soLuong));

                        capnhatSach();

                        alertDialog1.cancel();
                        Toast.makeText(BookManager.this, "Thêm sách thành công!!", Toast.LENGTH_LONG).show();
                    }
                });
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog1.cancel();

                    }
                });
            }
        });

    }

    public void capnhatSach(){
        sachDAO = new SachDAO(BookManager.this);
        ds_sach = sachDAO.getAllSach();
        sachAdapter = new SachAdapter(BookManager.this, ds_sach);
        rcv_sach.setAdapter(sachAdapter);
    }


    @Override
    public void onBackPressed() {
    }


}