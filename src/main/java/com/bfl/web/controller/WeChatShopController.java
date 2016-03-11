package com.bfl.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by shidd on 2016/3/11.
 * http://114.215.146.85:8080/WeChatShop/Public/Api这个接口提供给微信使用
 */

@Controller
@RequestMapping("/Public")
public class WeChatShopController {

    private static final String token = "WyCkSdd";

    //接入微信平台，详情见http://mp.weixin.qq.com/wiki/8/f9a0b8382e0b77d87b3bcc1ce6fbc104.html
    @RequestMapping(value="/Api",method = RequestMethod.GET)
    public @ResponseBody
    String getResXmlContent(HttpServletRequest request){

        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(token);
        arrayList.add(timestamp);
        arrayList.add(nonce);

        Collections.sort(arrayList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        String str = "";
        for (int i = 0; i < arrayList.size(); i++){
            str = str + arrayList.get(i);
        }

        //未完待续


        return echostr;
    }
}
