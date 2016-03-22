package com.bfl.web.controller;

import com.bfl.kernel.data.Jsons;
import com.bfl.kernel.data.Urls;
import com.bfl.kernel.entity.msg.TextMessage;
import com.bfl.kernel.tools.MessageUtil;
import com.bfl.kernel.tools.TokenUtil;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
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
    String recieveGetMsgFromWeChatServer(HttpServletRequest request){
        logger.info("调用/Public/Api的get接口");

        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        if (StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(nonce)){
            logger.warn("timestamp或者nonce为空，直接返回");
            return null;
        }

        //按字典序排序
        String[] strs = {timestamp,nonce,token};
        Arrays.sort(strs);

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
        MessageDigest msgDigest;
        String output = null;
        try {
            msgDigest = MessageDigest.getInstance("SHA-1");
            byte[] b = msgDigest.digest(str.getBytes("UTF-8"));
            output = Hex.encodeHexString(b);
        } catch (Exception e) {
        }
        return output;
    }

    //接收微信服务器推送过来的消息，详情见http://mp.weixin.qq.com/wiki/7/9f89d962eba4c5924ed95b513ba69d9b.html
    @RequestMapping(value="/Api",method = RequestMethod.POST)
    public @ResponseBody
    void recievePostMsgFromWeChatServer(HttpServletRequest request, HttpServletResponse response) {
        logger.info("调用/Public/Api的post接口");
        // xml格式的消息数据
        String respXml = null;
        // 默认返回的文本消息内容
        String respContent = "未知的消息类型！";
        try{
//            request.setCharacterEncoding("UTF-8");
//            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/text; charset=utf-8");
            Map<String, String> requestMap = MessageUtil.parseXml(request);

            // 发送方帐号
            String fromUserName = requestMap.get("FromUserName");
            // 开发者微信号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");

//            // 回复文本消息
//            TextMessage textMessage = new TextMessage();
//            textMessage.setToUserName(fromUserName);
//            textMessage.setFromUserName(toUserName);
//            textMessage.setCreateTime(new Date().getTime());
//            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                respContent = "您发送的是文本消息！";
            }
            // 图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "您发送的是图片消息！";
            }
            // 语音消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是语音消息！";
            }
            // 视频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
                respContent = "您发送的是视频消息！";
            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
            }
            // 链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "您发送的是链接消息！";
            }
            // 事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 关注
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    respContent = "谢谢您的关注！";
                }
                // 取消关注
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
                }
                // 用户已关注时扫描二维码的事件推送
                else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
                    // TODO 处理扫描带参数二维码事件
                }
                // 上报地理位置
                else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
                    // TODO 处理上报地理位置事件
                }
                // 自定义菜单
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    // TODO 处理菜单点击事件
                }
                // 订单支付成功
                else if (eventType.equals(MessageUtil.EVENT_TYPE_MERCHANT_ORDER)) {
                    //根据订单id获取订单详情信息
                    String url = TokenUtil.getInstance().addToken(Urls.getOrderDetailsById);
                    JSONObject jsonObject = new JSONObject(String.format(Jsons.orderId, requestMap.get("OrderId")));
                    jsonObject = MessageUtil.sendPost(url,jsonObject);
                    logger.info("订单详情：" + jsonObject);

                    // TODO 未完待续：至此已经获得这笔订单的产品信息，根据产品信息到数据库选择不同面值的卡密，然后进行分发
                    respContent = "卡号：123456\\n密码：123456";

                    //调用客服接口给用户发送卡密
                    url = TokenUtil.getInstance().addToken(Urls.sendCustomeMsg);
                    jsonObject = new JSONObject(String.format(Jsons.customeTextMsg, fromUserName, respContent));
                    MessageUtil.sendPost(url,jsonObject);
                }
            }
//            // 设置文本消息的内容
//            textMessage.setContent(respContent);
//            // 将文本消息对象转换成xml
//            respXml = MessageUtil.textMessageToXml(textMessage);
//            logger.info("回复的XML消息：" + respXml);
//            PrintWriter out = response.getWriter();
//            out.print(respXml);aaaaa
//            out.close();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }
}
