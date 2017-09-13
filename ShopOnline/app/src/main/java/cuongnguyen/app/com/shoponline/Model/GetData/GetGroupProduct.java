package cuongnguyen.app.com.shoponline.Model.GetData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import cuongnguyen.app.com.shoponline.Model.ObjectClass.GroupProduct;

/**
 * Created by CuongNguyen on 05/31/17.
 */

public class GetGroupProduct {
        @SerializedName("GroupProduct")
        @Expose
        private List<GroupProduct> groupProduct = null;

        public List<GroupProduct> getGroupProduct() {
            return groupProduct;
        }

        public void setGroupProduct(List<GroupProduct> groupProduct) {
            this.groupProduct = groupProduct;
        }
}
