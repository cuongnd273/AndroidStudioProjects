package cuongnguyen.app.com.shoponline.Model.GetData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import cuongnguyen.app.com.shoponline.Model.ObjectClass.Product;

/**
 * Created by CuongNguyen on 06/14/17.
 */

public class GetProduct {
    @SerializedName("product")
    @Expose
    private List<Product> product = null;

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
