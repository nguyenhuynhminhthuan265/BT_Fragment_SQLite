package com.example.bt_fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bt_fragment.dto.StudentDto;
import com.example.bt_fragment.entity.Student;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<StudentDto> {
    Context context;
    ArrayList<StudentDto> students;

    int posSelected;
    int layoutToBeInflated;

    public CustomListAdapter(Context context, int resource, ArrayList<StudentDto> students, int selected)
    {
        super(context, R.layout.layout_list_item, students);
        this.context = context;
        this.students = students;
        this.posSelected=selected; //mark that which item is selected => change color
        layoutToBeInflated = resource;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        View row = layoutInflater.inflate(R.layout.layout_list_item, null);

        TextView txtName = row.findViewById(R.id.listID);
        ImageView imgIcon = row.findViewById(R.id.listIcon);

        txtName.setText(String.valueOf(students.get(position).get_id()));
        imgIcon.setImageResource(students.get(position).getIcon());

        parent.setBackgroundColor(Color.TRANSPARENT);

        if (position==posSelected)
        {
            row.setBackgroundColor(Color.GREEN);
        }
        return row;
    }
}
