package com.example.cuong.convertapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cuong.convertapp.Adapter.AdapterViewPager;
import com.example.cuong.convertapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuong on 10/9/2016.
 */
public class FragmentConvertNumber extends Fragment{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AdapterViewPager adapter;
    private List<Fragment> listFragment=new ArrayList<Fragment>();
    private List<String> listTitle=new ArrayList<String>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listFragment.add(new FragmentConvertNumberUnsigned());listTitle.add("Số nguyên không dấu");
        listFragment.add(new FragmentConvertNumberSigned());listTitle.add("Số nguyên có dấu");
        listFragment.add(new FragmentConvertNumberIEEE());listTitle.add("Nhị phân dấu chấm động");
        adapter=new AdapterViewPager(getFragmentManager(),listFragment,listTitle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_convert_num,container,false);
        viewPager=(ViewPager)view.findViewById(R.id.viewPagerNum);
        tabLayout=(TabLayout)view.findViewById(R.id.tabNum);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
