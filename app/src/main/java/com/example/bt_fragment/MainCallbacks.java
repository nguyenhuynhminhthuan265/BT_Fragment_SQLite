package com.example.bt_fragment;

import com.example.bt_fragment.dto.StudentDto;
import com.example.bt_fragment.entity.Student;

import java.util.List;

public interface MainCallbacks {

    // Hàm này để đưa dữ liệu từ fragment lên hàm main , hàm main có vai trò trung gian trong truyền dữ liệu
    void onMsgFromFragToMain(String sender, List<StudentDto> students, int position);
}
