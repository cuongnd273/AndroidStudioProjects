package free.app.com.moviebooking.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ToggleButton;

import free.app.com.moviebooking.R;
import free.app.com.moviebooking.adapter.ViewPagerAdapter;
import free.app.com.moviebooking.custom.FilmViewPager;
import free.app.com.moviebooking.fragment.FragmentPhimDangChieu;
import free.app.com.moviebooking.fragment.FragmentPhimSapChieu;
import free.app.com.moviebooking.model.User;

import static android.R.id.toggle;

public class MainActivity extends AppCompatActivity {
    FilmViewPager viewPager;
    TabLayout tabLayout;
    ViewPagerAdapter adapter;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getControls();
    }
    public void getControls(){
        viewPager= (FilmViewPager) findViewById(R.id.viewPager);
        tabLayout= (TabLayout) findViewById(R.id.tabLayout);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        adapter=new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentPhimDangChieu(),"Đang chiếu");
        adapter.addFragment(new FragmentPhimSapChieu(),"Sắp chiếu");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.user:
                User user=User.getUser(this);
                if(user.getTaikhoan()==""){
                    Intent intent=new Intent(MainActivity.this,DangNhapActivity.class);
                    startActivity(intent);
                    break;
                }else{
                    Intent intent=new Intent(MainActivity.this,ThongTinActivity.class);
                    startActivity(intent);
                }
        }
        return super.onOptionsItemSelected(item);
    }
}
