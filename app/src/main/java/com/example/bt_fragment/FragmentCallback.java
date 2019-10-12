package com.example.bt_fragment;

import com.example.bt_fragment.dto.StudentDto;
import com.example.bt_fragment.entity.Student;

import java.util.List;

public interface FragmentCallback {
    // Hàm này để đưa dữ liệu từ main qua fragment
    void onMsgFromMainToFragment(List<StudentDto> students, int position);
}
