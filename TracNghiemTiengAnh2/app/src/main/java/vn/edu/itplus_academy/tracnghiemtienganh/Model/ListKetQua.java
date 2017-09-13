package vn.edu.itplus_academy.tracnghiemtienganh.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tuananh on 10/04/2016.
 */
public class ListKetQua implements Serializable {
    private List<CauHoi> cauHoiList;
    private int type;
    private int ID;

    public ListKetQua(List<CauHoi> cauHoiList, int type, int ID) {
        this.cauHoiList = cauHoiList;
        this.type = type;
        this.ID = ID;
    }

    public ListKetQua(List<CauHoi> cauHoiList) {
        this.cauHoiList = cauHoiList;
    }

    public List<CauHoi> getCauHoiList() {
        return cauHoiList;
    }

    public void setCauHoiList(List<CauHoi> cauHoiList) {
        this.cauHoiList = cauHoiList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
