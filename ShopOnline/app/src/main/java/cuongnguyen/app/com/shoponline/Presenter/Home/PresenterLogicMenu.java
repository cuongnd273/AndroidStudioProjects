package cuongnguyen.app.com.shoponline.Presenter.Home;

import android.util.Log;

import java.util.List;

import cuongnguyen.app.com.shoponline.API.InterfaceAPI;
import cuongnguyen.app.com.shoponline.Model.Constant.MyServices;
import cuongnguyen.app.com.shoponline.Model.GetData.GetGroupProduct;
import cuongnguyen.app.com.shoponline.Model.ObjectClass.GroupProduct;
import cuongnguyen.app.com.shoponline.View.Home.ViewMenu;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by CuongNguyen on 05/31/17.
 */

public class PresenterLogicMenu implements PresenterMenu {
    ViewMenu viewMenu;
    String TAG="MyResult";
    public PresenterLogicMenu(ViewMenu viewMenu) {
        this.viewMenu = viewMenu;
    }

    @Override
    public void getGroupProduct() {
        RestAdapter restAdapter=new RestAdapter.Builder().setEndpoint(MyServices.URL).build();
        InterfaceAPI api=restAdapter.create(InterfaceAPI.class);
        api.getListGroupProduct(new Callback<GetGroupProduct>() {
            @Override
            public void success(GetGroupProduct getGroupProduct, Response response) {
                viewMenu.showMenu(getGroupProduct.getGroupProduct());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i(TAG, error.toString());
            }
        });
    }
}
