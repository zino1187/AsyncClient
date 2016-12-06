package com.solu.asyncclient;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by zino on 2016-12-06.
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter{
    Fragment fragments[]=new Fragment[2];

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments[0] = new ListFragment();
        fragments[1]= new DetailFragment();
    }

    public int getCount() {
        return fragments.length;
    }


    public Fragment getItem(int position) {
        return fragments[position];
    }
}
