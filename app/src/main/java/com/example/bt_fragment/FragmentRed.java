package com.example.bt_fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bt_fragment.dto.StudentDto;
import com.example.bt_fragment.entity.Student;

import java.util.List;


public class FragmentRed extends Fragment implements FragmentCallback {
    MainActivity main;
    TextView maSo;
    TextView hoTen;
    TextView lop;
    TextView diem;
    Button btnFirst;
    Button btnPre;
    Button btnNext;
    Button btnLast;
    public static FragmentRed newInstance(String strArg1) {
        FragmentRed fragment = new FragmentRed();
        Bundle bundle = new Bundle();
        bundle.putString("arg1", strArg1);
        fragment.setArguments(bundle);
        return fragment;
    }// newInstance

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
// Activities containing this fragment must implement interface: MainCallbacks
        if (!(getActivity() instanceof MainCallbacks)) {
            throw new IllegalStateException( " Activity must implement MainCallbacks");
        }
        main = (MainActivity) getActivity(); // use this reference to invoke main callbacks
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// inflate res/layout_red.xml which includes a textview and a button
        LinearLayout view_layout_red = (LinearLayout) inflater.inflate(
                R.layout.layout_red, null);
// plumbing - get a reference to widgets in the inflated layout
        maSo = view_layout_red.findViewById(R.id.maso);
        hoTen =  view_layout_red.findViewById(R.id.hoten);
        lop = view_layout_red.findViewById(R.id.lop);
        diem = view_layout_red.findViewById(R.id.diemtb);

        try {
            Bundle arguments = getArguments();
            String redMessage = arguments.getString("arg1", "");
            maSo.setText(redMessage);
        } catch (Exception e) {
            Log.e("RED BUNDLE ERROR - ", "" + e.getMessage());
        }
// clicking the button changes the time displayed and sends a copy to MainActivity
        btnFirst = (Button) view_layout_red.findViewById(R.id.btnFirst);
        btnNext = (Button) view_layout_red.findViewById(R.id.btnNext);
        btnPre = (Button) view_layout_red.findViewById(R.id.btnPrev);
        btnLast = (Button) view_layout_red.findViewById(R.id.btnLast);
        btnFirst.getBackground().setAlpha(60);
        btnLast.getBackground().setAlpha(60);
        btnPre.getBackground().setAlpha(60);
        btnNext.getBackground().setAlpha(60);

        return view_layout_red;

    }

    private void setButtonClickable(final List<StudentDto> arr, int  pos)
    {
        if (pos==0)
        {
            btnFirst.setEnabled(false);
            btnFirst.getBackground().setAlpha(60);
            btnNext.setEnabled(true);
            btnNext.getBackground().setAlpha(255);
            btnPre.setEnabled(false);
            btnPre.getBackground().setAlpha(60);
            btnLast.setEnabled(true);
            btnLast.getBackground().setAlpha(255);

        }
        else
            if (pos==arr.size()-1)
            {
                btnFirst.setEnabled(true);
                btnFirst.getBackground().setAlpha(255);
                btnNext.setEnabled(false);
                btnNext.getBackground().setAlpha(60);
                btnPre.setEnabled(true);
                btnPre.getBackground().setAlpha(255);
                btnLast.setEnabled(false);
                btnLast.getBackground().setAlpha(60);

            }
            else
            {
                btnFirst.setEnabled(true);
                btnFirst.getBackground().setAlpha(255);
                btnNext.setEnabled(true);
                btnNext.getBackground().setAlpha(255);
                btnPre.setEnabled(true);
                btnPre.getBackground().setAlpha(255);
                btnLast.setEnabled(true);
                btnLast.getBackground().setAlpha(255);

            }
    }

    @Override
    public void onMsgFromMainToFragment(final List<StudentDto> students, final int position) {
// receiving a message from MainActivity (it may happen at any point in time)
        //txtMaSo.setText("THIS MESSAGE COMES FROM MAIN:" + strValue);
        maSo.setText(String.valueOf(students.get(position).get_id()));
        hoTen.setText(students.get(position).get_name());
        lop.setText(students.get(position).get_class());
        diem.setText(String.valueOf(students.get(position).get_grade()));
        setButtonClickable(students,position);

        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onMsgFromFragToMain("RED-FRAG", students, 0);
                onMsgFromMainToFragment(students, 0);

            }
        });
        btnLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onMsgFromFragToMain("RED-FRAG", students, students.size() -1);
                onMsgFromMainToFragment(students, students.size() -1);

            }
        });
        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position>0){
                    main.onMsgFromFragToMain("RED-FRAG", students, position-1);
                    onMsgFromMainToFragment(students, position-1);
                }

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position<students.size() -1){
                    main.onMsgFromFragToMain("RED-FRAG", students,position+1);
                    onMsgFromMainToFragment(students, position+1);
                }

            }
        });
    }
}