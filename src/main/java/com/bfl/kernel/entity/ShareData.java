package com.bfl.kernel.entity;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by shidd on 2016/3/15.
 */

@Component
public class ShareData {
    private static Logger logger = LoggerFactory.getLogger(ShareData.class);

    private String accessToken;

    public String getAccessToken(){
        if (StringUtils.isEmpty(accessToken)){
            getTokenByHttp();
            ApplicationContext ctx = new ClassPathXmlApplicationContext("timerTask.xml");
        }
        return accessToken;
    }

    @Scheduled(cron="0 */2 * * *")
    private void getTokenTask(){
        getTokenByHttp();
    }

    private void getTokenByHttp(){
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet("https://api.weixin.qq.com/cgi-bin/token?" +
                    "grant_type=client_credential&appid=wxa80eebcf2e021cdb&secret=78c9ea33c4a34824e3cfbda97d31b620");
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            String json = EntityUtils.toString(entity);
            accessToken = parseTokenByJson(json);
            httpclient.close();
        }catch (Exception e){
        }
    }

    private String parseTokenByJson(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        return jsonObject.getString("access_token");
    }

}
