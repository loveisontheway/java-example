package com.muxi.java.example.http;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Third-party Interface
 * 访问第三方接口
 *
 * @author jl.jiang 2022/3/21
 */
public class Http {
    /**
     * HTTPClient -> Third-party Interface
     * 通过HTTPClient访问第三方接口
     */
    public String http() throws IOException {
        // 1、创建httpClient
        CloseableHttpClient client = HttpClients.createDefault();
        // 2、封装请求参数
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
        // 这里默认都是String,String,但是之后会对相应的数据类型进行转换
        list.add(new BasicNameValuePair("username", "name"));
        // 3、转化参数
        String params = EntityUtils.toString(new UrlEncodedFormEntity(list, Consts.UTF_8));
        // 4、创建HttpGet请求
        HttpGet httpGet = new HttpGet("url接口地址" + "?" + params);
        CloseableHttpResponse response = client.execute(httpGet);
        // 5、获取实体
        HttpEntity entity = response.getEntity();
        // 将实体装成字符串
        String result = EntityUtils.toString(entity);
        response.close();
        return result;
    }

    /**
     * RestTemplate -> Third-party Interface
     * 通过 Spring Boot RestTemplate 访问第三方接口
     */
    public String rest() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HashMap<String, Object> map = new HashMap<>();
        map.put("typeid", 1);
        // params: url, return data type, params list
        String result = restTemplate.getForObject("url", String.class, map);
        return result;
    }
}
