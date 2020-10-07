package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.content.Context.AUDIO_SERVICE;

public class NumbersFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch(focusChange){
                case AudioManager.AUDIOFOCUS_GAIN :{
                    mediaPlayer.start();
                }
                break;

                case AudioManager.AUDIOFOCUS_LOSS :{
                    releaseMediaPlayer();
                }
                break;

                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK :{
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(0);
                }
            }
        }
    };
    public NumbersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_numbers, container, false);

        audioManager = (AudioManager)getActivity().getSystemService(AUDIO_SERVICE);

        final ArrayList<CustomWord> arrayList = new ArrayList<CustomWord>();

        arrayList.add(new CustomWord("one", "lutti",R.drawable.number_one,R.raw.number_one));
        arrayList.add(new CustomWord("two", "otiiko",R.drawable.number_two,R.raw.number_two));
        arrayList.add(new CustomWord("three", "tolookosu",R.drawable.number_three,R.raw.number_three));
        arrayList.add(new CustomWord("four", "oyyisa",R.drawable.number_four,R.raw.number_four));
        arrayList.add(new CustomWord("five", "massokka",R.drawable.number_five,R.raw.number_five));
        arrayList.add(new CustomWord("six", "temmokka",R.drawable.number_six,R.raw.number_six));
        arrayList.add(new CustomWord("seven", "kenekaku",R.drawable.number_seven,R.raw.number_seven));
        arrayList.add(new CustomWord("eight", "kawinta",R.drawable.number_eight,R.raw.number_eight));
        arrayList.add(new CustomWord("nine", "wo’e",R.drawable.number_nine,R.raw.number_nine));
        arrayList.add(new CustomWord("ten", "na’aacha",R.drawable.number_ten,R.raw.number_ten));

        CustomWordAdapter customWordAdapter = new CustomWordAdapter(getActivity(), arrayList,R.color.category_numbers);
        ListView listView = (ListView) view.findViewById(R.id.parentView);

        listView.setAdapter(customWordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                releaseMediaPlayer();

                int status = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(status == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

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

        audioManager.abandonAudioFocus(onAudioFocusChangeListener);
    }
}
