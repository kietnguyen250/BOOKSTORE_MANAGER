package com.kietnt.du_an_mau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kietnt.du_an_mau.dao.NguoiDungDAO;
import com.kietnt.du_an_mau.model.NguoiDung;

public class ChangePassword extends AppCompatActivity {
    EditText edt_oldPass, edt_newPass, edt_confirm;
    Button btn_change, btn_huy;
    NguoiDungDAO nguoiDungDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        edt_oldPass = findViewById(R.id.edt_oldpass);
        edt_newPass = findViewById(R.id.edt_newpass);
        edt_confirm = findViewById(R.id.edt_confirmpass);
        btn_change = findViewById(R.id.btn_change);
        btn_huy = findViewById(R.id.btn_huy);

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword(v);
            }
        });

        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public int validateForm(){
        int check = 1;

        if (edt_oldPass.getText().length()==0 || edt_newPass.getText().length() == 0 || edt_confirm.getText().length() == 0) {
            Toast.makeText(ChangePassword.this, "Bạn phải nhập đầy đủ thông tin!",
                    Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            String mk_moi = edt_newPass.getText().toString();
            String re_mk_moi = edt_confirm.getText().toString();
            if (!mk_moi.equals(re_mk_moi)) {
                Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp",
                        Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
    public void changePassword(View v) {
        SharedPreferences pref = getSharedPreferences("thongtin.dat",MODE_PRIVATE);
        String strUserName = pref.getString("userName","");
        String strPass = pref.getString("password","");
        nguoiDungDAO = new NguoiDungDAO(ChangePassword.this);
        NguoiDung user = new NguoiDung(strUserName, edt_newPass.getText().toString(), "",
                "");
        try {
            if (validateForm()>0){
                if (((edt_oldPass.getText().toString()).equals(strPass)) && (nguoiDungDAO.changePasswordNguoiDung(user) > 0)) {
                    finish();
                    Toast.makeText(ChangePassword.this, "Lưu thành công",
                            Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ChangePassword.this, "Sai mật khẩu",
                            Toast.LENGTH_SHORT).show();
                }
            }

        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }
}