package com.kietnt.du_an_mau;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kietnt.du_an_mau.adapter.HoaDonAdapter;
import com.kietnt.du_an_mau.adapter.TheLoaiAdapter;
import com.kietnt.du_an_mau.dao.HoaDonDAO;
import com.kietnt.du_an_mau.dao.TheLoaiDAO;
import com.kietnt.du_an_mau.model.HoaDon;
import com.kietnt.du_an_mau.model.HoaDonChiTiet;
import com.kietnt.du_an_mau.model.TheLoai;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.kietnt.du_an_mau.adapter.HoaDonAdapter.HoaDonViewHolder.tv_maHoaDon;

public class HoaDonActivity extends AppCompatActivity {
    EditText edt_maHoaDon;
    ImageView iv_add_hoaDon;
    public static RecyclerView rcv_hoa_don;
    ArrayList<HoaDon> ds_hoaDon;
    HoaDonDAO hoaDonDAO;
    HoaDonAdapter hoaDonAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        setTitle("HÓA ĐƠN");


        rcv_hoa_don = findViewById(R.id.rcv_hoaDon);
        rcv_hoa_don.setLayoutManager(new LinearLayoutManager(HoaDonActivity.this));

        Toolbar mytb = (Toolbar) findViewById(R.id.my_tb);
        iv_add_hoaDon = findViewById(R.id.iv_add_hoaDon);
        mytb.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(mytb);
        mytb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ds_hoaDon = new ArrayList<>();
        hoaDonDAO = new HoaDonDAO(HoaDonActivity.this);
        ds_hoaDon = hoaDonDAO.getAllHoaDon();
        hoaDonAdapter = new HoaDonAdapter(HoaDonActivity.this,ds_hoaDon);
        rcv_hoa_don.setAdapter(hoaDonAdapter);

        //Vào hóa đơn chi tiết*****
        hoaDonAdapter.setOnItemClickListener(new HoaDonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                HoaDon item = ds_hoaDon.get(position);
                Intent intent = new
                        Intent(HoaDonActivity.this,ListHoaDonChiTietActivity.class);
                Bundle b = new Bundle();
                b.putString("MAHOADON", item.getMaHoaDon());
                intent.putExtras(b);
                startActivity(intent);

            }
        });
        //******

        // Sửa hóa đơn***********
        hoaDonAdapter.setOnItemLongClickListener(new HoaDonAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, final int position) {
                AlertDialog.Builder dialog1 = new AlertDialog.Builder(HoaDonActivity.this);
                LayoutInflater inflater1 =getLayoutInflater();
                View dialogView1 = inflater1.inflate(R.layout.dialog_edit_hoa_don, null);
                dialog1.setView(dialogView1);

                final  EditText edt_maHoaDon = dialogView1.findViewById(R.id.edt_maHoaDon_edit);
                final Button btn_datePicker = dialogView1.findViewById(R.id.btn_datePicker);
                final EditText tv_ngayMua = dialogView1.findViewById(R.id.tv_ngayMua_edit);

                Button btn_edit_hoaDon = dialogView1.findViewById(R.id.btn_edit_hoaDon);
                Button btn_cancel = dialogView1.findViewById(R.id.btn_cancel);

                //Đưa dữ liệu vào ô nhập
                edt_maHoaDon.setText(ds_hoaDon.get(position).getMaHoaDon());
                tv_ngayMua.setText(ds_hoaDon.get(position).getNgayMua());


                final AlertDialog alertDialog1 = dialog1.create();
                alertDialog1.show();

                btn_datePicker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Date today =new Date();
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(today);

                        final int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                        final int months = calendar.get(Calendar.MONTH);
                        final int years = calendar.get(Calendar.YEAR);

                        final Calendar calendar1 = Calendar.getInstance();
                        int date = calendar1.get(Calendar.DAY_OF_MONTH);
                        int month = calendar1.get(Calendar.MONTH);
                        int year = calendar1.get(Calendar.YEAR);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(HoaDonActivity.this, new DatePickerDialog.OnDateSetListener(){
                            @Override
                            public void onDateSet(DatePicker view, int i, int i1, int i2) {
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                calendar1.set(i,i1,i2);
                                tv_ngayMua.setText(simpleDateFormat.format(calendar1.getTime()));
                            }
                        },years, months,dayOfWeek);
                        datePickerDialog.show();
                    }
                });
                btn_edit_hoaDon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        hoaDonDAO = new HoaDonDAO(HoaDonActivity.this);

                        String maHoaDon = ds_hoaDon.get(position).getMaHoaDon();
                        String ngayMua = tv_ngayMua.getText().toString().trim();


                        hoaDonDAO.updateHoaDon(new HoaDon(maHoaDon, ngayMua));
                        ds_hoaDon = hoaDonDAO.getAllHoaDon();
                        hoaDonAdapter = new HoaDonAdapter(HoaDonActivity.this, ds_hoaDon);
                        rcv_hoa_don.setAdapter(hoaDonAdapter);
                        alertDialog1.cancel();
                        Toast.makeText(HoaDonActivity.this, "Sửa hóa đơn thành công!", Toast.LENGTH_LONG).show();

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

        // Thêm HD
        iv_add_hoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog1 = new AlertDialog.Builder(HoaDonActivity.this);
                LayoutInflater inflater1 = getLayoutInflater();
                View dialogView1 = inflater1.inflate(R.layout.dialog_them_hoa_don, null);
                dialog1.setView(dialogView1);
                final EditText edt_maHoaDon = dialogView1.findViewById(R.id.edt_maHoaDon);
                Button btn_them_hoaDon = dialogView1.findViewById(R.id.btn_them_hoaDon);
                Button btn_cancel = dialogView1.findViewById(R.id.btn_cancel);
                final Button btn_datePicker = dialogView1.findViewById(R.id.btn_datePicker);
                final TextView tv_ngayMua = dialogView1.findViewById(R.id.tv_ngayMua);
                final AlertDialog alertDialog1 = dialog1.create();
                alertDialog1.show();

                btn_datePicker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Date today =new Date();
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(today);

                        final int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                        final int months = calendar.get(Calendar.MONTH);
                        final int years = calendar.get(Calendar.YEAR);

                        final Calendar calendar1 = Calendar.getInstance();
                        int date = calendar1.get(Calendar.DAY_OF_MONTH);
                        int month = calendar1.get(Calendar.MONTH);
                        int year = calendar1.get(Calendar.YEAR);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(HoaDonActivity.this, new DatePickerDialog.OnDateSetListener(){
                            @Override
                            public void onDateSet(DatePicker view, int i, int i1, int i2) {
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                calendar1.set(i,i1,i2);
                                tv_ngayMua.setText(simpleDateFormat.format(calendar1.getTime()));
                            }
                        },years, months,dayOfWeek);
                        datePickerDialog.show();
                    }
                });

                btn_them_hoaDon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String ngayMua = tv_ngayMua.getText().toString().trim();
                        hoaDonDAO.insertHoaDon(new HoaDon(null, ngayMua));
                        capnhatHoaDon();
                        alertDialog1.cancel();
                        Toast.makeText(HoaDonActivity.this, "Thêm hóa đơn thành công!!", Toast.LENGTH_LONG).show();
                        Intent intent = new
                                Intent(HoaDonActivity.this,HoaDonChiTietActivity.class);
                        Bundle b = new Bundle();
                        b.putString("MAHOADON", edt_maHoaDon.getText().toString());
                        intent.putExtras(b);
                        startActivity(intent);
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
    public void capnhatHoaDon(){
        hoaDonDAO = new HoaDonDAO(HoaDonActivity.this);
        ds_hoaDon = hoaDonDAO.getAllHoaDon();
        hoaDonAdapter = new HoaDonAdapter(HoaDonActivity.this, ds_hoaDon);
        rcv_hoa_don.setAdapter(hoaDonAdapter);
    }


    @Override
    public void onBackPressed() {
    }
}