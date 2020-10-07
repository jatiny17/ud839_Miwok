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
public class PhrasesFragment extends Fragment {

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

    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_phrases, container, false);

        final ArrayList<CustomWord> arrayList = new ArrayList<CustomWord>();

        audioManager = (AudioManager)getActivity().getSystemService(AUDIO_SERVICE);

        arrayList.add(new CustomWord("Where are you going?", "minto wuksus",R.raw.phrase_where_are_you_going));
        arrayList.add(new CustomWord("What is your name?", "tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        arrayList.add(new CustomWord("My name is...", "oyaaset...",R.raw.phrase_my_name_is));
        arrayList.add(new CustomWord("How are you feeling?", "michәksәs?",R.raw.phrase_how_are_you_feeling));
        arrayList.add(new CustomWord("I’m feeling good.", "kuchi achit",R.raw.phrase_im_feeling_good));
        arrayList.add(new CustomWord("Are you coming?", "әәnәs'aa?",R.raw.phrase_are_you_coming));
        arrayList.add(new CustomWord("Yes, I’m coming.", "hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        arrayList.add(new CustomWord("I’m coming.", "әәnәm",R.raw.phrase_im_coming));
        arrayList.add(new CustomWord("Let’s go.", "yoowutis",R.raw.phrase_lets_go));
        arrayList.add(new CustomWord("Come here.", "әnni'nem",R.raw.phrase_come_here));

        CustomWordAdapter customWordAdapter = new CustomWordAdapter(getActivity(), arrayList,R.color.category_phrases);
        ListView listView = (ListView)view.findViewById(R.id.parentView);

        listView.setAdapter(customWordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                releaseMediaPlayer();

                int state = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(state == AudioManager.AUDIOFOCUS_GAIN) {
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
