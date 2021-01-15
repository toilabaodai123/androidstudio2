package com.example.drawerfragment;

public class Phim {
    public Phim(String id, String tenphim,String noidungphim,String tenloaiphim) {
        this.id = id;
        this.tenphim = tenphim;
        this.noidungphim=noidungphim;
        this.tenloaiphim=tenloaiphim;
    }

    String id;
    String tenphim;

    public String getNoidungphim() {
        return noidungphim;
    }

    String noidungphim;

    public String getTenloaiphim() {
        return tenloaiphim;
    }

    String tenloaiphim;




    public String getId() {
        return id;
    }

    public String getTenphim() {
        return tenphim;
    }

}
