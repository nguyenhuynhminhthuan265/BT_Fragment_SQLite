package com.example.bt_fragment;


import android.app.Activity;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.example.bt_fragment.DatabaseConnection.SQLiteHelper;
import com.example.bt_fragment.dto.StudentDto;
import com.example.bt_fragment.entity.Student;

import java.util.List;

public class MainActivity extends Activity implements MainCallbacks {
    Integer[] icon={R.drawable.ntitled1,R.drawable.ntitled2,R.drawable.ntitled3,R.drawable.ntitled4,
            R.drawable.ntitled5,R.drawable.ntitled6,R.drawable.ntitled7,R.drawable.ntitled8,R.drawable.ntitled9,
            R.drawable.ntitled10,R.drawable.ntitled1,R.drawable.ntitled2,R.drawable.ntitled3,R.drawable.ntitled4,
            R.drawable.ntitled5};
    FragmentTransaction ft;
    FragmentBlue blueFragment;
    FragmentRed redFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ft = getFragmentManager().beginTransaction();
        blueFragment = FragmentBlue.newInstance("");
        ft.replace(R.id.framentA, blueFragment);
        ft.commit();
// create a new RED fragment - show it
        ft = getFragmentManager().beginTransaction();
        redFragment = FragmentRed.newInstance("");
        ft.replace(R.id.framentB, redFragment);
        ft.commit();

    }



    @Override
    public void onMsgFromFragToMain(String sender, List<StudentDto> students, int position) {
        // show message arriving to MainActivity
       // Toast.makeText(getApplication(),students.get(position).toString(), Toast.LENGTH_LONG).show();
        if (sender.equals("RED-FRAG")) {
            blueFragment.onMsgFromMainToFragment(students,position);
        }
        if (sender.equals("BLUE-FRAG")) {
            try {
// forward blue-data to redFragment using its callback method
                redFragment.onMsgFromMainToFragment(students, position);
            } catch (Exception e) {
                Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
            }
        }
    }
    }

