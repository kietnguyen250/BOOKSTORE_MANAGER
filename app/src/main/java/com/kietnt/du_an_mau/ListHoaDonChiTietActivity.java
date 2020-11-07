package com.kietnt.du_an_mau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kietnt.du_an_mau.adapter.CartAdapter;
import com.kietnt.du_an_mau.adapter.HoaDonChiTietAdapter;
import com.kietnt.du_an_mau.dao.HoaDonChiTietDAO;
import com.kietnt.du_an_mau.model.HoaDonChiTiet;

import java.util.ArrayList;

public class ListHoaDonChiTietActivity extends AppCompatActivity {
    public ArrayList<HoaDonChiTiet> ds_HDCT = new ArrayList<>();
    public static RecyclerView rcv_cart;
    HoaDonChiTietAdapter adapter = null;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hoa_don_chi_tiet);

        setTitle("CHI TIẾT HÓA ĐƠN");

        rcv_cart = findViewById(R.id.rcv_cart);
        rcv_cart.setLayoutManager(new LinearLayoutManager(ListHoaDonChiTietActivity.this));
        Toolbar mytb = (Toolbar) findViewById(R.id.my_tb);
        mytb.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(mytb);
        mytb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ds_HDCT = new ArrayList<>();
        hoaDonChiTietDAO = new HoaDonChiTietDAO(ListHoaDonChiTietActivity.this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            ds_HDCT = hoaDonChiTietDAO.getAllHoaDonChiTietByID(b.getString("MAHOADON"));
        }

        adapter = new HoaDonChiTietAdapter(ListHoaDonChiTietActivity.this, ds_HDCT);
        rcv_cart.setAdapter(adapter);
    }
}