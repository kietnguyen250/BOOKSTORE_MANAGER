package com.kietnt.du_an_mau.Dialog;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.kietnt.du_an_mau.R;

public class AddLoaiDialog extends BottomSheetDialog {
    Button btn_list;

    public AddLoaiDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_the_loai);

    }
}
