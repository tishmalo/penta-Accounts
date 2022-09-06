    package com.example.pentaaccount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pentaaccount.Model.FeesAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

    public class MainActivity extends AppCompatActivity {

    Toolbar tb;
    TextView name;
    String CODE;
    EditText balance;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences pp= this.getSharedPreferences("USERNAME",MODE_PRIVATE);
        CODE=pp.getString("username","");

        balance=findViewById(R.id.balance);
        name=findViewById(R.id.name);
        btn=findViewById(R.id.update);

        tb=findViewById(R.id.toolBar);


        name.setText(CODE);

        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        adddata();
        viewData();




    }

        private void viewData() {

            DatabaseReference ref;
            ref= FirebaseDatabase.getInstance().getReference("Fees");
            Query v=ref.orderByChild("username").equalTo(CODE);
            v.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if(snapshot.exists()) {

                        for (DataSnapshot sn: snapshot.getChildren()){

                            final String BALANCE = sn.child("balance").getValue().toString();

                            balance.setText(BALANCE);
                        }


                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

        private void adddata() {

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference ref;
                ref= FirebaseDatabase.getInstance().getReference("Fees");
                final String BALANCE=balance.getText().toString();

                FeesAdapter ad=new FeesAdapter(CODE,BALANCE);

                ref.child(CODE).setValue(ad);

                Intent intent=new Intent(MainActivity.this, StudentList.class);
                startActivity(intent);

                balance.setText("");

            }
        });




        }
    }