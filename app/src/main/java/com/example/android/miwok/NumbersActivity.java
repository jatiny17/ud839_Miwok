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
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        ArrayList<CustomWord> arrayList = new ArrayList<CustomWord>();

        arrayList.add(new CustomWord("one", "lutti",R.drawable.number_one));
        arrayList.add(new CustomWord("two", "otiiko",R.drawable.number_two));
        arrayList.add(new CustomWord("three", "tolookosu",R.drawable.number_three));
        arrayList.add(new CustomWord("four", "oyyisa",R.drawable.number_four));
        arrayList.add(new CustomWord("five", "massokka",R.drawable.number_five));
        arrayList.add(new CustomWord("six", "temmokka",R.drawable.number_six));
        arrayList.add(new CustomWord("seven", "kenekaku",R.drawable.number_seven));
        arrayList.add(new CustomWord("eight", "kawinta",R.drawable.number_eight));
        arrayList.add(new CustomWord("nine", "wo’e",R.drawable.number_nine));
        arrayList.add(new CustomWord("ten", "na’aacha",R.drawable.number_ten));

        CustomWordAdapter customWordAdapter = new CustomWordAdapter(this, arrayList,R.color.category_numbers);
        ListView listView = (ListView)findViewById(R.id.parentView);

        listView.setAdapter(customWordAdapter);
    }
}
