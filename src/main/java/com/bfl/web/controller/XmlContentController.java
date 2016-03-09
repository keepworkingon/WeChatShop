package com.bfl.web.controller;

import com.bfl.kernel.entity.XMLContent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by apple on 16/3/9.
 * 本类用于接受url请求,测试了返回xml的写法,传入参数只有name,并将它作为返回xml对应的toUserName字段
 */

@Controller
@RequestMapping("/xml")
public class XmlContentController {
    @RequestMapping(value="/xmlTest/{name}",method = RequestMethod.GET)
    public @ResponseBody
    XMLContent getResXmlContent(@PathVariable String name){
        XMLContent xmlContent = new XMLContent();
        xmlContent.setToUserName(name);
        xmlContent.setCreateTime(20160309);
        xmlContent.setFromUserName("testFromName");
        xmlContent.setMsgType("text");
        return xmlContent;
    }
}
