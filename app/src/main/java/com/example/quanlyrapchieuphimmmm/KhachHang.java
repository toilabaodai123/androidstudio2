package com.example.quanlyrapchieuphimmmm;

public class KhachHang {
    public String getTaikhoan() {
        return taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public String getHoten() {
        return hoten;
    }

    public KhachHang(String taikhoan, String matkhau, String hoten) {
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
        this.hoten = hoten;
    }

    String taikhoan,matkhau,hoten;
}
