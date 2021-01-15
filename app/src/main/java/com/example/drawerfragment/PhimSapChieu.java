package com.example.drawerfragment;

public class PhimSapChieu {


    public PhimSapChieu(String tenphim, String loaiphim, String noidungphim) {
        this.tenphim = tenphim;
        this.loaiphim = loaiphim;
        this.noidungphim = noidungphim;
    }

    public String getTenphim() {
        return tenphim;
    }

    public String getLoaiphim() {
        return loaiphim;
    }

    public String getNoidungphim() {
        return noidungphim;
    }

    String tenphim,loaiphim,noidungphim;
}
