package cuongnguyen.app.com.shoponline.View.Home;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.facebook.FacebookSdk;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.security.acl.Group;
import java.util.List;

import cuongnguyen.app.com.shoponline.Adapter.ExpandableMenuAdapter;
import cuongnguyen.app.com.shoponline.Adapter.ViewPagerAdapter;
import cuongnguyen.app.com.shoponline.Model.ObjectClass.Account;
import cuongnguyen.app.com.shoponline.Model.ObjectClass.GroupProduct;
import cuongnguyen.app.com.shoponline.Presenter.Home.PresenterLogicLogout;
import cuongnguyen.app.com.shoponline.Presenter.Home.PresenterLogicMenu;
import cuongnguyen.app.com.shoponline.R;
import cuongnguyen.app.com.shoponline.View.Login.LoginActivity;

public class HomeActivity extends AppCompatActivity implements ViewMenu,AppBarLayout.OnOffsetChangedListener,ViewLogout,GoogleApiClient.OnConnectionFailedListener {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter adapter;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ExpandableListView epMenu;
    ExpandableMenuAdapter expandableMenuAdapter;
    AppBarLayout appBarLayout;
    GoogleApiClient mGoogleApiClient;
    CollapsingToolbarLayout toolbarLayout;
    Menu menu;
    PresenterLogicLogout presenterLogicLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_home);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // [END configure_signin]

        // [START build_client]
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        getControl();
        presenterLogicLogout=new PresenterLogicLogout(this,this);
    }
    void getControl(){
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        tabLayout= (TabLayout) findViewById(R.id.tabs);
        viewPager= (ViewPager) findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(1);
        epMenu= (ExpandableListView) findViewById(R.id.epMenu);
        appBarLayout= (AppBarLayout) findViewById(R.id.appBar);
        toolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collapToolbar);

        PresenterLogicMenu presenterLogicMenu=new PresenterLogicMenu(this);
        presenterLogicMenu.getGroupProduct();

        setSupportActionBar(toolbar);
        drawerLayout= (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open_drawer,R.string.close_drawer);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();
        appBarLayout.addOnOffsetChangedListener(this);

    }
    void setUpViewPager(List<GroupProduct> list){
        adapter=new ViewPagerAdapter(getSupportFragmentManager());
        for(GroupProduct item : list){
            if(item.getIdGroup()==0){
                adapter.addFragment(new FragmentHome(item.getId()),item.getName());
            }
        }
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        this.menu=menu;
        updateMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))return true;
        switch (item.getItemId()){
            case R.id.menu_login:
                Intent intent=new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_logout:
                logOut();
                menu.clear();
                onCreateOptionsMenu(menu);
                break;
        }
        return true;
    }

    @Override
    public void showMenu(List<GroupProduct> list) {
        expandableMenuAdapter =new ExpandableMenuAdapter(this,list,null,1);
        epMenu.setAdapter(expandableMenuAdapter);
        setUpViewPager(list);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if(toolbarLayout.getHeight()+verticalOffset<= ViewCompat.getMinimumHeight(toolbarLayout)){
            LinearLayout linearLayout= (LinearLayout) findViewById(R.id.layoutSearch);
            linearLayout.animate().alpha(0).setDuration(1000);

            if(menu!=null){
                MenuItem item=menu.findItem(R.id.menu_search);
                item.setVisible(true);
            }
        }else{
            LinearLayout linearLayout= (LinearLayout) findViewById(R.id.layoutSearch);
            linearLayout.animate().alpha(1).setDuration(1000);

            if(menu!=null){
                MenuItem item=menu.findItem(R.id.menu_search);
                item.setVisible(false);
            }
        }
    }
    void updateMenu(Menu menu){
        if(Account.getLogged(this)!=0){
            MenuItem itemLogin=menu.findItem(R.id.menu_login);
            itemLogin.setTitle("Hi, "+Account.getName(this));

            MenuItem itemLogout=menu.findItem(R.id.menu_logout);
            itemLogout.setVisible(true);
        }
    }
    void logOut(){
        if(Account.getLogged(this)==Account.LOGIN_FACEBOOK){
            presenterLogicLogout.logoutFacebook();
        }else if(Account.getLogged(this)==Account.LOGIN_GOOGLE_PLUS){
            presenterLogicLogout.logoutGoogle(mGoogleApiClient);
        }else{
            presenterLogicLogout.logoutEmail();
        }
    }

    @Override
    public void logout() {
        finish();
        startActivity(getIntent());
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
