package com.example.sqliteproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudentActivity extends AppCompatActivity {
    Button btn_submit;
    EditText et_name, et_surname, et_class, et_avgrade;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        btn_submit = findViewById(R.id.btn_submit);
        et_name = findViewById(R.id.et_name);
        et_surname = findViewById(R.id.et_surname);
        et_class = findViewById(R.id.et_class);
        et_avgrade = findViewById(R.id.et_avgrade);
        db = openOrCreateDatabase(Utils.DB_NAME, MODE_PRIVATE, null);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String name = et_name.getText().toString();
                    String surname = et_surname.getText().toString();
                    String class_var = et_class.getText().toString();
                    int average_grade = Integer.parseInt(et_avgrade.getText().toString());
                    Utils.addStudent(db, name, surname, class_var, average_grade);
                    Context context = getApplicationContext();
                    CharSequence text = "User added successfully";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                catch(NumberFormatException e) {
                    Context context = getApplicationContext();
                    CharSequence text = "Wrong parameters";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }
}