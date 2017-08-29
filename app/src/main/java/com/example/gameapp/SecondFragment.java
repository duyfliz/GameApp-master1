package com.example.gameapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    ArrayList<Game> arrayList;
    DBHelper dbHelper;
    GameAdapter gameAdapter;
    GridView gridView;

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView = (GridView) view.findViewById(R.id.gridV2);
        dbHelper = new DBHelper(getContext());
        arrayList = dbHelper.getPS4Games();
        gameAdapter = new GameAdapter(getContext(), R.layout.game_layout, arrayList);
        gridView.setAdapter(gameAdapter);
        registerForContextMenu(gridView);
    }
}
