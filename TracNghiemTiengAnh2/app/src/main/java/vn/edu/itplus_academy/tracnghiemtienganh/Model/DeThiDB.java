package vn.edu.itplus_academy.tracnghiemtienganh.Model;

import java.io.Serializable;

/**
 * Created by VietUng on 04/06/2016.
 */
public class DeThiDB implements Serializable{

    private int madt;
    private int mand;
    private String path;
    private int thoigian;
    private int loaded;
    private int prg;

    public int getPrg() {
        return prg;
    }

    public DeThiDB(int madt, int mand, String path, int thoigian, int loaded, int prg) {
        this.madt = madt;
        this.mand = mand;
        this.path = path;
        this.thoigian = thoigian;
        this.loaded = loaded;
        this.prg = prg;
    }

    public DeThiDB() {

    }

    public void setPrg(int prg) {
        this.prg = prg;
    }

    public int getMadt() {
        return madt;
    }

    public void setMadt(int madt) {
        this.madt = madt;
    }

    public int getMand() {
        return mand;
    }

    public void setMand(int mand) {
        this.mand = mand;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getThoigian() {
        return thoigian;
    }

    public void setThoigian(int thoigian) {
        this.thoigian = thoigian;
    }

    public int getLoaded() {
        return loaded;
    }

    public void setLoaded(int loaded) {
        this.loaded = loaded;
    }
}
