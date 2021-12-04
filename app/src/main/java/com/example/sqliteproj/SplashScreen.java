package com.example.sqliteproj;

import static com.example.sqliteproj.Utils.addStudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {
    SQLiteDatabase db;
    Button btn_search, btn_mark, btn_allstudents, btn_class;
    TextView tv_app;
    EditText et_admin, et_mark, et_class;
    ImageView img;
    Animation anim_fadein;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        img = findViewById(R.id.img);
        anim_fadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
        img.startAnimation(anim_fadein);

        db = openOrCreateDatabase(Utils.DB_NAME, MODE_PRIVATE, null);
        Utils.createTables(db);
        Utils.addStudent(db, "Roman", "Kungurov", "YudBet", 100);
        Utils.addStudent(db, "Amram", "Boss", "Yud", 100);
        Utils.addStudent(db, "Korson", "Georgia", "YudBet", 10);
        Utils.addStudent(db, "Rafi", "Looser", "Alef", 0);
        Utils.addStudent(db, "Murad", "The King", "YudBet", 101);
        Utils.addClass(db, "YudBet", "Sara");
        Utils.addClass(db, "Alef", "Bibi");
        Utils.addClass(db, "Yud", "Ronny");
        Utils.addTeacher(db, "Sara", "Akiva", "English");
        Utils.addTeacher(db, "Bibi", "Ragimli", "Physics");
        Utils.addTeacher(db, "Ronny", "HaMelech", "CS");

        et_admin = findViewById(R.id.et_admin);
        et_class = findViewById(R.id.et_class);
        et_mark = findViewById(R.id.et_mark);
        btn_mark = findViewById(R.id.btn_mark);
        btn_class = findViewById(R.id.btn_class);
        btn_allstudents = findViewById(R.id.btn_allstudents);
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
        btn_mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(SplashScreen.this, SearchGradeActivity.class);
                    intent.putExtra(Utils.KEY_STUDENT_AVGRADE, Integer.parseInt(et_mark.getText().toString()));
                    startActivity(intent);
                }
                catch(Exception e) {
                    Context context = getApplicationContext();
                    CharSequence text = "Write a grade";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }

            }
        });
        btn_allstudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashScreen.this, SearchGradeActivity.class);
                intent.putExtra(Utils.KEY_STUDENT_AVGRADE, -1);
                startActivity(intent);
            }
        });
        btn_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashScreen.this, SearchClassActivity.class);
                intent.putExtra(Utils.KEY_STUDENT_CLASS, et_class.getText().toString());
                startActivity(intent);
            }
        });
    }
}