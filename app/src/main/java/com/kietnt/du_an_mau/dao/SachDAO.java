package com.kietnt.du_an_mau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.kietnt.du_an_mau.database.DBHelper;
import com.kietnt.du_an_mau.model.HoaDon;
import com.kietnt.du_an_mau.model.Sach;
import com.kietnt.du_an_mau.model.TheLoai;


import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    private SQLiteDatabase db;
    private DBHelper helper;


    public static final String TAG = "SachDAO";

    public SachDAO(Context context){
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public ArrayList<Sach> getAllSach(){
        ArrayList<Sach> dsSach = new ArrayList<>();
        Cursor cs = db.query("SACH", null, null, null, null, null, null);
        cs.moveToFirst();
        while (cs.isAfterLast() == false){
            Sach sach = new Sach();
            sach.setMaSach(cs.getString(0));
            sach.setMaLoai(cs.getString(1));
            sach.setTenSach(cs.getString(2));
            sach.setTacGia(cs.getString(3));
            sach.setNXB(cs.getString(4));
            sach.setGiaBia(cs.getDouble(5));
            sach.setSoLuong(cs.getInt(6));

            dsSach.add(sach);
            Log.d("//====", sach.toString());
            cs.moveToNext();
        }

        return dsSach;
    }

    public Sach getSachByID(String maSach){
        Sach s = null;
        //WHERE clause
        String selection = "maSach=?";
        //WHERE clause arguments
        String[] selectionArgs = {maSach};
        Cursor c = db.query("SACH",null,selection,selectionArgs,null,null,null);
        Log.d("getSachByID","===>"+ c.getCount());
        c.moveToFirst();
        while (c.isAfterLast()==false){
            s = new Sach();
            s.setMaSach(c.getString(0));
            s.setMaLoai(c.getString(1));
            s.setTenSach(c.getString(2));
            s.setTacGia(c.getString(3));
            s.setNXB(c.getString(4));
            s.setGiaBia(c.getDouble(5));
            s.setSoLuong(c.getInt(6));
            break;
        }
        c.close();
        return s;
    }
    //getAll
    public ArrayList<Sach> getSachTop10(String month){
        ArrayList<Sach> dsSach = new ArrayList<>();
        if (Integer.parseInt(month)<10){
            month = "0"+month;
        }
        String sSQL = "SELECT maSach, SUM(soLuongMua) as soLuongMua FROM HD_CHI_TIET INNER JOIN HOA_DON " + "ON HOA_DON.maHoaDon = HD_CHI_TIET.maHoaDon WHERE strftime('%m',HOA_DON.NgayMua) = '"+month+"' " + "GROUP BY maSach ORDER BY soLuongMua DESC LIMIT 10";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            Log.d("//=====",c.getString(0));
            Sach s = new Sach();
            s.setMaSach(c.getString(0));
            s.setSoLuong(c.getInt(1));
            s.setGiaBia(0);
            s.setMaLoai("");
            s.setTenSach("");
            s.setTacGia("");
            s.setNXB("");
            dsSach.add(s);
            c.moveToNext();
        }
        c.close();
        return dsSach;
    }

    public boolean insertSach(Sach s){
        ContentValues values = new ContentValues();
        values.put("maLoai", s.getMaLoai());
        values.put("tenSach", s.getTenSach());
        values.put("tacGia", s.getTacGia());
        values.put("NXB", s.getNXB());
        values.put("giaBia", s.getGiaBia());
        values.put("soLuong", s.getSoLuong());
        long row = db.insert("SACH",null, values);
        return row > 0;
    }

    public boolean updateSach(Sach s){
        ContentValues values = new ContentValues();
        values.put("maLoai", s.getMaLoai());
        values.put("tenSach", s.getTenSach());
        values.put("tacGia", s.getTacGia());
        values.put("NXB", s.getNXB());
        values.put("giaBia", s.getGiaBia());
        values.put("soLuong", s.getSoLuong());
        int row = db.update("SACH", values, "maSach=?",new String[]{s.getMaSach()+""});
        return row > 0;
    }

    public boolean deleteSach(String maSach){
        int row = db.delete("SACH", "maSach=?",new String[]{maSach+""});
        return row > 0;
    }
}
