package com.kietnt.du_an_mau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewManager;
import android.widget.EditText;
import android.widget.Toast;

import com.kietnt.du_an_mau.adapter.SachAdapter;
import com.kietnt.du_an_mau.adapter.TopSachAdapter;
import com.kietnt.du_an_mau.dao.SachDAO;
import com.kietnt.du_an_mau.model.Sach;

import java.util.ArrayList;

public class TopSachActivity extends AppCompatActivity {
    public static ArrayList<Sach> dsSach = new ArrayList<>();

    RecyclerView rcv_topSach;
    TopSachAdapter adapter;
    EditText edt_tim;
    SachDAO sachDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_sach);
        setTitle("TOP SÁCH BÁN CHẠY");

        rcv_topSach = findViewById(R.id.rcv_topSach);
        rcv_topSach.setLayoutManager(new LinearLayoutManager(TopSachActivity.this));
        Toolbar mytb = (Toolbar) findViewById(R.id.my_tb);
        mytb.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(mytb);
        mytb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        edt_tim = findViewById(R.id.edt_tim);


    }
    public void VIEW_SACH_TOP_10(View view) {
        if (Integer.parseInt(edt_tim.getText().toString()) > 13 ||
                Integer.parseInt(edt_tim.getText().toString()) < 0) {
            Toast.makeText(getApplicationContext(), "Không đúng định dạng tháng (1-12)", Toast.LENGTH_SHORT).show();
        } else {
            sachDAO = new SachDAO(TopSachActivity.this);
            dsSach = sachDAO.getSachTop10(edt_tim.getText().toString());
            adapter = new TopSachAdapter(TopSachActivity.this, dsSach);
            rcv_topSach.setAdapter(adapter);
        }
    }
}