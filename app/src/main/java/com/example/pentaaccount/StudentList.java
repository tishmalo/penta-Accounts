package com.example.pentaaccount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.pentaaccount.Adapter.StudentAdapter;
import com.example.pentaaccount.Model.StudentModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentList extends AppCompatActivity {

    String CODE;
    Toolbar tb;
    RecyclerView rv;

    List<StudentModel> userList;
    StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);




        tb=findViewById(R.id.toolBar);
        rv=findViewById(R.id.selectedrecycler);

        userList=new ArrayList<>();
        adapter=new StudentAdapter(this, userList);

        setSupportActionBar(tb);
        getSupportActionBar().setTitle(CODE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        LinearLayoutManager lm=new LinearLayoutManager(this);
        lm.setStackFromEnd(true);
        lm.setReverseLayout(true);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);

        populateData();


    }

    private void populateData() {

        DatabaseReference ref;
        ref= FirebaseDatabase.getInstance().getReference("Students");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList.clear();
                for (DataSnapshot sn: snapshot.getChildren()){

                    StudentModel um=sn.getValue(StudentModel.class);
                    userList.add(um);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}