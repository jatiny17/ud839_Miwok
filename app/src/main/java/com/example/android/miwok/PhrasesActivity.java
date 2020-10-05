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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        ArrayList<CustomWord> arrayList = new ArrayList<CustomWord>();

        arrayList.add(new CustomWord("Where are you going?", "minto wuksus"));
        arrayList.add(new CustomWord("What is your name?", "tinnә oyaase'nә"));
        arrayList.add(new CustomWord("My name is...", "oyaaset..."));
        arrayList.add(new CustomWord("How are you feeling?", "michәksәs?"));
        arrayList.add(new CustomWord("I’m feeling good.", "kuchi achit"));
        arrayList.add(new CustomWord("Are you coming?", "әәnәs'aa?"));
        arrayList.add(new CustomWord("Yes, I’m coming.", "hәә’ әәnәm"));
        arrayList.add(new CustomWord("I’m coming.", "әәnәm"));
        arrayList.add(new CustomWord("Let’s go.", "yoowutis"));
        arrayList.add(new CustomWord("Come here.", "әnni'nem"));

        CustomWordAdapter customWordAdapter = new CustomWordAdapter(this, arrayList);
        ListView listView = (ListView)findViewById(R.id.parentView);

        listView.setAdapter(customWordAdapter);
    }
}
