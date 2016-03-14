package com.bfl.web.controller;

import com.bfl.kernel.service.serviceImpl.AccountServiceImpl;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.util.*;

/**
 * Created by shidd on 2016/3/11.
 * http://114.215.146.85:8080/WeChatShop/Public/Api这个接口提供给微信使用
 */

@Controller
@RequestMapping("/Public")
public class WeChatShopController {
    private static Logger logger = LoggerFactory.getLogger(WeChatShopController.class);

    private static final String token = "WyCkSdd";

    //接入微信平台，详情见http://mp.weixin.qq.com/wiki/8/f9a0b8382e0b77d87b3bcc1ce6fbc104.html
    @RequestMapping(value="/Api",method = RequestMethod.GET)
    public @ResponseBody
    String getResXmlContent(HttpServletRequest request){
        logger.info("调用/Public/Api接口");

        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        if (StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(nonce)){
            logger.warn("timestamp或者nonce为空，直接返回");
            return null;
        }

        logger.info("signature = "+signature+",timestamp = "+timestamp+",nonce = "+nonce+",echostr = "+echostr);

        //按字典序排序
        String[] strs = {timestamp,nonce,token};
        Arrays.sort(strs);

        //另一种排序方法
//        ArrayList<String> arrayList = new ArrayList<String>();
//        arrayList.add(token);
//        arrayList.add(timestamp);
//        arrayList.add(nonce);
//
//        //按字典序排序
//        Collections.sort(arrayList, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.compareToIgnoreCase(o2);
//            }
//        });

        //三个字符串合成一个字符串，再进行加密
        String str = "";
        for (int i = 0; i < strs.length; i++){
            str = str + strs[i];
        }

        //sha1加密
        str = getSha1Val(str);

        //处理返回值
        if (StringUtils.equals(str,signature)){
            logger.info("接入微信成功");
            return echostr;
        }
        else {
            logger.warn("接入微信失败，signature = " + signature + ", str = " + str);
            return null;
        }
    }

    //sha1加密
    public static String getSha1Val(String str) {
        MessageDigest digest;
        String output = null;
        try {
            digest = MessageDigest.getInstance("SHA-1");
            byte[] b = digest.digest(str.getBytes("UTF-8"));
            output = Hex.encodeHexString(b);
        } catch (Exception e) {
        }
        return output;
    }
}
