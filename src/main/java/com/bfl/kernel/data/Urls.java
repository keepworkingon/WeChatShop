package com.bfl.kernel.data;

/**
 * Created by shidd on 2016/3/21.
 */
public class Urls {
    //获取token
    public static final String getToken = "https://api.weixin.qq.com/cgi-bin/token";
    //根据订单ID获取订单详情
    public static final String getOrderDetailsById = "https://api.weixin.qq.com/merchant/order/getbyid";
    //调用客服接口发消息
    public static final String sendCustomeMsg = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
}
