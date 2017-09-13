package cuongnguyen.app.com.musicworld.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CuongNguyen on 4/6/2017.
 */

public class ViewPagerCustom extends FragmentPagerAdapter {
    List<Fragment> fragmentList=new ArrayList<>();
    List<String> titleList=new ArrayList<>();
    public ViewPagerCustom(FragmentManager fm) {
        super(fm);
    }
    public void add(Fragment fragment,String title)
    {
        fragmentList.add(fragment);
        titleList.add(title);
    }
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
