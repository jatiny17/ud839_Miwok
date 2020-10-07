package com.example.android.miwok;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.content.Context.AUDIO_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            switch(focusChange){
                case AudioManager.AUDIOFOCUS_GAIN : {
                    mediaPlayer.start();
                }
                break;

                case AudioManager.AUDIOFOCUS_LOSS:{
                    releaseMediaPlayer();
                }
                break;

                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:{
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(0);
                }
            }
        }
    };

    public ColorsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_colors, container, false);

        final ArrayList<CustomWord> arrayList = new ArrayList<CustomWord>();
        audioManager = (AudioManager)getActivity().getSystemService(AUDIO_SERVICE);

        arrayList.add(new CustomWord("red", "weṭeṭṭi",R.drawable.color_red, R.raw.color_red));
        arrayList.add(new CustomWord("mustard yellow", "chiwiiṭә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
        arrayList.add(new CustomWord("dusty yellow", "ṭopiisә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        arrayList.add(new CustomWord("green", "chokokki",R.drawable.color_green,R.raw.color_green));
        arrayList.add(new CustomWord("brown", "ṭakaakki",R.drawable.color_brown,R.raw.color_brown));
        arrayList.add(new CustomWord("gray", "ṭopoppi",R.drawable.color_gray,R.raw.color_gray));
        arrayList.add(new CustomWord("black", "kululli",R.drawable.color_black,R.raw.color_black));
        arrayList.add(new CustomWord("white", "kelelli",R.drawable.color_white,R.raw.color_white));

        CustomWordAdapter customWordAdapter = new CustomWordAdapter(getActivity(), arrayList,R.color.category_colors);
        ListView listView = (ListView)view.findViewById(R.id.parentView);

        listView.setAdapter(customWordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                releaseMediaPlayer();
                int status = audioManager.requestAudioFocus(audioFocusChangeListener,AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(status == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                {
                    mediaPlayer = MediaPlayer.create(getActivity(), arrayList.get(i).getAudioId());
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            releaseMediaPlayer();
                        }
                    });
                }
            }
        });

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();

        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if(mediaPlayer!=null)
            mediaPlayer.release();

        mediaPlayer = null;
        audioManager.abandonAudioFocus(audioFocusChangeListener);
    }

}
