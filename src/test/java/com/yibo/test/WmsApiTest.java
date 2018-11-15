package com.yibo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yibo.http.ApiClientUtil;
import com.yibo.http.HttpHost;
import com.yibo.http.domain.common.Request;
import com.yibo.http.domain.common.Result;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WmsApiTest {
    private String token;

    @Test
    public void test01() throws IOException {
        JSONObject reqData = new JSONObject();
        reqData.put("operName", "zouyaadmin");
        reqData.put("operPassword", "111111");
        //Request<JSONObject> request = new Request<>(reqData);
        Map<String, JSONObject> request = new HashMap<>();
        request.put("reqData", reqData);

        System.out.println(JSON.toJSON(request));

        System.out.println(HttpHost.WMS_TEST_HOST + "/wms/auth/user/noauth/login");


        Result<JSONObject> result = ApiClientUtil.post(
                HttpHost.WMS_TEST_HOST + "/wms/auth/user/noauth/login",
                request);
        JSONObject repData = result.getRepData();
        if (repData != null) {
            token = repData.getString("token");
            System.out.println(token);
        }
        System.out.println(result);
    }

    @Test
    public void test02() throws IOException {
        if (token == null) {
            token = "zouyaadmin_11_1_1_2820ceb52e984660bd9897d02bd390f6";
        }
        JSONObject reqData = new JSONObject();
        reqData.put("whseId", "1044");
        reqData.put("pageNum", "1");
        reqData.put("pageRows", "100");
        Request<JSONObject> request = new Request<>(token, reqData);
        System.out.println();
        System.out.println(JSON.toJSON(request));
        System.out.println(HttpHost.WMS_TEST_HOST + "/wms/biz/warehouseswitch/query");
        Result<JSONArray> result = ApiClientUtil.postForList(
                HttpHost.WMS_TEST_HOST + "/wms/biz/warehouseswitch/query",
                request);
        System.out.println(result);
    }
}
