package com.example.drawerfragment;

public class thongtinlichsu {
    public thongtinlichsu(String tenphim, String thoigiandat,String id) {
        this.tenphim = tenphim;
        this.thoigiandat = thoigiandat;
        this.id=id;
    }

    public String getTenphim() {
        return tenphim;
    }

    public String getThoigiandat() {
        return thoigiandat;
    }
    public String getId(){return id;}

    String  tenphim , thoigiandat , id;
}
