package com.bfl.kernel.entity;

/**
 * Created by apple on 16/3/8.
 * 对应数据库中的帐号表,存储帐号密码
 */
public class Account {
    int id;
    String acc_num;
    String acc_psd;
    int mark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAcc_psd() {
        return acc_psd;
    }

    public void setAcc_psd(String acc_psd) {
        this.acc_psd = acc_psd;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getAcc_num() {
        return acc_num;
    }

    public void setAcc_num(String acc_num) {
        this.acc_num = acc_num;
    }



}
