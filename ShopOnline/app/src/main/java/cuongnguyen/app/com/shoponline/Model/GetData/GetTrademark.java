package cuongnguyen.app.com.shoponline.Model.GetData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import cuongnguyen.app.com.shoponline.Model.ObjectClass.Trademark;

/**
 * Created by CuongNguyen on 06/17/17.
 */

public class GetTrademark {
    @SerializedName("trade")
    @Expose
    private List<Trademark> trade = null;

    public List<Trademark> getTrade() {
        return trade;
    }

    public void setTrade(List<Trademark> trade) {
        this.trade = trade;
    }
}
