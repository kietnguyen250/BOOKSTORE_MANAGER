package com.kietnt.du_an_mau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kietnt.du_an_mau.dao.HoaDonChiTietDAO;

public class ThongKeActivity extends AppCompatActivity {

    TextView tv_tk_ngay, tv_tk_thang, tv_tk_nam;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        setTitle("THỐNG KÊ DOANH THU");
        Toolbar mytb = (Toolbar) findViewById(R.id.my_tb);

        mytb.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(mytb);
        mytb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_tk_ngay = findViewById(R.id.tv_tk_ngay);
        tv_tk_thang = findViewById(R.id.tv_tk_thang);
        tv_tk_nam = findViewById(R.id.tv_tk_nam);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(ThongKeActivity.this);

        tv_tk_ngay.setText(hoaDonChiTietDAO.getDoanhThuTheoNgay()+"");
        tv_tk_thang.setText(hoaDonChiTietDAO.getDoanhThuTheoThang()+"");
        tv_tk_nam.setText(hoaDonChiTietDAO.getDoanhThuTheoNam()+"");
    }
}