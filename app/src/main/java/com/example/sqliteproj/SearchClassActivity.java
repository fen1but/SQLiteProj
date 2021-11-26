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

public class SearchClassActivity extends AppCompatActivity {
    TextView tv_student;
    ListView lv_student;
    SQLiteDatabase db;
    ArrayList<Student> studentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_class);
        db = openOrCreateDatabase(Utils.DB_NAME,
                MODE_PRIVATE, null);

        Intent intent = getIntent();
        String name = intent.getStringExtra(Utils.KEY_STUDENT_CLASS);
        tv_student = findViewById(R.id.tv_student);
        lv_student = findViewById(R.id.lv_student);
        studentList = Utils.SearchClass(db, name);
        StudentAdapter adapter = new StudentAdapter(studentList, SearchClassActivity.this);
        lv_student.setAdapter(adapter);
    }
}