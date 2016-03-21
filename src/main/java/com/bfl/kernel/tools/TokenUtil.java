package com.bfl.kernel.tools;

import com.bfl.kernel.entity.Urls;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by shidd on 2016/3/21.
 */
public class TokenUtil {
    private static final String appid = "wxa80eebcf2e021cdb";

    private static final String secret = "78c9ea33c4a34824e3cfbda97d31b620";

    private String accessToken;

    private static final TokenUtil tokenUtil = new TokenUtil();

    //单实例类
    private TokenUtil() {}

    //获取TokenUtil实例
    public static TokenUtil getInstance() {
        return tokenUtil;
    }

    //给url添加token
    public String addToken(String url){
        if (StringUtils.isEmpty(accessToken)){
            startTimerTask();
            //先取一次token，以满足本次的请求
            getTokenFromHttp();
        }
        return url + "?access_token=" + accessToken;
    }

    //启动定时任务
    private void startTimerTask(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("timerTask.xml");
    }

    //定时任务
    @Scheduled(cron="* * 0/2 * * *")
    private void timerTask(){
        getTokenFromHttp();
    }

    //访问url获得token
    private void getTokenFromHttp(){
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(Urls.getToken + "?grant_type=" + "client_credential" + "&appid=" + appid + "&secret=" + secret);
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            String json = EntityUtils.toString(entity);
            accessToken = getTokenFromJson(json);
            httpclient.close();
        }catch (Exception e){
        }
    }

    //从返回的json中解析出token
    private String getTokenFromJson(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        return jsonObject.getString("access_token");
    }
}
