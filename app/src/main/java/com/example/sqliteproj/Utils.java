package com.example.sqliteproj;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class Utils {
    final static String DB_NAME = "db_school";

    final static String STUDENT_TABLE_NAME = "tbl_student";
    final static String STUDENT_ID_COL = "id";
    final static String STUDENT_NAME_COL = "name";
    final static String STUDENT_SURNAME_COL = "surname";
    final static String STUDENT_CLASS_COL = "class";
    final static String STUDENT_AVGRADE_COL = "average_grade";

    final static String CLASS_TABLE_NAME = "tbl_class";
    final static String CLASS_ID_COL = "id";
    final static String CLASS_NAME_COL = "name";
    final static String CLASS_TEACHER_COL = "class_teacher";

    final static String TEACHER_TABLE_NAME = "tbl_teacher";
    final static String TEACHER_ID_COL = "id";
    final static String TEACHER_NAME_COL = "name";
    final static String TEACHER_SURNAME_COL = "surname";
    final static String TEACHER_SUBJECT_COL = "subject";

    final static String KEY_STUDENT_NAME = "name";
    final static String KEY_STUDENT_AVGRADE = "average_grade";
    final static String KEY_STUDENT_CLASS = "class";

    public static void createTables(SQLiteDatabase db){
        db.execSQL("drop table if exists tbl_student");
        db.execSQL("drop table if exists tbl_class");
        db.execSQL("drop table if exists tbl_teacher");
        db.execSQL("create table if not exists "
                + STUDENT_TABLE_NAME+
                " ("+STUDENT_ID_COL+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                STUDENT_NAME_COL + " text, " +
                STUDENT_SURNAME_COL + " text, " +
                STUDENT_CLASS_COL + " text, " +
                STUDENT_AVGRADE_COL + " integer)");

        db.execSQL("create table if not exists "
                + CLASS_TABLE_NAME +
                " ("+CLASS_ID_COL+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                CLASS_NAME_COL + " text, "+
                CLASS_TEACHER_COL + " text)");

        db.execSQL("create table if not exists "
                + TEACHER_TABLE_NAME +
                "(" + TEACHER_ID_COL + " text, " +
                TEACHER_NAME_COL + " text, " +
                TEACHER_SURNAME_COL + " text, " +
                TEACHER_SUBJECT_COL + " text)");
    }

    //todo add addstudent method to button
    public static void addStudent(SQLiteDatabase db, String name, String surname, String class_var, int average_grade){
        db.execSQL("insert into tbl_student values(null,'"+name+"','"+surname+"','"+class_var+"',"+average_grade+")");
    }

    public static ArrayList<Student> SearchStudent(SQLiteDatabase db, String search_name) {
        Cursor cursor = db.rawQuery("select * from " + Utils.STUDENT_TABLE_NAME, null);
        ArrayList<Student> students_list = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String surname = cursor.getString(2);
            String class_var = cursor.getString(3);
            int average_grade = cursor.getInt(4);


            if(name.equals(search_name)){
                Student st = new Student(id, name, surname, class_var, average_grade);
                students_list.add(st);
            }
        }
        return students_list;
    }

    public static ArrayList<Student> SearchGrade(SQLiteDatabase db, int search_grade) {
        Cursor cursor = db.rawQuery("select * from " + Utils.STUDENT_TABLE_NAME, null);
        ArrayList<Student> students_list = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String surname = cursor.getString(2);
            String class_var = cursor.getString(3);
            int average_grade = cursor.getInt(4);

            if(average_grade > search_grade){
                Student st = new Student(id, name, surname, class_var, average_grade);
                students_list.add(st);
            }
        }
        return students_list;
    }

    public static ArrayList<Student> SearchClass(SQLiteDatabase db, String search_class) {
        Cursor cursor = db.rawQuery("select * from " + Utils.STUDENT_TABLE_NAME, null);
        ArrayList<Student> students_list = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String surname = cursor.getString(2);
            String class_var = cursor.getString(3);
            int average_grade = cursor.getInt(4);


            if(class_var.equals(search_class)){
                Student st = new Student(id, name, surname, class_var, average_grade);
                students_list.add(st);
            }
        }
        return students_list;
    }

    //todo rewrite (add id function)
    public static void deleteStudent(int studentId, SQLiteDatabase db){
        db.execSQL("delete from tbl_student where student_id =" +studentId);
        //db.delete("tbl_student", "student_id =" + studentId, null);
    }

    public static void updateStudent(Student student, SQLiteDatabase db){
        String name = student.getName();
        String surname = student.getSurname();
        String className = student.getClass_var();
        int avg = student.getAverage_grade();

        db.execSQL("update tbl_student set student_name ='" +name+ "' where student_id=" + id);
        db.execSQL("update tbl_student set student_surname ='" +surname+ "' where student_id=" + id);
        db.execSQL("update tbl_student set student_class_name ='" +className+ "' where student_id=" + id);
        db.execSQL("update tbl_student set student_average =" +avg+ " where student_id=" + id);
    }
}
}
