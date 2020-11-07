package com.kietnt.du_an_mau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.kietnt.du_an_mau.database.DBHelper;
import com.kietnt.du_an_mau.model.HoaDon;
import com.kietnt.du_an_mau.model.HoaDonChiTiet;
import com.kietnt.du_an_mau.model.Sach;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonChiTietDAO {
    private SQLiteDatabase db;
    private DBHelper helper;
    public static final String TABLE_NAME = "HD_CHI_TIET";
    public static final String TAG = "HoaDonChiTiet";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public HoaDonChiTietDAO(Context context) {
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }
    //insert
    public boolean inserHoaDonChiTiet(HoaDonChiTiet hd){
        ContentValues values = new ContentValues();
        values.put("maHoaDon",hd.getMaHoaDon());
        values.put("maSach",hd.getMaSach());
        values.put("soLuongMua",hd.getSoLuongMua());
        long row = db.insert("HD_CHI_TIET",null, values);
        return row > 0;
    }
    //getAll
    public ArrayList<HoaDonChiTiet> getAllHoaDonChiTiet(){
        ArrayList<HoaDonChiTiet> dsHoaDonChiTiet = new ArrayList<>();
        String sql = "SELECT hd.*, s.maSach,s.tenSach, s.giaBia, hdct.soLuongMua FROM HOA_DON hd JOIN HD_CHI_TIET hdct ON hd.maHoaDon=hdct.maHoaDon JOIN SACH s ON hdct.maSach=s.maSach";
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        try {
            while (c.isAfterLast()==false){
                HoaDonChiTiet ee = new HoaDonChiTiet();
                ee.setMaHDCT(c.getString(0));
                ee.setMaHoaDon(c.getString(1));
                ee.setMaSach(c.getString(2));
                ee.setSoLuongMua(c.getInt(5));
                dsHoaDonChiTiet.add(ee);
                Log.d("//=====",ee.toString());
                c.moveToNext();
            }
            c.close();
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
        return dsHoaDonChiTiet;
    }
    //getAll
    public ArrayList<HoaDonChiTiet> getAllHoaDonChiTietByID(String maHoaDon){
        ArrayList<HoaDonChiTiet> dsHoaDonChiTiet = new ArrayList<>();
        String sql = "SELECT hd.*, s.maSach,s.tenSach, s.giaBia, hdct.soLuongMua FROM HOA_DON hd JOIN HD_CHI_TIET hdct ON hd.maHoaDon=hdct.maHoaDon JOIN SACH s ON hdct.maSach=s.maSach where hdct.maHoaDon='"+maHoaDon+"'";
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        try {
            while (c.isAfterLast()==false){
                HoaDonChiTiet ee = new HoaDonChiTiet();
                ee.setMaHDCT(c.getString(0));
                ee.setMaHoaDon(c.getString(1));
                ee.setMaSach(c.getString(2));
                ee.setSoLuongMua(c.getInt(5));
                dsHoaDonChiTiet.add(ee);
                Log.d("//=====",ee.toString());
                c.moveToNext();
            }
            c.close();
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
        return dsHoaDonChiTiet;
    }
    //update
    public boolean updateHoaDonChiTiet(HoaDonChiTiet hd) {
        ContentValues values = new ContentValues();
        values.put("maHDCT", hd.getMaHDCT());
        values.put("maHoaDon", hd.getMaHoaDon());
        values.put("maSach", hd.getMaSach());
        values.put("soLuong", hd.getSoLuongMua());
        int row = db.update("HD_CHI_TIET", values, "maHDCT=?",new String[]{hd.getMaHDCT()+""});
        return row > 0;
    }

        //delete
        public boolean deleteHoaDonChiTietByID(String maHDCT){
            int row = db.delete("HD_CHI_TIET", "maHDCT=?",new String[]{maHDCT+""});
            return row > 0;
        }

        //check
        public boolean checkHoaDon(String maHoaDon){
            //SELECT
            String[] columns = {"maHoaDon"};
            //WHERE clause
            String selection = "maHoaDon=?";
            //WHERE clause arguments
            String[] selectionArgs = {maHoaDon};
            Cursor c = null;
            try{
                c = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null,
                        null);
                c.moveToFirst();
                int i = c.getCount();
                c.close();
                if(i <= 0){
                    return false;
                }
                return true;
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
        }
        public double getDoanhThuTheoNgay(){
            double doanhThu = 0;
            String sSQL ="SELECT SUM(tongtien) from (SELECT SUM(SACH.giaBia * HD_CHI_TIET.soLuongMua) as 'tongtien' " + "FROM HOA_DON INNER JOIN HD_CHI_TIET on HOA_DON.maHoaDon = HD_CHI_TIET.maHoaDon " + "INNER JOIN SACH on HD_CHI_TIET.maSach = SACH.maSach where HOA_DON.NgayMua = date('now') GROUP BY HD_CHI_TIET.maSach)tmp";
            Cursor c = db.rawQuery(sSQL, null);
            c.moveToFirst();
            while (c.isAfterLast()==false){
                doanhThu = c.getDouble(0);
                c.moveToNext();
            }
            c.close();
            return doanhThu;
        }
        public double getDoanhThuTheoThang(){
            double doanhThu = 0;
            String sSQL ="SELECT SUM(tongtien) from (SELECT SUM(SACH.giaBia * HD_CHI_TIET.soLuongMua) as 'tongtien' " + "FROM HOA_DON INNER JOIN HD_CHI_TIET on HOA_DON.maHoaDon = HD_CHI_TIET.maHoaDon " + "INNER JOIN SACH on HD_CHI_TIET.maSach = SACH.maSach where strftime('%m',HOA_DON.NgayMua) = strftime('%m','now') GROUP BY HD_CHI_TIET.maSach)tmp";
            Cursor c = db.rawQuery(sSQL, null);
            c.moveToFirst();
            while (c.isAfterLast()==false){
                doanhThu = c.getDouble(0);
                c.moveToNext();
            }
            c.close();
            return doanhThu;
        }
        public double getDoanhThuTheoNam(){
            double doanhThu = 0;
            String sSQL ="SELECT SUM(tongtien) from (SELECT SUM(SACH.giaBia * HD_CHI_TIET.soLuongMua) as 'tongtien' " + "FROM HOA_DON INNER JOIN HD_CHI_TIET on HOA_DON.maHoaDon = HD_CHI_TIET.maHoaDon " + "INNER JOIN SACH on HD_CHI_TIET.maSach = SACH.maSach where strftime('%Y',HOA_DON.NgayMua) = strftime('%Y','now') GROUP BY HD_CHI_TIET.maSach)tmp";
            Cursor c = db.rawQuery(sSQL, null);
            c.moveToFirst();
            while (c.isAfterLast()==false){
                doanhThu = c.getDouble(0);
                c.moveToNext();
            }
            c.close();
            return doanhThu;
        }
}
