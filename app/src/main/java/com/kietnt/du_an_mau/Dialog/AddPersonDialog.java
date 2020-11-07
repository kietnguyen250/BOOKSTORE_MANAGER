package com.kietnt.du_an_mau.Dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.kietnt.du_an_mau.R;

public class AddPersonDialog extends AppCompatActivity {
    Button btn_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_nguoi_dung);

    }
}