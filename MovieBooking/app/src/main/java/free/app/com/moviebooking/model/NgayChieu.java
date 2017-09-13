package free.app.com.moviebooking.model;

/**
 * Created by CuongNguyen on 08/20/17.
 */

public class NgayChieu {
    private int ma;
    private String ngaychieu;
    private boolean selected;

    public NgayChieu(String ngaychieu, boolean selected) {
        this.ngaychieu = ngaychieu;
        this.selected = selected;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getNgaychieu() {
        return ngaychieu;
    }

    public void setNgaychieu(String ngaychieu) {
        this.ngaychieu = ngaychieu;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
