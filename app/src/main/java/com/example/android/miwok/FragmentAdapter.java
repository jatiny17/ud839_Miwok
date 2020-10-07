package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentPagerAdapter {

    private Context context;

    public FragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        if(position == 0)
            return new NumbersFragment();
        else if(position == 1)
            return new FamilyFragment();
        else if(position == 2)
            return new ColorsFragment();
        else
            return new PhrasesFragment();
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        String [] tabs = new String[] {context.getResources().getString(R.string.category_numbers),
                context.getResources().getString(R.string.category_family),
                context.getResources().getString(R.string.category_colors),
                context.getResources().getString(R.string.category_phrases)};
        return tabs[position];
    }
}
