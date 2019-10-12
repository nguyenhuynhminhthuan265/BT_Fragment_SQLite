package com.example.bt_fragment.entity;

public class Student {
    private int _id;
    private String _name;
    private int _class;
    private float _grade;
    private int icon;

    public Student(){}

    public Student(int _id, String _name, int _class, float _grade, int icon) {
        this._id = _id;
        this._name = _name;
        this._class = _class;
        this._grade = _grade;
        this.icon = icon;
    }

    public Student(String _name, int _class, float _grade, int icon) {
        this._id = _id;
        this._name = _name;
        this._class = _class;
        this._grade = _grade;
        this.icon = icon;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_class() {
        return _class;
    }

    public void set_class(int _class) {
        this._class = _class;
    }

    public float get_grade() {
        return _grade;
    }

    public void set_grade(float _grade) {
        this._grade = _grade;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Student{" +
                "_id=" + _id +
                ", _name='" + _name + '\'' +
                ", _class=" + _class +
                ", _grade=" + _grade +
                ", icon=" + icon +
                '}';
    }
}
