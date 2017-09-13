package vn.edu.itplus_academy.tracnghiemtienganh.Activitys;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import vn.edu.itplus_academy.tracnghiemtienganh.Adapter.ViewPagerAdapter;
import vn.edu.itplus_academy.tracnghiemtienganh.Fragment.ExercisesFragment;
import vn.edu.itplus_academy.tracnghiemtienganh.Fragment.TheoryFragment;
import vn.edu.itplus_academy.tracnghiemtienganh.R;

public class GrammarsActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private static final int FRAGMENT_LUYENTAP = 1;
    private static final int FRAGMENT_LYTHUYET = 0;
    private static final int STRAT_LUYENTAP = 1;
    private static final int SHOW_TIME = 1;
    private int ID =0;
    private String grammar = "";
    private int macd = 0;
    private int mLuenTap = 0 ;
    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private TheoryFragment theoryFragment;
    private ExercisesFragment exercisesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);

        toolBar();

        getData();

        viewPager();

        mViewPager.setCurrentItem(ID);

    }
    /**
     * GETDATA
     */
    public void getData(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ID = bundle.getInt("ID");
        grammar = bundle.getString("grammar");
        macd = bundle.getInt("macd");
        //Toast.makeText(getApplicationContext(), macd + "---", Toast.LENGTH_SHORT).show();
    }
    /**
     *  TOOLBAR
     */
    private void toolBar(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    /**
     *  VIEWPAGER
     */
    private void viewPager(){
        mViewPager = (ViewPager) findViewById(R.id.viewPager_main);

        theoryFragment = new TheoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("grammar", grammar);
        theoryFragment.setArguments(bundle);

        exercisesFragment = new ExercisesFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putInt("macd",macd);
        bundle1.putString("grammar", grammar);
        exercisesFragment.setArguments(bundle1);

        mViewPagerAdapter= new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.addFragment(theoryFragment, "Lý Thuyết");
        mViewPagerAdapter.addFragment(exercisesFragment, "Luyện Tập");

        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOnPageChangeListener(this);


    }

    /**
     *   VIEWPAGER CHANGE
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mToolbar.setTitle(mViewPagerAdapter.getPageTitle(position));
        if(position == FRAGMENT_LUYENTAP){
            if( mLuenTap != STRAT_LUYENTAP){
                AlertDialog.Builder alert=new AlertDialog.Builder(GrammarsActivity.this);
                alert.setTitle("Luyện Tập ?");
                alert.setMessage("Hãy nhấn ok để bắt đầu luyện tập ");
                alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        mLuenTap = 1;
                        exercisesFragment.showTime(SHOW_TIME);
                    }
                });
                alert.show();

            }
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * MENU
     */

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

//        if (id == R.id.action_settings) {
//            return true;
//        }
        if(id == android.R.id.home)
        {
            if(mLuenTap == STRAT_LUYENTAP ){
                AlertDialog.Builder alert=new AlertDialog.Builder(GrammarsActivity.this);
                alert.setTitle("Luyên Tập ");
                alert.setMessage("Bạn muốn kết thúc luyện tập ?");
                alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        overridePendingTransition(R.anim.animation_finish_in, R.anim.animation_finish_out);
                    }
                });
                alert.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alert.show();
            }else {
                finish();
                overridePendingTransition(R.anim.animation_finish_in, R.anim.animation_finish_out);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

//        super.onBackPressed();
        if(mLuenTap == STRAT_LUYENTAP ){
            AlertDialog.Builder alert=new AlertDialog.Builder(GrammarsActivity.this);
            alert.setTitle("Luyên Tập ");
            alert.setMessage("Bạn muốn kết thúc luyện tập ?");
            alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    overridePendingTransition(R.anim.animation_finish_in, R.anim.animation_finish_out);
                }
            });
            alert.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alert.show();
        }else {
            finish();
            overridePendingTransition(R.anim.animation_finish_in, R.anim.animation_finish_out);
        }
    }
}
