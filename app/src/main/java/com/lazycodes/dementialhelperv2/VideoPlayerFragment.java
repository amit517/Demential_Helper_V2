package com.lazycodes.dementialhelperv2;



import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideoPlayerFragment extends Fragment {

    private PlayerView playerView;
    SimpleExoPlayer player;

    private Context thisContext;


    public VideoPlayerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v =inflater.inflate(R.layout.fragment_video_player, container, false);

        playerView = v.findViewById(R.id.player_view);
        thisContext = container.getContext();
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        super.onStart();
        player = ExoPlayerFactory.newSimpleInstance(getContext());
        playerView.setUseController(false);
        playerView.setPlayer(player);
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(thisContext,
                Util.getUserAgent(thisContext, "VideoPlayer"));

        MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse("asset:///video1.mp4"));
        // Prepare the player with the source.
        player.prepare(videoSource);
        player.setPlayWhenReady(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        playerView.setPlayer(null);
        player.release();
        player = null;
    }
}
