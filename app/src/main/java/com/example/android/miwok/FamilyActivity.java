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

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);

        ArrayList<CustomWord> arrayList = new ArrayList<CustomWord>();

        arrayList.add(new CustomWord("father", "әpә"));
        arrayList.add(new CustomWord("mother", "әṭa"));
        arrayList.add(new CustomWord("son", "angsi"));
        arrayList.add(new CustomWord("daughter", "tune"));
        arrayList.add(new CustomWord("older brother", "taachi"));
        arrayList.add(new CustomWord("younger brother", "chalitti"));
        arrayList.add(new CustomWord("older sister", "teṭe"));
        arrayList.add(new CustomWord("younger sister", "kolliti"));
        arrayList.add(new CustomWord("grandmother", "ama"));
        arrayList.add(new CustomWord("grandfather", "paapa"));

        CustomWordAdapter customWordAdapter = new CustomWordAdapter(this, arrayList);
        ListView listView = (ListView)findViewById(R.id.parentView);

        listView.setAdapter(customWordAdapter);
    }
}
