package vn.edu.itplus_academy.tracnghiemtienganh.Activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import vn.edu.itplus_academy.tracnghiemtienganh.Adapter.UngDungAdapter;
import vn.edu.itplus_academy.tracnghiemtienganh.R;
import vn.edu.itplus_academy.tracnghiemtienganh.databases.DBAdapter;
import vn.edu.itplus_academy.tracnghiemtienganh.models.UngDung;

public class UngDungActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private List<UngDung> ungDungList;
    private RecyclerView recyclerView;
    private UngDungAdapter ungDungAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ung_dung);
        toolBar();
        list();
        ListRecyclerView();
    }
    /**
     * LIST RECYCLERVIEW
     */
    private void ListRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UngDungActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        ungDungAdapter = new UngDungAdapter(UngDungActivity.this,ungDungList);
        recyclerView.setAdapter(ungDungAdapter);

    }
    /**
     * LIST
     */
    private void list(){

        ungDungList = new ArrayList<>();
        DBAdapter db = new DBAdapter(getApplicationContext());
        db.open();
        ungDungList = db.getAll_UngDung();
        db.close();

    }
    /**
     * TOOLBAR
     */
    private void toolBar(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Ứng Dụng Nổi Bật");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     *   MENU
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if(id == android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
