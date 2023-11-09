package com.example.audiobook.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.audiobook.R;

import org.w3c.dom.Text;

public class BookHolder extends RecyclerView.ViewHolder {
    ImageView image;
    TextView bookname;
    public BookHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.iv_popularbook);
        bookname = itemView.findViewById(R.id.tv_bookname);

    }
}
