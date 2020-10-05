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

import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        final ArrayList<CustomWord> arrayList = new ArrayList<CustomWord>();

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

        CustomWordAdapter customWordAdapter = new CustomWordAdapter(this, arrayList,R.color.category_phrases);
        ListView listView = (ListView)findViewById(R.id.parentView);

        listView.setAdapter(customWordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(mediaPlayer!=null)
                    mediaPlayer.release();
                mediaPlayer=null;

                mediaPlayer = MediaPlayer.create(PhrasesActivity.this, arrayList.get(i).getAudioId());
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        if(mediaPlayer!=null)
                            mediaPlayer.release();

                        mediaPlayer=null;
                    }
                });
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mediaPlayer!=null)
            mediaPlayer.release();

        mediaPlayer = null;
    }
}
