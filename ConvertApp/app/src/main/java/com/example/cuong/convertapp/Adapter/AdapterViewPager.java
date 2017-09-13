package com.example.cuong.convertapp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by cuong on 9/28/2016.
 */
public class AdapterViewPager extends FragmentStatePagerAdapter {
    private List<Fragment> listFragment;
    private List<String> listTitle;
    public AdapterViewPager(FragmentManager fm,List<Fragment> listFragment,List<String> listTitle) {
        super(fm);
        this.listFragment=listFragment;
        this.listTitle=listTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position);
    }
}
