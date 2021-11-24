package com.example.sqliteproj;

public class Student {
    private String name;
    private String surname;
    private String class_var;
    private int average_grade;

    public Student(int id, String name, String surname, String class_var, int average_grade){
        this.name = name;
        this.surname = surname;
        this.class_var = class_var;
        this.average_grade = average_grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getClass_var() {
        return class_var;
    }

    public void setClass_var(String class_var) {
        this.class_var = class_var;
    }

    public int getAverage_grade() {
        return average_grade;
    }

    public void setAverage_grade(int average_grade) {
        this.average_grade = average_grade;
    }
}
