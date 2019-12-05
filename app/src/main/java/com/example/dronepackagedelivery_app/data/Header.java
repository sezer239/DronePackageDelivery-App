package com.example.dronepackagedelivery_app.data;


import com.example.dronepackagedelivery_app.common.Data;

public class Header extends Data {

    private int type;

    private String header;

    private int pos;

    public Header(int type, String header, int pos) {
        this.type = type;
        this.header = header;
        this.pos = pos;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
