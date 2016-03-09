package com.bfl.kernel.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by apple on 16/3/9.
 */
@XmlRootElement(name = "xml")
public class XMLContent {
    String ToUserName;
    String FromUserName;
    int CreateTime;
    String MsgType;
    String Content;

    public String getToUserName() {
        return ToUserName;
    }
    @XmlElement
    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }
    @XmlElement
    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public int getCreateTime() {
        return CreateTime;
    }
    @XmlElement
    public void setCreateTime(int createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }
    @XmlElement
    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getContent() {
        return Content;
    }
    @XmlElement
    public void setContent(String content) {
        Content = content;
    }

    public XMLContent(){

    }


}
