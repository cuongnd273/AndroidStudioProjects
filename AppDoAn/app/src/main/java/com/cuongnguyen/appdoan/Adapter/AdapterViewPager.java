package com.cuongnguyen.appdoan.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cuongnguyen.appdoan.Model.NhomDoAn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CUONG on 7/21/2016.
 */
public class AdapterViewPager extends FragmentPagerAdapter {
    List<Fragment> fragmentList=new ArrayList<Fragment>();
    List<NhomDoAn> nhomDoAnList=new ArrayList<NhomDoAn>();
    public AdapterViewPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return nhomDoAnList.get(position).getTenNhom();
    }
    public void addFragment(Fragment fragment,NhomDoAn nhomDoAn)
    {
        fragmentList.add(fragment);
        nhomDoAnList.add(nhomDoAn);
    }
}
