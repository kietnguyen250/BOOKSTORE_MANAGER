package com.kietnt.du_an_mau;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kietnt.du_an_mau.adapter.NguoiDungAdapter;
import com.kietnt.du_an_mau.adapter.TheLoaiAdapter;
import com.kietnt.du_an_mau.dao.NguoiDungDAO;
import com.kietnt.du_an_mau.dao.TheLoaiDAO;
import com.kietnt.du_an_mau.model.NguoiDung;
import com.kietnt.du_an_mau.model.TheLoai;

import java.util.ArrayList;

public class ListTheLoaiActivity extends AppCompatActivity {
    ImageView iv_add_loai;
    public static RecyclerView rcv_the_loai;
    ArrayList<TheLoai> ds_loai;
    TheLoaiAdapter theLoaiAdapter;
    TheLoaiDAO theLoaiDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_the_loai);
        setTitle("THỂ LOẠI");

        rcv_the_loai = findViewById(R.id.rcv_theLoai);
        rcv_the_loai.setLayoutManager(new LinearLayoutManager(ListTheLoaiActivity.this));

        Toolbar mytb = (Toolbar) findViewById(R.id.my_tb);
        iv_add_loai = findViewById(R.id.iv_add_loai);

        mytb.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(mytb);
        mytb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ds_loai = new ArrayList<>();
        theLoaiDAO = new TheLoaiDAO(ListTheLoaiActivity.this);
        ds_loai = theLoaiDAO.getAllTheLoai();
        theLoaiAdapter = new TheLoaiAdapter(ListTheLoaiActivity.this,ds_loai);
        rcv_the_loai.setAdapter(theLoaiAdapter);

        //Edit Thể Loại*****
        theLoaiAdapter.setOnItemClickListener(new TheLoaiAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                AlertDialog.Builder dialog1 = new AlertDialog.Builder(ListTheLoaiActivity.this);
                LayoutInflater inflater1 =getLayoutInflater();
                View dialogView1 = inflater1.inflate(R.layout.dialog_edit_the_loai, null);
                dialog1.setView(dialogView1);

                final EditText edt_maLoai = dialogView1.findViewById(R.id.edt_maLoai_edit);
                final EditText edt_tenLoai = dialogView1.findViewById(R.id.edt_tenLoai_edit);
                final EditText edt_moTa = dialogView1.findViewById(R.id.edt_moTa_edit);
                final EditText edt_viTri = dialogView1.findViewById(R.id.edt_viTri_edit);

                Button btn_edit_loai = dialogView1.findViewById(R.id.btn_edit_loai);
                Button btn_cancel = dialogView1.findViewById(R.id.btn_cancel);

                //Đưa dữ liệu vào ô nhập
                edt_maLoai.setText(ds_loai.get(position).getMaLoai());
                edt_tenLoai.setText(ds_loai.get(position).getTenLoai());
                edt_moTa.setText(ds_loai.get(position).getMoTa());
                edt_viTri.setText(ds_loai.get(position).getViTri()+"");


                final AlertDialog alertDialog1 = dialog1.create();
                alertDialog1.show();
                btn_edit_loai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        theLoaiDAO = new TheLoaiDAO(ListTheLoaiActivity.this);

                        String maLoai = ds_loai.get(position).getMaLoai();
                        String tenLoai = edt_tenLoai.getText().toString().trim();
                        String moTa = edt_moTa.getText().toString().trim();
                        int viTri = Integer.parseInt(edt_viTri.getText().toString().trim());


                        theLoaiDAO.updateTheLoai(new TheLoai(maLoai, tenLoai, moTa, viTri));
                        ds_loai = theLoaiDAO.getAllTheLoai();
                        theLoaiAdapter = new TheLoaiAdapter(ListTheLoaiActivity.this, ds_loai);
                        rcv_the_loai.setAdapter(theLoaiAdapter);
                        alertDialog1.cancel();
                        Toast.makeText(ListTheLoaiActivity.this, "Sửa thể loại thành công!", Toast.LENGTH_LONG).show();

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
        //******

        iv_add_loai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog1 = new AlertDialog.Builder(ListTheLoaiActivity.this);
                LayoutInflater inflater1 = getLayoutInflater();
                View dialogView1 = inflater1.inflate(R.layout.dialog_add_the_loai, null);
                dialog1.setView(dialogView1);
                final EditText edt_tenLoai = dialogView1.findViewById(R.id.edt_tenLoai);
                final EditText edt_moTa = dialogView1.findViewById(R.id.edt_moTa);
                final EditText edt_viTri = dialogView1.findViewById(R.id.edt_viTri);

                Button btn_them_loai = dialogView1.findViewById(R.id.btn_them_loai);
                Button btn_cancel = dialogView1.findViewById(R.id.btn_cancel);
                final AlertDialog alertDialog1 = dialog1.create();
                alertDialog1.show();
                btn_them_loai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tenLoai = edt_tenLoai.getText().toString().trim();
                        String moTa = edt_moTa.getText().toString().trim();
                        int viTri = Integer.parseInt(edt_viTri.getText().toString().trim());
                        theLoaiDAO.insertTheLoai(new TheLoai(null, tenLoai, moTa, viTri));
                        capnhatTheLoai();
                        alertDialog1.cancel();
                        Toast.makeText(ListTheLoaiActivity.this, "Thêm loại thành công!!", Toast.LENGTH_LONG).show();

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

    public void capnhatTheLoai(){
        theLoaiDAO = new TheLoaiDAO(ListTheLoaiActivity.this);
        ds_loai = theLoaiDAO.getAllTheLoai();
        theLoaiAdapter = new TheLoaiAdapter(ListTheLoaiActivity.this, ds_loai);
        rcv_the_loai.setAdapter(theLoaiAdapter);
    }


    @Override
    public void onBackPressed() {
    }


}