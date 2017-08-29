package com.example.gameapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {


    ArrayList<Game> arrayList;
    DBHelper dbHelper;
    GameAdapter gameAdapter;
    GridView gridView;

    public ThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView = (GridView) view.findViewById(R.id.gridV3);
        dbHelper = new DBHelper(getContext());
        arrayList = dbHelper.getPSVITAGames();
        gameAdapter = new GameAdapter(getContext(), R.layout.game_layout, arrayList);
        gridView.setAdapter(gameAdapter);
        registerForContextMenu(gridView);
    }
}
