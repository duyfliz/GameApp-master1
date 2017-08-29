package com.example.gameapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by HOCVIEN on 8/18/2017.
 */

public class GameAdapter extends ArrayAdapter {

    Context context;
    ArrayList<Game> arrayList;
    int layout;

    public GameAdapter(Context context, int resource, ArrayList<Game> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.arrayList = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(layout, null);
        ImageView img = (ImageView) convertView.findViewById(R.id.imgV);
        //TextView txt = (TextView) convertView.findViewById(R.id.txt1);
        //Picasso.with(context).load(arrayList.get(position).getImage().replace("https", "http")).into(img);
        String link = arrayList.get(position).getImage();
        Picasso.with(context).load(link).into(img);
        //txt.setText(link);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("game",arrayList.get(position));
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
