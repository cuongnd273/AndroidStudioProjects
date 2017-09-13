package cuongnguyen.app.com.shoponline.View.Login;

import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import cuongnguyen.app.com.shoponline.Adapter.ViewPagerAdapter;
import cuongnguyen.app.com.shoponline.R;

public class LoginActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewPager viewPager;
    ViewPagerAdapter adapter;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getControls();
    }
    void getControls(){
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        viewPager= (ViewPager) findViewById(R.id.viewPager);
        tabLayout= (TabLayout) findViewById(R.id.tabs);
        adapter=new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentLogin(),getString(R.string.name_fragment_login));
        adapter.addFragment(new FragmentRegistry(),getString(R.string.name_fragment_registry));
        viewPager.setAdapter(adapter);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)finish();
        return true;
    }
}
