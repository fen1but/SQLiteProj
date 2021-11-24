package com.example.sqliteproj;

import static com.example.sqliteproj.Utils.addStudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    SQLiteDatabase db;
    Button btn_search, btn_mark;
    TextView tv_app;
    EditText et_admin, et_mark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        db = openOrCreateDatabase(Utils.DB_NAME, MODE_PRIVATE, null);
        Utils.createTables(db);
        Utils.addStudent(db, "Roman", "Kungurov", "Yud Bet 8", 100);
        Utils.addStudent(db, "Korson", "Georgia", "Yud Bet 8", 10);
        Utils.addStudent(db, "Murad", "The King", "Yud Bet 8", 101);


        et_admin = findViewById(R.id.et_admin);
        et_mark = findViewById(R.id.et_mark);
        btn_mark = findViewById(R.id.btn_mark);
        tv_app = findViewById(R.id.tv_app);
        btn_search = findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashScreen.this, SearchStudentActivity.class);
                intent.putExtra(Utils.KEY_STUDENT_NAME, et_admin.getText().toString());
                startActivity(intent);
            }
        });
        //todo fix searching by grade
        btn_mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashScreen.this, SearchGradeActivity.class);
                intent.putExtra(Utils.KEY_STUDENT_AVGRADE, et_mark.getText());
                startActivity(intent);
            }
        });
    }
}