package com.yibo.http;

import com.alibaba.fastjson.JSON;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Map;

public class ApiClient {
    public static CloseableHttpResponse get(String url) throws IOException {
        return HttpClients.createDefault().execute(new HttpGet(url));
    }

    public static CloseableHttpResponse get(String url, Map<String, String> headers) throws IOException {
        HttpGet get = new HttpGet(url);
        if (!CollectionUtils.isEmpty(headers)) {
            headers.forEach(get::addHeader);
        }
        return HttpClients.createDefault().execute(get);
    }

    public static <T> CloseableHttpResponse post(String url, T content) throws IOException {
        HttpPost post = new HttpPost(url);
        post.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        post.setEntity(new StringEntity(JSON.toJSONString(content), ContentType.APPLICATION_JSON));
        return HttpClients.createDefault().execute(post);
    }

    public static <T> CloseableHttpResponse post(String url, T content, Map<String, String> headers) throws IOException {
        HttpPost post = new HttpPost(url);
        if (!CollectionUtils.isEmpty(headers)) {
            headers.forEach(post::addHeader);
        }
        post.setEntity(new StringEntity(JSON.toJSONString(content)));
        return HttpClients.createDefault().execute(new HttpGet(url));
    }
}