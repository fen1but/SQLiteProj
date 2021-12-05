package com.example.sqliteproj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SubjectAdapter extends BaseAdapter {
    ArrayList<Subject> students;
    Context context;

    public SubjectAdapter(ArrayList<Subject> students, Context context){
        this.students = students;
        this.context = context;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Subject getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        Subject tmp = students.get(position);
        convertview = LayoutInflater.from(context).inflate(R.layout.array_list, null);

        TextView tv_name = convertview.findViewById(R.id.tv_name);
        TextView tv_surname = convertview.findViewById(R.id.tv_surname);
        TextView tv_class = convertview.findViewById(R.id.tv_class);
        TextView tv_average = convertview.findViewById(R.id.tv_average);

        tv_name.setText(tmp.getName());
        tv_surname.setText(tmp.getSurname());
        tv_class.setText(""+tmp.getSubject());
        tv_average.setText(""+tmp.getAverage());

        return convertview;
    }
}