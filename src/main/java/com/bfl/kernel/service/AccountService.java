package com.bfl.kernel.service;

import com.bfl.kernel.entity.Account;

/**
 * Created by apple on 16/3/8.
 */
public interface AccountService {
    //用于获取帐号及密码
    public Account getAccount();
    //用于将已消费的帐号密码的mark位置为0,这样有利于后期统计消费了多少充值卡,标志位置零是一个非常常用的小技巧
    public int setAccountMarkToZero(int id);
}
