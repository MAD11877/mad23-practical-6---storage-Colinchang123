package com.example.week2practical;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;
import com.example.week2practical.User;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Random random = new Random();
        for(int i = 0; i < 20; i++){
            UserList.List.add(new User("Name" + random.nextInt(), "Description" + random.nextInt(), random.nextInt(), random.nextBoolean()));
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new Adapter(this, UserList.List));

        /*        imageView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                new AlertDialog.Builder(view.getContext())
                        .setTitle("Profile")
                        .setMessage("MADness")
                        .setPositiveButton("VIEW", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                Random random = new Random();
                                int randInt = random.nextInt();

                                Intent startMain = new Intent(view.getContext(), MainActivity.class);
                                startMain.putExtra("randIntKey", randInt);
                                startActivity(startMain);
                            }
                        })
                        .setNegativeButton("CLOSE", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){

                            }
                        })
                        .show();
            }
        });*/
    }
}