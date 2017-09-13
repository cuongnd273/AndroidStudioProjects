package cuongnguyen.app.com.shoponline.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CuongNguyen on 05/30/17.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> listFragment=new ArrayList<>();
    List<String> listTitle=new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public void addFragment(Fragment fragment,String title){
        listFragment.add(fragment);
        listTitle.add(title);
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
