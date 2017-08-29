package com.example.gameapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentThree extends Fragment {

    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView img1;
    TextView txt;
    RatingBar ratingBar;


    public FragmentThree() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_three, container, false);
        //toolbar = view.findViewById(R.id.toolbar);
        collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getArguments().getString("1"));
//        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
//        if(((AppCompatActivity)getActivity()).getSupportActionBar() != null) {
//            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }
        img1 = view.findViewById(R.id.img1);
        txt = view.findViewById(R.id.reviewText);
        Picasso.with(getContext()).load(getArguments().getString("9")).into(img1);
        txt.setText(getArguments().getString("10"));
        ratingBar = view.findViewById(R.id.rating);
        ratingBar.setRating(getArguments().getFloat("4"));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
