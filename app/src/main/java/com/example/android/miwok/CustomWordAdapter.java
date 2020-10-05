package com.example.android.miwok;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomWordAdapter extends ArrayAdapter<CustomWord> {

    private int color;

    public CustomWordAdapter(Activity context, ArrayList<CustomWord> arrayList, int color){
        super(context,0, arrayList);
        this.color = color;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;

        if(listItem == null)
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,false);

        CustomWord customWord = getItem(position);

        TextView textView1 = (TextView)listItem.findViewById(R.id.list_item1);
        TextView textView2 = (TextView)listItem.findViewById(R.id.list_item2);

        textView1.setText(customWord.getMiwokTranslation());
        textView2.setText(customWord.getEnglishTranslation());

        LinearLayout linearLayout = (LinearLayout)listItem.findViewById(R.id.linearLayout);
        linearLayout.setBackgroundColor(ContextCompat.getColor(getContext(), color));

        ImageView imageView = (ImageView)listItem.findViewById(R.id.imageView);

        if(customWord.isImage())
        {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(customWord.getImageId());
        }

        else
            imageView.setVisibility(View.GONE);
        return listItem;
    }
}
