package com.kietnt.du_an_mau;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    CardView iv_per, iv_loai, iv_sach, iv_hoadon, iv_thongke, iv_ttsach;
    TextView tv_change_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("TRANG CHỦ");

        tv_change_pass = findViewById(R.id.tv_change_pass);
        iv_per = findViewById(R.id.iv_per);
        iv_loai = findViewById(R.id.iv_loai);
        iv_sach= findViewById(R.id.iv_sach);
        iv_hoadon= findViewById(R.id.iv_hoadon);
        iv_thongke= findViewById(R.id.iv_thongke);
        iv_ttsach = findViewById(R.id.iv_ttsach);


        tv_change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(HomeActivity.this);
                LayoutInflater inflater2 = getLayoutInflater();
                View dialogView2 = inflater2.inflate(R.layout.activity_change_password, null);
                dialogBuilder.setView(dialogView2);
                Button btn_change = dialogView2.findViewById(R.id.btn_change);
                Button btn_huy = dialogView2.findViewById(R.id.btn_huy);
                final AlertDialog alertDialog2 = dialogBuilder.create();
                alertDialog2.show();
                btn_change.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                btn_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog2.cancel();

                    }
                });
            }
        });

        iv_per.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, ListNguoiDungActivity.class);
                startActivity(i);
            }
        });

        iv_loai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, ListTheLoaiActivity.class);
                startActivity(i);
            }
        });

        iv_sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, BookManager.class);
                startActivity(i);
            }
        });

        iv_hoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, HoaDonActivity.class);
                startActivity(i);
            }
        });

        iv_thongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, ThongKeActivity.class);
                startActivity(i);
            }
        });

        iv_ttsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, TopSachActivity.class);
                startActivity(i);
            }
        });

    }
}