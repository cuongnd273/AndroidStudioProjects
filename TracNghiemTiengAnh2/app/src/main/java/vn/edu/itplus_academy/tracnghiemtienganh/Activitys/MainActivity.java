package vn.edu.itplus_academy.tracnghiemtienganh.Activitys;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import vn.edu.itplus_academy.tracnghiemtienganh.Adapter.ViewPagerAdapter;
import vn.edu.itplus_academy.tracnghiemtienganh.Fragment.BoDeFragment;
import vn.edu.itplus_academy.tracnghiemtienganh.Fragment.GrammarsFragment;
import vn.edu.itplus_academy.tracnghiemtienganh.Fragment.TestsFragment;
import vn.edu.itplus_academy.tracnghiemtienganh.GCM.GCMRegistrationIntentService;
import vn.edu.itplus_academy.tracnghiemtienganh.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,ViewPager.OnPageChangeListener {

    private static final int FRAGMENT_GRAMMARS = 0;
    private static final int FRAGMENT_TESTS = 1;
    private static final int FRAGMENT_BODE = 2;
    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolBar();

//        fab();

        navigationView();

        viewPager();

        gcm();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.w("MainActivity", "onResume");
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(GCMRegistrationIntentService.REGISTRATION_SUCCESS));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(GCMRegistrationIntentService.REGISTRATION_ERROR));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("MainActivity", "onPause");
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
    }
    /**
     *  TOOLBAR
     */
    public void gcm(){
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //Check type of intent filter
                if(intent.getAction().equals(GCMRegistrationIntentService.REGISTRATION_SUCCESS)){
                    //Registration success
                    String token = intent.getStringExtra("token");
//                    Toast.makeText(getApplicationContext(), "GCM token:" + token, Toast.LENGTH_LONG).show();
                } else if(intent.getAction().equals(GCMRegistrationIntentService.REGISTRATION_ERROR)){
                    //Registration error
                    Toast.makeText(getApplicationContext(), "GCM registration error!!!", Toast.LENGTH_LONG).show();
                } else {
                    //Tobe define
                }
            }
        };

        //Check status of Google play service in device
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        if(ConnectionResult.SUCCESS != resultCode) {
            //Check type of error
            if(GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                Toast.makeText(getApplicationContext(), "Google Play Service is not install/enabled in this device!", Toast.LENGTH_LONG).show();
                //So notification
                GooglePlayServicesUtil.showErrorNotification(resultCode, getApplicationContext());
            } else {
                Toast.makeText(getApplicationContext(), "This device does not support for Google Play Service!", Toast.LENGTH_LONG).show();
            }
        } else {
            //Start service
            Intent itent = new Intent(this, GCMRegistrationIntentService.class);
            startService(itent);
        }
    }
    private void toolBar(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }




    /**
     *  VIEWPAGER
     */
    private void viewPager(){
        mViewPager = (ViewPager) findViewById(R.id.viewPager_main);

        mViewPagerAdapter= new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.addFragment(new GrammarsFragment(), getString(R.string.nguphap));
        mViewPagerAdapter.addFragment(new TestsFragment(), getString(R.string.tracnghiem));
        mViewPagerAdapter.addFragment(new BoDeFragment(), getString(R.string.bode));
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOnPageChangeListener(this);


    }

    /**
     * VIEWPAGER CHANGE
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mToolbar.setTitle(mViewPagerAdapter.getPageTitle(position));
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    /**
     *  NAVIGATIONVIEW
     */
    private void navigationView(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_grammars) {
            mViewPager.setCurrentItem(FRAGMENT_GRAMMARS);
        } else if (id == R.id.nav_test) {
            mViewPager.setCurrentItem(FRAGMENT_TESTS);
        } else if (id == R.id.nav_bode) {
            mViewPager.setCurrentItem(FRAGMENT_BODE);
        }else if (id == R.id.nav_featapp) {
            Intent intent = new Intent(MainActivity.this,UngDungActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_rate) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=vn.edu.itplus_academy.tracnghiemtienganh"));
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=vn.edu.itplus_academy.tracnghiemtienganh");
            intent.setType("text/plain");
            startActivity(intent);
        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(MainActivity.this,GuiPhanHoiActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_thongtin) {
            Intent intent = new Intent(MainActivity.this,ThongTinActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    /**
     * Drawer
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }else {
                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "Nhấn lần nữa để thoát", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce=false;
                    }
                }, 2000);
            }
        }
    }



}
