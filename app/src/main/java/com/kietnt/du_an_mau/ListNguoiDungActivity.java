package com.kietnt.du_an_mau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kietnt.du_an_mau.adapter.NguoiDungAdapter;
import com.kietnt.du_an_mau.adapter.TheLoaiAdapter;
import com.kietnt.du_an_mau.dao.NguoiDungDAO;
import com.kietnt.du_an_mau.dao.TheLoaiDAO;
import com.kietnt.du_an_mau.model.NguoiDung;

import java.util.ArrayList;

import static com.kietnt.du_an_mau.LoginActivity.chk_save;


public class ListNguoiDungActivity extends AppCompatActivity {
    public static RecyclerView rcv_nguoi_dung;
    ArrayList<NguoiDung> ds_nguoidung;
    NguoiDungDAO nguoiDungDAO;
    NguoiDungAdapter nguoiDungAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nguoi_dung);
        setTitle("NGƯỜI DÙNG");
        rcv_nguoi_dung = findViewById(R.id.rcv_nguoiDung);
        rcv_nguoi_dung.setLayoutManager(new LinearLayoutManager(ListNguoiDungActivity.this));

        Toolbar mytb = (Toolbar) findViewById(R.id.my_tb);

        mytb.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(mytb);
        mytb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ds_nguoidung = new ArrayList<>();
        nguoiDungDAO = new NguoiDungDAO(ListNguoiDungActivity.this);
        ds_nguoidung = nguoiDungDAO.getAllNguoiDung();
        nguoiDungAdapter = new NguoiDungAdapter(ListNguoiDungActivity.this, ds_nguoidung);
        rcv_nguoi_dung.setAdapter(nguoiDungAdapter);

        //Edit Người dùng ***************
        //setOnItemClick từ hàm tạo của adapter
        nguoiDungAdapter.setOnItemClickListener(new NguoiDungAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                AlertDialog.Builder dialog1 = new AlertDialog.Builder(ListNguoiDungActivity.this);
                LayoutInflater inflater1 =getLayoutInflater();
                View dialogView1 = inflater1.inflate(R.layout.dialog_edit_nguoi_dung, null);
                dialog1.setView(dialogView1);

                final EditText edt_username = dialogView1.findViewById(R.id.edt_taikhoan_edit);
                final EditText edt_hoten = dialogView1.findViewById(R.id.edt_hoten_edit);
                final EditText edt_sdt = dialogView1.findViewById(R.id.edt_sdt_edit);

                Button btn_sua_nguoi = dialogView1.findViewById(R.id.btn_edit_nguoi);
                Button btn_cancel = dialogView1.findViewById(R.id.btn_cancel);

                //Đưa dữ liệu vào ô nhập
                edt_username.setText(ds_nguoidung.get(position).getUserName());
                edt_hoten.setText(ds_nguoidung.get(position).getHoTen());
                edt_sdt.setText(ds_nguoidung.get(position).getPhone());


                final AlertDialog alertDialog1 = dialog1.create();
                alertDialog1.show();
                btn_sua_nguoi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        nguoiDungDAO = new NguoiDungDAO(ListNguoiDungActivity.this);

                        String username = ds_nguoidung.get(position).getUserName();
                        String hoTen = edt_hoten.getText().toString().trim();
                        String sdt = edt_sdt.getText().toString().trim();


                        nguoiDungDAO.updateNguoiDung(new NguoiDung(username, sdt, hoTen));
                        ds_nguoidung = nguoiDungDAO.getAllNguoiDung();
                        nguoiDungAdapter = new NguoiDungAdapter(ListNguoiDungActivity.this, ds_nguoidung);
                        rcv_nguoi_dung.setAdapter(nguoiDungAdapter);
                        alertDialog1.cancel();
                        Toast.makeText(ListNguoiDungActivity.this, "Sửa người dùng thành công!", Toast.LENGTH_LONG).show();

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
        //***********

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
        return true;
    }
    @Override
    public void onBackPressed() {
    }

    public void capnhatNguoiDung(){
        nguoiDungDAO = new NguoiDungDAO(ListNguoiDungActivity.this);
        ds_nguoidung = nguoiDungDAO.getAllNguoiDung();
        nguoiDungAdapter = new NguoiDungAdapter(ListNguoiDungActivity.this, ds_nguoidung);
        rcv_nguoi_dung.setAdapter(nguoiDungAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.item1:

                AlertDialog.Builder dialog1 = new AlertDialog.Builder(ListNguoiDungActivity.this);
                LayoutInflater inflater1 = getLayoutInflater();
                View dialogView1 = inflater1.inflate(R.layout.dialog_add_nguoi_dung, null);
                dialog1.setView(dialogView1);

                final EditText edt_taikhoan = dialogView1.findViewById(R.id.edt_taikhoan);
                final EditText edt_matkhau = dialogView1.findViewById(R.id.edt_matkhau);
                final EditText edt_rematkhau = dialogView1.findViewById(R.id.edt_rematkhau);
                final EditText edt_hoten = dialogView1.findViewById(R.id.edt_hoten);
                final EditText edt_sdt = dialogView1.findViewById(R.id.edt_sdt);

                Button btn_them_nguoi = dialogView1.findViewById(R.id.btn_them_nguoi);
                Button btn_cancel = dialogView1.findViewById(R.id.btn_cancel);
                final AlertDialog alertDialog1 = dialog1.create();
                alertDialog1.show();
                btn_them_nguoi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String taiKhoan = edt_taikhoan.getText().toString().trim();
                        String matKhau = edt_matkhau.getText().toString().trim();
                        String rematKhau = edt_rematkhau.getText().toString().trim();
                        String hoTen = edt_hoten.getText().toString().trim();
                        String sdt = edt_sdt.getText().toString().trim();

                        if (rematKhau.equals(matKhau)){
                           nguoiDungDAO = new NguoiDungDAO(ListNguoiDungActivity.this);
                           nguoiDungDAO.insertNguoiDung(new NguoiDung(taiKhoan, matKhau, sdt, hoTen));
                           capnhatNguoiDung();

                            alertDialog1.cancel();
                          Toast.makeText(ListNguoiDungActivity.this, "Thêm người dùng thành công!", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(ListNguoiDungActivity.this, "Mật khẩu không trùng khớp!", Toast.LENGTH_LONG).show();
                        }


                    }
                });
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog1.cancel();

                    }
                });
                break;
            case R.id.item2:

                Intent i = new Intent(ListNguoiDungActivity.this, ChangePassword.class);
                startActivity(i);

                break;
            case R.id.item3:

                Intent i3 = new Intent(ListNguoiDungActivity.this, LoginActivity.class);
                chk_save.setChecked(false);
                startActivity(i3);
                Toast.makeText(ListNguoiDungActivity.this, "Đã đăng xuất", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);


    }



}