package com.example.sqliteproj;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

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
    final static String KEY_STUDENT_SURNAME = "surname";
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
                "(" + TEACHER_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                TEACHER_NAME_COL + " text, " +
                TEACHER_SURNAME_COL + " text, " +
                TEACHER_SUBJECT_COL + " text)");
    }

    public static void addStudent(SQLiteDatabase db, String name, String surname, String class_var, int average_grade){
        db.execSQL("insert into tbl_student values(null,'"+name+"','"+surname+"','"+class_var+"',"+average_grade+")");
    }

    public static void addClass(SQLiteDatabase db, String name, String class_teacher){
        db.execSQL("insert into tbl_class values(null,'"+name+"','"+class_teacher+"')");
    }

    public static void addTeacher(SQLiteDatabase db, String name, String surname, String subject){
        db.execSQL("insert into tbl_teacher values(null,'"+name+"','"+surname+"','"+subject+"')");
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

    public static ArrayList<Subject> SubjectList(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("select * from " + Utils.STUDENT_TABLE_NAME, null);
        ArrayList<Subject> subject_list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Subject sj = new Subject(cursor.getString(1), cursor.getString(2), cursor.getString(5), cursor.getInt(4));
            subject_list.add(sj);
        }
        return subject_list;
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

    public static void deleteStudent(int id, SQLiteDatabase db){
        db.execSQL("delete from " + Utils.STUDENT_TABLE_NAME + "where id =" +id);
    }

    public static void updateStudent(Student student, SQLiteDatabase db){
        int id = student.getId();
        String name = student.getName();
        String surname = student.getSurname();
        String class_var = student.getClass_var();
        int average_grade = student.getAverage_grade();

        db.execSQL("update "+Utils.STUDENT_TABLE_NAME +"set student_name ='" +name+ "' where student_id=" + id);
        db.execSQL("update "+Utils.STUDENT_TABLE_NAME +"set student_surname ='" +surname+ "' where student_id=" + id);
        db.execSQL("update "+Utils.STUDENT_TABLE_NAME +"set student_class_name ='" +class_var+ "' where student_id=" + id);
        db.execSQL("update "+Utils.STUDENT_TABLE_NAME +"set student_average =" +average_grade+ " where student_id=" + id);
    }
    public static String getSubject(int id, SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("select class from tbl_student where id =" + id, null);
        cursor.moveToNext();
        String classs = cursor.getString(0);
        cursor = db.rawQuery("select class_teacher from tbl_class where name ='" + classs + "'", null);
        cursor.moveToNext();
        String teacher = cursor.getString(0);
        cursor = db.rawQuery("select subject from tbl_teacher where name ='" + teacher + "'", null);
        cursor.moveToNext();
        String subject = cursor.getString(0);
        return subject;
    }
    public static void addSubjects(SQLiteDatabase db){
        try{
            db.execSQL("ALTER TABLE tbl_student" +
                    "  ADD COLUMN subject 'null'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor cursor = db.rawQuery("select * from tbl_student", null);
        int i = 1;
        while(cursor.moveToNext()){
            db.execSQL("UPDATE tbl_student SET subject = " + "'" + getSubject(i, db) + "'" + " WHERE id =" + "'" + i + "'");
            i++;
        }
        }
    }




