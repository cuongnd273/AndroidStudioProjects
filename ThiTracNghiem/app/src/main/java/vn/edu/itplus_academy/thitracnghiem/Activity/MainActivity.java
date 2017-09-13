package vn.edu.itplus_academy.thitracnghiem.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.edu.itplus_academy.thitracnghiem.DataBase.DBAdapter;
import vn.edu.itplus_academy.thitracnghiem.Fragment.ThamGiaThiFragment;
import vn.edu.itplus_academy.thitracnghiem.Fragment.TraCuuDiemThiFragment;
import vn.edu.itplus_academy.thitracnghiem.Fragment.TrangChuFragment;
import vn.edu.itplus_academy.thitracnghiem.Model.MonThi;
import vn.edu.itplus_academy.thitracnghiem.Model.ThiSinh;
import vn.edu.itplus_academy.thitracnghiem.R;
import vn.edu.itplus_academy.thitracnghiem.Service.TimeIntentService;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private final static String CHUALAM = "ChuaLam";
    private final static String DANGLAM = "DangLam";
    private final static String DALAM = "DaLam";
    public final static String MAINACTIVITY = "MainActivity";
    private Fragment frag = null;
    private Toolbar toolbar;
    private boolean doubleBackToExitPressedOnce = false;
    private DBAdapter db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBAdapter(MainActivity.this);
        toolBar();

        navigationView();

        startFagementTrangChu();
    }
    public void toolBar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void navigationView(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_trangchu) {
            startFagementTrangChu();
        } else if (id == R.id.nav_thamgiathi) {
            startFagementThamGiaThi();
        } else if (id == R.id.nav_tracuudiemthi) {
            startFagementTraCuuDiemThi();
        } else if (id == R.id.nav_gioithieu) {
            PrefManager prefManager = new PrefManager(getApplicationContext());

            // make first time launch TRUE
            prefManager.setFirstTimeLaunch(true);

            Intent intentWelcome = new Intent(this, WelcomeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("start",MAINACTIVITY);
            intentWelcome.putExtras(bundle);
            startActivity(intentWelcome);

        } else if (id == R.id.nav_dangxuat) {
            ArrayList<ThiSinh> thiSinhs=new ArrayList<>();
            db=new DBAdapter(MainActivity.this);
            thiSinhs= (ArrayList<ThiSinh>) db.getAll_ThiSinh();
            if(thiSinhs.size() >0) {
                int somondalam = 0;
                final List<MonThi> monThiList = db.getAll_MonThi();
                for (int i = 0; i < monThiList.size(); i++) {
                    if (monThiList.get(i).getStatusMonThi().equals(DALAM)) {
                        somondalam++;
                    }
                }
                Log.i("Status", thiSinhs.get(0).getStatusThiSinh().toString());
                if (somondalam != monThiList.size()) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Thi Trắc Nghiệm ");
                    alert.setMessage("Bạn có chắc chắn muốn đăng xuất không ?");
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent serviceIntent = new Intent(MainActivity.this, TimeIntentService.class);
                            stopService(serviceIntent);
                            db.deleteAll_ThiSinh();
                            db.deleteAll_MonThi();
                            db.deleteAll_CauHoi();
                            Intent intentLogIn = new Intent(MainActivity.this, LogInActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("start", MAINACTIVITY);
                            intentLogIn.putExtras(bundle);
                            startActivity(intentLogIn);
                            finish();
                        }
                    });
                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alert.show();
                } else {

                    db.deleteAll_ThiSinh();
                    db.deleteAll_MonThi();
                    db.deleteAll_CauHoi();
                    Intent intentLogIn = new Intent(this, LogInActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("start", MAINACTIVITY);
                    intentLogIn.putExtras(bundle);
                    startActivity(intentLogIn);
                    finish();
                }
            }


        } else if (id == R.id.nav_dangky) {
            ArrayList<ThiSinh> thiSinhs=new ArrayList<>();
            db=new DBAdapter(MainActivity.this);
            thiSinhs= (ArrayList<ThiSinh>) db.getAll_ThiSinh();
            if(thiSinhs.size() >0) {
                int somondalam = 0;
                final List<MonThi> monThiList = db.getAll_MonThi();
                for (int i = 0; i < monThiList.size(); i++) {
                    if (monThiList.get(i).getStatusMonThi().equals(DALAM)) {
                        somondalam++;
                    }
                }
                if (somondalam != monThiList.size()) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Thi Trắc Nghiệm ");
                    alert.setMessage("Bạn phải đăng xuất thì mới có thể đăng ký?");
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent serviceIntent = new Intent(MainActivity.this, TimeIntentService.class);
                            stopService(serviceIntent);
                            db.deleteAll_ThiSinh();
                            db.deleteAll_MonThi();
                            db.deleteAll_CauHoi();
                            Intent intentSignUp = new Intent(MainActivity.this, SignUpActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("start", MAINACTIVITY);
                            intentSignUp.putExtras(bundle);
                            startActivity(intentSignUp);
                            finish();
                        }
                    });
                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alert.show();
                } else {

                    db.deleteAll_ThiSinh();
                    db.deleteAll_MonThi();
                    db.deleteAll_CauHoi();
                    Intent intentSignUp = new Intent(this, SignUpActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("start", MAINACTIVITY);
                    intentSignUp.putExtras(bundle);
                    startActivity(intentSignUp);
                    finish();
                }
            }else{
                Intent intent=new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void startFagementTrangChu(){
        frag = new TrangChuFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_main,frag,"mainFrag");
        ft.commit();
        toolbar.setTitle(R.string._trangchu);
    }

    public void startFagementThamGiaThi(){
        ArrayList<ThiSinh> thiSinhs=new ArrayList<>();
        db=new DBAdapter(this);
        thiSinhs= (ArrayList<ThiSinh>) db.getAll_ThiSinh();
        if(thiSinhs.size() >0)
        {
            Fragment frag = new ThamGiaThiFragment();
            FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_main, frag, "mainFrag");
            ft.commit();
            this.setTitle(R.string._thamgiathi);
        }else{
            Intent intentLogin = new Intent(MainActivity.this,LogInActivity.class);
            startActivity(intentLogin);
        }
    }

    private void startFagementTraCuuDiemThi() {
        frag = new TraCuuDiemThiFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_main,frag,"mainFrag");
        ft.commit();
        toolbar.setTitle(R.string._thamgiathi);
    }


}
