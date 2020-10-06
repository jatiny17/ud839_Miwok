/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        final ArrayList<CustomWord> arrayList = new ArrayList<CustomWord>();
        audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);

        arrayList.add(new CustomWord("red", "weṭeṭṭi",R.drawable.color_red, R.raw.color_red));
        arrayList.add(new CustomWord("mustard yellow", "chiwiiṭә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
        arrayList.add(new CustomWord("dusty yellow", "ṭopiisә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        arrayList.add(new CustomWord("green", "chokokki",R.drawable.color_green,R.raw.color_green));
        arrayList.add(new CustomWord("brown", "ṭakaakki",R.drawable.color_brown,R.raw.color_brown));
        arrayList.add(new CustomWord("gray", "ṭopoppi",R.drawable.color_gray,R.raw.color_gray));
        arrayList.add(new CustomWord("black", "kululli",R.drawable.color_black,R.raw.color_black));
        arrayList.add(new CustomWord("white", "kelelli",R.drawable.color_white,R.raw.color_white));

        CustomWordAdapter customWordAdapter = new CustomWordAdapter(this, arrayList,R.color.category_colors);
        ListView listView = (ListView)findViewById(R.id.parentView);

        listView.setAdapter(customWordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                releaseMediaPlayer();
                int status = audioManager.requestAudioFocus(audioFocusChangeListener,AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(status == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                {
                    mediaPlayer = MediaPlayer.create(ColorsActivity.this, arrayList.get(i).getAudioId());
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
    }

    @Override
    protected void onStop() {
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
