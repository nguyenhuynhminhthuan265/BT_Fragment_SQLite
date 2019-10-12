package com.example.bt_fragment;


import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bt_fragment.DatabaseConnection.SQLiteHelper;
import com.example.bt_fragment.dao.StudentDao;
import com.example.bt_fragment.dto.StudentDto;
import com.example.bt_fragment.entity.Class;
import com.example.bt_fragment.entity.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;


public class FragmentBlue extends Fragment implements FragmentCallback{
    MainActivity main;
    Context context = null;
    // data to fill-up the ListView
    ArrayList<StudentDto> students = new ArrayList<>();
    Integer[] icon={R.drawable.ntitled1,R.drawable.ntitled2,R.drawable.ntitled3,R.drawable.ntitled4,
            R.drawable.ntitled5,R.drawable.ntitled6,R.drawable.ntitled7,R.drawable.ntitled8,R.drawable.ntitled9,
            R.drawable.ntitled10,R.drawable.ntitled1,R.drawable.ntitled2,R.drawable.ntitled3,R.drawable.ntitled4,
            R.drawable.ntitled5};
    LinearLayout layout_blue;
    TextView txtBlue;
    ListView listView;
    CustomListAdapter adapter;
    StudentDao studentDao=null;

//    private void readResource() throws IOException
//    {
//        int idResource = R.raw.students;
//        InputStream is = this.getResources().openRawResource(idResource);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//
//
//        for (int i = 0; i < 15; i++) {
//            Student student = new Student();
//            student.set_id(Integer.parseInt(reader.readLine()));
//            student.set_name(reader.readLine());
//            student.set_class(Integer.parseInt(reader.readLine()));
//            student.set_grade(Float.parseFloat((reader.readLine())));
//            student.setIcon(icon[i]);
//            students.add(student);
//        }
//        reader.close();
//        is.close();
//    }

    // convenient constructor(accept arguments, copy them to a bundle, binds bundle to fragment)
    public static FragmentBlue newInstance(String strArg) {
        FragmentBlue fragment = new FragmentBlue();
        Bundle args = new Bundle();
        args.putString("strArg1", strArg);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        try {
//            readResource();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            context = getActivity(); // use this reference to invoke main callbacks
            main=(MainActivity) getActivity();
            SQLiteHelper sqLiteHelper= new SQLiteHelper(main,null,null,1);
            sqLiteHelper.CreateTable();
            sqLiteHelper.InsertTableClass();
//            Cursor cursor1=sqLiteHelper.getData("SELECT *FROM LOP");
//            cursor1.moveToPosition(-1);
//            Class clazz = new Class();
//            if (cursor1.moveToNext()){
//
//                clazz.setMaLop(cursor1.getInt(cursor1.getColumnIndex("MaLop")));
//                clazz.setTenLop(cursor1.getString(cursor1.getColumnIndex("TenLop")));
//            }
//            Toast.makeText(main,clazz.toString(),Toast.LENGTH_LONG).show();
            sqLiteHelper.InsertTableStudent();
//            Cursor cursor=sqLiteHelper.getData("SELECT *FROM HOCSINH");
//            cursor.moveToPosition(-1);
//            int i=0;
//            while (cursor.moveToNext() ){
//                Student student= new Student();
//                student.set_id(cursor.getInt(cursor.getColumnIndex("MaHS")));
//                student.set_name(cursor.getString(cursor.getColumnIndex("HoTen")));
//                student.set_grade(cursor.getFloat(cursor.getColumnIndex("DTB")));
//                student.set_class(cursor.getInt(cursor.getColumnIndex("MaLop")));
//                student.setIcon(icon[i++]);
//                students.add(student);
//// do something with the record here...
//            }
            studentDao= new StudentDao();
            //students = (ArrayList<Student>) studentDao.findAll(main);
            students= (ArrayList<StudentDto>) studentDao.findAllWithNameClass(main);
        } catch (IllegalStateException e) {
            throw new IllegalStateException(
                    "MainActivity must implement callbacks");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        layout_blue = (LinearLayout) inflater.inflate(R.layout.layout_blue,null);

        txtBlue = (TextView) layout_blue.findViewById(R.id.textViewBlue);
        listView = (ListView) layout_blue.findViewById(R.id.listViewBlue);
        listView.setBackgroundColor(Color.parseColor("#ffccddff"));

        adapter = new CustomListAdapter(context,R.layout.layout_list_item, students,-1); //first time
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                main.onMsgFromFragToMain("BLUE-FRAG", students, position);
                Toast.makeText(main,"Selected: " + position, LENGTH_SHORT).show();
                txtBlue.setText("Mã số: " + students.get(position).get_id());

                adapter = new CustomListAdapter(context,R.layout.layout_list_item, students,position); //uodate adapter with new selected item to change color
                listView.setAdapter(adapter); //update new adapter to listview

                listView.setSelection(position); //select again to scroll screen to selected item
            }
        });

        return layout_blue;
    }

    @Override
    public void onMsgFromMainToFragment(List<StudentDto> students, int position)
    {
        listView.performItemClick(listView,position,listView.getAdapter().getItemId(position)); //call function setOnItemClickListener() above by emulator an clicking - action
    }
}
