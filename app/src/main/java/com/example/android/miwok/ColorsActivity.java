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

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        ArrayList<CustomWord> arrayList = new ArrayList<CustomWord>();

        arrayList.add(new CustomWord("red", "weṭeṭṭi",R.drawable.color_red));
        arrayList.add(new CustomWord("mustard yellow", "chiwiiṭә",R.drawable.color_mustard_yellow));
        arrayList.add(new CustomWord("dusty yellow", "ṭopiisә",R.drawable.color_dusty_yellow));
        arrayList.add(new CustomWord("green", "chokokki",R.drawable.color_green));
        arrayList.add(new CustomWord("brown", "ṭakaakki",R.drawable.color_brown));
        arrayList.add(new CustomWord("gray", "ṭopoppi",R.drawable.color_gray));
        arrayList.add(new CustomWord("black", "kululli",R.drawable.color_black));
        arrayList.add(new CustomWord("white", "kelelli",R.drawable.color_white));

        CustomWordAdapter customWordAdapter = new CustomWordAdapter(this, arrayList,R.color.category_colors);
        ListView listView = (ListView)findViewById(R.id.parentView);

        listView.setAdapter(customWordAdapter);
    }
}
