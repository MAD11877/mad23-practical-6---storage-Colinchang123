package com.example.week2practical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //User User1 = (User) getIntent().getSerializableExtra("User");
        Intent receiving = getIntent();
        String Name = receiving.getStringExtra("Name");
        String Desc = receiving.getStringExtra("desc");

        int id = 0;

        for (int i = 0; i < UserList.List.size(); i ++) {
            if (UserList.List.get(i).name.equals(Name)) {
                id = i;
            }
        }
        User user = UserList.List.get(id);

        TextView intText = findViewById(R.id.textView);
        intText.setText(Name);
        TextView descText = findViewById(R.id.textView3);
        descText.setText(Desc);


        Button followButton = findViewById(R.id.button);

        if(user.followed == true){
            //user.followed = false;
            followButton.setText("Unfollow");
        }
        else if(user.followed == false){
            //user.followed = true;
            followButton.setText("Follow");
        }
        followButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(user.followed == true){
                    //User1.setFollowed(false);
                    user.followed = false;
                    followButton.setText("Follow");
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();
                }
                else if(user.followed == false){
                    //User1.setFollowed(true);
                    user.followed = true;
                    followButton.setText("Unfollow");
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
                }

            }
        });
        Button messageButton = findViewById(R.id.button2);
        messageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent startMessage = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(startMessage);
            }
        });
    }
}