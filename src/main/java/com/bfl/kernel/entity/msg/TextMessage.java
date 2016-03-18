package com.bfl.kernel.entity.msg;

/**
 * Created by shidd on 2016/3/18.
 */
public class TextMessage extends BaseMessage{
    // 回复的消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

}
