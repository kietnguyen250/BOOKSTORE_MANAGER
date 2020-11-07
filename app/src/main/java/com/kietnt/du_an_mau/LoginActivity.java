package com.kietnt.du_an_mau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.kietnt.du_an_mau.dao.NguoiDungDAO;

public class LoginActivity extends AppCompatActivity {
    TextInputLayout txt_username, txt_pass;
    public static EditText edt_username, edt_pass;
    Button btn_login;
    public static CheckBox chk_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(ContextCompat.getColor(LoginActivity.this,R.color.colorAccent));
        initView();
        loadData();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    String username = edt_username.getText().toString();
                    String password = edt_pass.getText().toString();

                    if (!validate()){
                        Toast.makeText(LoginActivity.this, "Sai Username hoặc Password!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (NguoiDungDAO.checkLogin(LoginActivity.this, username, password)){
                            boolean check = chk_save.isChecked();
                            SaveTT(username, password, check);
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                            Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Sai Username hoặc Password!", Toast.LENGTH_SHORT).show();

                        }
                    }

                        boolean check = chk_save.isChecked();
                        SaveTT(username, password, check);
                }
            }
        });


    }

    private void SaveTT(String usn, String pwd, boolean check){
        SharedPreferences preferences = getSharedPreferences("thongtin.dat", MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();
        if (check){
            editor.putString("userName", usn);
            editor.putString("password", pwd);
            editor.putBoolean("check", check);
        }else {
            editor.clear();
        }
        editor.commit();

    }
    private void loadData(){
        SharedPreferences pref =getSharedPreferences("thongtin.dat", MODE_PRIVATE);
        boolean check = pref.getBoolean("check", false);
        if (check){
            edt_username.setText(pref.getString("userName", ""));
            edt_pass.setText(pref.getString("password", ""));
            chk_save.setChecked(check);
        }
    }

    public void initView(){

        txt_username = findViewById(R.id.txt_username);
        txt_pass = findViewById(R.id.txt_pass);
        btn_login = findViewById(R.id.btn_login);
        edt_username = findViewById(R.id.edt_username);
        edt_pass = findViewById(R.id.edt_pass);
        chk_save = findViewById(R.id.chk_save);


    }


    public boolean validate() {
        boolean valid = false;

        String Username = edt_username.getText().toString();
        String Password = edt_pass.getText().toString();


        if (Username.isEmpty()) {
            valid = false;
            edt_username.setError("Vui lòng nhập tài khoản!");
        }
        else if (Password.isEmpty()){
            valid = false;
            edt_pass.setError("Vui lòng nhập mật khẩu!");
        }
        else {
            if (Password.length() >= 5) {
                valid = true;
                edt_pass.setError(null);
            } else {
                valid = false;
                edt_pass.setError("Mật khẩu quá ngắn!");
            }
        }

        return valid;
    }
}