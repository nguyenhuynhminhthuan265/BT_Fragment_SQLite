package com.example.bt_fragment.DatabaseConnection;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.sql.Connection;

public class SQLiteHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "My_Friends";

    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        //context.deleteDatabase(DATABASE_NAME);
    }

    public void CreateTable(){
        SQLiteDatabase db=getWritableDatabase();
        String CREATE_TABLE_CLASS="CREATE TABLE IF NOT  EXISTS LOP (MaLop INTEGER PRIMARY KEY AUTOINCREMENT, TenLop NVARCHAR(100));";
        db.execSQL(CREATE_TABLE_CLASS);


        String CREATE_TABLE_STUDENT="CREATE TABLE IF NOT  EXISTS HOCSINH (MaHS INTEGER PRIMARY KEY AUTOINCREMENT, HoTen NVARCHAR(100), DTB FLOAT, MaLop INTEGER, FOREIGN KEY (MaLop) REFERENCES LOP(MaLop));";

        db.execSQL(CREATE_TABLE_STUDENT);



    }

    public void InsertTableClass(){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL( "insert into LOP(TenLop) values ('Lop 1');" );
        db.execSQL( "insert into LOP(TenLop) values ('Lop 2');" );
        db.execSQL( "insert into LOP(TenLop) values ('Lop 3');" );
        db.execSQL( "insert into LOP(TenLop) values ('Lop 4');" );

        db.close();
    }

    public void InsertTableStudent(){
        SQLiteDatabase db=getWritableDatabase();


        db.execSQL( "insert into HOCSINH(HoTen,DTB,MaLop) values ('AAA', 3.1,1);" );
        db.execSQL( "insert into HOCSINH(HoTen,DTB,MaLop) values ('BBB', 4.2,3);" );
        db.execSQL( "insert into HOCSINH(HoTen,DTB,MaLop) values ('CCC', 5.5,2);" );
        db.execSQL( "insert into HOCSINH(HoTen,DTB,MaLop) values ('CCC', 8.5,4);" );
    }

    public Cursor getData(String sql){
        SQLiteDatabase db=getReadableDatabase();
        return db.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

//        try {
//            db = SQLiteDatabase.openDatabase(myDbPath, null,
//                    SQLiteDatabase.CREATE_IF_NECESSARY);
//// here you do something with your database ...
//
//            db.execSQL("create table LOP ("
//                    + " MaLop number PRIMARY KEY, "
//                    + " HoTen text );");
//            db.execSQL( "insert into LOP(MaLop, HoTen) values (1,'Lop 1');" );
//            db.execSQL( "insert into LOP(MaLop,HoTen) values (2,'Lop 2');" );
//
//
//            db.execSQL("create table HOCSINH ("
//                    + " MaHS integer PRIMARY KEY autoincrement, "
//                    + " HoTen text, "
//                    + " DTB number ,"+"MaLop number)");
//            db.execSQL( "insert into HOCSINH(HoTen, DTB, MaLop) values ('AAA', 3,1);" );
//            db.execSQL( "insert into HOCSINH( HoTen,DTB,MaLop) values ('BBB', 4,1);" );
//            db.execSQL( "insert into HOCSINH(HoTen,DTB,MaLop) values ('CCC', 5,2);" );
//
//
//
//            db.close();
//            //txtMsg.append("\nAll done!");
//        } catch (SQLiteException e) {
//            //txtMsg.append("\nERROR " + e.getMessage());
//        }




//        db.execSQL( "insert into LOP(MaLop, HoTen) values (1,'Lop 1');" );
//        db.execSQL( "insert into LOP(MaLop,HoTen) values (2,'Lop 2');" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DETACH DATABASE " + DATABASE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + "LOP");
        db.execSQL("DROP TABLE IF EXISTS " + "HOCSINH");


        // Recreate
        onCreate(db);
    }
}
