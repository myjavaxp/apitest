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
    /**
     * get请求
     *
     * @param url        请求路径
     * @param resultType 返回值类型，即{@link Result} 里的repData成员变量的类型
     * @param <V>        同resultType
     * @return 标准返回体Result内含类型为V的repData
     * @throws IOException IO异常
     */
    public static <V> Result<V> get(String url, Class<V> resultType) throws IOException {
        CloseableHttpResponse response = ApiClient.get(url);
        return handleResult(resultType, response);
    }

    /**
     * get请求 不知道repData类型或者没有给repData单独建类的时候使用
     *
     * @param url 请求路径
     * @return 标准返回体Result内含类型为JSONObject的repData
     * @throws IOException IO异常
     */
    public static Result<JSONObject> get(String url) throws IOException {
        CloseableHttpResponse response = ApiClient.get(url);
        return handleResult(JSONObject.class, response);
    }

    /**
     * post请求
     *
     * @param url         请求路径
     * @param resultType  返回值类型，即{@link Result} 里的repData成员变量的类型
     * @param requestData 请求体内容，其实是{@link Request}内的成员变量reqData
     * @param <V>         返回值类型，其实是{@link Result}里的repData的类型
     * @param <T>         请求体类型，其实是{@link Request}内的成员变量reqData
     * @return 标准返回体Result内含类型为V的repData
     * @throws IOException IO异常
     */
    public static <V, T> Result<V> post(String url, Class<V> resultType, T requestData) throws IOException {
        Request<T> request = new Request<>();
        request.setReqData(requestData);
        CloseableHttpResponse response = ApiClient.post(url, request);
        return handleResult(resultType, response);
    }

    /**
     * post请求 不知道repData,reqData类型或者没有给repData,reqData单独建类的时候使用
     *
     * @param url     请求路径
     * @param request 包含类型JSONObject的Request的请求体
     * @return 标准返回体Result内含类型为JSONObject的repData
     * @throws IOException IO异常
     */
    public static Result<JSONObject> post(String url, Request<JSONObject> request) throws IOException {
        CloseableHttpResponse response = ApiClient.post(url, request);
        return handleResult(JSONObject.class, response);
    }

    /**
     * post请求 不知道repData,reqData类型或者没有给repData,reqData单独建类的时候使用
     *
     * @param url     请求路径
     * @param request 包含类型JSONObject的Request的请求体
     * @return 标准返回体Result内含类型为JSONArray的repData
     * @throws IOException IO异常
     */
    public static Result<JSONArray> postForList(String url, Request<JSONObject> request) throws IOException {
        CloseableHttpResponse response = ApiClient.post(url, request);
        return handleResult(JSONArray.class, response);
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
