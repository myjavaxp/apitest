package com.yibo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yibo.http.ApiClientUtil;
import com.yibo.http.constant.HttpHost;
import com.yibo.http.domain.common.Request;
import com.yibo.http.domain.common.Result;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.yibo.http.constant.CommonConstants.FEATURES;

public class WmsApiTest {
    private String token;

    @Before
    public void login() throws IOException {
        JSONObject reqData = new JSONObject();
        reqData.put("operName", "zouyaadmin");
        reqData.put("operPassword", "111111");
        Request<JSONObject> request = new Request<>(reqData);
        request.setReqData(reqData);

        Result<JSONObject> result = ApiClientUtil.post(
                HttpHost.WMS_TEST_HOST + "/wms/auth/user/noauth/login",
                request);
        JSONObject repData = result.getRepData();
        if (repData != null) {
            token = repData.getString("token");
        }
    }

    @Test
    public void test02() throws IOException {
        JSONObject reqData = new JSONObject();
        reqData.put("whseId", "1044");
        reqData.put("pageNum", "1");
        reqData.put("pageRows", "1000");
        Request<JSONObject> request = new Request<>(token, reqData);
        System.out.println();
        Result<JSONObject> result = ApiClientUtil.post(
                HttpHost.WMS_TEST_HOST + "/wms/biz/warehouseswitch/query",
                request);
        System.out.println(JSON.toJSONString(result, FEATURES));
    }
}
