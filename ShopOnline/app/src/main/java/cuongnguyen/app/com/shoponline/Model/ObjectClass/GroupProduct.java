package cuongnguyen.app.com.shoponline.Model.ObjectClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CuongNguyen on 05/31/17.
 */

public class GroupProduct {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("idGroup")
    @Expose
    private int idGroup;
    @SerializedName("name")
    @Expose
    private String name;
    private List<GroupProduct> listChild;
    public GroupProduct(int id, int idGroup, String name) {
        this.id = id;
        this.idGroup = idGroup;
        this.name = name;
        listChild=new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GroupProduct> getListChild() {
        return listChild;
    }

    public void setListChild(List<GroupProduct> listChild) {
        this.listChild = listChild;
    }
    public void addChild(GroupProduct groupProduct){
        listChild.add(groupProduct);
    }
}
