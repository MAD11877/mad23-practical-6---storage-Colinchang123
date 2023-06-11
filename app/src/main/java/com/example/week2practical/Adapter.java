package com.example.week2practical;





import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.activity.ComponentActivity;

import java.util.List;
import java.util.Random;

public class Adapter extends RecyclerView.Adapter<VH> {

    Context context;
    List<User> users;

    public Adapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.textView.setText(users.get(position).getName());
        holder.descView.setText(users.get(position).getDescription());
        holder.imageView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                new AlertDialog.Builder(context)
                        .setTitle("Profile")
                        .setMessage("" + users.get(holder.getAdapterPosition()).getName())
                        .setPositiveButton("VIEW", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){

                                Intent startMain = new Intent(view.getContext(), MainActivity.class);
                                startMain.putExtra("Name", users.get(holder.getAdapterPosition()).getName());
                                startMain.putExtra("Desc", users.get(holder.getAdapterPosition()).getDescription());
                                view.getContext().startActivity(startMain);



                            }
                        })
                        .setNegativeButton("CLOSE", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){

                            }
                        })
                        .show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
