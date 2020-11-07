package com.kietnt.du_an_mau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.kietnt.du_an_mau.database.DBHelper;
import com.kietnt.du_an_mau.model.NguoiDung;

import java.util.ArrayList;

public class NguoiDungDAO {
    private SQLiteDatabase db;
    private DBHelper helper;


    public static final String TAG = "NguoiDungDAO";

    public NguoiDungDAO(Context context){
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }
    //getAll
    public ArrayList<NguoiDung> getAllNguoiDung(){
        ArrayList<NguoiDung> dsNguoiDung = new ArrayList<>();
        Cursor cs = db.query("NGUOI_DUNG", null, null, null, null, null, null);
        cs.moveToFirst();
        while (cs.isAfterLast() == false){
            NguoiDung nd = new NguoiDung();
            nd.setUserName(cs.getString(0));
            nd.setPassword(cs.getString(1));
            nd.setPhone(cs.getString(2));
            nd.setHoTen(cs.getString(3));
            dsNguoiDung.add(nd);
            Log.d("//====", nd.toString());
            cs.moveToNext();
        }

        return dsNguoiDung;
    }
    //insert
    public boolean insertNguoiDung(NguoiDung nd){
        ContentValues values = new ContentValues();
        values.put("userName", nd.getUserName());
        values.put("password", nd.getPassword());
        values.put("phone", nd.getPhone());
        values.put("hoTen", nd.getHoTen());
        long row = db.insert("NGUOI_DUNG",null, values);
        return row > 0;
//        try {
//            if(db.insert("NGUOI_DUNG",null,values)== -1){
//                return -1;
//            }
//        }catch (Exception ex){
//            Log.e(TAG,ex.toString());
//        }
//        return 1;
    }



    //update
    public boolean updateNguoiDung(NguoiDung nd){
        ContentValues values = new ContentValues();
        values.put("userName", nd.getUserName());
        values.put("phone",nd.getPhone());
        values.put("hoTen",nd.getHoTen());
        long row = db.update("NGUOI_DUNG",values,"userName=?", new
                String[]{nd.getUserName()+""});
//        if (result == 0){
//            return -1;
//        }
//        return 1;
        return row > 0;
    }
    public int changePasswordNguoiDung(NguoiDung nd){
        ContentValues values = new ContentValues();
        values.put("userName",nd.getUserName());
        values.put("password",nd.getPassword());
        int result = db.update("NGUOI_DUNG",values,"userName=?", new
                String[]{nd.getUserName()+""});
        if (result == 0){
            return -1;
        }
        return 1;
    }
    //delete
    public int deleteNguoiDungByID(String username){
        int result = db.delete("NGUOI_DUNG","userName=?",new String[]{username});
        if (result == 0)
            return -1;
        return 1;
    }
    //check login
    public static boolean checkLogin(Context context, String userName, String pass){
        DBHelper helper1 = new DBHelper(context);
        SQLiteDatabase db1 = helper1.getReadableDatabase();
        String sql = "SELECT * FROM NGUOI_DUNG WHERE userName LIKE '" + userName + "' AND password LIKE '" + pass +"'";
        Cursor cs = db1.rawQuery(sql, null);
        if (cs.getCount() <= 0){
            return false;
        }
        return true;
    }
}
