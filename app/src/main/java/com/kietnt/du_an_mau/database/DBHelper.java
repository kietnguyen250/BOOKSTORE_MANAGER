package com.kietnt.du_an_mau.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "bookstore.db";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE THE_LOAI(maLoai INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenLoai TEXT, moTa TEXT, viTri INTEGER)");

        db.execSQL("INSERT INTO THE_LOAI(tenLoai, moTa, viTri) VALUES('Tiểu thuyết', 'Li kỳ', '1')");
        db.execSQL("INSERT INTO THE_LOAI(tenLoai, moTa, viTri) VALUES('Trinh thám', 'Hồi hộp', '3')");
        db.execSQL("INSERT INTO THE_LOAI(tenLoai, moTa, viTri) VALUES('Anime', 'Rất hay', '2')");





        db.execSQL("CREATE TABLE NGUOI_DUNG(userName TEXT PRIMARY KEY," +
                "password TEXT NOT NULL, phone TEXT NOT NULL, hoTen TEXT)");

        db.execSQL("INSERT INTO NGUOI_DUNG(userName, password, phone, hoTen) VALUES('keokiet250', 'keokiet150', '0394028301', 'Nguyễn Tuấn Kiệt')");
        db.execSQL("INSERT INTO NGUOI_DUNG(userName, password, phone, hoTen) VALUES('minh250', 'minh150', '0343892681', 'Dư Công Minh')");
        db.execSQL("INSERT INTO NGUOI_DUNG(userName, password, phone, hoTen) VALUES('antruong147', 'truongan123', '0325263418', 'Nguyễn Trường An')");



        db.execSQL("CREATE TABLE SACH(maSach INTEGER PRIMARY KEY AUTOINCREMENT, maLoai INTEGER references THE_LOAI(maLoai), tenSach TEXT NOT NULL," +
                "tacGia TEXT, NXB TEXT , giaBia DOUBLE NOT NULL, soLuong INTEGER)");

        db.execSQL("INSERT INTO SACH(maLoai, tenSach, tacGia, NXB, giaBia, soLuong) VALUES(1, 'Độc giả thứ 7', 'Lôi Mễ', 'Kim Khánh', 22000, 10)");
        db.execSQL("INSERT INTO SACH(maLoai, tenSach, tacGia, NXB, giaBia, soLuong) VALUES(2, 'Conan', 'Aoyama Gosho', 'TruyenQQ', 15000, 15)");
        db.execSQL("INSERT INTO SACH(maLoai, tenSach, tacGia, NXB, giaBia, soLuong) VALUES(3, 'God of high school', 'Mori Jin', 'TruyenQQ', 30000, 20)");



        db.execSQL("CREATE TABLE HOA_DON(maHoaDon INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NgayMua DATE NOT NULL)");

//            db.execSQL("INSERT INTO HOA_DON(NgayMua) VALUES('2020-10-18')");
//        db.execSQL("INSERT INTO HOA_DON(NgayMua) VALUES('2020-12-19')");
//        db.execSQL("INSERT INTO HOA_DON(NgayMua) VALUES('2019-11-20')");


        db.execSQL("CREATE TABLE HD_CHI_TIET(maHDCT INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maHoaDon INTEGER references HOA_DON(maHoaDon) NOT NULL, maSach INTEGER references SACH(maSach) NOT NULL, soLuongMua INTEGER NOT NULL)");

//        db.execSQL("INSERT INTO HD_CHI_TIET(maHoaDon, maSach, soLuongMua) VALUES(1, 1, 2)");
//        db.execSQL("INSERT INTO HD_CHI_TIET(maHoaDon, maSach, soLuongMua) VALUES(1, 2, 1)");
//        db.execSQL("INSERT INTO HD_CHI_TIET(maHoaDon, maSach, soLuongMua) VALUES(2, 1, 2)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS THE_LOAI");
        db.execSQL("DROP TABLE IF EXISTS NGUOI_DUNG");
        db.execSQL("DROP TABLE IF EXISTS SACH");
        db.execSQL("DROP TABLE IF EXISTS HOA_DON");
        db.execSQL("DROP TABLE IF EXISTS HD_CHI_TIET");
        onCreate(db);
    }
}
