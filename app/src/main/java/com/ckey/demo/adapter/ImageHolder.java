package com.ckey.demo.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ckey.demo.R;

public class ImageHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;

    public ImageHolder(@NonNull View view) {
        super(view);
        this.imageView = (ImageView) view.findViewById(R.id.image);

    }

}
