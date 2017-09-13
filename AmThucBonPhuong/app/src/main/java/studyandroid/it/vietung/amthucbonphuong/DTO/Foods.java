package studyandroid.it.vietung.amthucbonphuong.DTO;

/**
 * Created by VietUng on 31/12/2015.
 */
public class Foods {

    private  int id;
    private  String namefood;
    private  String imagefood;
    private  String nguyenlieu;
    private  String cachthuchien;
    private  String idfood;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdfood() {
        return idfood;
    }

    public void setIdfood(String idfood) {
        this.idfood = idfood;
    }

    public String getNamefood() {
        return namefood;
    }

    public void setNamefood(String namefood) {
        this.namefood = namefood;
    }

    public String getImagefood() {
        return imagefood;
    }

    public void setImagefood(String imagefood) {
        this.imagefood = imagefood;
    }

    public String getNguyenlieu() {
        return nguyenlieu;
    }

    public void setNguyenlieu(String nguyenlieu) {
        this.nguyenlieu = nguyenlieu;
    }

    public String getCachthuchien() {
        return cachthuchien;
    }

    public void setCachthuchien(String cachthuchien) {
        this.cachthuchien = cachthuchien;
    }
}
