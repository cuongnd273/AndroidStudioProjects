package vn.edu.itplus_academy.tracnghiemtienganh.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by VietUng on 08/04/2016.
 */
public class CauHoiKT {


        @SerializedName("MaCH")
        @Expose
        private int mach;

        @SerializedName("CauHoi")
        @Expose
        private String cauhoi;

        @SerializedName("DA1")
        @Expose
        private String dapan_A;

        @SerializedName("DA2")
        @Expose
        private String dapan_B;

        @SerializedName("DA3")
        @Expose
        private String dapan_C;

        @SerializedName("DA4")
        @Expose
        private String dapan_D;

        @SerializedName("DAD")
        @Expose
        private String ketqua;

        public String getCauhoi() {
            return cauhoi;
        }

        public void setCauhoi(String cauhoi) {
            this.cauhoi = cauhoi;
        }

        public String getDapan_A() {
            return dapan_A;
        }

        public void setDapan_A(String dapan_A) {
            this.dapan_A = dapan_A;
        }

        public String getDapan_B() {
            return dapan_B;
        }

        public void setDapan_B(String dapan_B) {
            this.dapan_B = dapan_B;
        }

        public String getDapan_C() {
            return dapan_C;
        }

        public void setDapan_C(String dapan_C) {
            this.dapan_C = dapan_C;
        }

        public String getDapan_D() {
            return dapan_D;
        }

        public void setDapan_D(String dapan_D) {
            this.dapan_D = dapan_D;
        }

        public String getKetqua() {
            return ketqua;
        }

        public void setKetqua(String ketqua) {
            this.ketqua = ketqua;
        }

        public int getMach() {
            return mach;
        }

        public void setMach(int mach) {
            this.mach = mach;
        }

}
