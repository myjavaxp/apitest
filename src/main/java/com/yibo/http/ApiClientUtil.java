package com.yibo.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yibo.http.domain.common.Request;
import com.yibo.http.domain.common.Result;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public class ApiClientUtil {
    public static <V> Result<V> get(String url, Class<V> resultType) throws IOException {
        CloseableHttpResponse response = ApiClient.get(url);
        return handleResult(resultType, response);
    }


    public static <V, T> Result<V> post(String url, Class<V> resultType, T requestData) throws IOException {
        Request<T> request = new Request<>();
        request.setReqData(requestData);
        CloseableHttpResponse response = ApiClient.post(url, request);
        return handleResult(resultType, response);
    }

    public static <T> Result<JSONObject> post(String url, T requestData) throws IOException {
        return post(url, JSONObject.class, requestData);
    }

    public static <T> Result<JSONArray> postForList(String url, T requestData) throws IOException {
        return post(url, JSONArray.class, requestData);
    }

    private static <V> Result<V> handleResult(Class<V> clazz, CloseableHttpResponse response) throws
            IOException {
        int code = response.getStatusLine().getStatusCode();
        if (code != HttpStatus.OK.value()) {
            return Result.ofFailedStatus(code, "请求失败");
        }
        String json = EntityUtils.toString(response.getEntity());
        JSONObject object = JSON.parseObject(json);
        Result<V> result = new Result<>();
        result.setRepCode(object.getIntValue("repCode"));
        result.setRepMsg(object.getString("repMsg"));
        V data = JSON.parseObject(object.getString("repData"), clazz);
        result.setRepData(data);
        return result;
    }
}
