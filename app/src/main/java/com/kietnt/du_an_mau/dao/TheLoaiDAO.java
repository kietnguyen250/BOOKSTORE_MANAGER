package com.kietnt.du_an_mau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.kietnt.du_an_mau.database.DBHelper;
import com.kietnt.du_an_mau.model.NguoiDung;
import com.kietnt.du_an_mau.model.TheLoai;

import java.util.ArrayList;

public class TheLoaiDAO {
    private SQLiteDatabase db;
    private DBHelper helper;


    public static final String TAG = "TheLoaiDAO";

    public TheLoaiDAO(Context context){
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public ArrayList<TheLoai> getAllTheLoai(){
        ArrayList<TheLoai> dsLoai = new ArrayList<>();
        Cursor cs = db.query("THE_LOAI", null, null, null, null, null, null);
        cs.moveToFirst();
        while (cs.isAfterLast() == false){
            TheLoai tl = new TheLoai();
            tl.setMaLoai(cs.getString(0));
            tl.setTenLoai(cs.getString(1));
            tl.setMoTa(cs.getString(2));
            tl.setViTri(cs.getInt(3));
            dsLoai.add(tl);
            Log.d("//====", tl.toString());
            cs.moveToNext();
        }

        return dsLoai;
    }

    public boolean insertTheLoai(TheLoai tl){
        ContentValues values = new ContentValues();
        values.put("tenLoai", tl.getTenLoai());
        values.put("moTa", tl.getMoTa());
        values.put("viTri", tl.getViTri());
        long row = db.insert("THE_LOAI",null, values);
        return row > 0;
    }

    public boolean updateTheLoai(TheLoai tl){
        ContentValues values = new ContentValues();
        values.put("tenLoai", tl.getTenLoai());
        values.put("moTa", tl.getMoTa());
        values.put("viTri", tl.getViTri());
        int row = db.update("THE_LOAI", values, "maLoai=?",new String[]{tl.getMaLoai()+""});
        return row > 0;
    }

    public boolean deleteTheLoai(String maLoai){
        int row = db.delete("THE_LOAI", "maLoai=?",new String[]{maLoai+""});
        return row > 0;
    }
}
