package cuongnguyen.app.com.shoponline.View.ListProduct;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import cuongnguyen.app.com.shoponline.Adapter.AdapterListProduct;
import cuongnguyen.app.com.shoponline.Model.ObjectClass.Product;
import cuongnguyen.app.com.shoponline.Presenter.ListProduct.PresenterLogicListProduct;
import cuongnguyen.app.com.shoponline.R;

public class ListProductActivity extends AppCompatActivity implements ViewListProduct{
    Toolbar toolbar;
    AppBarLayout appBarLayout;
    RecyclerView listRecyclerView;
    AdapterListProduct adapterListProduct;
    ArrayList<Product> listProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        getControls();
        PresenterLogicListProduct logicListProduct=new PresenterLogicListProduct(this);
        logicListProduct.getProductOfTrademark(1,1);
    }
    void getControls(){
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getIntent().getStringExtra("name"));
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listRecyclerView= (RecyclerView) findViewById(R.id.list_product);
        listRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void showListProduct(ArrayList<Product> list) {
        listProduct=new ArrayList<>();
        listProduct=list;
        adapterListProduct=new AdapterListProduct(this,listProduct);
        listRecyclerView.setAdapter(adapterListProduct);
    }
}
