package com.example.gameapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment  {

    TextView textView1, textView2, textView3, textView4, textView5;
    Game g;
    Bundle bundle;
    ImageView imageView;
    ScrollView scrollView;

    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_fragment_one, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String s = getArguments().getString("1");
        //g = (Game) bundle.getSerializable("game");
        textView1 = view.findViewById(R.id.text_1);
        textView2 = view.findViewById(R.id.text_2);
        textView3 = view.findViewById(R.id.text_3);
        textView5 = view.findViewById(R.id.text_4);
        imageView = view.findViewById(R.id.pic);
        //scrollView = view.findViewById(R.id.scrollV);

        textView4 = view.findViewById(R.id.textV);


        textView1.setText(textView1.getText() + s);
        textView2.setText(textView2.getText() + getArguments().getString("5"));
        textView3.setText(textView3.getText() + getArguments().getString("6"));
        textView5.setText(textView5.getText() + getArguments().getString("7"));
        Picasso.with(getContext()).load(getArguments().getString("3")).into(imageView);
        textView4.setText("Description:\n" + getArguments().getString("2"));


    }
}
