package cuongnguyen.app.com.shoponline.Presenter.ListProduct;

import java.util.ArrayList;

import cuongnguyen.app.com.shoponline.API.InterfaceAPI;
import cuongnguyen.app.com.shoponline.Model.Constant.MyServices;
import cuongnguyen.app.com.shoponline.Model.GetData.GetProduct;
import cuongnguyen.app.com.shoponline.Model.ObjectClass.Product;
import cuongnguyen.app.com.shoponline.View.ListProduct.ViewListProduct;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by CuongNguyen on 07/07/17.
 */

public class PresenterLogicListProduct implements PresenterListProduct {
    ViewListProduct viewListProduct;

    public PresenterLogicListProduct(ViewListProduct viewListProduct) {
        this.viewListProduct = viewListProduct;
    }

    @Override
    public void getProductOfTrademark(int idGroupProduct, int idTrademark) {
        RestAdapter restAdapter=new RestAdapter.Builder().setEndpoint(MyServices.URL).build();
        InterfaceAPI api=restAdapter.create(InterfaceAPI.class);
        final ArrayList<Product> list=new ArrayList<>();
        api.getBestSellingProduct(1, new Callback<GetProduct>() {
            @Override
            public void success(GetProduct getProduct, Response response) {
                for(Product item : getProduct.getProduct() ){
                    list.add(item);
                }
                viewListProduct.showListProduct(list);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
