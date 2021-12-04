package com.example.sqliteproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SearchGradeActivity extends AppCompatActivity {
    TextView tv_student;
    ListView lv_student;
    SQLiteDatabase db;
    ArrayList<Student> studentList;
    FloatingActionButton fab;
    Switch switch1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_grade);
        switch1 = findViewById(R.id.switch1);
        Boolean switchState = switch1.isChecked();
        db = openOrCreateDatabase(Utils.DB_NAME,
                MODE_PRIVATE, null);

        Intent intent = getIntent();
        int avgrade = intent.getIntExtra(Utils.KEY_STUDENT_AVGRADE, 0);
        tv_student = findViewById(R.id.tv_student);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchGradeActivity.this, AddStudentActivity.class));
            }
        });
        lv_student = findViewById(R.id.lv_student);
        studentList = Utils.SearchGrade(db, avgrade);
        StudentAdapter adapter = new StudentAdapter(studentList, SearchGradeActivity.this);
        lv_student.setAdapter(adapter);
        lv_student.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student tmp = studentList.get(position);
                Log.d("check", "student");
                Intent intent = new Intent(SearchGradeActivity.this, Details.class);
                intent.putExtra(Utils.KEY_STUDENT_NAME, tmp.getName());
                Log.d("check", "name " + tmp.getName());
                intent.putExtra(Utils.KEY_STUDENT_SURNAME, tmp.getSurname());
                intent.putExtra(Utils.KEY_STUDENT_CLASS, tmp.getClass_var());
                intent.putExtra(Utils.KEY_STUDENT_AVGRADE, tmp.getAverage_grade());
                startActivity(intent);
            }
        });
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean switchState) {
                if(!switchState){
                    studentList = Utils.SearchGrade(db, avgrade);
                    StudentAdapter adapter = new StudentAdapter(studentList, SearchGradeActivity.this);
                    lv_student.setAdapter(adapter);
                    lv_student.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Student tmp = studentList.get(position);
                            Intent intent = new Intent(SearchGradeActivity.this, Details.class);
                            intent.putExtra(Utils.KEY_STUDENT_NAME, tmp.getName());
                            intent.putExtra(Utils.KEY_STUDENT_SURNAME, tmp.getSurname());
                            intent.putExtra(Utils.KEY_STUDENT_CLASS, tmp.getClass_var());
                            intent.putExtra(Utils.KEY_STUDENT_AVGRADE, tmp.getAverage_grade());
                            startActivity(intent);
                        }
                    });
                }
                if(switchState){
                    String teacher = Utils.getSubject(1, db);
                    Log.d("check", teacher);
                    Utils.addSubjects(db);
                }
            }
        });
    }
}