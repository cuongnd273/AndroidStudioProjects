package com.example.manh.appconvert.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manh.appconvert.Adapter.AdapterViewPager;
import com.example.manh.appconvert.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuong on 9/28/2016.
 */
public class FragmentUnit extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AdapterViewPager adapter;
    private List<Fragment> listFragment=new ArrayList<Fragment>();
    private List<String> listTitle=new ArrayList<String>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listFragment.add(new FragmentConvertLength());listTitle.add("Độ dài");
        listFragment.add(new FragmentConvertVolume());listTitle.add("Thể tích");
        listFragment.add(new FragmentConvertTemperature());listTitle.add("Nhiệt độ");
        listFragment.add(new FragmentConvertWeight());listTitle.add("Khối lượng");
        adapter=new AdapterViewPager(getFragmentManager(),listFragment,listTitle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_convert_unit,container,false);
        viewPager=(ViewPager)view.findViewById(R.id.viewPagerUnit);
        tabLayout=(TabLayout)view.findViewById(R.id.tabs);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
