package com.web.vo;

public class HostHolder {
    private String nowId; //固定值，用于查询
    private int id;       //当前用户ID

    public String getNowId() {
        return nowId;
    }

    public int getId() {
        return id;
    }

    public void setNowId(String nowId) {
        this.nowId = nowId;
    }

    public void setId(int id) {
        this.id = id;
    }

}
