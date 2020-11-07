package com.kietnt.du_an_mau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.kietnt.du_an_mau.database.DBHelper;
import com.kietnt.du_an_mau.model.HoaDon;
import com.kietnt.du_an_mau.model.TheLoai;

import java.util.ArrayList;

public class HoaDonDAO {
    private SQLiteDatabase db;
    private DBHelper helper;


    public static final String TAG = "HoaDonDAO";

    public HoaDonDAO(Context context){
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public ArrayList<HoaDon> getAllHoaDon(){
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
        Cursor cs = db.query("HOA_DON", null, null, null, null, null, null);
        cs.moveToFirst();
        while (cs.isAfterLast() == false){
            HoaDon hd = new HoaDon();
            hd.setMaHoaDon(cs.getString(0));
            hd.setNgayMua(cs.getString(1));
            dsHoaDon.add(hd);
            Log.d("//====", hd.toString());
            cs.moveToNext();
        }

        return dsHoaDon;
    }
    public boolean insertHoaDon(HoaDon hd){
        ContentValues values = new ContentValues();
        values.put("NgayMua", hd.getNgayMua());
        long row = db.insert("HOA_DON",null, values);
        return row > 0;
    }

    public boolean updateHoaDon(HoaDon hd){
        ContentValues values = new ContentValues();
        values.put("NgayMua", hd.getNgayMua());
        int row = db.update("HOA_DON", values, "maHoaDon=?",new String[]{hd.getMaHoaDon()+""});
        return row > 0;
    }

    public boolean deleteHoaDon(String maHoaDon){
        int row = db.delete("HOA_DON", "maHoaDon=?",new String[]{maHoaDon+""});
        return row > 0;
    }
}
