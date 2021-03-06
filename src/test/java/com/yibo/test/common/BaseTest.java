package com.yibo.test.common;

import com.alibaba.fastjson.JSONObject;
import com.yibo.http.ApiClientUtil;
import com.yibo.http.constant.HttpHost;
import com.yibo.http.domain.common.Request;
import com.yibo.http.domain.common.Result;
import org.junit.Before;

import java.io.IOException;

public class BaseTest {
    protected String token;

    @Before
    public void login() throws IOException {
        JSONObject reqData = new JSONObject();
        reqData.put("operName", "zouyaadmin");
        reqData.put("operPassword", "111111");
        Result<JSONObject> result = ApiClientUtil.post(
                HttpHost.WMS_TEST_HOST + "/wms/auth/user/noauth/login",
                new Request<>(reqData));
        JSONObject repData = result.getRepData();
        if (repData != null) {
            token = repData.getString("token");
        }
    }
}