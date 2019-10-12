package com.example.bt_fragment.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bt_fragment.DatabaseConnection.SQLiteHelper;
import com.example.bt_fragment.MainActivity;
import com.example.bt_fragment.R;
import com.example.bt_fragment.dto.StudentDto;
import com.example.bt_fragment.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    SQLiteDatabase db;
    //SQLiteHelper sqLiteHelper;
    Integer[] icon={R.drawable.ntitled1,R.drawable.ntitled2,R.drawable.ntitled3,R.drawable.ntitled4,
            R.drawable.ntitled5,R.drawable.ntitled6,R.drawable.ntitled7,R.drawable.ntitled8,R.drawable.ntitled9,
            R.drawable.ntitled10,R.drawable.ntitled1,R.drawable.ntitled2,R.drawable.ntitled3,R.drawable.ntitled4,
            R.drawable.ntitled5};
    public StudentDao() {

        //conn= new SQLiteHelper(context);
    }


    public void close(){
        db.close();
    }
    public void SetDataStatic(){
        // insert data

    }
    public List<StudentDto> findAllWithNameClass(MainActivity main){
        SQLiteHelper sqLiteHelper= new SQLiteHelper(main,null,null,2);
        List<StudentDto> students= new ArrayList<>();
        Cursor cursor=sqLiteHelper.getData("SELECT h.MaHS, h.HoTen,h.DTB,l.TenLop FROM HOCSINH h JOIN LOP l ON (h.MaLop=l.MaLop) ");
        cursor.moveToPosition(-1);
        int i=0;
        while (cursor.moveToNext() ){
            StudentDto student= new StudentDto();
            if (i>=icon.length){
                i=0;
            }
            student.set_id(cursor.getInt(cursor.getColumnIndex("MaHS")));
            student.set_name(cursor.getString(cursor.getColumnIndex("HoTen")));
            student.set_grade(cursor.getFloat(cursor.getColumnIndex("DTB")));
            student.set_class(cursor.getString(cursor.getColumnIndex("TenLop")));
            student.setIcon(icon[i++]);

            students.add(student);
// do something with the record here...
        }
        return students;
    }
    public List<Student> findAll(MainActivity main){
        SQLiteHelper sqLiteHelper= new SQLiteHelper(main,null,null,2);
        List<Student> students= new ArrayList<>();
        Cursor cursor=sqLiteHelper.getData("SELECT *FROM HOCSINH");
        cursor.moveToPosition(-1);
        int i=0;
        while (cursor.moveToNext() ){
            Student student= new Student();
            student.set_id(cursor.getInt(cursor.getColumnIndex("MaHS")));
            student.set_name(cursor.getString(cursor.getColumnIndex("HoTen")));
            student.set_grade(cursor.getFloat(cursor.getColumnIndex("DTB")));
            student.set_class(cursor.getInt(cursor.getColumnIndex("MaLop")));
            student.setIcon(icon[i++]);
            students.add(student);
// do something with the record here...
        }
        return students;
    }
}
