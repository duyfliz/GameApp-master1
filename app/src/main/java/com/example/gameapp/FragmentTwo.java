package com.example.gameapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment implements YouTubePlayer.OnInitializedListener {

    FrameLayout frameLayout;
    TextView textView;

    public FragmentTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment_two, container, false);
        textView = view.findViewById(R.id.textView_title);
        YouTubePlayerSupportFragment youTubePlayerFragment = new YouTubePlayerSupportFragment();
        youTubePlayerFragment.initialize(getString(R.string.youtube_api_key), this);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_video, youTubePlayerFragment);
        fragmentTransaction.commit();

        //Get the title of the video
        textView.setText(getArguments().getString("1"));
        return view;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI);
        //youTubePlayer.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI);

        if(!b) {

            youTubePlayer.cueVideo(getArguments().getString("8"));
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
    }
}
