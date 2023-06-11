package com.example.week2practical;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Random;

public class VH extends RecyclerView.ViewHolder {
    TextView textView;
    TextView descView;
    ImageView imageView;


    public VH (View itemView){
        super (itemView);
        textView = itemView.findViewById(R.id.itemname);
        descView = itemView.findViewById(R.id.itemdesc);
        imageView= itemView.findViewById(R.id.itemimage);


    }
}
