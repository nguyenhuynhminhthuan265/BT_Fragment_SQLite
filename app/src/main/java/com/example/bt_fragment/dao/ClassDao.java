package com.example.bt_fragment.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.bt_fragment.DatabaseConnection.SQLiteHelper;
import com.example.bt_fragment.entity.Class;

import java.util.ArrayList;
import java.util.List;

public class ClassDao {
    SQLiteDatabase db;
    SQLiteHelper conn;
    public ClassDao(Context context) {

       try {
           // create connection to database
           //conn = new SQLiteHelper(context);
           // open connect to Database
           db=conn.getWritableDatabase();

           // insert data
           db.execSQL( "insert into LOP(MaLop, HoTen) values (1,'Lop 1');" );
           db.execSQL( "insert into LOP(MaLop,HoTen) values (2,'Lop 2');" );



       }catch (SQLException e){
           e.printStackTrace();
       }
    }
    public List<Class> findAll(){
        List<Class> classes= new ArrayList<>();
        try {

            // open connect to Database
            db=conn.getReadableDatabase();

            String mySQL = "select * FROM LOP";
            // String[] args = {"1", "BBB"};
            Cursor c1 = db.rawQuery(mySQL, null);
            c1.moveToPosition(-1);
            while ( c1.moveToNext() ){
                Class clazz= new Class();
                int MaLop = c1.getInt(c1.getColumnIndex("MaLop"));
                String TenLop = c1.getString(c1.getColumnIndex("TenLop"));

                clazz.setMaLop(MaLop);
                clazz.setTenLop(TenLop);

                classes.add(clazz);
                //String phone = c1.getString(c1.getColumnIndex("phone"));
// do something with the record here...
                //  Toast.makeText(get,"Student:" +MaHs ,Toast.LENGTH_LONG).show();
            }

            // close connection
            //conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }



        return classes;
    }
}
