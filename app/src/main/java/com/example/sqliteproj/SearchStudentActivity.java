package com.example.sqliteproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SearchStudentActivity extends AppCompatActivity {
    TextView tv_student;
    ListView lv_student;
    SQLiteDatabase db;
    ArrayList<Student> studentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_student);
        db = openOrCreateDatabase(Utils.DB_NAME,
                MODE_PRIVATE, null);

        Intent intent = getIntent();
        String name = intent.getStringExtra(Utils.KEY_STUDENT_NAME);
        tv_student = findViewById(R.id.tv_student);
        lv_student = findViewById(R.id.lv_student);
        studentList = Utils.SearchStudent(db, name);
        StudentAdapter adapter = new StudentAdapter(studentList, SearchStudentActivity.this);
        lv_student.setAdapter(adapter);
    }
}