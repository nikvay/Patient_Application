package com.nikvay.patientapplication.view.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.nikvay.patientapplication.R;
import com.nikvay.patientapplication.model.ClassModel;
import com.nikvay.patientapplication.view.adapter.ClassAdapter;

import java.util.ArrayList;

public class ClassActivity extends AppCompatActivity {

    private RecyclerView recyclerClassList;
    private ImageView iv_close;
    private ArrayList<ClassModel> classModelArrayList=new ArrayList<>();
    private ClassAdapter classAdapter;
    private FloatingActionButton fabAddClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        find_All_IDs();
        events();
    }

    private void events() {
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        fabAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(ClassActivity.this,NewClassActivity.class);
                startActivity(intent);
            }
        });
    }

    private void find_All_IDs() {
        recyclerClassList=findViewById(R.id.recyclerClassList);
        iv_close=findViewById(R.id.iv_close);
        fabAddClass=findViewById(R.id.fabAddClass);


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(ClassActivity.this);
        recyclerClassList.setLayoutManager(linearLayoutManager);

        for(int i=1;i<=5;i++)
        {

            classModelArrayList.add(new ClassModel("1","Demo","60","10","100"));
        }

        classAdapter=new ClassAdapter(ClassActivity.this,classModelArrayList);
        recyclerClassList.setAdapter(classAdapter);
        recyclerClassList.addItemDecoration(new DividerItemDecoration(ClassActivity.this, DividerItemDecoration.VERTICAL));
        recyclerClassList.setHasFixedSize(true);

    }
}
