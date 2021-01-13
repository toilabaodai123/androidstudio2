package com.example.drawerfragment;

public class ghe {
    public ghe(String id, String tenghe) {
        this.id = id;
        this.tenghe = tenghe;
    }

    public String getId() {
        return id;
    }

    public String getTenghe() {
        return tenghe;
    }

    String id , tenghe;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    boolean isSelected;

}
