package com.web.vo;

/**
 * 用户信息
 * @author yyq
 */
public class User {
    private String username;    //用户名
    private String password;    //密码
    private int id;             //用户ID
    private String name;        //用户姓名
    private int flag;           //身份判断

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFlag() {
        return flag;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

}
