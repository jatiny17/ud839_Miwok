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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

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

        CustomWordAdapter customWordAdapter = new CustomWordAdapter(this, arrayList,R.color.category_numbers);
        ListView listView = (ListView)findViewById(R.id.parentView);

        listView.setAdapter(customWordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(mediaPlayer!=null)
                    mediaPlayer.release();

                mediaPlayer = null;

                mediaPlayer = MediaPlayer.create(NumbersActivity.this, arrayList.get(i).getAudioId());
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
}
