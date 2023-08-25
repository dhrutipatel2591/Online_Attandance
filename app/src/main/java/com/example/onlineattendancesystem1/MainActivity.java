package com.example.onlineattendancesystem1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    RecyclerView recyclerView;
    ClassAdapter classAdapter;
    RecyclerView.LayoutManager layoutManager;
  ArrayList<ClassItem> classItems = new ArrayList<>();
    EditText ClassEdt,SubjectEdt;
    Button Add,Cancel;
    ClassHelper ch = new ClassHelper(this);


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab_main);
        fab.setOnClickListener(v-> showDialog());

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);

        classAdapter = new ClassAdapter(this,classItems);
        recyclerView.setAdapter(classAdapter);
        Add= findViewById(R.id .btnAdd);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ch.opendb();
                try {
                    long result = ch.insert(ClassEdt.getText().toString(),SubjectEdt.getText().toString());
                    ch.closedb();
                    if (result != -1) {
                        Toast.makeText(MainActivity.this, "Record inserted Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Insert All REcords", Toast.LENGTH_SHORT).show();
                    }

                }
                catch (Exception e)
                {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.class_dialog,null);
        builder.setView(view);
        AlertDialog dialog =  builder.create();
        dialog.show();

        ClassEdt = findViewById(R.id.ClassEdit);
        SubjectEdt = findViewById(R.id .SubjectEdit);

        Add = findViewById(R.id .btnAdd);
        Cancel = findViewById(R.id .btnCancel);

      Cancel.setOnClickListener(v-> dialog.dismiss());
      Add.setOnClickListener(v-> {
          addClass();
          dialog.dismiss();
      });

    }

    private void addClass() {
        String className = ClassEdt.getText().toString();
        String subjectName = SubjectEdt.getText().toString();
        classItems.add(new ClassItem(className,subjectName));
        classAdapter.notifyDataSetChanged();
    }
}