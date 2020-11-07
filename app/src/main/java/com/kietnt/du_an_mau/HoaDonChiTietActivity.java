package com.kietnt.du_an_mau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kietnt.du_an_mau.adapter.CartAdapter;
import com.kietnt.du_an_mau.adapter.Sach_Spinner_Adapter;
import com.kietnt.du_an_mau.dao.HoaDonChiTietDAO;
import com.kietnt.du_an_mau.dao.SachDAO;
import com.kietnt.du_an_mau.model.HoaDon;
import com.kietnt.du_an_mau.model.HoaDonChiTiet;
import com.kietnt.du_an_mau.model.Sach;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.kietnt.du_an_mau.adapter.CartAdapter.CartViewHolder.tv_maSach;
import static com.kietnt.du_an_mau.adapter.CartAdapter.CartViewHolder.tv_thanh_tien;

public class HoaDonChiTietActivity extends AppCompatActivity {
    EditText edt_maHoaDon_HDCT, edt_soLuong_HDCT;
    Spinner edt_maSach_HDCT;
    TextView tv_thanhTien;
    Sach_Spinner_Adapter sach_spinner_adapter;
    Button btn_add_HDCT, btn_thanhToan;
    public static RecyclerView rcv_hdct;
    SachDAO sachDAO;
    ArrayList<Sach> ds_sach;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    public ArrayList<HoaDonChiTiet> dsHDCT = new ArrayList<>();
    CartAdapter cartAdapter = null;
    double thanhTien = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);
        setTitle("HÓA ĐƠN CHI TIẾT");


        rcv_hdct = findViewById(R.id.rcv_htct);
        rcv_hdct.setLayoutManager(new LinearLayoutManager(HoaDonChiTietActivity.this));
        Toolbar mytb = (Toolbar) findViewById(R.id.my_tb);
        mytb.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(mytb);
        mytb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edt_maHoaDon_HDCT = findViewById(R.id.edt_maHoaDon_HDCT);
        edt_maSach_HDCT = findViewById(R.id.edt_tenSach_spinner);
        tv_thanhTien = findViewById(R.id.tv_thanhTien);
        edt_soLuong_HDCT = findViewById(R.id.edt_soLuong_HDCT);
        btn_add_HDCT = findViewById(R.id.btn_add_HDCT);
        btn_thanhToan = findViewById(R.id.btn_thanhToan);

        btn_add_HDCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ADDHoaDonCHITIET(v);
            }
        });
        btn_thanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thanhToanHoaDon(v);
            }
        });


        dsHDCT = new ArrayList<>();
        hoaDonChiTietDAO = new HoaDonChiTietDAO(HoaDonChiTietActivity.this);
        dsHDCT = hoaDonChiTietDAO.getAllHoaDonChiTiet();
        sachDAO = new SachDAO(HoaDonChiTietActivity.this);
        ds_sach = sachDAO.getAllSach();
        sach_spinner_adapter = new Sach_Spinner_Adapter(HoaDonChiTietActivity.this, ds_sach);
        edt_maSach_HDCT.setAdapter(sach_spinner_adapter);
        cartAdapter = new CartAdapter(HoaDonChiTietActivity.this, dsHDCT);
        rcv_hdct.setAdapter(cartAdapter);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edt_maHoaDon_HDCT.setText(b.getString("MAHOADON"));
        }
    }
    public void ADDHoaDonCHITIET(View view) {
        hoaDonChiTietDAO = new HoaDonChiTietDAO(HoaDonChiTietActivity.this);
        String maHoaDon = edt_maHoaDon_HDCT.getText().toString();
        int soLuongMua = Integer.parseInt(edt_soLuong_HDCT.getText().toString());
        int index_ = edt_maSach_HDCT.getSelectedItemPosition();
        String maSach = ds_sach.get(index_).getMaSach();
//        if (maSach.equals(tv_maSach)){
//            int tong =
//        }
        HoaDonChiTietDAO chiTietDAO = new HoaDonChiTietDAO(HoaDonChiTietActivity.this);
        chiTietDAO.inserHoaDonChiTiet(new HoaDonChiTiet(maHoaDon, maSach, soLuongMua));
        Toast.makeText(HoaDonChiTietActivity.this, "Thêm chi tiết hóa đơn thành công", Toast.LENGTH_SHORT).show();
        capnhatHDCT();


    }
    public void thanhToanHoaDon(View view) {
        hoaDonChiTietDAO = new HoaDonChiTietDAO(HoaDonChiTietActivity.this);
        //tinh tien
        thanhTien = 0;
        try {
            for (HoaDonChiTiet hd: dsHDCT) {
                hoaDonChiTietDAO.inserHoaDonChiTiet(hd);
                thanhTien = thanhTien + hd.getSoLuongMua() * (Double.parseDouble(tv_thanh_tien.getText().toString()));
            }
            tv_thanhTien.setText("Tổng tiền: " +thanhTien);
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public void capnhatHDCT(){
        hoaDonChiTietDAO = new HoaDonChiTietDAO(HoaDonChiTietActivity.this);
        dsHDCT = hoaDonChiTietDAO.getAllHoaDonChiTiet();
        cartAdapter = new CartAdapter(HoaDonChiTietActivity.this, dsHDCT);
        rcv_hdct.setAdapter(cartAdapter);
    }
}