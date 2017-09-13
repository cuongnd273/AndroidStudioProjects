package cuongnguyen.app.com.shoponline.View.Home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cuongnguyen.app.com.shoponline.API.InterfaceAPI;
import cuongnguyen.app.com.shoponline.Adapter.AdapterHomeProduct;
import cuongnguyen.app.com.shoponline.Adapter.AdapterHomeTrademark;
import cuongnguyen.app.com.shoponline.Model.Constant.MyServices;
import cuongnguyen.app.com.shoponline.Model.GetData.GetProduct;
import cuongnguyen.app.com.shoponline.Model.GetData.GetTrademark;
import cuongnguyen.app.com.shoponline.Model.ObjectClass.Product;
import cuongnguyen.app.com.shoponline.Model.ObjectClass.Trademark;
import cuongnguyen.app.com.shoponline.R;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by CuongNguyen on 06/14/17.
 */

public class FragmentHome extends Fragment {
    private int groupID;
    RecyclerView trademark,best_selling_product,favorite_product,latest_product,random_product;
    AdapterHomeTrademark adapterTrademark;
    AdapterHomeProduct adapterSellingProduct;
    ArrayList listTrademark,listSellingProduct;
    RestAdapter restAdapter;
    InterfaceAPI api;
    public FragmentHome(int groupID) {
        this.groupID = groupID;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        restAdapter=new RestAdapter.Builder().setEndpoint(MyServices.URL).build();
        api=restAdapter.create(InterfaceAPI.class);

        listTrademark=new ArrayList<Trademark>();
        listSellingProduct=new ArrayList<Product>();
        adapterTrademark=new AdapterHomeTrademark(getContext(),listTrademark);
        adapterSellingProduct= new AdapterHomeProduct(getContext(),listSellingProduct);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        getControls(view);
        return view;
    }
    void getControls(View view){
        trademark= (RecyclerView) view.findViewById(R.id.trademark);
        best_selling_product= (RecyclerView) view.findViewById(R.id.bets_selling_product);
        favorite_product= (RecyclerView) view.findViewById(R.id.favorite_produc);
        latest_product= (RecyclerView) view.findViewById(R.id.latest_product);
        random_product= (RecyclerView) view.findViewById(R.id.random_product);

        trademark.setLayoutManager(new GridLayoutManager(getContext(),3));
        trademark.setAdapter(adapterTrademark);

        best_selling_product.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        best_selling_product.setAdapter(adapterSellingProduct);

        favorite_product.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        favorite_product.setAdapter(adapterSellingProduct);

        latest_product.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        latest_product.setAdapter(adapterSellingProduct);

        random_product.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        random_product.setAdapter(adapterSellingProduct);

        api.getTrademarkOfGroup(0, new Callback<GetTrademark>() {
            @Override
            public void success(GetTrademark getTrademark, Response response) {
                listTrademark.clear();
                for(Trademark item : getTrademark.getTrade()){
                    listTrademark.add(item);
                }
                adapterTrademark.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("Ketqua",error.toString());
            }
        });
        api.getBestSellingProduct(0, new Callback<GetProduct>() {
            @Override
            public void success(GetProduct getProduct, Response response) {
                listSellingProduct.clear();
                for(Product item : getProduct.getProduct()){
                    listSellingProduct.add(item);
                }
                adapterSellingProduct.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("Ketqua","Thanh cong");
            }
        });
    }
}
