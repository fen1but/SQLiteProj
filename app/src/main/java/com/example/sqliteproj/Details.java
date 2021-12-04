package com.example.sqliteproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Details extends AppCompatActivity {
    TextView tvname, tvsurname, tvclass, tvavgrade, tvname2, tvsurname2, tvclass2, tvavgrade2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tvname = findViewById(R.id.tvname);
        tvsurname = findViewById(R.id.tvsurname);
        tvclass = findViewById(R.id.tvclass);
        tvavgrade = findViewById(R.id.tvavgrade);
        tvname2 = findViewById(R.id.tvname2);
        tvsurname2 = findViewById(R.id.tvsurname2);
        tvclass2 = findViewById(R.id.tvclass2);
        tvavgrade2 = findViewById(R.id.tvavgrade2);

        Intent intent = getIntent();
        //todo fix
        String name = intent.getStringExtra(Utils.KEY_STUDENT_NAME);
        String surname = intent.getStringExtra(Utils.KEY_STUDENT_SURNAME);
        String classvar = intent.getStringExtra(Utils.KEY_STUDENT_CLASS);
        int avgrade = intent.getIntExtra(Utils.KEY_STUDENT_AVGRADE, 0);
        tvname2.setText(name);
        tvsurname2.setText(surname);
        tvclass2.setText(classvar);
        tvavgrade2.setText(""+avgrade);
    }
}