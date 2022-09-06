package com.example.pentaaccount.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pentaaccount.MainActivity;
import com.example.pentaaccount.Model.StudentModel;
import com.example.pentaaccount.R;
import com.example.pentaaccount.StudentList;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    Context context;
    List<StudentModel> userList;

    public StudentAdapter(Context context, List<StudentModel> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.show_students,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.ViewHolder holder, int position) {
        StudentModel sl=userList.get(position);
        holder.CODE.setText(sl.getUsername());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences pf= context.getSharedPreferences("USERNAME",Context.MODE_PRIVATE);
                SharedPreferences.Editor ed=pf.edit();
                ed.putString("username",sl.getUsername());
                ed.apply();


                Intent intent=new Intent(context, MainActivity.class);
                intent.putExtra("username", sl.getUsername());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView CODE;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CODE=itemView.findViewById(R.id.code);
        }
    }
}
