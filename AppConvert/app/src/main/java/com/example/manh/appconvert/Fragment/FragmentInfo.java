package com.example.manh.appconvert.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.manh.appconvert.R;

/**
 * Created by cuong on 10/13/2016.
 */
public class FragmentInfo extends Fragment {
    int i=0;
    private int[] layouts;
    private MyViewPagerAdapter myViewPagerAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layouts=new int[]{
                R.layout.fragment_info_1,
                R.layout.fragment_info_2,
                R.layout.fragment_info_3,
                R.layout.fragment_info_4,
                R.layout.fragment_info_5,
        };
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_info,container,false);
        Button left=(Button)view.findViewById(R.id.left);
        Button right=(Button)view.findViewById(R.id.right);
        final ViewPager viewPager=(ViewPager)view.findViewById(R.id.viewPagerInfo);
        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                i=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //Nhấn button trái để chuyển ảnh
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==0)
                {
                    i=4;
                }else{
                    i--;
                }
                viewPager.setCurrentItem(i);
            }
        });
        //Nhấn button phải để chuyển ảnh
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==4)
                {
                    i=0;
                }else{
                    i++;
                }
                viewPager.setCurrentItem(i);
            }
        });
        return  view;
    }
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
